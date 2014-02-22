package info.u250.c2d.tests.animations;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.EngineDrive;
import info.u250.c2d.engine.Scene;
import info.u250.c2d.engine.resources.AliasResourceManager;
import info.u250.c2d.graphic.AdvanceSprite;
import info.u250.c2d.graphic.Analog;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;


public class AnalogTest  extends Engine {
	@Override
	protected EngineDrive onSetupEngineDrive() {
		return new EngineX();
	}
	
	
	private class EngineX implements EngineDrive{
		@Override
		public void onResourcesRegister(AliasResourceManager<String> reg) {
			reg.texture("logo",  "data/c2d.png");
			reg.texture("ControlBase", "data/analog/control-base.png");
			reg.texture("ControlKnob", "data/analog/control-knob.png");
			reg.object("logoSprite",new AdvanceSprite(Engine.resource("logo",Texture.class)));
		}
		@Override
		public void dispose() {}
		@Override
		public EngineOptions onSetupEngine() {
			EngineOptions opt = new EngineOptions(new String[]{
					"data/c2d.png",
					"data/analog"
					},800,480);
			opt.resizeSync = false;
			return opt ;
		}
		
		AdvanceSprite sprite ;
		
		Analog analog ;

		@Override
		public void onLoadedResourcesCompleted() {
			sprite  = Engine.resource("logoSprite");
			sprite.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
			analog = new Analog(
					new AdvanceSprite(Engine.resource("ControlBase",Texture.class)),
					new AdvanceSprite(Engine.resource("ControlKnob",Texture.class)),
					new Vector2(100,50));
			

			Engine.setMainScene(new Scene() {
				@Override
				public void update(float delta) {}
				@Override
				public void hide() {}

				@Override
				public void show() {}
				@Override
				public void render(float delta) {
					Engine.getSpriteBatch().begin();
					sprite.setPosition(
							Engine.getWidth()/2+analog.getAmount()*Engine.getWidth()/2*(float)Math.cos(analog.getAngle()),
							Engine.getHeight()/2+analog.getAmount()*Engine.getHeight()/2*(float)Math.sin(analog.getAngle()));
					sprite.draw(Engine.getSpriteBatch());
					analog.render(delta);
					Engine.getSpriteBatch().end();
					
					
					Engine.debugInfo("by touch the Thumbpad .You can see the change of this. \n" +
							"the amount is :"+analog.getAmount()+",\n" +
							"the angle is :"+analog.getAngle()*MathUtils.radiansToDegrees);
				}
				
				@Override
				public InputProcessor getInputProcessor() {
					return analog;
				}
			});
		}
	}

}
