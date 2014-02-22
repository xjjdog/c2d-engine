package info.u250.c2d.tests.parallax;


import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.EngineDrive;
import info.u250.c2d.engine.SceneStage;
import info.u250.c2d.engine.load.startup.LineLoading;
import info.u250.c2d.engine.load.startup.StartupLoading;
import info.u250.c2d.engine.resources.AliasResourceManager;
import info.u250.c2d.graphic.AdvanceSprite;
import info.u250.c2d.graphic.parallax.Day2NightAction;
import info.u250.c2d.graphic.parallax.ParallaxGroup;
import info.u250.c2d.graphic.parallax.ParallaxGroupSpeedToAction;
import info.u250.c2d.graphic.parallax.ParallaxLayer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;


public class ParallaxGroupEventsTest extends Engine{
	@Override
	protected EngineDrive onSetupEngineDrive() {
		return new EngineX();
	}
	
	@Override
	protected StartupLoading getStartupLoading() {
		return new LineLoading();
	}
	private class EngineX implements EngineDrive{
		@Override
		public void onResourcesRegister(AliasResourceManager<String> reg) {
			reg.textureAtlas("bgAtlas", "data/parallax/bg.atlas");
		}
		@Override
		public void dispose() {}
		@Override
		public EngineOptions onSetupEngine() {
			final EngineOptions opt =  new EngineOptions(new String[]{"data/parallax/bg.atlas"},800,480);
			return opt;
		}
		
		ParallaxGroup rbg ;
		
		@Override
		public void onLoadedResourcesCompleted() {
			final TextureAtlas bgAtlas = Engine.resource("bgAtlas",TextureAtlas.class);
			rbg = new ParallaxGroup(480, 320, new Vector2(50,100));
			rbg.addActor(new Image(new AdvanceSprite(bgAtlas.findRegion("bg") )));
			rbg.addActor(new ParallaxLayer(rbg,new Image(new AdvanceSprite(bgAtlas.findRegion("cloud") )), new Vector2(0.5f,0),new Vector2(0,1000), new Vector2(0,70)));
			rbg.addActor(new ParallaxLayer(rbg,new Image(new AdvanceSprite(bgAtlas.findRegion("front") )), new Vector2(1f,0),new Vector2(0,1000), new Vector2()));
			rbg.addActor(new ParallaxLayer(rbg,new Image(new AdvanceSprite(bgAtlas.findRegion("dock-tree") )), new Vector2(1f,0),new Vector2(1000,1000), new Vector2()));

			rbg.addAction(Actions.forever(Actions.sequence(
					ParallaxGroupSpeedToAction.obtain(1000,300,1),
					ParallaxGroupSpeedToAction.obtain(50,300,2),
					Actions.scaleTo(2, 2,0.5f),
					Actions.scaleTo(1, 1,1.5f),
					Day2NightAction.obtain(new Color(0.2f,0.2f,0.2f,1), 2),
					Day2NightAction.obtain(new Color(1,1,1,1), 1)
					)));
			final SceneStage stage = new SceneStage();
			final Group group = new Group();
			group.setScale(Engine.getWidth()/480f);
			group.addActor(rbg);
			stage.addActor(group);
			Engine.setMainScene(stage);
		}
	}

}
