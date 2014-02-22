package info.u250.c2d.tests.animations;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.EngineDrive;
import info.u250.c2d.engine.Scene;
import info.u250.c2d.engine.resources.AliasResourceManager;
import info.u250.c2d.graphic.AnimationSprite;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class AnimationSpriteLoopWithTimesTest extends Engine {
	@Override
	protected EngineDrive onSetupEngineDrive() {
		return new EngineX();
	}

	private class EngineX implements EngineDrive{
		@Override
		public void dispose() {}
		AnimationSprite sprite ;

		boolean toggle = false;
		@Override
		public EngineOptions onSetupEngine() {
			return new EngineOptions(new String[]{"data/animationsprite/"},480,320);
		}

		@Override
		public void onResourcesRegister(AliasResourceManager<String> reg) {
			reg.textureAtlas("Anim",  "data/animationsprite/turkey.atlas");
		}
		
		@Override
		public void onLoadedResourcesCompleted() {
			TextureAtlas atlas = Engine.resource("Anim");
			sprite = new AnimationSprite(new float[]{
					0.1f,
					0.1f,
					0.02f,
					0.05f,
					0.04f,
					0.05f,
					0.09f,
			}, new TextureRegion[]{
					atlas.findRegion("fly1"),
					atlas.findRegion("fly2"),
					atlas.findRegion("fly3"),
					atlas.findRegion("fly4"),
					atlas.findRegion("fly5"),
					atlas.findRegion("fly6"),
					atlas.findRegion("fly7"),
					atlas.findRegion("fly8"),
					
			});
			sprite.setPosition((Engine.getWidth()-sprite.getWidth())/2, 
					(Engine.getHeight()-sprite.getHeight())/2);
			sprite.setLoopTimes(2);
			sprite.setWaitingIndex(4);
			
			
			
			Engine.setMainScene(new Scene() {
				@Override
				public void render(float delta) {
					Engine.getSpriteBatch().begin();
					sprite.render( delta);
					Engine.getSpriteBatch().end();
					
					Engine.debugInfo(  "animation with different keyframes duration .\n touch the screen to replay the animation .\n touch the sprite to handle the onClick event ");
					
				}
				@Override
				public void update(float delta) {}
				@Override
				public InputProcessor getInputProcessor() {
					return new InputAdapter(){
						@Override
						public boolean touchUp(int x, int y, int pointer,
								int button) {
							toggle = !toggle;
							if(toggle){
								
							}else{
								sprite.replay();
							}
							return super.touchUp(x, y, pointer, button);
						}
					};
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

