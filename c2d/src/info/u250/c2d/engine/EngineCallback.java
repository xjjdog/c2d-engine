package info.u250.c2d.engine;

import com.badlogic.gdx.Graphics.DisplayMode;

/**
 * How to load the game resources . 
 * If you supply different resources of diff resolution ratio , You can load them here .
 * @see DefaultEngineCallback 
 */
public interface EngineCallback {
	void preLoad(DisplayMode mode , String[] assets);
	void postLoad();
}
