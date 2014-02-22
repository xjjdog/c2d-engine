package info.u250.c2d.graphic.parallax;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;

public class Day2NightAction extends TemporalAction {
	public static Day2NightAction obtain(Color diffuseColor,float duration,Interpolation interpolation){
		Day2NightAction action = Actions.action(Day2NightAction.class);
		action.diffuseColor.set(diffuseColor);
		action.setDuration(duration);
		action.setInterpolation(interpolation);
		return action;
	}
	public static Day2NightAction obtain(Color diffuseColor,float duration){
		Day2NightAction action = Actions.action(Day2NightAction.class);
		action.diffuseColor.set(diffuseColor);
		action.setDuration(duration);
		return action;
	}
	
	
	private float startR, startG, startB, startA;
	private Color diffuseColor = new Color();
	private final Color end = new Color();

	protected void begin () {
		startR = diffuseColor.r;
		startG = diffuseColor.g;
		startB = diffuseColor.b;
		startA = diffuseColor.a;
	}

	protected void update (float percent) {
		float r = startR + (end.r - startR) * percent;
		float g = startG + (end.g - startG) * percent;
		float b = startB + (end.b - startB) * percent;
		float a = startA + (end.a - startA) * percent;
		GL10 gl = Gdx.gl10;
		gl.glEnable(GL10.GL_LIGHTING);
		gl.glEnable(GL10.GL_LIGHT0);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, new float[]{r, g, b, a}, 0);
	}

	public void reset () {
		super.reset();
		diffuseColor = null;
	}

	public Color getEndColor () {
		return end;
	}

	/** Sets the color to transition to. Required. */
	public void setEndColor (Color color) {
		end.set(color);
	}
}
