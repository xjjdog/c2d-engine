package info.u250.c2d.tests.sfx;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.EngineDrive;
import info.u250.c2d.engine.Scene;
import info.u250.c2d.engine.resources.AliasResourceManager;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;

public class EngineSimpleSfxTest extends Engine{
	@Override
	protected EngineDrive onSetupEngineDrive() {
		return new EngineX();
	}
	
	
	private class EngineX implements EngineDrive{
		@Override
		public void onResourcesRegister(AliasResourceManager<String> reg) {
			reg.texture("logo", "data/c2d.png");
			reg.sound("BG1", "data/sound/test.ogg");
			reg.music("BG2", "data/music/test.ogg");
		}
		@Override
		public void dispose() {}
		@Override
		public EngineOptions onSetupEngine() {
			final EngineOptions opt =  new EngineOptions(new String[]{"data/sound","data/music","data/c2d.png"},800,480);
			opt.useGL20 = false;
			return opt;
		}

		@Override
		public void onLoadedResourcesCompleted() {
			Engine.getSoundManager().playSound("BG1");
			Engine.getMusicManager().playMusic("BG2", true);
			
			
			Engine.setMainScene(new Scene() {
				@Override
				public void update(float delta) {}
				@Override
				public void hide() {}
				@Override
				public void show() {}
				@Override
				public void render(float delta) {
					Engine.getSpriteBatch().begin();
					Engine.getSpriteBatch().draw(Engine.resource("logo",Texture.class), 0,0);
					Engine.getSpriteBatch().end();
					Engine.debugInfo( "This will load all resources and just play a sound and a music");
				}
				
				@Override
				public InputProcessor getInputProcessor() {
					return null;
				}
			});
		}
	}
	
}