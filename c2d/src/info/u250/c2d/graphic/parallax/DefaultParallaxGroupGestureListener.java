package info.u250.c2d.graphic.parallax;

import info.u250.c2d.graphic.parallax.ParallaxGroup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class DefaultParallaxGroupGestureListener implements ParallaxGroupGestureListener {
	float velX, velY;
	boolean flinging = false;
	float initialScale = 1;
	ParallaxGroup parallaxGroup;

	public DefaultParallaxGroupGestureListener(ParallaxGroup parallaxGroup) {
		this.parallaxGroup = parallaxGroup;
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		flinging = false;
		initialScale = 1;
		return false;
	}
	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {

		parallaxGroup.getResult().speedTracker.add(
				-deltaX * parallaxGroup.getScaleX(), deltaY
						* parallaxGroup.getScaleY());

		return false;
	}

	@Override
	public boolean zoom(float originalDistance, float currentDistance) {

		float ratio = originalDistance / currentDistance;
		parallaxGroup.setScale( initialScale * ratio );
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialFirstPointer,
			Vector2 initialSecondPointer, Vector2 firstPointer,
			Vector2 secondPointer) {
		return false;
	}

	@Override
	public void update() {

		if (flinging) {
			velX *= 0.98f;
			velY *= 0.98f;
			parallaxGroup.getResult().speedTracker.add(-velX * Gdx.graphics.getDeltaTime(), velY
					* Gdx.graphics.getDeltaTime());
			if (Math.abs(velX) < 0.01f)
				velX = 0;
			if (Math.abs(velY) < 0.01f)
				velY = 0;
		}

	}


	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		flinging = true;
		velX = parallaxGroup.getScaleX() * velocityX * 0.5f;
		velY = parallaxGroup.getScaleY() * velocityY * 0.5f;
		return false;
	}
	@Override
	public boolean tap(float x, float y, int count, int button) {
		return false;
	}
	@Override
	public boolean longPress(float x, float y) {
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		return false;
	}
}
