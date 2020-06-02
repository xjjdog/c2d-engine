package info.u250.c2d.engine.transitions;

import com.badlogic.gdx.math.Vector3;
import info.u250.c2d.engine.Engine;

/**
 * the scene move in from left
 *
 * @author lycying@gmail.com
 */
final class TransitionSceneMoveInLeft extends AbstractTransitionSceneMoveIn {
    @Override
    Vector3 iniTargetPositionOffset() {
        return new Vector3(Engine.getWidth(), 0, 0);
    }
}
