package info.u250.c2d.engine.events;


/**
 * The event object include an id and a source . Which  can be any type .
 * @author lycying@gmail.com
 */
public class Event{
	protected String id;
	protected Object source;
	

	public void setSource(Object source) {
		this.source = source;
	}

	public Object getSource() {
		return source;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}

}