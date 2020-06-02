package info.u250.c2d.engine.transitions;

import aurelienribon.tweenengine.Tween;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import info.u250.c2d.accessors.MeshMaskAccessor;
import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.Transition;
import info.u250.c2d.graphic.FadeMask;

/**
 * the scene fade in and fade out . we use the mask to archive this
 *
 * @author lycying@gmail.com
 */
final class TransitionSceneFadeWhite extends Transition {
    public TransitionSceneFadeWhite() {
        this.mask = new FadeMask(new Color(1, 1, 1, 1));
    }

    FadeMask mask;

    @Override
    protected void doTransition(final int halfDurationMillis) {
        outgoing.hide();
        Tween.to(mask, MeshMaskAccessor.Transparency, halfDurationMillis)
                .target(1f).setCallback(
                (type, source) -> {
                    doSetMainScene(incoming);
                    Tween.to(mask, MeshMaskAccessor.Transparency, halfDurationMillis).target(0).setCallback(
                            (t, s) -> {
                                Gdx.input.setInputProcessor(incoming.getInputProcessor());
                                incoming.show();
                                reset();
                            }).start(Engine.getTweenManager());
                }).start(Engine.getTweenManager());
    }

    @Override
    public void render(float delta) {
        Engine.getMainScene().render(delta);
        if (isTransiting()) {
            mask.render(delta);
        }
    }
}
