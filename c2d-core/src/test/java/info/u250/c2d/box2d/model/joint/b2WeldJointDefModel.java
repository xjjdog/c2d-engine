package info.u250.c2d.box2d.model.joint;

import info.u250.c2d.box2d.model.b2JointDefModel;

import com.badlogic.gdx.math.Vector2;


public class b2WeldJointDefModel extends b2JointDefModel {
	private static final long serialVersionUID = 1L;

	// / The local anchor point relative to body1's origin.
	public final Vector2 localAnchorA = new Vector2();

	// / The local anchor point relative to body2's origin.
	public final Vector2 localAnchorB = new Vector2();

	// / The body2 angle minus body1 angle in the reference state (Degrees).
	public float referenceDegrees = 0;

}
