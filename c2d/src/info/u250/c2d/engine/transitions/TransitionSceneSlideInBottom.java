package info.u250.c2d.engine.transitions;

import info.u250.c2d.engine.Engine;

import com.badlogic.gdx.math.Vector3;

/**
 * the scene slide in from bottom 
 * @author lycying@gmail.com
 */
final class TransitionSceneSlideInBottom extends AbstractTransitionSceneSlideIn{
	@Override
	Vector3 targetPositionOffset() {
		return Engine.getDefaultCamera().position.cpy().add(0, Engine.getHeight(), 0);
	}

	@Override
	Vector3 orgiPosition() {
		return Engine.getDefaultCamera().position.cpy().set(Engine.getWidth()/2,Engine.getHeight()/2-Engine.getHeight(),0);
	}


}
