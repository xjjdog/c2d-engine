package info.u250.c2d.graphic;

import info.u250.c2d.engine.Engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

 /** 
 *  @author lycying@gmail.com
 *  
 */
public class FadeMask {
	private float transparency = 0.6f;
	private Color color = null;

	public Color getColor() {
		return color;
	}
	public float getTransparency() {
		return transparency;
	}
	public void setTransparency(float transparency) {
		this.transparency = transparency;
	}

	public FadeMask(Color color) {
		//bugfix : should make a new color instance instead use the orgi color such as Color.Black
		this.color = new Color(color);
	}

	public void render(float delta) {
		this.color.a = transparency;
		Gdx.gl.glEnable(GL10.GL_BLEND);
		Gdx.gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		Engine.getShapeRenderer().setProjectionMatrix(Engine.getDefaultCamera().combined);
		Engine.getShapeRenderer().begin(ShapeType.Filled);
		Engine.getShapeRenderer().setColor(color);
		Engine.getShapeRenderer().rect(0, 0, Engine.getWidth(), Engine.getHeight());
		Engine.getShapeRenderer().end();
		Gdx.gl.glDisable(GL10.GL_BLEND);
	}
}
