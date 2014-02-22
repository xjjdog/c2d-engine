package info.u250.c2d.engine.transitions;

import info.u250.c2d.accessors.C2dCameraAccessor;
import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.Transition;
import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;

import com.badlogic.gdx.Gdx;
/**
 * the scene zoom in with rotate
 * @author lycying@gmail.com
 */
final class TransitionSceneRotateWithZoomIn extends Transition{	
	
	public TransitionSceneRotateWithZoomIn(){
	}
	
	@Override
	protected void doTransition(final int halfDurationMillis) {
		outgoing.hide();
		Timeline.createParallel().beginParallel()
		.push(
				Tween
				.to(Engine.getDefaultCamera(), C2dCameraAccessor.ROTATION, halfDurationMillis).target(360*2)
			)
		.push(
				Tween
				.to(Engine.getDefaultCamera(), C2dCameraAccessor.Zoom, halfDurationMillis).target(20f)
			)
		.end()
		.setCallback(new TweenCallback() {
			@Override
			public void onEvent(int type, BaseTween<?> source) {
				doSetMainScene(incoming);
				Timeline.createParallel().beginParallel()
				.push(
						Tween
						.to(Engine.getDefaultCamera(), C2dCameraAccessor.ROTATION, 500).target(0)
					)
				.push(
						Tween
						.to(Engine.getDefaultCamera(), C2dCameraAccessor.Zoom, 500).target(1f)
					)
				.end()
				.setCallback(new TweenCallback() {
					@Override
					public void onEvent(int type, BaseTween<?> source) {
						Gdx.input.setInputProcessor(incoming.getInputProcessor());
						incoming.show();
						reset();
					}
				}).start(Engine.getTweenManager());
			}
		}).start(Engine.getTweenManager());
	}
	@Override
	public void render(float delta) {
		Engine.getMainScene().render(delta);
	}
}
