package info.u250.c2d.graphic.parallax;

import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;

/** the background gesture listener use the core {@link GestureDetector} , a {@link ParallaxGroupGestureListener#update()} is added */
public interface ParallaxGroupGestureListener extends GestureListener{
	public void update();
}
