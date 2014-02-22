package info.u250.c2d.engine;

import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Vector3;
/**
 * @author lycying@gmail.com
 */
public class Flip3DCamera extends PerspectiveCamera {
	private float angleX = 0;
	private float angleY = 0;
	private float angleZ = 0;
	
	private Vector3 lookAt = new Vector3();
	
	private Vector3 axisX = new Vector3(1,0,0);
	private Vector3 axisY = new Vector3(0,1,0);
	private Vector3 axisZ = new Vector3(0,0,1);
	
	public Flip3DCamera(float width,float height){
		this.viewportWidth = width;
		this.viewportHeight = height;
		this.position.set(width/2, height/2,height/2);
		this.near = 0;
		this.far = height;
		this.lookAt.set(width/2,height/2,0);
		this.lookAt(lookAt.x, lookAt.y, lookAt.z);
		this.fieldOfView = 90;//not 67 now
	}
	
	public void resize(float width,float height){
		this.viewportWidth = width;
		this.viewportHeight = height;
		this.position.set(width/2, height/2,height/2);
		this.near = 1;
		this.far = height;
		this.lookAt.set(width/2,height/2,0);
		this.lookAt(lookAt.x, lookAt.y, lookAt.z);
		this.fieldOfView = 90;//not 67 now
		
		final float zoom = this.getZoom();
		this.setZoom(zoom);
	}
	
	public void setZoom(float zoom){
		this.position.z = this.viewportHeight/2*zoom;
	}
	public float getZoom(){
		return  this.position.z/(this.viewportHeight/2);
	}
	
	public float getAngleX() {
		return angleX;
	}
	public void setAngleX(float angleX) {
		this.rotateAround(lookAt, axisX , angleX-this.angleX);
		this.angleX = angleX;
	}
	public float getAngleY() {
		return angleY;
	}
	public void setAngleY(float angleY) {
		this.rotateAround(lookAt, axisY , angleY-this.angleY);
		this.angleY = angleY;
	}
	public float getAngleZ() {
		return angleZ;
	}
	public void setAngleZ(float angleZ) {	
		this.rotateAround(lookAt, axisZ , angleZ-this.angleZ);
		this.angleZ = angleZ;
	}

}
