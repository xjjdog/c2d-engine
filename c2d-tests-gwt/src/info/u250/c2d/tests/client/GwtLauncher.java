package info.u250.c2d.tests.client;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.EngineCallback;
import info.u250.c2d.tests.tools.MotionWelderTest;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

public class GwtLauncher extends GwtApplication {
	@Override
	public GwtApplicationConfiguration getConfig () {
		GwtApplicationConfiguration cfg = new GwtApplicationConfiguration(800, 480);
		return cfg;
	}

	@Override
	public ApplicationListener getApplicationListener () {
		Engine engine = new MotionWelderTest();
		Engine.setEngineCallback(new EngineCallback() {
			@Override
			public void postLoad() {}

			@Override
			public void preLoad(DisplayMode mode, String[] assets) {
				for(String key : getPreloader().binaries.keys() ){
					itr(assets, key);
				}
				for(String key : getPreloader().images.keys() ){
					itr(assets, key);
				}
				for(String key : getPreloader().texts.keys() ){
					itr(assets, key);
				}
				for(String key : getPreloader().audio.keys() ){
					itr(assets, key);
				}
			}
			
			void itr(String[] assets,String key){
				for(String s : assets){
					if(key.contains(s)){
						Engine.getAliasResourceManager().load(key);
						return;
					}
				}
			}
			
		});
		return engine;
	}
}