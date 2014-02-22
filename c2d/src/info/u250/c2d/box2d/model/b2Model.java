package info.u250.c2d.box2d.model;


public abstract class b2Model implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * This is the  name of the model , every model should have a name 
	 */
	public String name = "";
	/**
	 * The more information attached to the model . Such as how to draw the model 
	 */
	public String mark = "";
}
