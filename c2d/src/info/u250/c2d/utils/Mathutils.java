package info.u250.c2d.utils;

import com.badlogic.gdx.math.Vector2;


public class Mathutils{
	/** if the three points is clock wise , if not , make it to be */
	public static  boolean isClockwise(Vector2 p1,Vector2 p2,Vector2 p3) { 
		return ((p1.x-p3.x)*(p2.y-p3.y)-(p1.y-p3.y)*(p2.x-p3.x))<0;
	}
}
