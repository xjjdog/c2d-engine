package info.u250.c2d.box2d.model.joint;

import info.u250.c2d.box2d.model.b2JointDefModel;

import com.badlogic.gdx.math.Vector2;

public class b2WheelJointDefModel extends b2JointDefModel {
	private static final long serialVersionUID = 1L;
	
	/** The local anchor point relative to body1's origin. **/
	public final Vector2 localAnchorA = new Vector2();

	/** The local anchor point relative to body2's origin. **/
	public final Vector2 localAnchorB = new Vector2();

	/** The local translation axis in body1. **/
	public final Vector2 localAxisA = new Vector2(1, 0);

	/** Enable/disable the joint motor. **/
	public boolean enableMotor = false;

	/** The maximum motor torque, usually in N-m. */
	public float maxMotorTorque = 0;

	/** The desired motor speed in radians per second. */
	public float motorSpeed = 0;

	/** Suspension frequency, zero indicates no suspension */
	public float frequencyHz = 2;

	/** Suspension damping ratio, one indicates critical damping */
	public float dampingRatio = 0.7f;
}
