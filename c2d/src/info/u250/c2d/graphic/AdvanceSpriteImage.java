package info.u250.c2d.graphic;

import info.u250.c2d.engine.Engine;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class AdvanceSpriteImage extends Actor {
	final AdvanceSprite sprite ;
	public AdvanceSprite getSprite() {
		return sprite;
	}
	public AdvanceSpriteImage(AdvanceSprite sprite){
		this.sprite = sprite;
		this.setWidth(this.sprite.getWidth());
		this.setHeight( this.sprite.getHeight() );
	}
	@Override
	public void draw(Batch batch, float parentAlpha) {
		final Color color = getColor();
		this.sprite.setOrigin(this.getOriginX(), this.getOriginY());
		this.sprite.setColor(color);
		this.sprite.setScale(this.getScaleX(), this.getScaleY());
		this.sprite.setRotation(this.getRotation());
		this.sprite.setPosition(this.getX(), this.getY());
		this.sprite.render(Engine.getDeltaTime());
	}
}
