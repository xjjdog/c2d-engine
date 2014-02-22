package info.u250.c2d.box2d.model.joint;

import info.u250.c2d.box2d.model.b2JointDefModel;

import com.badlogic.gdx.math.Vector2;

public class b2RevoluteJointDefModel extends b2JointDefModel{
	private static final long serialVersionUID = 1L;
	
	/** The local anchor point relative to body1's origin. */
	public final Vector2 localAnchorA = new Vector2();

	/** The local anchor point relative to body2's origin. */
	public final Vector2 localAnchorB = new Vector2();;

	/** The body2 angle minus body1 angle in the reference state (Degrees). */
	public float referenceDegrees = 0;

	/** A flag to enable joint limits. */
	public boolean enableLimit = false;

	/** The lower angle for the joint limit (Degrees). */
	public float lowerDegrees = 0;

	/** The upper angle for the joint limit (Degrees). */
	public float upperDegrees = 0;

	/** A flag to enable the joint motor. */
	public boolean enableMotor = false;

	/** The desired motor speed. Usually in radians per second. */
	public float motorSpeed = 0;

	/** The maximum motor torque used to achieve the desired motor speed. Usually in N-m. */
	public float maxMotorTorque = 0;

}
