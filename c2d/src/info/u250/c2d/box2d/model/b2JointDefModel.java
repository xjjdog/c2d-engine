package info.u250.c2d.box2d.model;

import com.badlogic.gdx.physics.box2d.Joint;


public abstract class b2JointDefModel extends b2Model {
	private static final long serialVersionUID = 1L;
	
	/** The first attached body. **/
	public b2BodyDefModel bodyA = null;

	/** The second attached body **/
	public b2BodyDefModel bodyB = null;

	/** Set this flag to true if the attached bodies should collide. **/
	public boolean collideConnected = false;
	
	public transient Joint joint = null ;
}
