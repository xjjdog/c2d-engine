package info.u250.c2d.box2deditor.adapter;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;

import info.u250.c2d.box2d.model.fixture.b2PolygonFixtureDefModel;

public class PolygonFixtureDefModel extends b2PolygonFixtureDefModel {
	private static final long serialVersionUID = 1L;
	/** this is all the vertices that after not be split yet 
	 *  We use this to adapter the editor but will not be used outside the editor
	 *  use {@link b2PolygonFixtureDefModel#vertices} instead
	 * */
	public final List<Vector2> polygon = new ArrayList<Vector2>();
	public PolygonFixtureDefModel(){
		polygon.add(new Vector2(-50,-50));
		polygon.add(new Vector2(50,-50));
		polygon.add(new Vector2(0,50));
		
		Vector2[] vv = new Vector2[]{
				polygon.get(0),polygon.get(1),polygon.get(2)
		};
		
		this.vertices.add(vv);
	}
}
