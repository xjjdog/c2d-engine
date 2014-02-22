package info.u250.c2d.graphic;

import info.u250.c2d.engine.Engine;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
/**
 * the advance sprite to attach more effect 
 * functions:
 * <ul>
 *  <li>shadow </li>
 *  </ul>
 * @author lycying@gmail.com
 */
public class AdvanceSprite extends Sprite {
	private boolean visible = true;
	
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public AdvanceSprite (Texture texture) {
		super(texture);
	}
	public AdvanceSprite (TextureRegion region) {
		super(region);
	}
	public AdvanceSprite (Sprite sprite) {
		super(sprite);
	}
	
	
	public void render(float delta) {
		if(visible){
			this.draw(Engine.getSpriteBatch());
		}
		this.shadowUpdate(delta);
	}
	@Override
	public void draw(Batch spriteBatch) {
		if(visible){
			super.draw(spriteBatch);
			this.shadowRender(spriteBatch);
		}
	}
	@Override
	public void draw(Batch spriteBatch, float alphaModulation) {
		if(visible){
			super.draw(spriteBatch, alphaModulation);
			this.shadowRender(spriteBatch);
		}
	}
	
	private Sprite[] shadows ;
	private int shadowNumber = 12;
	private float shadowInterval = 0.01f;
	private float deltaAppender  = 0;
	private boolean shadow;
	public boolean isShadow() {
		return shadow;
	}
	public void setShadow(boolean shadow) {
		this.shadow = shadow;
	}
	public int getShadowNumber() {
		return shadowNumber;
	}
	public void setShadowNumber(int shadowNumber) {
		shadows = new Sprite[shadowNumber];
		for(int i=shadows.length-1;i>=0;i--){
			shadows[i] = new Sprite(this);
		}
		this.shadowNumber = shadowNumber;
	}
	public float getShadowInterval() {
		return shadowInterval;
	}
	public void setShadowInterval(float shadowInterval) {
		this.shadowInterval = shadowInterval;
	}
	private Array<Vector2> catchPoints = new Array<Vector2>();
	private void shadowUpdate(float delta){
		if(shadow){
			deltaAppender+= delta;
			if(deltaAppender>shadowInterval){
				deltaAppender = 0;
				if(catchPoints.size>=shadowNumber){
					catchPoints.removeIndex(0);
				}
				catchPoints.add(new Vector2(this.getX(),this.getY()));
			}
		}
	}
	private void shadowRender(Batch batch){
		if(shadow){
			for(int i=shadows.length-1;i>=0;i--){
				Sprite shadow = shadows[i];
				shadow.setSize(this.getWidth(), this.getHeight());
				shadow.setRotation(this.getRotation());
				if(catchPoints.size>i){
					final Vector2 tmp = catchPoints.get(i);
					//do not draw the static object
					if(tmp.dst(vTmp.set(this.getX(), this.getY()))>5){
						shadow.setPosition(tmp.x, tmp.y);
						shadow.draw(batch,(0.64f-0.36f/shadowNumber*(shadowNumber-i))*this.getColor().a );
					}
				}
			}
		}
	}
	private Vector2 vTmp = new Vector2();
	public AdvanceSprite enableShadow(){
		this.setShadow(true);
		this.setShadowNumber(12);
		this.setShadowInterval(0.01f);
		return this;
	}
	public AdvanceSprite disableShadow(){
		this.setShadow(false);
		this.shadows = null;
		this.catchPoints.clear();
		return this;
	}
	
	
	/**
	 * Used by {@info.u250.c2d.input.AnimationSpriteInputProcessManager} to ensure if this sprite is touched .
	 * @return true if touch it 
	 */
	public boolean isTouched(int x, int y, int pointer, int button) {
		if (this.getBoundingRectangle().contains(x, y)) {
			return true;
		}
		return false;
	}
}
