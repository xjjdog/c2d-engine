package info.u250.c2d.box2deditor.adapter;

import info.u250.c2d.box2d.model.joint.b2PrismaticJointDefModel;

import com.badlogic.gdx.math.Vector2;

public class PrismaticJointDefModel extends b2PrismaticJointDefModel {
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
	
	public boolean useABCenterLine = false;
}
