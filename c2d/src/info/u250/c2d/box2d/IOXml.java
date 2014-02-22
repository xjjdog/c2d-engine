package info.u250.c2d.box2d;

import info.u250.c2d.box2d.model.b2BodyDefModel;
import info.u250.c2d.box2d.model.b2FixtureDefModel;
import info.u250.c2d.box2d.model.b2Scene;
import info.u250.c2d.box2d.model.fixture.b2CircleFixtureDefModel;
import info.u250.c2d.box2d.model.fixture.b2PolygonFixtureDefModel;
import info.u250.c2d.box2d.model.fixture.b2RectangleFixtureDefModel;
import info.u250.c2d.box2d.model.joint.b2DistanceJointDefModel;
import info.u250.c2d.box2d.model.joint.b2FrictionJointDefModel;
import info.u250.c2d.box2d.model.joint.b2PrismaticJointDefModel;
import info.u250.c2d.box2d.model.joint.b2PulleyJointDefModel;
import info.u250.c2d.box2d.model.joint.b2RevoluteJointDefModel;
import info.u250.c2d.box2d.model.joint.b2RopeJointDefModel;
import info.u250.c2d.box2d.model.joint.b2WeldJointDefModel;
import info.u250.c2d.box2d.model.joint.b2WheelJointDefModel;

import java.io.IOException;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;
/**
 * @author lycying@gmail.com
 */
