package info.u250.c2d.accessors;

import info.u250.c2d.engine.Flip3DCamera;
import aurelienribon.tweenengine.TweenAccessor;

/** change some attributes of the camera 
 * @author lycying@gmail.com*/
public class Flip3DCameraAccessor implements TweenAccessor<Flip3DCamera>{
	public final static int Zoom = 1;
	public final static int XY = 2;
	public final static int ROTATION_Z = 3;
	public final static int ROTATION_X = 4;
	public final static int ROTATION_Y = 5;
	
	@Override
	public int getValues(Flip3DCamera target, int tweenType, float[] returnValues) {
		switch(tweenType){
		case Zoom:
			returnValues[0] = target.getZoom();
			return 1;
		case XY:
			returnValues[0] = target.position.x ;
			returnValues[1] = target.position.y ;
			return 2;
		case ROTATION_Z:
			returnValues[0] = target.getAngleZ();
			return 1;
		case ROTATION_X:
			returnValues[0] = target.getAngleX();
			return 1;
		case ROTATION_Y:
			returnValues[0] = target.getAngleY();
			return 1;
		default: assert false; return -1;
		}
	}

	@Override
	public void setValues(Flip3DCamera target, int tweenType, float[] newValues) {
		switch(tweenType){
		case Zoom:
			target.setZoom(newValues[0]);
			break;
		case XY:
			target.position.x = newValues[0];
			target.position.y = newValues[1];
			break;
		case ROTATION_Z:
			target.setAngleZ(newValues[0]);
			break;
		case ROTATION_X:
			target.setAngleX(newValues[0]);
			break;
		case ROTATION_Y:
			target.setAngleY(newValues[0]);
			break;
		default: assert false;
		}
	}

}