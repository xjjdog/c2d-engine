package info.u250.c2d.accessors;

import info.u250.c2d.engine.C2dCamera;
import aurelienribon.tweenengine.TweenAccessor;

/** change some attributes of the camera 
 * @author lycying@gmail.com*/
public class C2dCameraAccessor implements TweenAccessor<C2dCamera>{

	public final static int Zoom = 1;
	public final static int XY = 2;
	public final static int ROTATION=3;

	
	@Override
	public int getValues(C2dCamera target, int tweenType, float[] returnValues) {
		switch(tweenType){
		case Zoom:
			returnValues[0] = target.zoom;
			return 1;
		case XY:
			returnValues[0] = target.position.x ;
			returnValues[1] = target.position.y ;
			return 2;
		case ROTATION:
			returnValues[0] = target.getRotate();
			return 1;
		default: assert false; return -1;
		}
	}

	@Override
	public void setValues(C2dCamera target, int tweenType, float[] newValues) {
		switch(tweenType){
		case Zoom:
			target.zoom = newValues[0];
			break;
		case XY:
			target.position.x = newValues[0];
			target.position.y = newValues[1];
			break;
		case ROTATION:
			target.setRotate(newValues[0]);
			break;
		default: assert false;
		}
	}

}