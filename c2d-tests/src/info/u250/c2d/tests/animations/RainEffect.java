package info.u250.c2d.tests.animations;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.EngineDrive;
import info.u250.c2d.engine.Scene;
import info.u250.c2d.engine.resources.AliasResourceManager;
import info.u250.c2d.graphic.AdvanceSprite;
import info.u250.c2d.graphic.background.SimpleMeshBackground;

import java.util.Random;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;


public class RainEffect extends Engine {
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
			reg.texture("r1", "data/rain/rain1.png");
			reg.texture("r2", "data/rain/rain2.png");
			reg.texture("r3", "data/rain/rain3.png");
			reg.texture("r4", "data/rain/rain4.png");
		}
		@Override
		public void dispose() {}
		@Override
		public EngineOptions onSetupEngine() {
			final EngineOptions opt = new EngineOptions(new String[]{
					"data/rain/rain1.png",
					"data/rain/rain2.png",
					"data/rain/rain3.png",
					"data/rain/rain4.png",
					},800,480);
			opt.useGL20 = true;
			
			return opt;
		}

		
		@Override
		public void onLoadedResourcesCompleted() {
			int number = (int)(Engine.getWidth()/Rain.WIDTH);
			final Rain[] rains = new Rain[number];
			for(int i=0;i<number;i++){
				rains[i] = new Rain(Engine.resource("r"+(new Random().nextInt(4)+1),Texture.class));
				rains[i].setX(Rain.WIDTH*i);
			}
			final SimpleMeshBackground bg = new SimpleMeshBackground(Color.GRAY,Color.BLACK);
			Engine.setMainScene(new Scene() {
				@Override
				public void render(float delta) {
					bg.render(delta);
					Engine.getSpriteBatch().begin();
					for(Rain rain:rains){
						rain.render(delta);
					}
					Engine.getSpriteBatch().end();
				}
				@Override
				public InputProcessor getInputProcessor() {
					return null;
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
	
	private class Rain extends AdvanceSprite{
		public static final float WIDTH = 15;
		public final Random random = new Random();
		float speed = 0;
		public void setup(){
			speed = random.nextFloat()*1000+500;
			this.setY(Engine.getHeight());
		}
		public Rain(Texture texture) {
			super(texture);
			this.setup();
			this.setY(this.getY()+random.nextFloat()*200);
		}
		@Override
		public void render(float delta) {
			super.render(delta);
			if(this.getY()<-this.getHeight()){
				this.setup();
			}
			this.setY(this.getY()-speed*delta);
		}
		
	}
}
