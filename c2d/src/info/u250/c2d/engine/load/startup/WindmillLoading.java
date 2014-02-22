package info.u250.c2d.engine.load.startup;

import info.u250.c2d.engine.Engine;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
/**
 * WindmillLoading
 * @author lycying@gmail.com
 */
public class WindmillLoading extends StartupLoading{
	
	@Override
	public void finishLoadingCleanup() {
		
	}
	float deltaAppend  = 0f;
	@Override
	protected void inLoadingRender(float delta) {
		deltaAppend+=delta;
		Engine.getShapeRenderer().identity();
		Engine.getShapeRenderer().translate(Engine.getWidth()/2, Engine.getHeight()/2, 0);
		Engine.getShapeRenderer().rotate(0, 0, 1, deltaAppend*50);
		Engine.getShapeRenderer().setProjectionMatrix(Engine.getDefaultCamera().combined);
		Engine.getShapeRenderer().setColor(Color.YELLOW);
		Engine.getShapeRenderer().begin(ShapeType.Filled);
		Engine.getShapeRenderer().triangle(
				0,0,
				-50,	100, 
				50, 	100);
		Engine.getShapeRenderer().end();
		Engine.getShapeRenderer().setColor(Color.RED);
		Engine.getShapeRenderer().begin(ShapeType.Filled);
		Engine.getShapeRenderer().triangle(
				0,0,
				-50,	-100, 
				50, 	-100);
		Engine.getShapeRenderer().end();
		Engine.getShapeRenderer().setColor(Color.BLUE);
		Engine.getShapeRenderer().begin(ShapeType.Filled);
		Engine.getShapeRenderer().triangle(
				0,0,
				-100,	-50, 
				-100, 	50);
		Engine.getShapeRenderer().end();
		Engine.getShapeRenderer().setColor(Color.GREEN);
		Engine.getShapeRenderer().begin(ShapeType.Filled);
		Engine.getShapeRenderer().triangle(
				0,0,
				100,	-50, 
				100, 	50);
		Engine.getShapeRenderer().end();
		Engine.getShapeRenderer().setColor(Color.WHITE);
		Engine.getShapeRenderer().begin(ShapeType.Filled);
		Engine.getShapeRenderer().circle(0, 0, 15);
		Engine.getShapeRenderer().end();
		Engine.getShapeRenderer().setColor(Color.ORANGE);
		Engine.getShapeRenderer().begin(ShapeType.Filled);
		Engine.getShapeRenderer().circle(0, 0, 10);
		Engine.getShapeRenderer().end();
	}
}
