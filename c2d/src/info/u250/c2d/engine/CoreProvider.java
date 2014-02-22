package info.u250.c2d.engine;


/**
 * the core provider give some static access attributes that need by the engine .
 * it director the appearance of the UI and the action of the functions .
 * but not all attributes is needed so you can choose what you want to control the logic 
 * of the game .
 * @author lycying@gmail.com 
 */
public abstract interface CoreProvider {
	/**
	 * The core events of the engine 
	 * @author lycying@gmail.com
	 */
	public static final class CoreEvents {
		/** the system will fire this event if you pause the game */
		public static final String SystemPause = "__Event_SystemPause";
		/** the system will fire this event if your resume method is called */
		public static final String SystemResume = "__Event_SystemResume";
	}
	/** the core transition type of two scene 
	 * @author lycying@gmail.com*/
	public enum TransitionType{
		/** split the first scene rows and move them out , finally show the second scene */
		SplitRows,
		/** split the first scene cols and move them out , finally show the second scene */
		SplitCols,
		/** the first scene zoom out , and then the second scene zoom in */
		ZoomOut,
		/** Transition Scene Flip Horizontal */
		FlipHorizontal,
		/** Transition Scene Flip Vertical */
		FlipVertical,
		/** the first scene zoom in , and then the second scene zoom out */
		ZoomIn,
		/**Rotate*/
		Rotate,
		/**Rotate With ZoomIn*/
		RotateWithZoomIn,
		/** the first scene fade out , and then the second scene fade in */
		Fade,
		/**fade white*/
		FadeWhite,
		/** the scene slide in from left */
		SlideInLeft,
		/** the scene slide in from right */
		SlideInRight,
		/** the scene slide in from bottom */
		SlideInBottom,
		/** the scene slide in from top */
		SlideInTop,
		/** the scene move in from left */
		MoveInLeft,
		/** the scene move in from right */
		MoveInRight,
		/** the scene move in from top */
		MoveInTop,
		/** the scene move in from bottom */
		MoveInBottom,
		
	}
}
