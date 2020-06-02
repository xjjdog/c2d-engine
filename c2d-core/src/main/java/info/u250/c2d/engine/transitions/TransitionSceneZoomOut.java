package info.u250.c2d.engine.transitions;

import aurelienribon.tweenengine.Tween;
import com.badlogic.gdx.Gdx;
import info.u250.c2d.accessors.C2dCameraAccessor;
import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.Transition;

/**
 * the scene zoom out
 *
 * @author xjjdog
 */
final class TransitionSceneZoomOut extends Transition {
    public TransitionSceneZoomOut() {
    }

    @Override
    protected void doTransition(final int halfDurationMillis) {
        outgoing.hide();
        Tween.to(Engine.getDefaultCamera(), C2dCameraAccessor.Zoom, halfDurationMillis).target(0.2f)
                .setCallback((type, source) -> {
                    doSetMainScene(incoming);
                    Tween.to(Engine.getDefaultCamera(), C2dCameraAccessor.Zoom, halfDurationMillis).target(1)
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
