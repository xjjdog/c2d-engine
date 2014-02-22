package info.u250.c2d.box2d.model;

import java.util.ArrayList;
import java.util.List;
/**
 * An exmaple that how to render the model
 * <pre>
 void setup(b2Scene model,Stage stage){
		for(b2BodyDefModel body:model.bodyDefModels){
			DefaultBuilder.buildBody(world, body);
		}
		for(b2JointDefModel joint:model.jointDefModels){
			DefaultBuilder.buildJoint(world, joint);
		}
		
	    for(b2BodyDefModel b2Body:model.bodyDefModels){
	        Box2dObject obj = new Box2dObject(b2Body);
	        for(b2FixtureDefModel b2Def:b2Body.fixtures){
	           if(b2Def instanceof b2CircleFixtureDefModel){
	              b2CircleFixtureDefModel tmp = (b2CircleFixtureDefModel)b2Def;
	              Image image = new Image(new TextureRegion(Engine.resource("Circle",Texture.class)));
	              image.setSize(tmp.radius*2, tmp.radius*2);
	              image.setOrigin(tmp.radius, tmp.radius);
	              image.setPosition(b2Body.drawableOffsetX-tmp.radius, b2Body.drawableOffsetY-tmp.radius);
	              image.setColor(generateColor());
	              obj.addActor(image);
	           }else if(b2Def instanceof b2RectangleFixtureDefModel){
	              b2RectangleFixtureDefModel tmp = (b2RectangleFixtureDefModel)b2Def;
	              Image image = new Image(new TextureRegion(Engine.resource("Box",Texture.class)));
	              image.setSize(tmp.width, tmp.height);
	              image.setOrigin(tmp.width/2, tmp.height/2);
	              image.setPosition(b2Body.drawableOffsetX-tmp.width/2, b2Body.drawableOffsetY-tmp.height/2);
	              image.setColor(generateColor());
	              obj.addActor(image);
	           }else if(b2Def instanceof b2PolygonFixtureDefModel){
	              b2PolygonFixtureDefModel tmp = (b2PolygonFixtureDefModel)b2Def;
	              Vector2 lower = new Vector2();
	              Vector2 upper = new Vector2();
	              for(Vector2[] vv:tmp.vertices){
	                 for(Vector2 v:vv){
	                    lower.x = Math.min(lower.x, v.x);
	                    lower.y = Math.min(lower.y, v.y);
	                    upper.x = Math.max(upper.x, v.x);
	                    upper.y = Math.max(upper.y, v.y);
	                 }
	              }
	              PolygonActor actor = new PolygonActor(Engine.resource("Polygon",Texture.class), tmp.vertices,b2Body.drawableOffsetX,b2Body.drawableOffsetY);
	              actor.setPosition(b2Body.drawableOffsetX-lower.x,b2Body.drawableOffsetY-lower.y);
	              obj.addActor(actor);
	              meshs.add(actor);
	           }
	        }
	        stage.addActor(obj);
	    }
	}
 * </pre>
 * 
 * @author lycying@gmail.com
 *
 */
public class b2Scene implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	public List<b2FixtureDefModel> fixtureDefModels = new ArrayList<b2FixtureDefModel>();
	public List<b2BodyDefModel> bodyDefModels = new ArrayList<b2BodyDefModel>();
	public List<b2JointDefModel> jointDefModels = new ArrayList<b2JointDefModel>();
}
