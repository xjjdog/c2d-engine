package info.u250.c2d.box2d.model.joint;

import info.u250.c2d.box2d.model.b2JointDefModel;

import com.badlogic.gdx.math.Vector2;


public class b2PrismaticJointDefModel extends b2JointDefModel{
	private static final long serialVersionUID = 1L;
	
	/** The local anchor point relative to body1's origin. */
	public final Vector2 localAnchorA = new Vector2();

	/** The local anchor point relative to body2's origin. */
	public final Vector2 localAnchorB = new Vector2();

	/** The local translation axis in body1. */
	public final Vector2 localAxisA = new Vector2(1, 0);

	/** The constrained degrees between the bodies: body2_angle - body1_angle. */
	public float referenceDegrees = 0;

	/** Enable/disable the joint limit. */
	public boolean enableLimit = false;

	/** The lower translation limit, usually in meters. */
	public float lowerTranslation = 0;

	/** The upper translation limit, usually in meters. */
	public float upperTranslation = 0;

	/** Enable/disable the joint motor. */
	public boolean enableMotor = false;

	/** The maximum motor torque, usually in N-m. */
	public float maxMotorForce = 0;

	/** The desired motor speed in radians per second. */
	public float motorSpeed = 0;
}
