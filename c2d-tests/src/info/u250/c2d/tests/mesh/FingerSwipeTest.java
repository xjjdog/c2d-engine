package info.u250.c2d.tests.mesh;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.EngineDrive;
import info.u250.c2d.engine.Scene;
import info.u250.c2d.engine.resources.AliasResourceManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;


public class FingerSwipeTest extends Engine {
	@Override
	protected EngineDrive onSetupEngineDrive() {
		return new EngineX();
	}
	@Override
	public void dispose () {
		super.dispose();
	}
	
	private class EngineX implements EngineDrive{
		@Override
		public void onResourcesRegister(AliasResourceManager<String> reg) {
			reg.texture("RES", "data/gradient.png");
		}
		@Override
		public void dispose() {}
		@Override
		public EngineOptions onSetupEngine() {
			final EngineOptions opt = new EngineOptions(new String[]{"data/gradient.png"},800,480);
			opt.useGL20 = true;
			return opt;
		}

		@Override
		public void onLoadedResourcesCompleted() {
			final SwipeHandler swipe = new SwipeHandler(10);;
			final Texture tex = Engine.resource("RES");
			final ShapeRenderer shapes = Engine.getShapeRenderer();
			final SwipeTriStrip tris = new SwipeTriStrip();
			//minimum distance between two points
			swipe.minDistance = 10;
			//minimum distance between first and second point
			swipe.initialDistance = 10;
			//we will use a texture for the smooth edge, and also for stroke effects
			tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
			
			Engine.setMainScene(new Scene() {
				@Override
				public void render(float delta) {
					Gdx.gl.glEnable(GL20.GL_BLEND);
					Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
					tex.bind();
					
					tris.endcap = 5f;
					//the thickness of the line
					tris.thickness = 30f;
					//generate the triangle strip from our path
					tris.update(swipe.path());
					//the vertex color for tinting, i.e. for opacity
					tris.color = Color.WHITE;
					//render the triangles to the screen
					tris.draw(Engine.getDefaultCamera());
					
					drawDebug();
				}
				
				//optional debug drawing..
				public void drawDebug() {
					Array<Vector2> input = swipe.input();
					
					//draw the raw input
					shapes.begin(ShapeType.Line);
					shapes.setColor(Color.GRAY);		
					for (int i=0; i<input.size-1; i++) {
						Vector2 p = input.get(i);
						Vector2 p2 = input.get(i+1);
						shapes.line(p.x, p.y, p2.x, p2.y);
					}
					shapes.end();
					
					//draw the smoothed and simplified path
					shapes.begin(ShapeType.Line);
					shapes.setColor(Color.RED);
					Array<Vector2> out = swipe.path(); 
					for (int i=0; i<out.size-1; i++) {
						Vector2 p = out.get(i);
						Vector2 p2 = out.get(i+1);
						shapes.line(p.x, p.y, p2.x, p2.y);
					}
					shapes.end();
					
					
					//render our perpendiculars
					shapes.begin(ShapeType.Line);
					Vector2 perp = new Vector2();
					
					for (int i=1; i<input.size-1; i++) {
						Vector2 p = input.get(i);
						Vector2 p2 = input.get(i+1);
						
						shapes.setColor(Color.LIGHT_GRAY);
						perp.set(p).sub(p2).nor();
						perp.set(perp.y, -perp.x);
						perp.scl(10f);
						shapes.line(p.x, p.y, p.x+perp.x, p.y+perp.y);
						perp.scl(-1f);
						shapes.setColor(Color.BLUE);
						shapes.line(p.x, p.y, p.x+perp.x, p.y+perp.y);	
					}
					shapes.end();
				}
				@Override
				public InputProcessor getInputProcessor() {
					return swipe;
				}
				@Override
				public void update(float delta) {	
				}
				@Override
				public void hide() {	
				}
				@Override
				public void show() {
				}
			});	
		}
	}
	
	public static class FixedList<T> extends Array<T> {

		/**
		 * Safely creates a list that is backed by an array which
		 * can be directly accessed. 
		 * 
		 * @param capacity the fixed-size capacity of this list
		 * @param type the class type of the elements in this list
		 */
		public FixedList(int capacity, Class<T> type) {
			super(false, capacity, type);
		}

		/**
		 * Inserts the item into index zero, shifting all items to the right,
		 * but without increasing the list's size past its array capacity.
		 * @param t the element to insert
		 */
		public void insert(T t) {
			T[] items = this.items;

			// increase size if we have a new point
			size = Math.min(size + 1, items.length);
			
			// shift elements right
			for (int i = size - 1; i > 0; i--) {
				items[i] = items[i - 1];
			}

			// insert new item at first index
			items[0] = t;
		}
		
		
	}
	
