package info.u250.c2d.box2d.model.joint;

import info.u250.c2d.box2d.model.b2JointDefModel;

import com.badlogic.gdx.math.Vector2;

public class b2FrictionJointDefModel extends b2JointDefModel{
	private static final long serialVersionUID = 1L;
	/** The local anchor point relative to bodyA's origin. */
	public final Vector2 localAnchorA = new Vector2();

	/** The local anchor point relative to bodyB's origin. */
	public final Vector2 localAnchorB = new Vector2();

	/** The maximum friction force in N. */
	public float maxForce = 0;

	/** The maximum friction torque in N-m. */
	public float maxTorque = 0;
}
