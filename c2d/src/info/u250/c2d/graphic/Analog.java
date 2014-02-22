package info.u250.c2d.graphic;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.Scene;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/**
 * @author lycying@gmail.com
 */
public class Analog extends InputAdapter implements Scene{

	protected AdvanceSprite spriteBase ;
	protected AdvanceSprite spriteStick;
	
	boolean touchDown = false; // the single touch index currently acquired
	// in radians: 0 -> 2PI, -0 -> -2PI
	// equivalent degrees: 0 -> 180 and -0 -> -180
	float angle = 0;
	// (capped to 1, which is on the circle circumference boundary)
	float amount = 0;
	boolean sticky = false;
	float radius = 64;
	final Vector2 position = new Vector2();
	final Vector2 distance = new Vector2(0, 0);
	final Vector2 tmp = new Vector2();
	public Analog(AdvanceSprite spriteBase,AdvanceSprite spriteStick,Vector2 position){
		this.spriteBase = spriteBase;
		this.spriteStick = spriteStick;
		this.radius = spriteBase.getWidth()/2 ;
		this.position.set(position);
		this.spriteBase.setPosition(position.x, position.y);
	}
	
	public float getAngle() {
		return angle;
	}


	public float getAmount() {
		return amount;
	}
	void stick() {
		sticky = true;
	}

	void unstick() {
		sticky = false;
		if (!touchDown)
			reset();
	}

	
	void reset() {
		amount = 0;
		angle = 0;
	}
	
	boolean hitTest(float x, float y) {
		if(new Vector2(this.position).add(radius, radius).dst(new Vector2(x,y))<radius){
			this.stick();
			return true;
		}
		if(touchDown){
			this.stick();
			return false;
		}
		this.unstick();
		return false;
	}
	
	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		tmp.set(Engine.screenToWorld(x, y));
		if (hitTest(tmp.x, tmp.y)) {
			touchDown = true;
			touchDragged(x, y, pointer);
			return true;
		}
		return false;
	}
	@Override
	public boolean touchDragged(int x, int y, int index) {
		tmp.set(Engine.screenToWorld(x, y));
		if (touchDown) {
			distance.x = tmp.x - (position.x+radius);
			distance.y = tmp.y - (position.y+radius);
			angle = (float) (Math.atan2(distance.y, distance.x));
			
			amount = distance.len() / radius;

			if (amount > 1) {
				amount = 1;
			}
		}
		return hitTest(tmp.x, tmp.y);
	}
	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		tmp.set(Engine.screenToWorld(x, y));
		touchDown = false;
		unstick();
		return false;
	}
	
	@Override
	public void update(float delta) {
		
	}

	@Override
	public void render(float delta) {
		if (sticky) {
			final Color color = spriteBase.getColor();
			color.a = .5f;
			spriteBase.setColor(color);
		} else {
			final Color color = spriteBase.getColor();
			color.a = 1f;
			spriteBase.setColor(color);
		}
		float lenX = amount * radius * (float) Math.cos(angle) ;
		float lenY = amount * radius * (float) Math.sin(angle) ;
		
		this.spriteStick.setPosition(
				lenX + (this.position.x + (this.spriteBase.getWidth() - this.spriteStick.getWidth())/2),
				lenY + (this.position.y + (this.spriteBase.getHeight() - this.spriteStick.getHeight())/2));
		
		this.spriteBase.render(delta);
		this.spriteStick.render(delta);
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {}

	@Override
	public InputProcessor getInputProcessor() {
		return this;
	}
	
}
