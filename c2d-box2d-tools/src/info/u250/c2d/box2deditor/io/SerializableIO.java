package info.u250.c2d.box2deditor.io;

import info.u250.c2d.box2d.model.b2BodyDefModel;
import info.u250.c2d.box2d.model.b2FixtureDefModel;
import info.u250.c2d.box2d.model.b2JointDefModel;
import info.u250.c2d.box2d.model.b2Scene;
import info.u250.c2d.box2d.model.fixture.b2CircleFixtureDefModel;
import info.u250.c2d.box2d.model.fixture.b2PolygonFixtureDefModel;
import info.u250.c2d.box2d.model.fixture.b2RectangleFixtureDefModel;
import info.u250.c2d.box2d.model.joint.b2DistanceJointDefModel;
import info.u250.c2d.box2d.model.joint.b2FrictionJointDefModel;
import info.u250.c2d.box2d.model.joint.b2GearJointDefModel;
import info.u250.c2d.box2d.model.joint.b2PrismaticJointDefModel;
import info.u250.c2d.box2d.model.joint.b2PulleyJointDefModel;
import info.u250.c2d.box2d.model.joint.b2RevoluteJointDefModel;
import info.u250.c2d.box2d.model.joint.b2RopeJointDefModel;
import info.u250.c2d.box2d.model.joint.b2WeldJointDefModel;
import info.u250.c2d.box2d.model.joint.b2WheelJointDefModel;
import info.u250.c2d.box2deditor.adapter.SceneModelAdapter;
import info.u250.c2d.box2deditor.gdx.PhysicalWorld;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.JointDef.JointType;
import com.badlogic.gdx.utils.XmlWriter;

