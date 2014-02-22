package info.u250.c2d.graphic.parallax;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

/**
 * This is the layer of the  parallax-background
 * @author lycying@gmail.com
 */
public class ParallaxLayer extends Group{
	public Vector2 parallaxRatio = new Vector2();	
	public Vector2 padding = new Vector2();
	public Vector2 startPosition = new Vector2();
	private ParallaxGroup parallaxGroup;
	
	
	protected ParallaxLayer(ParallaxGroup parallaxGroup){
		this.parallaxGroup = parallaxGroup;
	}
	public ParallaxLayer(ParallaxGroup parallaxGroup,Actor defaultActor,Vector2 parallaxRatio,Vector2 padding,Vector2 startPosition){
		this.parallaxGroup = parallaxGroup;
		this.parallaxRatio.set(parallaxRatio);
		this.padding.set(padding);
		this.startPosition.set(startPosition);
		if(null!=defaultActor){
			this.addActor(defaultActor);
			this.setSize(defaultActor.getWidth(), defaultActor.getHeight());
		}
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		float currentX =  - parallaxGroup.getResult().speedTracker.x * parallaxRatio.x % ( this.getWidth() + padding.x) ;
		if(currentX>0){
			currentX -= ( this.getWidth() + padding.x);
		}
		do{
			this.setX(startPosition.x + currentX);
			float currentY =  - parallaxGroup.getResult().speedTracker.y * parallaxRatio.y % ( this.getHeight() + padding.y) ;
			if(currentY>0){
				currentY  -= ( this.getHeight() + padding.y);
			}
			do{
				this.setY(startPosition.y+currentY);
				super.draw(batch, parentAlpha);
				currentY += ( this.getHeight() + padding.y);
			}while(currentY < parallaxGroup.getHeight());
			currentX += ( this.getWidth() + padding.x);
		}while(currentX < parallaxGroup.getWidth() );
	}
}