package info.u250.c2d.tests.mesh;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.EngineDrive;
import info.u250.c2d.engine.Scene;
import info.u250.c2d.engine.resources.AliasResourceManager;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer20;


public class GradientTest extends Engine {
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
			final EngineOptions opt = new EngineOptions(new String[]{},800,480);
			return opt;
		}

		@Override
		public void onLoadedResourcesCompleted() {
			final ImmediateModeRenderer20 renderer = new ImmediateModeRenderer20(false,true,0);
			Engine.setMainScene(new Scene() {
				@Override
				public void render(float delta) {
					renderer.begin(Engine.getDefaultCamera().combined, GL10.GL_TRIANGLE_STRIP);
					renderer.color(1, 1, 1, 1);
					renderer.vertex(0, 0, 0);
					renderer.color(1, 1, 1, 1);
					renderer.vertex(400, 0, 0);
					renderer.color(255 / 255f, 0 / 255f, 0 / 255f, 1);
					renderer.vertex(0, 400 , 0);
					renderer.color(0 / 255f, 255 / 255f, 0 / 255f, 1);
					renderer.vertex(400, 400 , 0);
					renderer.end();
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
