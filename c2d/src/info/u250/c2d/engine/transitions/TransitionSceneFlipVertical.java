package info.u250.c2d.engine.transitions;

import info.u250.c2d.accessors.Flip3DCameraAccessor;
import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.Flip3DCamera;
import info.u250.c2d.engine.Transition;
import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;

import com.badlogic.gdx.Gdx;
/**
 * Transition Scene Flip Horizontal
 * @author lycying@gmail.com
 */
final class TransitionSceneFlipVertical extends Transition{	
	public TransitionSceneFlipVertical(){
	}
	Flip3DCamera flipCamera ;
	@Override
	protected void doTransition(final int halfDurationMillis) {
		if(Tween.getRegisteredAccessor(Flip3DCamera.class)==null){
			Tween.registerAccessor(Flip3DCamera.class,new Flip3DCameraAccessor());
		}
		flipCamera = new Flip3DCamera(Engine.getWidth(), Engine.getHeight());
		outgoing.hide();
		Tween
		.to(flipCamera, Flip3DCameraAccessor.ROTATION_X, halfDurationMillis).target(90)
		.setCallback(new TweenCallback() {
			@Override
			public void onEvent(int type, BaseTween<?> source) {
				flipCamera.setAngleX(-90);
				doSetMainScene(incoming);
				Tween
				.to(flipCamera, Flip3DCameraAccessor.ROTATION_X, halfDurationMillis).target(0)
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
		flipCamera.update();
		Engine.getSpriteBatch().setProjectionMatrix(flipCamera.combined);
		Engine.getMainScene().render(delta);
	}
}
