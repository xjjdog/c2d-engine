package info.u250.c2d.engine.transitions;

import com.badlogic.gdx.math.Vector3;
import info.u250.c2d.engine.Engine;

/**
 * the scene move in from bottom
 *
 * @author xjjdog
 */
final class TransitionSceneMoveInBottom extends AbstractTransitionSceneMoveIn {
    @Override
    Vector3 iniTargetPositionOffset() {
        return new Vector3(0, Engine.getHeight(), 0);
    }
}
