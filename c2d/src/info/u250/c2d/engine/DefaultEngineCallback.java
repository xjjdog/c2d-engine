package info.u250.c2d.engine;

import com.badlogic.gdx.Graphics.DisplayMode;

public class DefaultEngineCallback implements EngineCallback {

	@Override
	public void preLoad(DisplayMode mode, String[] assets) {
		for(final String path:assets){
			Engine.getAliasResourceManager().load(path);
		}
	}

	@Override
	public void postLoad() {
		
	}

}