	public static class ResolverRadialChaikin{
		
		private Array<Vector2> tmp = new Array<Vector2>(Vector2.class);
		
		public static int iterations = 2;
		public static float simplifyTolerance = 35f;
		
		public void resolve(Array<Vector2> input, Array<Vector2> output) {
			output.clear();
			if (input.size<=2) { //simple copy
				output.addAll(input);
				return;
			}

			//simplify with squared tolerance
			if (simplifyTolerance>0 && input.size>3) {
				simplify(input, simplifyTolerance * simplifyTolerance, tmp);
				input = tmp;
			}
			
			//perform smooth operations
			if (iterations<=0) { //no smooth, just copy input to output
				output.addAll(input);
			} else if (iterations==1) { //1 iteration, smooth to output
				smooth(input, output);
			} else { //multiple iterations.. ping-pong between arrays
				int iters = iterations;
				//subsequent iterations
				do {
					smooth(input, output);
					tmp.clear();
					tmp.addAll(output);
					Array<Vector2> old = output;
					input = tmp;
					output = old;
				} while (--iters > 0);
			}
		}
		
		public static void smooth(Array<Vector2> input, Array<Vector2> output) {
			//expected size
			output.clear();
			output.ensureCapacity(input.size*2);
			
			//first element
			output.add(input.get(0));
			//average elements
			for (int i=0; i<input.size-1; i++) {
				Vector2 p0 = input.get(i);
				Vector2 p1 = input.get(i+1);
		
				Vector2 Q = new Vector2(0.75f * p0.x + 0.25f * p1.x, 0.75f * p0.y + 0.25f * p1.y);
				Vector2 R = new Vector2(0.25f * p0.x + 0.75f * p1.x, 0.25f * p0.y + 0.75f * p1.y);
		        output.add(Q);
		        output.add(R);
			}
			
			//last element
			output.add(input.get(input.size-1));
		}
		
		//simple distance-based simplification
		//adapted from simplify.js
		public static void simplify(Array<Vector2> points, float sqTolerance, Array<Vector2> out) {
			int len = points.size;

			Vector2 point = new Vector2();
			Vector2 prevPoint = points.get(0);
			
			out.clear();
			out.add(prevPoint);
			
			for (int i = 1; i < len; i++) {
				point = points.get(i);
				if (distSq(point, prevPoint) > sqTolerance) {
					out.add(point);
					prevPoint = point;
				}
			}
			if (!prevPoint.equals(point)) {
				out.add(point);
			}
		}
		
		public static float distSq(Vector2 p1, Vector2 p2) {
			float dx = p1.x - p2.x, dy = p1.y - p2.y;
			return dx * dx + dy * dy;
		}

	}
	
	public static  class SwipeHandler extends InputAdapter {
		
		private FixedList<Vector2> inputPoints;
		
		/** The pointer associated with this swipe event. */
		private int inputPointer = 0;
		
		/** The minimum distance between the first and second point in a drawn line. */
		public int initialDistance = 10;
		
		/** The minimum distance between two points in a drawn line (starting at the second point). */
		public int minDistance = 20;
		
		private Vector2 lastPoint = new Vector2();
		
		private boolean isDrawing = false;
		
		private ResolverRadialChaikin simplifier = new ResolverRadialChaikin();
		private Array<Vector2> simplified;
		
		public SwipeHandler(int maxInputPoints) {
			this.inputPoints = new FixedList<Vector2>(maxInputPoints, Vector2.class);
			simplified = new Array<Vector2>(true, maxInputPoints, Vector2.class);
			resolve(); //copy initial empty list
		}

		/**
		 * Returns the fixed list of input points (not simplified).
		 * @return the list of input points
		 */
		public Array<Vector2> input() {
			return this.inputPoints;
		}
		
		/**
		 * Returns the simplified list of points representing this swipe.
		 * @return
		 */
		public Array<Vector2> path() {
			return simplified;
		}
		
		/**
		 * If the points are dirty, the line is simplified.
		 */
		public void resolve() {
			simplifier.resolve(inputPoints, simplified);
		}
		
		public boolean touchDown(int screenX, int screenY, int pointer, int button) {
			if (pointer!=inputPointer)
				return false;
			isDrawing = true;
			
			//clear points
			inputPoints.clear();
			
			//starting point
			lastPoint = new Vector2(screenX, Gdx.graphics.getHeight()-screenY);
			inputPoints.insert(lastPoint);
			
			resolve();
			return true;
		}
		
