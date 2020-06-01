package info.u250.c2d.box2d.model.joint;

import info.u250.c2d.box2d.model.b2JointDefModel;

import com.badlogic.gdx.math.Vector2;

public class b2DistanceJointDefModel extends b2JointDefModel{
	private static final long serialVersionUID = 1L;
	/** The local anchor point relative to body1's origin. */
	public final Vector2 localAnchorA = new Vector2();

	/** The local anchor point relative to body2's origin. */
	public final Vector2 localAnchorB = new Vector2();

	/** The natural length between the anchor points. */
	public float length = 1;

	/** The mass-spring-damper frequency in Hertz. */
	public float frequencyHz = 0;

	/** The damping ratio. 0 = no damping, 1 = critical damping. */
	public float dampingRatio = 0;
}
