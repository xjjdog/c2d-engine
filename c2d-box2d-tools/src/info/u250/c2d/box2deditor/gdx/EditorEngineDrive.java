package info.u250.c2d.box2deditor.gdx;

import info.u250.c2d.box2deditor.gdx.scenes.MainScene;
import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.EngineDrive;
import info.u250.c2d.engine.resources.AliasResourceManager;

public class EditorEngineDrive implements EngineDrive {
	MainScene main = null;
	@Override
	public EngineOptions onSetupEngine() {
		EngineOptions opt = new EngineOptions(new String[]{
				"data/",
		}, 800, 480);
		opt.resizeSync = true;
		opt.debug = false;
		return opt;
	}
	@Override
	public void onResourcesRegister(AliasResourceManager<String> reg) {
		reg.textureAtlas("TA", "data/cb2.atlas");
		reg.texture("T1", "data/T1.png");
		reg.texture("T2", "data/T2.png");
		reg.texture("T3", "data/T3.png");
	}

	@Override
	public void onLoadedResourcesCompleted() {
		main = new MainScene();
		Engine.setMainScene(main);
	}

	@Override
	public void dispose() {}

}
