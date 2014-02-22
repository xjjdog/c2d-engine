package info.u250.c2d.tests.mesh;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.EngineDrive;
import info.u250.c2d.engine.Scene;
import info.u250.c2d.engine.resources.AliasResourceManager;
import info.u250.c2d.graphic.background.RepeatTextureBackground;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class RepeatTextureBackgroundTest extends Engine {
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
			reg.texture("BG", "data/textures/default.png");
		}
		@Override
		public void dispose() {}
		@Override
		public EngineOptions onSetupEngine() {
			EngineOptions opt = new EngineOptions(new String[]{"data/textures/default.png"},800,480);
			opt.useGL20 = true;
			return opt;
		}

		RepeatTextureBackground bg ;
		Sprite sprite;
		@Override
		public void onLoadedResourcesCompleted() {
			bg = new RepeatTextureBackground("BG");
			sprite = new Sprite(Engine.resource("BG",Texture.class));
			sprite.setPosition(Engine.getWidth()/2, Engine.getHeight()/2);
			Engine.setMainScene(new Scene() {
				@Override
				public void render(float delta) {
					bg.render(delta);
					
					Engine.getSpriteBatch().begin();
					sprite.setColor(Color.GRAY);
					sprite.draw(Engine.getSpriteBatch());
					Engine.getSpriteBatch().end();
					Engine.debugInfo("draw a background use the repeatbackground.\n" +
							" The sprite in the center is the texture");
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
}
