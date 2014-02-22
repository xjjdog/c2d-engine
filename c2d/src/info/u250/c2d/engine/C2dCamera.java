package info.u250.c2d.engine;

import com.badlogic.gdx.graphics.OrthographicCamera;
/**
 * @author lycying@gmail.com
 */
public class C2dCamera extends OrthographicCamera {
	private float rotate;
	
	public float getRotate() {
		return rotate;
	}

	public void setRotate(float rotate) {
		this.rotate = rotate;
	}

	public C2dCamera(float width,float height){
		super(width, height);
		this.position.set(width/2, height/2,0);
	}
	
	public void resize(float width,float height){
		this.viewportWidth = width;
		this.viewportHeight = height;
		this.position.set(width/2, height/2,0);
		this.update();
	}

}
