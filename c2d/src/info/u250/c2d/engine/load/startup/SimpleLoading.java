package info.u250.c2d.engine.load.startup;

import info.u250.c2d.engine.Engine;

import com.badlogic.gdx.graphics.Color;
/**Jus show the percent of the loading progress
 * @author lycying@gmail.com
 */
public class SimpleLoading extends StartupLoading{
	@Override
	public void finishLoadingCleanup() {
	}
	@Override
	protected void inLoadingRender(float delta) {
		Engine.getSpriteBatch().begin();
		Engine.getDefaultFont().setColor(Color.WHITE);
		Engine.getDefaultFont().draw(Engine.getSpriteBatch(), "Loading "+(int)(100*this.percent())+"%", 100, 200);
		Engine.getSpriteBatch().end();
	}
}
