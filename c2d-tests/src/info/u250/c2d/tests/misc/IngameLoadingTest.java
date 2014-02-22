package info.u250.c2d.tests.misc;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.EngineDrive;
import info.u250.c2d.engine.Scene;
import info.u250.c2d.engine.load.Loading.LoadingComplete;
import info.u250.c2d.engine.resources.AliasResourceManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;


public class IngameLoadingTest extends Engine {
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
			reg.texture("AAA", "data/c2d.png");
		}
		@Override
		public void dispose() {}
		@Override
		public EngineOptions onSetupEngine() {
			return new EngineOptions(new String[]{"data/c2d.png"},800,480);
		}

		@Override
		public void onLoadedResourcesCompleted() {
			Engine.setMainScene(new Scene() {
				@Override
				public void render(float delta) {
					Engine.getSpriteBatch().begin();
					Engine.getSpriteBatch().draw(Engine.resource("AAA",Texture.class),100,100);
					Engine.getSpriteBatch().end();
					Engine.debugInfo("touch the screen to get a in game loading");
				}
				@Override
				public InputProcessor getInputProcessor() {
					return new InputAdapter(){
						@Override
						public boolean touchDown(int x, int y, int pointer,
								int button) {
							//bak it .
							final InputProcessor preInputProcess = Gdx.input.getInputProcessor();
							Gdx.input.setInputProcessor(null);
							Engine.load(new String[]{"data/"},new LoadingComplete() {
								@Override
								public void onReady(AliasResourceManager<String> reg) {
									reg.unload("AAA");
									reg.texture("AAA", "data/textures/default.png");
									Gdx.input.setInputProcessor(preInputProcess);
								}
							});
							return super.touchDown(x, y, pointer, button);
						}
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