public class SerializableIO implements IO{
	File file = null;
	@Override
	public void save(File file) {
		this.file = file;
		if(null == this.file)return;
		try{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(null==file?this.file:file));
			out.writeObject(PhysicalWorld.MODEL);
			out.close();
		}catch(Exception ex){
			System.out.println("Error save "+ ex.getMessage()+"");
		}
	}

	@Override
	public void read(File file) {
		try {
			this.read(new FileInputStream(file));
		} catch (FileNotFoundException ex) {
			System.out.println("Error read "+ ex.getMessage()+",Try make a new one !");
			PhysicalWorld.MODEL = makeDefault();
		}
		this.file = file;
	}

	public void read(InputStream input){
		this.file = null;
		try{
			ObjectInputStream in = new ObjectInputStream(input);
			PhysicalWorld.MODEL = (SceneModelAdapter) in.readObject();
			in.close();
		}catch(Exception ex){
			System.out.println("Error read "+ ex.getMessage()+",Try make a new one !");
			PhysicalWorld.MODEL = makeDefault();
		}
	}
	public void reset(){
		this.file = null;
		PhysicalWorld.MODEL = new SceneModelAdapter();
	}
	private SceneModelAdapter makeDefault(){
		SceneModelAdapter currentSceneModelAdapter = new SceneModelAdapter();
		return currentSceneModelAdapter;
	}
	
	@Override
	public void exportXML(File file) {
		try{
			XmlWriter xml = new XmlWriter(new FileWriter(file));
			b2Scene scene = PhysicalWorld.MODEL;
			xml.element("world");
			
			xml.element("fixtures");
			for(b2FixtureDefModel fixture:scene.fixtureDefModels){
				if(fixture instanceof b2CircleFixtureDefModel){
					xml.element("circle");
				}else if(fixture instanceof b2RectangleFixtureDefModel){
					xml.element("box");
				}else if(fixture instanceof b2PolygonFixtureDefModel){
					xml.element("polygons");
				}
				xml.attribute("friction", fixture.friction)
				.attribute("restitution", fixture.restitution)
				.attribute("density", fixture.density)
				.attribute("isSensor", fixture.isSensor)
				.attribute("categoryBits", fixture.categoryBits)
				.attribute("maskBits", fixture.maskBits)
				.attribute("groupIndex", fixture.groupIndex)
				.attribute("name", fixture.name)
				.attribute("mark", fixture.mark);
				if(fixture instanceof b2CircleFixtureDefModel){
					xml.attribute("radius", b2CircleFixtureDefModel.class.cast(fixture).radius);
				}else if(fixture instanceof b2RectangleFixtureDefModel){
					xml.attribute("width", b2RectangleFixtureDefModel.class.cast(fixture).width);
					xml.attribute("height", b2RectangleFixtureDefModel.class.cast(fixture).height);
				}else if(fixture instanceof b2PolygonFixtureDefModel){
					b2PolygonFixtureDefModel polygonFixture = b2PolygonFixtureDefModel.class.cast(fixture);
					for(Vector2[] polygon:polygonFixture.vertices){
						xml.element("polygon");
						for(Vector2 vertex:polygon){
							xml.element("vertex", vector2ToStr(vertex));
						}
						xml.pop();
					}
				}
				xml.pop();
			}
			xml.pop();
			
			xml.element("bodys");
			for(b2BodyDefModel body:scene.bodyDefModels){
				xml.element("body")
					.attribute("name", body.name)
					.attribute("type", body.type==0?"StaticBody":(body.type == 1?"KinematicBody":"DynamicBody"))
					.attribute("position", vector2ToStr(body.position))
					.attribute("degrees", body.degrees)
					.attribute("linearVelocity", vector2ToStr(body.linearVelocity))
					.attribute("angularVelocity", body.angularVelocity)
					.attribute("linearDamping", body.linearDamping)
					.attribute("angularDamping", body.angularDamping)
					.attribute("allowSleep", body.allowSleep)
					.attribute("awake", body.awake)
					.attribute("fixedRotation", body.fixedRotation)
					.attribute("bullet", body.bullet)
					.attribute("active", body.active)
					.attribute("gravityScale", body.gravityScale)
					.attribute("drawableOffsetX", body.drawableOffsetX)
					.attribute("drawableOffsetY", body.drawableOffsetY)
					.attribute("drawableWidth", body.drawableWidth)
					.attribute("drawableHeight", body.drawableHeight)
					.attribute("mark", body.mark);
					for(b2FixtureDefModel fixture:body.fixtures){
						xml.element("fixture").attribute("ref", fixture.name).pop();
					}
				xml.pop();
			}
			xml.pop();
			xml.element("joints");
			for(b2JointDefModel b2Joint:scene.jointDefModels){
				xml.element("joint");
				xml.attribute("collideConnected", b2Joint.collideConnected);
				xml.attribute("bodyA", b2Joint.bodyA.name);
				xml.attribute("bodyB", b2Joint.bodyB.name);
				xml.attribute("name", b2Joint.name);
				xml.attribute("mark", b2Joint.mark);
				if(b2Joint instanceof b2DistanceJointDefModel){
					b2DistanceJointDefModel b2Def = b2DistanceJointDefModel.class.cast(b2Joint);
					xml.attribute("type", JointType.DistanceJoint)
					.attribute("length", b2Def.length)
					.attribute("localAnchorA", vector2ToStr(b2Def.localAnchorA))
					.attribute("localAnchorB", vector2ToStr(b2Def.localAnchorB))
					.attribute("frequencyHz", b2Def.frequencyHz)
					.attribute("dampingRatio", b2Def.dampingRatio);
				}else if(b2Joint instanceof b2FrictionJointDefModel){
					b2FrictionJointDefModel b2Def = b2FrictionJointDefModel.class.cast(b2Joint);
					xml.attribute("type", JointType.FrictionJoint)
					.attribute("localAnchorA", vector2ToStr(b2Def.localAnchorA))
					.attribute("localAnchorB", vector2ToStr(b2Def.localAnchorB))
					.attribute("maxForce", b2Def.maxForce)
					.attribute("maxTorque", b2Def.maxTorque);
				}else if(b2Joint instanceof b2PrismaticJointDefModel){
					b2PrismaticJointDefModel b2Def = b2PrismaticJointDefModel.class.cast(b2Joint);
					xml.attribute("type", JointType.PrismaticJoint)
					.attribute("localAxisA", vector2ToStr(b2Def.localAxisA))
					.attribute("localAnchorA", vector2ToStr(b2Def.localAnchorA))
					.attribute("localAnchorB", vector2ToStr(b2Def.localAnchorB))
					.attribute("referenceDegrees", b2Def.referenceDegrees)
					.attribute("enableLimit", b2Def.enableLimit)
					.attribute("lowerTranslation", b2Def.lowerTranslation)
					.attribute("upperTranslation", b2Def.upperTranslation)
					.attribute("enableMotor", b2Def.enableMotor)
					.attribute("maxMotorForce", b2Def.maxMotorForce)
					.attribute("motorSpeed", b2Def.motorSpeed);
				}else if(b2Joint instanceof b2RevoluteJointDefModel){
					b2RevoluteJointDefModel b2Def = b2RevoluteJointDefModel.class.cast(b2Joint);
					xml.attribute("type", JointType.RevoluteJoint)
					.attribute("localAnchorA", vector2ToStr(b2Def.localAnchorA))
					.attribute("localAnchorB", vector2ToStr(b2Def.localAnchorB))
					.attribute("referenceDegrees", b2Def.referenceDegrees)
					.attribute("enableLimit", b2Def.enableLimit)
					.attribute("enableMotor", b2Def.enableMotor)
					.attribute("maxMotorTorque", b2Def.maxMotorTorque)
					.attribute("enableMotor", b2Def.enableMotor)
					.attribute("motorSpeed", b2Def.motorSpeed)
					.attribute("lowerDegrees", b2Def.lowerDegrees)
					.attribute("upperDegrees", b2Def.upperDegrees);
				}else if(b2Joint instanceof b2RopeJointDefModel){
					b2RopeJointDefModel b2Def = b2RopeJointDefModel.class.cast(b2Joint);
					xml.attribute("type", JointType.RopeJoint)
					.attribute("localAnchorA", vector2ToStr(b2Def.localAnchorA))
					.attribute("localAnchorB", vector2ToStr(b2Def.localAnchorB))
					.attribute("maxLength", b2Def.maxLength);
				}else if(b2Joint instanceof b2WeldJointDefModel){
					b2WeldJointDefModel b2Def = b2WeldJointDefModel.class.cast(b2Joint);
					xml.attribute("type", JointType.WeldJoint)
					.attribute("localAnchorA", vector2ToStr(b2Def.localAnchorA))
					.attribute("localAnchorB", vector2ToStr(b2Def.localAnchorB))
					.attribute("referenceDegrees", b2Def.referenceDegrees);
				}else if(b2Joint instanceof b2WheelJointDefModel){
					b2WheelJointDefModel b2Def = b2WheelJointDefModel.class.cast(b2Joint);
					xml.attribute("type", JointType.WheelJoint)
					.attribute("localAxisA", vector2ToStr(b2Def.localAxisA))
					.attribute("localAnchorA", vector2ToStr(b2Def.localAnchorA))
					.attribute("localAnchorB", vector2ToStr(b2Def.localAnchorB))
					.attribute("enableMotor", b2Def.enableMotor)
					.attribute("maxMotorTorque", b2Def.maxMotorTorque)
					.attribute("motorSpeed", b2Def.motorSpeed)
					.attribute("frequencyHz", b2Def.frequencyHz)
					.attribute("dampingRatio", b2Def.dampingRatio);
				}else if(b2Joint instanceof b2PulleyJointDefModel){
					b2PulleyJointDefModel b2Def = b2PulleyJointDefModel.class.cast(b2Joint);
					xml.attribute("type", JointType.PulleyJoint)
					.attribute("groundAnchorA", vector2ToStr(b2Def.groundAnchorA))
					.attribute("groundAnchorB", vector2ToStr(b2Def.groundAnchorB))
					.attribute("localAnchorA", vector2ToStr(b2Def.localAnchorA))
					.attribute("localAnchorB", vector2ToStr(b2Def.localAnchorB))
					.attribute("lengthA", b2Def.lengthA)
					.attribute("lengthB", b2Def.lengthB)
					.attribute("ratio", b2Def.ratio);
				}else if(b2Joint instanceof b2GearJointDefModel){
					//TODO
				}
				xml.pop();
			}
			xml.pop();
			xml.pop();
			xml.flush();
			xml.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	String vector2ToStr(Vector2 vector2){
		return "{" + vector2.x + "," + vector2.y + "}";
	}

}
