package info.u250.c2d.engine.events;


/**<p>Provides a way to register/unregister events and listeners to handle the those events.
 * Any accsess can be from {@link info.u250.c2d.engine.Engine#getEventManager() } 
 * We also have some core events {@link info.u250.c2d.engine.Engine.CoreEvents } to handle system events <br/>
 * If you want to register a new Event, it takes two steps:
 * <ul><li>Register it use {@link #register(String, EventListener)} , where the logic is in {@link EventListener#onEvent(Event)}</li>
 * <li>Fire the event when you want to make the event on use {@link #fire(String, Object)} , the event source can be null if you do not need this on the {@link EventListener}</li></ul></p>
 * @author lycying@gmail.com
 */
public interface EventManager{
	/**
	 * Registers a new event specifying the id and the object which generated the event
	 */
	void fire(String id, Object source);
	void fire(String id);

	/**
	 * Registers a new EventListener to listen the specified eventId.
	 */
	void register(String eventId, EventListener eventListener);

	/**
	 * Unregisters the specified EventListener from listening events with the specified eventId.
	 */
	void unregister(String eventId, EventListener eventListener);

	/**
	 * Unregisters the specified EventListener from listening all events it was registered for.
	 */
	void unregister(EventListener listener);

	/**
	 * Process all events from registered using the EventManager interface.
	 */
	void update(float delta);

}