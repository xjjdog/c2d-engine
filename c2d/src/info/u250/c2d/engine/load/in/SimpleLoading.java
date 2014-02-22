package info.u250.c2d.engine.load.in;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.graphic.FadeMask;

import com.badlogic.gdx.graphics.Color;
/**Jus show the percent of the loading progress
 * @author lycying@gmail.com
 */
public class SimpleLoading extends InGameLoading{
	FadeMask mask;
	public SimpleLoading(){
		mask = new FadeMask(Color.WHITE);
		mask.setTransparency(0.4f);
	}
	@Override
	public void finishLoadingCleanup() {
	}
	@Override
	protected void inLoadingRender(float delta) {
		mask.render(delta);
		Engine.getSpriteBatch().begin();
		Engine.getDefaultFont().setColor(Color.WHITE);
		Engine.getDefaultFont().draw(Engine.getSpriteBatch(), "Loading "+(int)(100*this.percent())+"%", 100, 200);
		Engine.getSpriteBatch().end();
	}
}
