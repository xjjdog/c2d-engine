package info.u250.c2d.utils;

import com.badlogic.gdx.scenes.scene2d.Actor;
import info.u250.c2d.engine.Engine;

/**
 * @author xjjdog
 */
public class UiUtils {
    /**
     * aligin the Actor to the center of the screen
     */
    public static void centerActor(final Actor actor) {
        actor.setX((Engine.getWidth() - actor.getWidth()) / 2);
        actor.setY((Engine.getHeight() - actor.getHeight()) / 2);
    }
}
