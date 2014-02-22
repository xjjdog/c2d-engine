package info.u250.c2d.box2deditor.adapter;

import info.u250.c2d.box2d.model.joint.b2RevoluteJointDefModel;

import com.badlogic.gdx.math.Vector2;

public class RevoluteJointDefModel extends b2RevoluteJointDefModel {
	private static final long serialVersionUID = 1L;
	/**
	 * The temp anchor attribute
	 */
	public Vector2 anchor = new Vector2();
	/**
	 * the convenient attributes 
	 */
	public boolean useBodyACenter = false;
	public boolean useBodyBCenter = false;
}
