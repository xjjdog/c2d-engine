package info.u250.c2d.box2d.model.fixture;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;

import info.u250.c2d.box2d.model.b2FixtureDefModel;


public  class b2PolygonFixtureDefModel extends b2FixtureDefModel{
	private static final long serialVersionUID = 1L;
	/** this is all the vertices that after be split yet */
	public final List<Vector2[]> vertices = new ArrayList<Vector2[]>();
}
