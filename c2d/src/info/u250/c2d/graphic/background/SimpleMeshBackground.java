package info.u250.c2d.graphic.background;


import info.u250.c2d.engine.Engine;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
/**
 * this is a simple background for test or publish use the shaperrender
 * @author lycying@gmail.com
 */
public class SimpleMeshBackground{
	Color color1, color2;
	public SimpleMeshBackground(){
		this(new Color(1, 1, 1, 1f),new Color(152f/255f, 181f/255f, 249f/255f, 1));
	}
	public SimpleMeshBackground(Color color1,Color color2){
		this.color1 = color1;
		this.color2 = color2;
	}
	public void render (float delta) {
		Engine.getShapeRenderer().setProjectionMatrix(Engine.getDefaultCamera().combined);
		Engine.getShapeRenderer().begin(ShapeType.Filled);
		Engine.getShapeRenderer().rect(0, 0, Engine.getWidth(), Engine.getHeight(), color1, color1, color2, color2);
		Engine.getShapeRenderer().end();
	}
}
