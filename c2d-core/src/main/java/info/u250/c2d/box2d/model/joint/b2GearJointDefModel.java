package info.u250.c2d.box2d.model.joint;

import info.u250.c2d.box2d.model.b2JointDefModel;

import com.badlogic.gdx.physics.box2d.joints.GearJoint;


public class b2GearJointDefModel extends b2JointDefModel{
	private static final long serialVersionUID = 1L;
	/** The first revolute/prismatic joint attached to the gear joint. */
	public b2JointDefModel joint1 = null;

	/** The second revolute/prismatic joint attached to the gear joint. */
	public b2JointDefModel joint2 = null;

	/** The gear ratio.
	 * @see GearJoint for explanation. */
	public float ratio = 1;
}
