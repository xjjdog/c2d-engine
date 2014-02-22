package info.u250.c2d.engine.transitions;

import info.u250.c2d.accessors.C2dCameraAccessor;
import info.u250.c2d.engine.C2dCamera;
import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.Transition;
import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
/**
 * a base class to supply side in transition .
 * @author lycying@gmail.com
 */
abstract class AbstractTransitionSceneSlideIn extends Transition{	
	
	/**the target camera to make side in transition . it will be no use after the transition*/
	C2dCamera targetCamera ;
	/**the target scene's position */
	abstract Vector3 targetPositionOffset();
	/**the scene1's position */
	abstract Vector3 orgiPosition();
	@Override
	protected void doTransition(int halfDurationMillis) {
		this.targetCamera = new C2dCamera(Engine.getWidth(), Engine.getHeight());
		
		final Vector3 positionBak = Engine.getDefaultCamera().position.cpy();
		outgoing.hide();
		Engine.getDefaultCamera().position.set(orgiPosition().x, orgiPosition().y, positionBak.z);
		Tween
		.to(Engine.getDefaultCamera(), C2dCameraAccessor.XY, halfDurationMillis*2).target(positionBak.x,positionBak.y)
		.setCallback(new TweenCallback() {
			@Override
			public void onEvent(int type, BaseTween<?> source) {
				doSetMainScene(incoming);
				Gdx.input.setInputProcessor(incoming.getInputProcessor());
				incoming.show();
				Engine.getDefaultCamera().position.set(positionBak);
				reset();
			}
		}).start(Engine.getTweenManager());
	}
	
	@Override
	public void render(float delta) {
		incoming.render(delta);
		
		targetCamera.position.set(this.targetPositionOffset());
		targetCamera.update();
		Engine.getSpriteBatch().setProjectionMatrix(targetCamera.combined);
		outgoing.render(delta);
		
	}

}
