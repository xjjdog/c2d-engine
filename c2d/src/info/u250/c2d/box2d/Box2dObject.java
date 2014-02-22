package info.u250.c2d.box2d;

import info.u250.c2d.box2d.model.b2BodyDefModel;
import info.u250.c2d.box2d.model.fixture.b2CircleFixtureDefModel;
import info.u250.c2d.box2d.model.fixture.b2RectangleFixtureDefModel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
/**
 * everything follow the Actor's design so position is at (0,0)
 */
public class Box2dObject extends Group{
	public static float RADIO = 32.00000000f;
	/**
	 * The default restitution of the rigid body
	 * @see com.badlogic.gdx.physics.box2d.FixtureDef#restitution
	 */
	public static final float DEFAULT_restitution = 0.3f;
	/**
	 * The default angularDamping of the rigid body
	 * @see com.badlogic.gdx.physics.box2d.BodyDef#angularDamping
	 */
	public static final float DEFAULT_angularDamping = (float) (Math.PI / 2);
	/**
	 * The default linerDamping of the rigid body
	 * @see com.badlogic.gdx.physics.box2d.BodyDef#linearDamping
	 */
	public static final float DEFAULT_linearDamping = 0.3f;
	/**
	 * The default Friction of the rigid body
	 * @see com.badlogic.gdx.physics.box2d.FixtureDef#friction
	 */
	public static final float DEFAULT_friction = 0.4f;
	/**
	 * The default density of the rigid body 
	 * @see com.badlogic.gdx.physics.box2d.FixtureDef#density
	 */
	public static final float DEFAULT_density = 1f;
	
	/**
	 * the world body , call it directly . its not null 
	 */
	private b2BodyDefModel model ;
	public b2BodyDefModel getModel() {
		return model;
	}
	
	public Box2dObject(b2BodyDefModel model){
		this.model = model;
		this.init();
	}
	public Box2dObject(float width,float height){
		this.model = new b2BodyDefModel();
		b2RectangleFixtureDefModel box = new b2RectangleFixtureDefModel();
		box.width = width;
		box.height= height;
		this.model.fixtures.add(box);
		this.model.drawableWidth = width;
		this.model.drawableHeight= height;
		this.model.drawableOffsetX = width/2;
		this.model.drawableOffsetY = height/2;
		this.init();
	}
	public Box2dObject(float radius){
		this.model = new b2BodyDefModel();
		b2CircleFixtureDefModel circle = new b2CircleFixtureDefModel();
		circle.radius = radius;
		this.model.fixtures.add(circle);
		this.model.drawableWidth = radius*2;
		this.model.drawableHeight= radius*2;
		this.model.drawableOffsetX = radius;
		this.model.drawableOffsetY = radius;
		this.init();
	}
	protected void init(){
		if(null == this.model) {
			Gdx.app.error("C2d", "the model must be not null");
			System.exit(0);
		}
		this.setSize(model.drawableWidth, model.drawableHeight);
		this.setPosition(model.position.x-model.drawableOffsetX, model.position.y-model.drawableOffsetY);
		this.setOrigin(model.drawableOffsetX, model.drawableOffsetY);
		this.setRotation(model.degrees);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		final Vector2  position = model.body.getPosition();
		this.setPosition(position.x*RADIO -model.drawableOffsetX,position.y*RADIO -model.drawableOffsetY);
		this.setRotation(MathUtils.radiansToDegrees * model.body.getAngle());
		super.draw(batch, parentAlpha);
	}
	/**
	 * set the position use the camera's viewPort . the zero-zero is on the left bottom 
	 */
	public void setPosition(final float x,final float y){
		super.setPosition(x, y);
		model.body.setTransform(new Vector2(x,y).add(model.drawableOffsetX,model.drawableOffsetY).scl(1/RADIO), model.body.getAngle());
	}
	
	public void setRotation(float degrees){
		super.setRotation(degrees);
		model.body.setTransform(model.body.getPosition(), degrees*MathUtils.degreesToRadians);
	}
	
	@Override
	public void addAction(Action action) {
		//NOT ALLOW
	}
}