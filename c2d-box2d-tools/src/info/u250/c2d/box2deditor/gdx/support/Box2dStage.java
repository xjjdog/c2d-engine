package info.u250.c2d.box2deditor.gdx.support;

import info.u250.c2d.box2d.Box2dObject;
import info.u250.c2d.box2deditor.gdx.PhysicalWorld;
import info.u250.c2d.graphic.C2dStage;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
/**
 * One Instance At runtime . No More
 */
public class Box2dStage extends C2dStage implements com.badlogic.gdx.utils.Disposable{
	@Override
	public void act(float delta) {
		super.act(delta);
		if(PhysicalWorld.WORLD!=null){
			PhysicalWorld.WORLD.step(delta, 3, 3);
		}
	}
	public void dispose(){
		if(PhysicalWorld.WORLD!=null){
			PhysicalWorld.WORLD.dispose();
		}
	}
	
	public Body createScreenBox(final Rectangle rect){
		ChainShape shape  = new ChainShape();
		shape.createLoop(new Vector2[]{
				new Vector2(rect.x,rect.y).scl(1/Box2dObject.RADIO),
				new Vector2(rect.x+rect.width,rect.y).scl(1/Box2dObject.RADIO),
				new Vector2(rect.x+rect.width,rect.y+rect.height).scl(1/Box2dObject.RADIO),
				new Vector2(rect.x,rect.y+rect.height).scl(1/Box2dObject.RADIO),
		});
		
		final FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.isSensor = false;
		fixtureDef.shape = shape;
		
		BodyDef bodyDef = new BodyDef();
		bodyDef.bullet = false;
		bodyDef.type = BodyType.StaticBody;
		bodyDef.linearDamping = 0f;

		Body body = PhysicalWorld.WORLD.createBody(bodyDef);
		body.createFixture(fixtureDef);
		shape.dispose();
		return body;
	}
}