package info.u250.c2d.engine.events;


import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
/**the event pool . to reuse all of the events 
 * @author lycying@gmail.com
 */
class InternalEventManager {
	private final Pool<Event> eventPool = new Pool<Event>(50) {
		@Override
		protected Event newObject() {
			return new Event();
		}
	};

	private Array<Event> eventList = new Array<Event>();

	public void registerEvent(String id, Object source) {
		Event event = eventPool.obtain();
		event.setSource(source);
		event.setId(id);
		eventList.add(event);
	}
	

	public void clear() {
		for (int i = 0; i < getEventCount(); i++)
			eventPool.free(eventList.get(i));
		eventList.clear();
	}
	

	public int getEventCount() {
		return eventList.size;
	}

	public Event getEvent(int index) {
		if (index < 0 || index >= eventList.size)
			return null;
		return eventList.get(index);
	}

}