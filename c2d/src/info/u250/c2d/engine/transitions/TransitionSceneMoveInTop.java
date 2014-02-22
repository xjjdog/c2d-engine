package info.u250.c2d.engine.transitions;

import info.u250.c2d.engine.Engine;

import com.badlogic.gdx.math.Vector3;

/**
 * the scene move in from top 
 * @author lycying@gmail.com
 */
final class TransitionSceneMoveInTop extends AbstractTransitionSceneMoveIn{
	@Override
	Vector3 iniTargetPositionOffset() {
		return new Vector3(0, -Engine.getHeight(), 0);
	}
}
