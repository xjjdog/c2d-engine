package info.u250.c2d.box2deditor.adapter;

import info.u250.c2d.box2d.model.joint.b2PulleyJointDefModel;

public class PulleyJointDefModel extends b2PulleyJointDefModel {
	private static final long serialVersionUID = 1L;
	/**
	 * the convenient attributes 
	 */
	public boolean setBodyAZero = false;
	public boolean setBodyBZero = false;
	
	public boolean groundAAlignAnchorA = true;
	public boolean groundBAlignAnchorB = true;
}
