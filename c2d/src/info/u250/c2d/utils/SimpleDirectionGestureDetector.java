package info.u250.c2d.utils;

import com.badlogic.gdx.input.GestureDetector;
/**
 * @author lycying@gmail.com
 */
public class SimpleDirectionGestureDetector extends GestureDetector{
	public  interface DirectionListener{
		void onLeft();
		void onRight();
		void onUp();
		void onDown();
	}
	
	public SimpleDirectionGestureDetector(DirectionListener directionListener) {
		super(new DirectionGestureListener(directionListener));
	}
	private static class  DirectionGestureListener extends GestureAdapter {
		DirectionListener directionListener;
		public DirectionGestureListener(DirectionListener directionListener){
			this.directionListener = directionListener;
		}
		@Override
		public boolean fling(float velocityX, float velocityY, int button) {
			if(Math.abs(velocityX)>Math.abs(velocityY)){
				if(velocityX>0){
					directionListener.onRight();
				}else{
					directionListener.onLeft();
				}
			}else{
				if(velocityY>0){
					directionListener.onDown();
				}else{					
					directionListener.onUp();
				}
			}
			return super.fling(velocityX, velocityY, button);
		}
	}
}
