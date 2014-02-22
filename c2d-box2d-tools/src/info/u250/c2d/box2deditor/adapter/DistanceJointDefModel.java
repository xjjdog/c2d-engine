package info.u250.c2d.box2deditor.adapter;

import info.u250.c2d.box2d.model.joint.b2DistanceJointDefModel;

public class DistanceJointDefModel extends b2DistanceJointDefModel {
	private static final long serialVersionUID = 1L;

	public boolean setBodyAZero = false;
	public boolean setBodyBZero = false;
	public boolean autoCalculateLength = true;
}
