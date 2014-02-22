package info.u250.c2d.tests.misc;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.EngineDrive;
import info.u250.c2d.engine.Scene;
import info.u250.c2d.engine.load.startup.StartupLoading;
import info.u250.c2d.engine.resources.AliasResourceManager;
import info.u250.c2d.tests.utils.SimpleAnimationLoading;

import com.badlogic.gdx.InputProcessor;

public class CustomLoadingTest extends Engine{
	@Override
	protected EngineDrive onSetupEngineDrive() {
		return new EngineX();
	}
	
	@Override
	protected StartupLoading getStartupLoading() {
		return new SimpleAnimationLoading();
	}
	private class EngineX implements EngineDrive{
		@Override
		public void onResourcesRegister(AliasResourceManager<String> reg) {
			
		}
		@Override
		public void dispose() {}
		@Override
		public EngineOptions onSetupEngine() {
			final EngineOptions opt =  new EngineOptions(new String[]{"data/"},480,320);
			opt.useGL20 = true;
			return opt;
		}

		@Override
		public void onLoadedResourcesCompleted() {
			
			
			Engine.setMainScene(new Scene() {
				@Override
				public void update(float delta) {}
				@Override
				public void hide() {}
				@Override
				public void show() {}
				@Override
				public void render(float delta) {
					Engine.debugInfo( "Load all.... nothing to draw.");
				}
				
				@Override
				public InputProcessor getInputProcessor() {
					return null;
				}
			});
		}
	}
	
}