public class IOXml {
	public static b2Scene parse(FileHandle file) throws IOException{
		return parse(new XmlReader().parse(file));
	}
	private static b2Scene parse(Element root){
		b2Scene model = new b2Scene();
		Element eleFixtures = root.getChildByName("fixtures");
		for(int i=0;i<eleFixtures.getChildCount();i++){
			Element eleFixture = eleFixtures.getChild(i);
			String fixtureType = eleFixture.getName();
			if(fixtureType.equals("polygons")){
				b2PolygonFixtureDefModel fixture = new b2PolygonFixtureDefModel();
				fixture.friction = Float.parseFloat(eleFixture.getAttribute("friction"));
				fixture.restitution = Float.parseFloat(eleFixture.getAttribute("restitution"));
				fixture.density = Float.parseFloat(eleFixture.getAttribute("density"));
				fixture.isSensor = Boolean.parseBoolean(eleFixture.getAttribute("isSensor"));
				fixture.categoryBits = Short.parseShort(eleFixture.getAttribute("categoryBits"));
				fixture.maskBits = Short.parseShort(eleFixture.getAttribute("maskBits"));
				fixture.groupIndex = Short.parseShort(eleFixture.getAttribute("groupIndex"));
				fixture.name = eleFixture.getAttribute("name");
				fixture.mark = eleFixture.getAttribute("mark");
				/*
			<polygon>
				<vertex>{-16.0,3.0000114}</vertex>
				<vertex>{70.000015,16.000006}</vertex>
				<vertex>{80.0,77.0}</vertex>
			</polygon>
				 */
				for(int j=0;j<eleFixture.getChildCount();j++){
					Element elePolygon = eleFixture.getChild(j);
					int size = elePolygon.getChildCount();
					Vector2[] vertexs = new Vector2[size];
					for(int k=0;k<size;k++){
						vertexs[k] = str2Vector(elePolygon.getChild(k).getText());
					}
					fixture.vertices.add(vertexs);
				}
				model.fixtureDefModels.add(fixture);
			}else if(fixtureType.equals("box")){
				b2RectangleFixtureDefModel fixture = new b2RectangleFixtureDefModel();
				fixture.friction = Float.parseFloat(eleFixture.getAttribute("friction"));
				fixture.restitution = Float.parseFloat(eleFixture.getAttribute("restitution"));
				fixture.density = Float.parseFloat(eleFixture.getAttribute("density"));
				fixture.isSensor = Boolean.parseBoolean(eleFixture.getAttribute("isSensor"));
				fixture.categoryBits = Short.parseShort(eleFixture.getAttribute("categoryBits"));
				fixture.maskBits = Short.parseShort(eleFixture.getAttribute("maskBits"));
				fixture.groupIndex = Short.parseShort(eleFixture.getAttribute("groupIndex"));
				fixture.name = eleFixture.getAttribute("name");
				fixture.mark = eleFixture.getAttribute("mark");
				
				fixture.width = Float.parseFloat(eleFixture.getAttribute("width"));
				fixture.height = Float.parseFloat(eleFixture.getAttribute("height"));
				model.fixtureDefModels.add(fixture);
			}else if(fixtureType.equals("circle")){
				b2CircleFixtureDefModel fixture = new b2CircleFixtureDefModel();
				fixture.friction = Float.parseFloat(eleFixture.getAttribute("friction"));
				fixture.restitution = Float.parseFloat(eleFixture.getAttribute("restitution"));
				fixture.density = Float.parseFloat(eleFixture.getAttribute("density"));
				fixture.isSensor = Boolean.parseBoolean(eleFixture.getAttribute("isSensor"));
				fixture.categoryBits = Short.parseShort(eleFixture.getAttribute("categoryBits"));
				fixture.maskBits = Short.parseShort(eleFixture.getAttribute("maskBits"));
				fixture.groupIndex = Short.parseShort(eleFixture.getAttribute("groupIndex"));
				fixture.name = eleFixture.getAttribute("name");
				fixture.mark = eleFixture.getAttribute("mark");
				
				fixture.radius = Float.parseFloat(eleFixture.getAttribute("radius"));
				model.fixtureDefModels.add(fixture);
			}
		}
		
		Element eleBodys = root.getChildByName("bodys");
		for(int i=0;i<eleBodys.getChildCount();i++){
			Element eleBody = eleBodys.getChild(i);
			b2BodyDefModel body = new b2BodyDefModel();
			String bType = eleBody.getAttribute("type");
			body.name = eleBody.getAttribute("name");
			body.type = bType.equals("StaticBody")?0:(bType.equals("KinematicBody")?1:2);
			body.position.set(str2Vector(eleBody.getAttribute("position")));
			body.degrees = Float.parseFloat(eleBody.getAttribute("degrees"));
			body.linearVelocity.set(str2Vector(eleBody.getAttribute("linearVelocity")));
			body.angularVelocity = Float.parseFloat(eleBody.getAttribute("angularVelocity"));
			body.linearDamping = Float.parseFloat(eleBody.getAttribute("linearDamping"));
			body.angularDamping = Float.parseFloat(eleBody.getAttribute("angularDamping"));
			body.allowSleep = Boolean.parseBoolean(eleBody.getAttribute("allowSleep"));
			body.awake = Boolean.parseBoolean(eleBody.getAttribute("awake"));
			body.fixedRotation = Boolean.parseBoolean(eleBody.getAttribute("fixedRotation"));
			body.bullet = Boolean.parseBoolean(eleBody.getAttribute("bullet"));
			body.active = Boolean.parseBoolean(eleBody.getAttribute("active"));
			body.gravityScale = Float.parseFloat(eleBody.getAttribute("gravityScale"));
			body.drawableOffsetX = Float.parseFloat(eleBody.getAttribute("drawableOffsetX"));
			body.drawableOffsetY = Float.parseFloat(eleBody.getAttribute("drawableOffsetY"));
			body.drawableWidth = Float.parseFloat(eleBody.getAttribute("drawableWidth"));
			body.drawableHeight = Float.parseFloat(eleBody.getAttribute("drawableHeight"));
			body.mark = eleBody.getAttribute("mark");
			for(int j=0;j<eleBody.getChildCount();j++){
				b2FixtureDefModel ref = refFixture(model,eleBody.getChild(j).getAttribute("ref"));
				if(null!=ref)body.fixtures.add(ref);
			}
			model.bodyDefModels.add(body);
		}
		
		Element eleJoints = root.getChildByName("joints");
		for(int i=0;i<eleJoints.getChildCount();i++){
			Element eleJoint = eleJoints.getChild(i);
			String jointType = eleJoint.getAttribute("type");
			if(jointType.equals("DistanceJoint")){
				b2DistanceJointDefModel def = new b2DistanceJointDefModel();
				def.collideConnected = Boolean.parseBoolean(eleJoint.getAttribute("collideConnected"));
				def.bodyA = refBody(model, eleJoint.getAttribute("bodyA"));
				def.bodyB = refBody(model, eleJoint.getAttribute("bodyB"));
				def.mark = eleJoint.get("mark");
				def.name = eleJoint.get("name");
				
				def.length = Float.parseFloat(eleJoint.getAttribute("length"));
				def.frequencyHz = Float.parseFloat(eleJoint.getAttribute("frequencyHz"));
				def.dampingRatio = Float.parseFloat(eleJoint.getAttribute("dampingRatio"));
				def.localAnchorA.set(str2Vector(eleJoint.getAttribute("localAnchorA")));
				def.localAnchorB.set(str2Vector(eleJoint.getAttribute("localAnchorB")));
				
				model.jointDefModels.add(def);
			}else if(jointType.equals("FrictionJoint")){
				b2FrictionJointDefModel def = new b2FrictionJointDefModel();
				def.collideConnected = Boolean.parseBoolean(eleJoint.getAttribute("collideConnected"));
				def.bodyA = refBody(model, eleJoint.getAttribute("bodyA"));
				def.bodyB = refBody(model, eleJoint.getAttribute("bodyB"));
				def.mark = eleJoint.get("mark");
				def.name = eleJoint.get("name");
				
				def.maxForce = Float.parseFloat(eleJoint.getAttribute("maxForce"));
				def.maxTorque = Float.parseFloat(eleJoint.getAttribute("maxTorque"));
				def.localAnchorA.set(str2Vector(eleJoint.getAttribute("localAnchorA")));
				def.localAnchorB.set(str2Vector(eleJoint.getAttribute("localAnchorB")));
				
				model.jointDefModels.add(def);
			}else if(jointType.equals("PrismaticJoint")){
				b2PrismaticJointDefModel def = new b2PrismaticJointDefModel();
				def.collideConnected = Boolean.parseBoolean(eleJoint.getAttribute("collideConnected"));
				def.bodyA = refBody(model, eleJoint.getAttribute("bodyA"));
				def.bodyB = refBody(model, eleJoint.getAttribute("bodyB"));
				def.mark = eleJoint.get("mark");
				def.name = eleJoint.get("name");
				
				def.referenceDegrees = Float.parseFloat(eleJoint.getAttribute("referenceDegrees"));
				def.enableLimit = Boolean.parseBoolean(eleJoint.getAttribute("enableLimit"));
				def.enableMotor = Boolean.parseBoolean(eleJoint.getAttribute("enableMotor"));
				def.localAnchorA.set(str2Vector(eleJoint.getAttribute("localAnchorA")));
				def.localAnchorB.set(str2Vector(eleJoint.getAttribute("localAnchorB")));
				def.localAxisA.set(str2Vector(eleJoint.getAttribute("localAxisA")));
				def.motorSpeed = Float.parseFloat(eleJoint.getAttribute("motorSpeed"));
				def.maxMotorForce = Float.parseFloat(eleJoint.getAttribute("maxMotorForce"));
				def.upperTranslation = Float.parseFloat(eleJoint.getAttribute("upperTranslation"));
				def.lowerTranslation = Float.parseFloat(eleJoint.getAttribute("lowerTranslation"));
				
				model.jointDefModels.add(def);
			}else if(jointType.equals("RevoluteJoint")){
				b2RevoluteJointDefModel def = new b2RevoluteJointDefModel();
				def.collideConnected = Boolean.parseBoolean(eleJoint.getAttribute("collideConnected"));
				def.bodyA = refBody(model, eleJoint.getAttribute("bodyA"));
				def.bodyB = refBody(model, eleJoint.getAttribute("bodyB"));
				def.mark = eleJoint.get("mark");
				def.name = eleJoint.get("name");
				
				def.referenceDegrees = Float.parseFloat(eleJoint.getAttribute("referenceDegrees"));
				def.enableLimit = Boolean.parseBoolean(eleJoint.getAttribute("enableLimit"));
				def.enableMotor = Boolean.parseBoolean(eleJoint.getAttribute("enableMotor"));
				def.localAnchorA.set(str2Vector(eleJoint.getAttribute("localAnchorA")));
				def.localAnchorB.set(str2Vector(eleJoint.getAttribute("localAnchorB")));
				def.upperDegrees = Float.parseFloat(eleJoint.getAttribute("upperDegrees"));
				def.lowerDegrees = Float.parseFloat(eleJoint.getAttribute("lowerDegrees"));
				def.motorSpeed = Float.parseFloat(eleJoint.getAttribute("motorSpeed"));
				def.maxMotorTorque = Float.parseFloat(eleJoint.getAttribute("maxMotorTorque"));
				
				model.jointDefModels.add(def);
			}else if(jointType.equals("RopeJoint")){
				b2RopeJointDefModel def = new b2RopeJointDefModel();
				def.collideConnected = Boolean.parseBoolean(eleJoint.getAttribute("collideConnected"));
				def.bodyA = refBody(model, eleJoint.getAttribute("bodyA"));
				def.bodyB = refBody(model, eleJoint.getAttribute("bodyB"));
				def.mark = eleJoint.get("mark");
				def.name = eleJoint.get("name");
				
				def.localAnchorA.set(str2Vector(eleJoint.getAttribute("localAnchorA")));
				def.localAnchorB.set(str2Vector(eleJoint.getAttribute("localAnchorB")));
				def.maxLength = Float.parseFloat(eleJoint.getAttribute("maxLength"));
				
				
				model.jointDefModels.add(def);
			}else if(jointType.equals("WeldJoint")){
				b2WeldJointDefModel def = new b2WeldJointDefModel();
				def.collideConnected = Boolean.parseBoolean(eleJoint.getAttribute("collideConnected"));
				def.bodyA = refBody(model, eleJoint.getAttribute("bodyA"));
				def.bodyB = refBody(model, eleJoint.getAttribute("bodyB"));
				def.mark = eleJoint.get("mark");
				def.name = eleJoint.get("name");
				
				def.localAnchorA.set(str2Vector(eleJoint.getAttribute("localAnchorA")));
				def.localAnchorB.set(str2Vector(eleJoint.getAttribute("localAnchorB")));
				def.referenceDegrees = Float.parseFloat(eleJoint.getAttribute("referenceDegrees"));
				
				
				model.jointDefModels.add(def);
			}else if(jointType.equals("WheelJoint")){
				b2WheelJointDefModel def = new b2WheelJointDefModel();
				def.collideConnected = Boolean.parseBoolean(eleJoint.getAttribute("collideConnected"));
				def.bodyA = refBody(model, eleJoint.getAttribute("bodyA"));
				def.bodyB = refBody(model, eleJoint.getAttribute("bodyB"));
				def.mark = eleJoint.get("mark");
				def.name = eleJoint.get("name");
				
				def.localAnchorA.set(str2Vector(eleJoint.getAttribute("localAnchorA")));
				def.localAnchorB.set(str2Vector(eleJoint.getAttribute("localAnchorB")));
				def.localAxisA.set(str2Vector(eleJoint.getAttribute("localAxisA")));
				def.maxMotorTorque = Float.parseFloat(eleJoint.getAttribute("maxMotorTorque"));
				def.motorSpeed = Float.parseFloat(eleJoint.getAttribute("motorSpeed"));
				def.frequencyHz = Float.parseFloat(eleJoint.getAttribute("frequencyHz"));
				def.dampingRatio = Float.parseFloat(eleJoint.getAttribute("dampingRatio"));
				def.enableMotor = Boolean.parseBoolean(eleJoint.getAttribute("enableMotor"));
				
				model.jointDefModels.add(def);
			}else if(jointType.equals("PulleyJoint")){
				
				b2PulleyJointDefModel def = new b2PulleyJointDefModel();
				def.collideConnected = Boolean.parseBoolean(eleJoint.getAttribute("collideConnected"));
				def.bodyA = refBody(model, eleJoint.getAttribute("bodyA"));
				def.bodyB = refBody(model, eleJoint.getAttribute("bodyB"));
				def.mark = eleJoint.get("mark");
				def.name = eleJoint.get("name");
				
				def.localAnchorA.set(str2Vector(eleJoint.getAttribute("localAnchorA")));
				def.localAnchorB.set(str2Vector(eleJoint.getAttribute("localAnchorB")));
				def.groundAnchorA.set(str2Vector(eleJoint.getAttribute("groundAnchorA")));
				def.groundAnchorB.set(str2Vector(eleJoint.getAttribute("groundAnchorB")));
				def.lengthA = Float.parseFloat(eleJoint.getAttribute("lengthA"));
				def.lengthB = Float.parseFloat(eleJoint.getAttribute("lengthB"));
				def.ratio = Float.parseFloat(eleJoint.getAttribute("ratio"));
				
				model.jointDefModels.add(def);
			}else if(jointType.equals("")){
				//TODO: gear
			}
			
		}
		
		return model;
	}
	static Vector2 str2Vector(String str){
		String[] values = str.replace("{", "").replace("}", "").split(",");
		return new Vector2(Float.parseFloat(values[0]),Float.parseFloat(values[1]));
	}
	/**
	 * get the reference fixture from the fixture list. 
	 */
	static b2FixtureDefModel refFixture(b2Scene model,String ref){
		for(b2FixtureDefModel fixture:model.fixtureDefModels){
			if(fixture.name.equals(ref)){
				return fixture;
			}
		}
		return null;
	} 
	static b2BodyDefModel refBody(b2Scene model,String ref){
		for(b2BodyDefModel body:model.bodyDefModels){
			if(body.name.equals(ref)){
				return body;
			}
		}
		return null;
	} 
}
