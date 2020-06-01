package info.u250.c2d.utils;

import info.u250.c2d.engine.Engine;

import com.badlogic.gdx.scenes.scene2d.Actor;
/**
 * @author lycying@gmail.com*/
public class UiUtils {
	/**aligin the Actor to the center of the screen */
	public static void centerActor(final Actor actor){
		actor.setX( ( Engine.getWidth() - actor.getWidth() )/2 );
		actor.setY( ( Engine.getHeight() - actor.getHeight() )/2 );
	}
}
