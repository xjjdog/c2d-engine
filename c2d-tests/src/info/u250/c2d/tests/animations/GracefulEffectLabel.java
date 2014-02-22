package info.u250.c2d.tests.animations;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.scaleTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.EngineDrive;
import info.u250.c2d.engine.Scene;
import info.u250.c2d.engine.resources.AliasResourceManager;
import info.u250.c2d.graphic.C2dStage;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;


public class GracefulEffectLabel extends Engine {
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
			reg.font("Font", "data/fonts/battle.fnt");
		}
		@Override
		public void dispose() {}
		@Override
		public EngineOptions onSetupEngine() {
			final EngineOptions opt = new EngineOptions(new String[]{"data/fonts/battle.fnt"},800,480);
			return opt;
		}

		
		@Override
		public void onLoadedResourcesCompleted() {
			final C2dStage stage = new C2dStage();
			final ColorfulText textGroup = new ColorfulText();
			stage.addActor(textGroup);
			textGroup.show(true, "Boss Battle!!!!");
			textGroup.addAction(Actions.forever(Actions.sequence(Actions.delay(4,Actions.run(new Runnable() {
				@Override
				public void run() {
					textGroup.show(false, "Battle 16/2500");
				}
			})),Actions.delay(4,Actions.run(new Runnable() {
				@Override
				public void run() {
					textGroup.show(true, "Boss Battle!!!!");
				}
			})))));
			Engine.setMainScene(new Scene() {
				@Override
				public void render(float delta) {
					stage.act();
					stage.draw();
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
	
	
	public class ColorfulText extends Group {
		public void show(boolean isBoss,String text) {
			this.clear();
			if(isBoss){
				String[] strs = text.split("");
				float width = 0;
				for(int i=0;i<strs.length;i++){
					String s = strs[i];
					Label temp = new Label(s, new LabelStyle(Engine.resource("Font", BitmapFont.class), Color.WHITE));
					temp.setPosition(width, 400);
					temp.addAction(Actions.delay(0.1f*i,Actions.sequence(Actions.moveBy(0, -300,0.5f,Interpolation.swingOut),Actions.delay(1f,Actions.moveBy(0, 300,0.3f)))));
					this.addActor(temp);
					width+=temp.getPrefWidth();
				}
				this.setPosition(Engine.getWidth() / 2 - width / 2, 100);
			}else{
				Label battleLabel = new Label(text, new LabelStyle(Engine.resource("Font", BitmapFont.class), Color.WHITE));
				this.setSize(battleLabel.getPrefWidth(), battleLabel.getPrefHeight());
				this.setPosition(Engine.getWidth() / 2 - battleLabel.getPrefWidth() / 2, 200);
				this.setOrigin(this.getWidth() / 2, this.getHeight()/2);
				this.setScale(0);
				this.addAction(sequence(scaleTo(1, 1, 1f, Interpolation.swingOut), delay(1f), moveBy(50, 0, 0.1f), moveBy(-1500, 0, 0.3f)));
				this.addActor(battleLabel);
			}
		}
	}

}
