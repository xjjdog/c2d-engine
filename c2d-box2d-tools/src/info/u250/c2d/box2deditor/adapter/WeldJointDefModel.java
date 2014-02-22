package info.u250.c2d.box2deditor.adapter;

import info.u250.c2d.box2d.model.joint.b2WeldJointDefModel;

import com.badlogic.gdx.math.Vector2;

public class WeldJointDefModel extends b2WeldJointDefModel {
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
