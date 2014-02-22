package info.u250.c2d.tests.animations;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.EngineDrive;
import info.u250.c2d.engine.Scene;
import info.u250.c2d.engine.resources.AliasResourceManager;
import info.u250.c2d.graphic.AnimationSprite;
import info.u250.c2d.graphic.AdvanceSpriteImage;
import info.u250.c2d.graphic.C2dStage;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;


public class ActionBLink extends Engine {
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
			reg.textureAtlas("Anim",  "data/animationsprite/turkey.atlas");
		}
		@Override
		public void dispose() {}
		@Override
		public EngineOptions onSetupEngine() {
			final EngineOptions opt = new EngineOptions(new String[]{
					"data/animationsprite/"
			},800,480);
			return opt;
		}

		@Override
		public void onLoadedResourcesCompleted() {
			Engine.setMainScene(new MainScene());	
		}
	}
	private static class MainScene extends C2dStage implements Scene{
		final AdvanceSpriteImage actor;
		public MainScene(){
			actor = new AdvanceSpriteImage(new AnimationSprite(0.05f, Engine.resource("Anim",TextureAtlas.class),"fly"));
			actor.setPosition(200, 100);
			actor.addAction(Actions.forever(
					Actions.sequence(
							Actions.alpha(0.1f,0.1f),
							Actions.fadeIn(0.1f)
							)
					));
			this.addActor(actor);
		}
		@Override
		public void render(float delta) {
			this.act(delta);
			this.draw();
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
	}
}
