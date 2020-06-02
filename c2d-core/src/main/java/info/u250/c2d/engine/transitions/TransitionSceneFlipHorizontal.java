package info.u250.c2d.engine.transitions;

import aurelienribon.tweenengine.Tween;
import com.badlogic.gdx.Gdx;
import info.u250.c2d.accessors.Flip3DCameraAccessor;
import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.Flip3DCamera;
import info.u250.c2d.engine.Transition;

/**
 * Transition Scene Flip Horizontal
 *
 * @author xjjdog
 */
final class TransitionSceneFlipHorizontal extends Transition {
    public TransitionSceneFlipHorizontal() {
    }

    Flip3DCamera flipCamera;

    @Override
    protected void doTransition(final int halfDurationMillis) {
        if (Tween.getRegisteredAccessor(Flip3DCamera.class) == null) {
            Tween.registerAccessor(Flip3DCamera.class, new Flip3DCameraAccessor());
        }
        flipCamera = new Flip3DCamera(Engine.getWidth(), Engine.getHeight());

        outgoing.hide();
        Tween
                .to(flipCamera, Flip3DCameraAccessor.ROTATION_Y, halfDurationMillis).target(90)
                .setCallback((type, source) -> {
                    flipCamera.setAngleY(-90);
                    doSetMainScene(incoming);
                    Tween.to(flipCamera, Flip3DCameraAccessor.ROTATION_Y, halfDurationMillis).target(0)
                            .setCallback((t, s) -> {
                                Gdx.input.setInputProcessor(incoming.getInputProcessor());
                                incoming.show();
                                reset();
                            }).start(Engine.getTweenManager());
                }).start(Engine.getTweenManager());
    }

    @Override
    public void render(float delta) {
        flipCamera.update();
        Engine.getSpriteBatch().setProjectionMatrix(flipCamera.combined);
        Engine.getMainScene().render(delta);
    }
}
