package info.u250.c2d.graphic.parallax;

import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;

/**
 *  This only make a group only via to the {@link SpriteParallaxLayerDrawable} .if you want to 
 * get full control , you can full access the background API.
 * manual install the layers 
 * <pre>
			final TextureAtlas bgAtlas = Engine.resource("bgAtlas",TextureAtlas.class);
			rbg = new ParallaxGroup(480, 320, new Vector2(50,100), false);
			rbg.addActor(new Image(new AdvanceSprite(bgAtlas.findRegion("bg") )));
			rbg.addActor(new ParallaxLayer(rbg,new Image(new AdvanceSprite(bgAtlas.findRegion("cloud") )), new Vector2(0.5f,0),new Vector2(0,1000), new Vector2(0,70)));
			rbg.addActor(new ParallaxLayer(rbg,new Image(new AdvanceSprite(bgAtlas.findRegion("front") )), new Vector2(1f,0),new Vector2(0,1000), new Vector2()));
 * </pre>
 *@author lycying@gmail.com
 */
public class ParallaxGroup extends Group{
	class EveryFrameResult{
		Vector2 speedTracker = new Vector2();
	}
	/**
	 * @param width	The screenWith 
	 * @param height The screenHeight
	 * @param speed A Vector2 attribute to point out the x and y speed
	 */
	public ParallaxGroup(float width,float height,Vector2 speed){
		this.speed.set(speed);
		this.setSize(width, height);
	}
	
	protected Vector2 speed = new Vector2();
	private GestureDetector gestureDetector;
	private ParallaxGroupGestureListener gestureListener;
	private EveryFrameResult result = new EveryFrameResult();
	
	public GestureDetector getGestureDetector(){
		return this.gestureDetector;
	}
	public void setDefaultGestureDetector(ParallaxGroupGestureListener gestureListener){
		this.gestureListener = gestureListener;
		gestureDetector = new GestureDetector(20, 0.5f, 2, 0.15f,gestureListener );
	}
	public void setGestureDetector(GestureDetector gestureDetector,ParallaxGroupGestureListener gestureListener){
		this.gestureDetector = gestureDetector;
		this.gestureListener= gestureListener;
	}
	/**
	 * get the full speed of the background , include the x and y axis 
	 */
	public Vector2 getSpeed(){
		return this.speed;
	}
	public Vector2 getSpeedTracker(){
		return result.speedTracker;
	}
	/**
	 * Dynamic change the speed 
	 */
	public void setSpeed(float xSpeed,float ySpeed){
		this.speed.set(xSpeed, ySpeed);
	}
	
	public EveryFrameResult getResult(){
		return result;
	}
	
	@Override
	public void act(float delta) {
		if(null!=this.gestureListener){
			this.gestureListener.update();
		}
		this.result.speedTracker.add(speed.x*delta,speed.y*delta);
		super.act(delta);
	}
}
