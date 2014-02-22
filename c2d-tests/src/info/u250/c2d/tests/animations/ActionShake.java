package info.u250.c2d.tests.animations;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.EngineDrive;
import info.u250.c2d.engine.Scene;
import info.u250.c2d.engine.resources.AliasResourceManager;
import info.u250.c2d.graphic.AnimationSprite;
import info.u250.c2d.graphic.AdvanceSpriteImage;
import info.u250.c2d.graphic.C2dStage;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;


public class ActionShake extends Engine {
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
			this.addActor(actor);
			
			this.shake();
		}
		@Override
		public void render(float delta) {
			this.act(delta);
			this.draw();
			
			Engine.debugInfo("Touch the screen to shake again");
		}
		private void shake(){
			actor.addAction(Actions.repeat(10,(
					Actions.sequence(
							Actions.moveBy(10, 0,0.02f),
							Actions.moveBy(-10, 0,0.02f),
							Actions.moveBy(0, 10,0.02f),
							Actions.moveBy(0, -10,0.02f)
					)
			)));
		}
		@Override
		public InputProcessor getInputProcessor() {
			return new InputAdapter(){
				@Override
				public boolean touchUp(int x, int y, int pointer, int button) {
					shake();
					return super.touchUp(x, y, pointer, button);
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
	}
}
