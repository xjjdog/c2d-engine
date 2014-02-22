package info.u250.c2d.engine.transitions;

import info.u250.c2d.engine.Engine;

import com.badlogic.gdx.math.Vector3;

/**
 * the scene move in from right
 * @author lycying@gmail.com
 */
final class TransitionSceneMoveInRight extends AbstractTransitionSceneMoveIn{
	@Override
	Vector3 iniTargetPositionOffset() {
		return new Vector3(-Engine.getWidth(),0, 0);
	}
}
