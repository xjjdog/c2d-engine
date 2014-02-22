package info.u250.c2d.box2deditor.gdx;

import info.u250.c2d.box2deditor.adapter.SceneModelAdapter;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class PhysicalWorld {
	public static  World WORLD = new World(new Vector2(0,-10),false);
	public static  SceneModelAdapter MODEL = new SceneModelAdapter();
}
