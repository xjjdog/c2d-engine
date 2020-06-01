package info.u250.c2d.engine.events;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.badlogic.gdx.utils.Array;

/**use a event map to handle all events .When you fire the event , this will iterator all values of the map and find the true type to fire ~
 * @author lycying@gmail.com
 */
public class EventManagerImpl implements EventManager {

	private final Map<String, Array<EventListener>> eventListeners = new HashMap<String, Array<EventListener>>();
	private final InternalEventManager eventManager = new InternalEventManager();

	@Override
	public void register(String eventId, EventListener listener) {
		Array<EventListener> listeners = getListenersForEvent(eventId);
		listeners.add(listener);
	}

	@Override
	public void unregister(String eventId, EventListener listener) {
		Array<EventListener> listeners = getListenersForEvent(eventId);
		listeners.removeValue(listener,true);
	}

	@Override
	public void unregister(EventListener listener) {
		Set<String> keySet = eventListeners.keySet();
		for (String key : keySet)
			eventListeners.get(key).removeValue(listener,true);
	}

	private Array<EventListener> getListenersForEvent(String event) {
		if (!eventListeners.containsKey(event))
			eventListeners.put(event, new Array<EventListener>());
		return eventListeners.get(event);
	}

	public void process(Event event) {
		Array<EventListener> listeners = eventListeners.get(event.getId());
		if (listeners == null){
			return;
		}
		for (int i = 0; i < listeners.size; i++){
			listeners.get(i).onEvent(event);
		}
	}
	
	@Override
	public void update(float delta) {
		for (int i = 0; i < eventManager.getEventCount(); i++) {
			Event event = eventManager.getEvent(i);
			process(event);
		}
		eventManager.clear();
	}

	@Override
	public void fire(String id, Object source) {
		eventManager.registerEvent(id, source);
	}

	@Override
	public void fire(String id) {
		eventManager.registerEvent(id, null);
	}
}