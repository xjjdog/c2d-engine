package info.u250.c2d.tests.mesh;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.EngineDrive;
import info.u250.c2d.engine.Scene;
import info.u250.c2d.engine.resources.AliasResourceManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;


public class LightningTest extends Engine {
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
			
		}
		@Override
		public void dispose() {}
		@Override
		public EngineOptions onSetupEngine() {
			final EngineOptions opt = new EngineOptions(new String[]{
					
			},800,480);
			return opt;
		}

		@Override
		public void onLoadedResourcesCompleted() {
			Engine.setMainScene(new Scene() {
				LightingBoltEffect effect = new LightingBoltEffect(new Vector2(0,200), new Vector2(500,200), 1,50,2);
				@Override
				public void render(float delta) {
					effect.render(delta);
				}
				@Override
				public InputProcessor getInputProcessor() {
					return new InputAdapter(){
						@Override
						public boolean touchUp(int screenX, int screenY,
								int pointer, int button) {
							Vector2 v = Engine.screenToWorld(screenX, screenY);
							effect =  new LightingBoltEffect(new Vector2(v.x,v.y), new Vector2(v.x+500,v.y), 1,50,4);
							return super.touchUp(screenX, screenY, pointer, button);
						}
					};
				}
				@Override
				public void update(float delta) {	
					effect.update(delta);
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
}
class LightingBoltEffect {
	public  final class Line {
		public Line(Vector2 p0, Vector2 p1) {
			this.start.set(p0);
			this.end.set(p1);
		}

		public void render(){
			Engine.getShapeRenderer().begin(ShapeType.Line);
			Engine.getShapeRenderer().line(start, end);
			Engine.getShapeRenderer().end();
		}
		Vector2 start = new Vector2();
		Vector2 end = new Vector2();
	}
	Collection<Line> segments;
	 
	public LightingBoltEffect(Vector2 p0, Vector2 p1, float duration,float height,int partitions){
		generateLightingBolt(p0,p1,duration,height,partitions);
	}
	float currentTime = 0;
	float totalTime = 0;
	public void update(float delta){
		currentTime -= delta;  
	     if (currentTime <= 0)  
	         currentTime = 0;
	    
	}
	protected void generateLightingBolt(Vector2 p0, Vector2 p1, float duration,float height,int partitions) {
		this.currentTime = duration;
		this.totalTime = duration;
		
		Collection<Line> segments = new ArrayList<Line>();

		segments.add(new Line(p0, p1));

		float offset = 200f;
		double probability = 0.3; // probability to generate new partitions

		Random random = new Random();


		for (int i = 0; i < partitions; i++) {

			Collection<Line> newSegments = new ArrayList<Line>();

			for (Line segment : segments) {

				Vector2 midPoint = segment.start.cpy().add(segment.end).scl(0.5f);

				Vector2 perpendicular = midPoint.cpy().add(90, 90);
				perpendicular.nor().scl(random.nextFloat() * offset - (offset / 2));
				midPoint.add(perpendicular);

				if (random.nextFloat() < probability) {
					// generate new branch
					Vector2 direction = midPoint.cpy().sub(segment.start);
					float add = random.nextFloat() * height;
					direction.add(add, add);
					newSegments.add(new Line(midPoint.cpy(), midPoint.cpy().add(direction)));
				}

				newSegments.add(new Line(segment.start.cpy(), midPoint.cpy()));
				newSegments.add(new Line(midPoint.cpy(), segment.end.cpy()));
			}

			segments = newSegments;

			offset /= 2;
			
			this.segments = segments;
		}
	}
	Color c = new Color(1,1,0,1);
	public void render(float delta){
		if(0 == totalTime)return;
		
		Gdx.gl.glEnable(GL10.GL_BLEND);
		Gdx.gl.glLineWidth(4);
		c.a = (float) currentTime / (float) totalTime;
		Engine.getShapeRenderer().setColor(c);
		for(Line line:this.segments){
			line.render();
		}
		Gdx.gl.glLineWidth(1);
	}

}