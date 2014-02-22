package info.u250.c2d.tests.parallax;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.EngineDrive;
import info.u250.c2d.engine.SceneStage;
import info.u250.c2d.engine.load.startup.StartupLoading;
import info.u250.c2d.engine.load.startup.WindmillLoading;
import info.u250.c2d.engine.resources.AliasResourceManager;
import info.u250.c2d.graphic.AdvanceSprite;
import info.u250.c2d.graphic.parallax.DefaultParallaxGroupGestureListener;
import info.u250.c2d.graphic.parallax.ParallaxGroup;
import info.u250.c2d.graphic.parallax.ParallaxLayer;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;


public class ParallaxGroupGestureDetectorTest  extends Engine{
	@Override
	protected EngineDrive onSetupEngineDrive() {
		return new EngineX();
	}
	
	@Override
	protected StartupLoading getStartupLoading() {
		return new WindmillLoading();
	}
	
	private class EngineX implements EngineDrive{
		@Override
		public void onResourcesRegister(AliasResourceManager<String> reg) {
			reg.textureAtlas("bgAtlas", "data/parallax/bg.atlas");
		}
		@Override
		public void dispose() {}
		ParallaxGroup rbg ;
		@Override
		public EngineOptions onSetupEngine() {
			final EngineOptions opt =  new EngineOptions(new String[]{"data/parallax/bg.atlas"},480,320);
			return opt;
		}
		
		@Override
		public void onLoadedResourcesCompleted() {
			final TextureAtlas bgAtlas = Engine.resource("bgAtlas",TextureAtlas.class);
			rbg = new ParallaxGroup(480, 320, new Vector2(50,100));
			rbg.addActor(new Image(new AdvanceSprite(bgAtlas.findRegion("bg") )));
			rbg.addActor(new ParallaxLayer(rbg,new Image(new AdvanceSprite(bgAtlas.findRegion("cloud") )), new Vector2(0.5f,0),new Vector2(0,1000), new Vector2(0,70)));
			rbg.addActor(new ParallaxLayer(rbg,new Image(new AdvanceSprite(bgAtlas.findRegion("front") )), new Vector2(1f,0),new Vector2(0,1000), new Vector2()));

			rbg.setScale(Engine.getWidth()/480f);
			
			rbg.setSpeed(0, 0);
			
			final SceneStage stage = new SceneStage(){
				@Override
				public InputProcessor getInputProcessor() {
					DefaultParallaxGroupGestureListener gestureListener=new DefaultParallaxGroupGestureListener(rbg);
					rbg.setDefaultGestureDetector(gestureListener);
					return rbg.getGestureDetector();
				}
				@Override
				public void render(float delta) {
					super.render(delta);
					Engine.debugInfo("The parallax background with layer number:"+rbg.getChildren().size+"\n" +
							"GestureDetector by make a gesture use your mouse or your finger\n");
				}
			};
			stage.addActor(rbg);
			
			Engine.setMainScene(stage);
		}
	}
}
