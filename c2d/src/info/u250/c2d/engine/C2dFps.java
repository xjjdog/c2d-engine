package info.u250.c2d.engine;

import info.u250.c2d.engine.Engine;

import com.badlogic.gdx.Gdx;

/**The FPS label was shown on top of all the scenes . Which show the performance of your game
 * @author lycying@gmail.com*/
class C2dFps{
	
	public void render(float delta){
		String text = 
				"FPS:"+ Math.min( Gdx.graphics.getFramesPerSecond(), 60)
				+"\nJHeap:"+Gdx.app.getJavaHeap()/1024/1204+"M"
				+"\nNHeap:"+Gdx.app.getNativeHeap()/1024/1024+"M";
		Engine.getSpriteBatch().begin();
		//don not follow the main camera . 
		Engine.getDefaultFont().drawMultiLine(
				Engine.getSpriteBatch(), text, 
				Engine.getWidth()-120 + Engine.getDefaultCamera().position.x - Engine.getWidth()/2 ,
				60f + Engine.getDefaultCamera().position.y - Engine.getHeight()/2);
		Engine.getSpriteBatch().end();
	}
}
