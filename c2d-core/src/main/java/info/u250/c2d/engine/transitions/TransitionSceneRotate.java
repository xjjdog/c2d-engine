package info.u250.c2d.engine.transitions;

import aurelienribon.tweenengine.Tween;
import com.badlogic.gdx.Gdx;
import info.u250.c2d.accessors.C2dCameraAccessor;
import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.Transition;

/**
 * the scene rotate
 *
 * @author lycying@gmail.com
 */
final class TransitionSceneRotate extends Transition {
    public TransitionSceneRotate() {
    }

    @Override
    protected void doTransition(final int halfDurationMillis) {
        outgoing.hide();
        Tween
                .to(Engine.getDefaultCamera(), C2dCameraAccessor.ROTATION, halfDurationMillis).target(360)
                .setCallback((type, source) -> {
                    doSetMainScene(incoming);
                    Tween.to(Engine.getDefaultCamera(), C2dCameraAccessor.ROTATION, halfDurationMillis).target(0)
                            .setCallback((t, s) -> {
                                Gdx.input.setInputProcessor(incoming.getInputProcessor());
                                incoming.show();
                                reset();
                            }).start(Engine.getTweenManager());
                }).start(Engine.getTweenManager());
    }

    @Override
    public void render(float delta) {
        Engine.getMainScene().render(delta);
    }
}
