package info.u250.c2d.accessors;

import aurelienribon.tweenengine.TweenAccessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com
 * @author lycying@gmail.com 
 * add rgb color change 
 */
public class SpriteAccessor implements TweenAccessor<Sprite> {
	public static final int POSITION_XY = 1;
	public static final int SCALE_XY = 2;
	public static final int ROTATION = 3;
	public static final int OPACITY = 4;
	public static final int RGB = 5;

	@Override
	public int getValues(Sprite target, int tweenType, float[] returnValues) {
		switch (tweenType) {
			case POSITION_XY:
				returnValues[0] = target.getX();
				returnValues[1] = target.getY();
				return 2;

			case SCALE_XY:
				returnValues[0] = target.getScaleX();
				returnValues[1] = target.getScaleY();
				return 2;

			case ROTATION: returnValues[0] = target.getRotation(); return 1;
			case OPACITY: returnValues[0] = target.getColor().a; return 1;
			case RGB:
				returnValues[0] = target.getColor().r;
				returnValues[1] = target.getColor().g;
				returnValues[2] = target.getColor().b;
				return 3;
			default: assert false; return -1;
		}
	}

	@Override
	public void setValues(Sprite target, int tweenType, float[] newValues) {
		switch (tweenType) {
			case POSITION_XY: target.setPosition(newValues[0], newValues[1]); break;
			case SCALE_XY: target.setScale(newValues[0], newValues[1]); break;
			case ROTATION: target.setRotation(newValues[0]); break;
			case OPACITY:
				Color c = target.getColor();
				c.set(c.r, c.g, c.b, newValues[0]);
				target.setColor(c);
				break;
			case RGB:
				Color color = target.getColor();
				color.r  = newValues[0];
				color.g = newValues[1];
				color.b = newValues[2];
				target.setColor(color);
				break;
			default: assert false;
		}
	}
}
