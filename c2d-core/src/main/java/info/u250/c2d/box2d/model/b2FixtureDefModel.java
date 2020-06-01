package info.u250.c2d.box2d.model;


public abstract class b2FixtureDefModel extends b2Model{
	private static final long serialVersionUID = 1L;

	/** The friction coefficient, usually in the range [0,1]. **/
	public float friction = 0.2f;

	/** The restitution (elasticity) usually in the range [0,1]. **/
	public float restitution = 0;

	/** The density, usually in kg/m^2. **/
	public float density = 1;

	/** A sensor shape collects contact information but never generates a collision response. */
	public boolean isSensor = false;

	/** Contact filtering data. **/
	
	/** The collision category bits. Normally you would just set one bit. */
	public short categoryBits = 0x0001;

	/** The collision mask bits. This states the categories that this shape would accept for collision. */
	public short maskBits = -1;

	/** Collision groups allow a certain group of objects to never collide (negative) or always collide (positive). Zero means no
	 * collision group. Non-zero group filtering always wins against the mask bits. */
	public short groupIndex = 0;

	
}
