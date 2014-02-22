package info.u250.c2d.tests.misc;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.EngineDrive;
import info.u250.c2d.engine.Scene;
import info.u250.c2d.engine.events.Event;
import info.u250.c2d.engine.events.EventListener;
import info.u250.c2d.engine.resources.AliasResourceManager;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;


public class EventTest extends Engine {
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
		}
		@Override
		public void dispose() {}
		@Override
		public EngineOptions onSetupEngine() {
			return new EngineOptions(new String[]{},800,480);
		}

		String test = "";
		@Override
		public void onLoadedResourcesCompleted() {
			
			Engine.getEventManager().register("_test_", new EventListener(){
				@Override
				public void onEvent(Event event) {
					test = "\n wawa \n i click \n yes \n event fire ~~~\n :)";
				}
			});
			Engine.setMainScene(new Scene() {
				@Override
				public void render(float delta) {
					Engine.debugInfo("Event manager: touch the screen to fire ."+test);
				}
				@Override
				public InputProcessor getInputProcessor() {
					return new InputAdapter(){
						public boolean touchDown(int x, int y, int pointer, int button) {
							Engine.getEventManager().fire("_test_", null);
							return false;
						};
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
			});	
		}
	}
}
