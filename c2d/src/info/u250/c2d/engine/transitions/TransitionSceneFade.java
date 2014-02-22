package info.u250.c2d.engine.transitions;

import info.u250.c2d.accessors.MeshMaskAccessor;
import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.Transition;
import info.u250.c2d.graphic.FadeMask;
import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
/**
 * the scene fade in and fade out . we use the mask to archive this
 * @author lycying@gmail.com
 */
final class TransitionSceneFade extends Transition{	
	public TransitionSceneFade(){
		this.mask = new FadeMask(Color.BLACK);
	}
	FadeMask mask ;

	@Override
	protected void doTransition(final int halfDurationMillis) {
		outgoing.hide();
		Tween
		.to(mask, MeshMaskAccessor.Transparency, halfDurationMillis).target(1f)
		.setCallback(new TweenCallback() {

			@Override
			public void onEvent(int type, BaseTween<?> source) {
				doSetMainScene(incoming);
				Tween
				.to(mask, MeshMaskAccessor.Transparency, halfDurationMillis).target(0)
				.setCallback(new TweenCallback() {
					
					@Override
					public void onEvent(int type, BaseTween<?> source) {
							Gdx.input.setInputProcessor(incoming.getInputProcessor());
							incoming.show();
							reset();
					}
				}).start(Engine.getTweenManager());
				
			}
		})
		.start(Engine.getTweenManager());
	}
	@Override
	public void render(float delta) {
		Engine.getMainScene().render(delta);
		if(isTransiting()){
			mask.render(delta);
		}
	}
}
