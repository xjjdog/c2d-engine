package info.u250.c2d.graphic.parallax;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;

public class ParallaxGroupSpeedToAction extends TemporalAction {
	public static ParallaxGroupSpeedToAction obtain(float endX,float  endY,float duration,Interpolation interpolation){
		ParallaxGroupSpeedToAction action = Actions.action(ParallaxGroupSpeedToAction.class);
		action.endX = endX;
		action.endY = endY;
		action.setDuration(duration);
		action.setInterpolation(interpolation);
		return action;
	}
	public static ParallaxGroupSpeedToAction obtain(float endX,float  endY,float duration){
		ParallaxGroupSpeedToAction action = Actions.action(ParallaxGroupSpeedToAction.class);
		action.endX = endX;
		action.endY = endY;
		action.setDuration(duration);
		return action;
	}
	
	
	private float startX, startY;
	private float endX, endY;

	protected void begin () {
		startX = ((ParallaxGroup)actor).getSpeed().x;
		startY = ((ParallaxGroup)actor).getSpeed().x;
	}

	protected void update (float percent) {
		((ParallaxGroup)actor).setSpeed(startX + (endX - startX) * percent, startY + (endY - startY) * percent);
	}
}
