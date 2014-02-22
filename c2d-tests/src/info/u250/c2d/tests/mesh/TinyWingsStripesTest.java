package info.u250.c2d.tests.mesh;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static com.badlogic.gdx.graphics.GL20.GL_DEPTH_BUFFER_BIT;
import static com.badlogic.gdx.graphics.GL20.GL_DST_COLOR;
import static com.badlogic.gdx.graphics.GL20.GL_ONE_MINUS_SRC_ALPHA;
import static com.badlogic.gdx.graphics.GL20.GL_SRC_ALPHA;
import static com.badlogic.gdx.graphics.GL20.GL_TEXTURE_2D;
import static com.badlogic.gdx.graphics.GL20.GL_ZERO;
import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.EngineDrive;
import info.u250.c2d.engine.Scene;
import info.u250.c2d.engine.resources.AliasResourceManager;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;


public class TinyWingsStripesTest extends Engine {
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
			reg.texture("RES", "data/noise.png");
		}
		@Override
		public void dispose() {}
		@Override
		public EngineOptions onSetupEngine() {
			final EngineOptions opt = new EngineOptions(new String[]{
					"data/noise.png"
			},800,480);
			opt.useGL20 = false;
			return opt;
		}

		@Override
		public void onLoadedResourcesCompleted() {
			Engine.setMainScene(new SceneRender());	
		}
	}
	private static class SceneRender implements Scene{
		
		Mesh stripMesh ;
		Mesh highlightsMesh ;
		Texture texture ;

		public SceneRender(){
			
			color = generateDarkColor();
			Color c = generateLightColorFrom(color);
			texture = Engine.resource("RES");
			texture.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
			
			highlightsMesh = new Mesh(false, 4, 4 , 
					new VertexAttribute(Usage.Position, 3, ShaderProgram.POSITION_ATTRIBUTE),
					new VertexAttribute(Usage.Color, 4,ShaderProgram.COLOR_ATTRIBUTE));
			highlightsMesh.setIndices(new short[]{0,1,2,3});
			highlightsMesh.setVertices(new float[]{
					0,0,0,		0,0,0,0.7f,
					512,0,0,	0,0,0,0.7f,
					512,512,0,	0,0,0,0,
					0,512,0, 	0,0,0,0
			});
			
			int nStripes = 6;
			stripMesh = new Mesh(false, nStripes*6, nStripes*6 , new VertexAttribute(Usage.Position, 3, ShaderProgram.POSITION_ATTRIBUTE),
					new VertexAttribute(Usage.Color, 4,ShaderProgram.COLOR_ATTRIBUTE));
			
			short[] indices = new short[nStripes*6];
			for (int i = 0; i < nStripes*6; i++) {
				indices[i] = (short) i;
		    }
			float[] vertices = new float[nStripes*6*7];
			int nVertices = 0;
			float x1 =-512;
		    float x2;
		    float y1 = 0;
		    float y2 = 512;
		    float dx = 512 / nStripes * 2;
		    float stripeWidth = dx/2;
		    for (int i=0; i< nStripes; i++) {
		    	
		        x2 = x1 + 512;
		        //left bottom
		        vertices[nVertices++] = x1;
		        vertices[nVertices++] = y1;
		        vertices[nVertices++] = 0;
		        vertices[nVertices++] = c.r;
		        vertices[nVertices++] = c.g;
		        vertices[nVertices++] = c.b;
		        vertices[nVertices++] = c.a;
		        
		        //right bottom
		        vertices[nVertices++] = x1+stripeWidth;
		        vertices[nVertices++] = y1;
		        vertices[nVertices++] = 0;
		        vertices[nVertices++] = c.r;
		        vertices[nVertices++] = c.g;
		        vertices[nVertices++] = c.b;
		        vertices[nVertices++] = c.a;
		        
		        //left top
		        vertices[nVertices++] = x2;
		        vertices[nVertices++] = y2;
		        vertices[nVertices++] = 0;
		        vertices[nVertices++] = c.r;
		        vertices[nVertices++] = c.g;
		        vertices[nVertices++] = c.b;
		        vertices[nVertices++] = c.a;
		        
		        vertices[nVertices++] = x1+stripeWidth;
		        vertices[nVertices++] = y1;
		        vertices[nVertices++] = 0;
		        vertices[nVertices++] = c.r;
		        vertices[nVertices++] = c.g;
		        vertices[nVertices++] = c.b;
		        vertices[nVertices++] = c.a;
		        
		        vertices[nVertices++] = x2;
		        vertices[nVertices++] = y2;
		        vertices[nVertices++] = 0;
		        vertices[nVertices++] = c.r;
		        vertices[nVertices++] = c.g;
		        vertices[nVertices++] = c.b;
		        vertices[nVertices++] = c.a;
		        
		        //right top
		        vertices[nVertices++] = x2+stripeWidth;
		        vertices[nVertices++] = y2;
		        vertices[nVertices++] = 0;
		        vertices[nVertices++] = c.r;
		        vertices[nVertices++] = c.g;
		        vertices[nVertices++] = c.b;
		        vertices[nVertices++] = c.a;
		        x1 += dx;
		    }
		    stripMesh.setIndices(indices);
			stripMesh.setVertices(vertices);
		}
		
		Color color ;
		@Override
		public void render(float delta) {
			GL11 gl = Gdx.gl11;
			gl.glEnable(GL11.GL_BLEND);
			gl.glDisable(GL_TEXTURE_2D);
			gl.glDisableClientState(GL11.GL_TEXTURE_COORD_ARRAY);

			
			gl.glClearColor(color.r,color.g,color.b,1);
			gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			
			stripMesh.render(GL11.GL_TRIANGLES);
			highlightsMesh.render(GL11.GL_TRIANGLE_FAN);
			Engine.getSpriteBatch().begin();
			Engine.getSpriteBatch().setBlendFunction(GL_DST_COLOR, GL_ZERO);
			Engine.getSpriteBatch().draw(texture, 0, 0 ,512,512);
			Engine.getSpriteBatch().end();
			Engine.getSpriteBatch().setBlendFunction(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
			
		
		}
		@Override
		public InputProcessor getInputProcessor() {
			return new InputAdapter(){
				@Override
				public boolean keyUp(int keycode) {
					switch(keycode){
					case Keys.UP:
						Engine.getDefaultCamera().zoom= Engine.getDefaultCamera().zoom+0.1f;
						break;
					case Keys.DOWN:
						Engine.getDefaultCamera().zoom = Engine.getDefaultCamera().zoom-0.1f;
						break;
					case Keys.LEFT:
						Engine.getDefaultCamera().position.x-=10;
						break;
					case Keys.RIGHT:
						Engine.getDefaultCamera().position.x+=10;
						break;
					}
					return super.keyUp(keycode);
				}
			};
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
		
		final Random random = new Random();
		Color generateDarkColor() {
		    final int threshold = 250;
		    int r, g, b;
		    while (true) {
		        r = random.nextInt(256);
		        g = random.nextInt(256);
		        b = random.nextInt(256);
		        if (r+g+b > threshold) break;
		    }
		    return new Color(r/255f, g/255f, b/255f,1);
		}
		Color generateLightColorFrom(Color c) {
		    final int addon = 30;
		    float r, g, b;
		    r = c.r*255 + addon;
		    g = c.g*255 + addon;
		    b = c.b*255 + addon;
		    if (r > 255) r = 255;
		        if (g > 255) g = 255;
		            if (b > 255) b = 255;
		                return new Color(r/255f, g/255f, b/255f, 1);
		}
		
	}
}
