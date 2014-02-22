package info.u250.c2d.engine.transitions;

import info.u250.c2d.engine.Engine;

import com.badlogic.gdx.math.Vector3;
/**
 * the scene slide in from right 
 * @author lycying@gmail.com
 */
final class TransitionSceneSlideInRight extends AbstractTransitionSceneSlideIn{
	@Override
	Vector3 targetPositionOffset() {
		return Engine.getDefaultCamera().position.cpy().add(-Engine.getWidth(), 0, 0);
	}

	@Override
	Vector3 orgiPosition() {
		return Engine.getDefaultCamera().position.cpy().set(Engine.getWidth()/2+Engine.getWidth(),Engine.getHeight()/2,0);
	}

}
