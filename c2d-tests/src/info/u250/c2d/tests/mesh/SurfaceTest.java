package info.u250.c2d.tests.mesh;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.EngineDrive;
import info.u250.c2d.engine.Scene;
import info.u250.c2d.engine.resources.AliasResourceManager;
import info.u250.c2d.graphic.surfaces.SurfaceData;
import info.u250.c2d.graphic.surfaces.TriangleSurfaces;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;


public class SurfaceTest extends Engine {
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
			reg.texture("Texture", "data/textures/default.png");
		}
		@Override
		public void dispose() {}
		@Override
		public EngineOptions onSetupEngine() {
			final EngineOptions opt = new EngineOptions(new String[]{
					"data/textures/default.png"
			},800,480);
			return opt;
		}

		@Override
		public void onLoadedResourcesCompleted() {
			final SurfaceData data = new SurfaceData();
			data.primitiveType = GL10.GL_TRIANGLE_STRIP;
			data.texture="Texture";
			data.points = new Array<Vector2>(){{
				add(new Vector2(-27.005554f,87.99661f));
				add(new Vector2(-20,-4));
				add(new Vector2(119,125));
				add(new Vector2(200.99362f,-14.00885f));
				add(new Vector2(293.00104f,131.99255f));
				add(new Vector2(356.99304f,-9.0069275f));
				add(new Vector2(360.9969f,131.99257f));
				add(new Vector2(458.9926f,-9.006897f));
				add(new Vector2(510.99628f,125.992584f));
				add(new Vector2(556.0f,-7f));
				add(new Vector2(693.0f,92.0f));
				add(new Vector2(685.0f,-53.0f));
				add(new Vector2(766.00696f,54.990906f));
				add(new Vector2(813.0028f,0));
			}};
			final TriangleSurfaces surface  = new TriangleSurfaces(data);
			Engine.setMainScene(new Scene() {
				@Override
				public void render(float delta) {
					surface.render(delta);
					
					Engine.debugInfo("Surface! can be physicalable");
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