		public boolean touchUp(int screenX, int screenY, int pointer, int button) {
			//on release, the line is simplified
			resolve();
			isDrawing = false;
			return false;
		}
		
		public boolean touchDragged(int screenX, int screenY, int pointer) {
			if (pointer!=inputPointer)
				return false;
			isDrawing = true;
			
			Vector2 v = new Vector2(screenX, Gdx.graphics.getHeight()-screenY);
			
			//calc length
			float dx = v.x - lastPoint.x;
			float dy = v.y - lastPoint.y;
			float len = (float)Math.sqrt(dx*dx + dy*dy);
			//TODO: use minDistanceSq
			
			//if we are under required distance
			if (len < minDistance && (inputPoints.size>1 || len<initialDistance)) 
				return false;
			
			//add new point
			inputPoints.insert(v);
			
			lastPoint = v;
			
			//simplify our new line
			resolve();
			return true;
		}

		public boolean isDrawing() {
			return isDrawing;
		}

		public void setDrawing(boolean isDrawing) {
			this.isDrawing = isDrawing;
		}
		
		
	}
	
	
	public static class SwipeTriStrip {

		Array<Vector2> texcoord = new Array<Vector2>();
		Array<Vector2> tristrip = new Array<Vector2>();
		int batchSize;
		Vector2 perp = new Vector2();
		public float thickness = 30f;
		public float endcap = 8.5f;
		public Color color = new Color(Color.WHITE);
		ImmediateModeRenderer20 gl20;
		
		public SwipeTriStrip() {
			gl20 = new ImmediateModeRenderer20(false, true, 1);
		}
		
		public void draw(Camera cam) {
			if (tristrip.size<=0)
				return;

			gl20.begin(cam.combined, GL20.GL_TRIANGLE_STRIP);
			for (int i=0; i<tristrip.size; i++) {
				if (i==batchSize) {
					gl20.end();
					gl20.begin(cam.combined, GL20.GL_TRIANGLE_STRIP);
				}	
				Vector2 point = tristrip.get(i);
				Vector2 tc = texcoord.get(i);
				gl20.color(color.r, color.g, color.b, color.a);
				gl20.texCoord(tc.x, 0f);
				gl20.vertex(point.x, point.y, 0f);
			}
			gl20.end();
		}
		
		private int generate(Array<Vector2> input, int mult) {
			int c = tristrip.size;
			if (endcap<=0) {
				tristrip.add(input.get(0));
			} else {
				Vector2 p = input.get(0);
				Vector2 p2 = input.get(1);
				perp.set(p).sub(p2).scl(endcap);
				tristrip.add(new Vector2(p.x+perp.x, p.y+perp.y));
			}
			texcoord.add(new Vector2(0f, 0f));
			
			for (int i=1; i<input.size-1; i++) {
				Vector2 p = input.get(i);
				Vector2 p2 = input.get(i+1);
				
				//get direction and normalize it
				perp.set(p).sub(p2).nor();
				
				//get perpendicular
				perp.set(-perp.y, perp.x);
				
				float thick = thickness * (1f-((i)/(float)(input.size)));
				
				//move outward by thickness
				perp.scl(thick/2f);
				
				//decide on which side we are using
				perp.scl(mult);
				
				//add the tip of perpendicular
				tristrip.add(new Vector2(p.x+perp.x, p.y+perp.y));
				//0.0 -> end, transparent
				texcoord.add(new Vector2(0f, 0f));
				
				//add the center point
				tristrip.add(new Vector2(p.x, p.y));
				//1.0 -> center, opaque
				texcoord.add(new Vector2(1f, 0f));
			}
			
			//final point
			if (endcap<=0) {
				tristrip.add(input.get(input.size-1));
			} else {
				Vector2 p = input.get(input.size-2);
				Vector2 p2 = input.get(input.size-1);
				perp.set(p2).sub(p).scl(endcap);
				tristrip.add(new Vector2(p2.x+perp.x, p2.y+perp.y));
			}
			//end cap is transparent
			texcoord.add(new Vector2(0f, 0f));
			return tristrip.size-c;
		}
		
		public void update(Array<Vector2> input) {
			tristrip.clear();
			texcoord.clear();
			
			if (input.size<2)
				return;
			batchSize = generate(input, 1);
			generate(input, -1);
		}
		
	}
}


