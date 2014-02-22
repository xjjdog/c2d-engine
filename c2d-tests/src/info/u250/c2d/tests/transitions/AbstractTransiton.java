package info.u250.c2d.tests.transitions;

import info.u250.c2d.engine.CoreProvider.TransitionType;
import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.EngineDrive;
import info.u250.c2d.engine.resources.AliasResourceManager;


public abstract class AbstractTransiton extends Engine {
	abstract TransitionType a2b();
	abstract TransitionType b2a();
	
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
			reg.texture("A", "data/noise.png");
		}
		@Override
		public void dispose() {}
		@Override
		public EngineOptions onSetupEngine() {
			final EngineOptions opt = new EngineOptions(new String[]{"data/noise.png"},800,480);
			return opt;
		}

		@Override
		public void onLoadedResourcesCompleted() {
			SceneA sceneA = SceneA.getInstance();
			
			SceneB.getInstance();
			
			Engine.setMainScene(sceneA);
		}
	}
}
