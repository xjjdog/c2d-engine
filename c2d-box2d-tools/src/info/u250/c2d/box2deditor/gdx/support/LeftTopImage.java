package info.u250.c2d.box2deditor.gdx.support;

import info.u250.c2d.engine.Engine;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class LeftTopImage extends Image {
	public LeftTopImage (TextureRegion region) {
		super(region);
	}
	@Override
	public void act(float delta) {
		super.act(delta);
		this.setScale(Engine.getDefaultCamera().zoom);
		float tx = Engine.getDefaultCamera().position.x - Engine.getDefaultCamera().viewportWidth / 2 * Engine.getDefaultCamera().zoom ;
		float ty = Engine.getDefaultCamera().position.y + Engine.getDefaultCamera().viewportHeight / 2 * Engine.getDefaultCamera().zoom - this.getHeight()*this.getScaleX();
		this.setPosition(tx, ty);
	}
	Color color = new Color(1,1,154f/255f,1f);
	@Override
	public void draw(Batch batch, float parentAlpha) {
		ShapeRenderer render = Engine.getShapeRenderer();

		render.setColor(color);
		render.begin(ShapeType.Filled);
		render.rect(getX(), getY(), Engine.getWidth()*this.getScaleX(), this.getHeight()*this.getScaleX());
		render.end();
		
		super.draw(batch, parentAlpha);
	}
}
