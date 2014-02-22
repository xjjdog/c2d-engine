package info.u250.c2d.box2d.model.joint;

import info.u250.c2d.box2d.model.b2JointDefModel;

import com.badlogic.gdx.math.Vector2;

public class b2PulleyJointDefModel extends b2JointDefModel{
	private static final long serialVersionUID = 1L;
	
	/** The first ground anchor in world coordinates. This point never moves. */
	public final Vector2 groundAnchorA = new Vector2(-1, 1);

	/** The second ground anchor in world coordinates. This point never moves. */
	public final Vector2 groundAnchorB = new Vector2(1, 1);

	/** The local anchor point relative to bodyA's origin. */
	public final Vector2 localAnchorA = new Vector2(-1, 0);

	/** The local anchor point relative to bodyB's origin. */
	public final Vector2 localAnchorB = new Vector2(1, 0);

	/** The a reference length for the segment attached to bodyA. */
	public float lengthA = 0;

	/** The a reference length for the segment attached to bodyB. */
	public float lengthB = 0;

	/** The pulley ratio, used to simulate a block-and-tackle. */
	public float ratio = 1;

}
