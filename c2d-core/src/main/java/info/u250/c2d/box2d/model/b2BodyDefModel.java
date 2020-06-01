package info.u250.c2d.box2d.model;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class b2BodyDefModel extends b2Model{
	private static final long serialVersionUID = 1L;
	
	/** The body type: static, kinematic, or dynamic. Note: if a dynamic body would have zero mass, the mass is set to one. **/
	public int type = 2;

	/** The world position of the body. Avoid creating bodies at the origin since this can lead to many overlapping shapes. **/
	public final Vector2 position = new Vector2(200,200);

	/** The world angle of the body in degrees. **/
	public float degrees = 0;

	/** The linear velocity of the body's origin in world co-ordinates. **/
	public final Vector2 linearVelocity = new Vector2();

	/** The angular velocity of the body. **/
	public float angularVelocity = 0;

	/** Linear damping is use to reduce the linear velocity. The damping parameter can be larger than 1.0f but the damping effect
	 * becomes sensitive to the time step when the damping parameter is large. **/
	public float linearDamping = 0;

	/** Angular damping is use to reduce the angular velocity. The damping parameter can be larger than 1.0f but the damping effect
	 * becomes sensitive to the time step when the damping parameter is large. **/
	public float angularDamping = 0;

	/** Set this flag to false if this body should never fall asleep. Note that this increases CPU usage. **/
	public boolean allowSleep = true;

	/** Is this body initially awake or sleeping? **/
	public boolean awake = true;

	/** Should this body be prevented from rotating? Useful for characters. **/
	public boolean fixedRotation = false;

	/** Is this a fast moving body that should be prevented from tunneling through other moving bodies? Note that all bodies are
	 * prevented from tunneling through kinematic and static bodies. This setting is only considered on dynamic bodies.
	 * @warning You should use this flag sparingly since it increases processing time. **/
	public boolean bullet = false;

	/** Does this body start out active? **/
	public boolean active = true;

	/** Scale the gravity applied to this body. **/
	public float gravityScale = 1;
	
	/** All fixtures */
	public final List<b2FixtureDefModel> fixtures = new ArrayList<b2FixtureDefModel>();
	
	public transient Body body = null;
	
	

	/** bellow is for texture Drawable , this is our custom attributes to avoid runtime calculate*/
	public float drawableOffsetX = 0;//is lower than zero
	public float drawableOffsetY = 0;//is lower than zero
	public float drawableWidth = 100;
	public float drawableHeight= 100;
}
