package info.u250.c2d.box2d.model.joint;

import info.u250.c2d.box2d.model.b2JointDefModel;

import com.badlogic.gdx.math.Vector2;

public class b2MouseJointDefModel extends b2JointDefModel{
	private static final long serialVersionUID = 1L;
	/** The initial world target point. This is assumed to coincide with the body anchor initially. */
	public final Vector2 target = new Vector2();

	/** The maximum constraint force that can be exerted to move the candidate body. Usually you will express as some multiple of
	 * the weight (multiplier * mass * gravity). */
	public float maxForce = 0;

	/** The response speed. */
	public float frequencyHz = 5.0f;

	/** The damping ratio. 0 = no damping, 1 = critical damping. */
	public float dampingRatio = 0.7f;
}
