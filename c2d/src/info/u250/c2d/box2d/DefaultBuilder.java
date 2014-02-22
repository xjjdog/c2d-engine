package info.u250.c2d.box2d;

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

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef;
import com.badlogic.gdx.physics.box2d.joints.FrictionJointDef;
import com.badlogic.gdx.physics.box2d.joints.GearJointDef;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef;
import com.badlogic.gdx.physics.box2d.joints.PulleyJointDef;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.physics.box2d.joints.RopeJointDef;
import com.badlogic.gdx.physics.box2d.joints.WeldJointDef;
import com.badlogic.gdx.physics.box2d.joints.WheelJointDef;

public class DefaultBuilder {
	public static void buildAll(World world,b2Scene model){
		for(b2BodyDefModel body:model.bodyDefModels){
			DefaultBuilder.buildBody(world, body);
		}
		for(b2JointDefModel joint:model.jointDefModels){
			DefaultBuilder.buildJoint(world, joint);
		}
	}
	
	public static void buildBody(World world,b2BodyDefModel b2Body){
		if(b2Body.fixtures.size()>0){
			BodyDef bodyDef = new BodyDef();
			bodyDef.type = b2Body.type==0?BodyType.StaticBody:b2Body.type==1?BodyType.KinematicBody:BodyType.DynamicBody;
			bodyDef.position.set(b2Body.position).div(Box2dObject.RADIO);
			bodyDef.angle = b2Body.degrees*MathUtils.degreesToRadians;
			bodyDef.linearVelocity.set(b2Body.linearVelocity);
			bodyDef.angularVelocity = b2Body.angularVelocity;
			bodyDef.linearDamping = b2Body.linearDamping;
			bodyDef.angularDamping = b2Body.angularDamping;
			bodyDef.allowSleep = b2Body.allowSleep;
			bodyDef.awake = b2Body.awake;
			bodyDef.fixedRotation = b2Body.fixedRotation;
			bodyDef.bullet = b2Body.bullet;
			bodyDef.active = b2Body.active;
			bodyDef.gravityScale = b2Body.gravityScale;
			b2Body.body = world.createBody(bodyDef);
			for(b2FixtureDefModel b2Fixture:b2Body.fixtures){
				FixtureDef def = new FixtureDef();
				def.friction = b2Fixture.friction;
				def.restitution = b2Fixture.restitution;
				def.density = b2Fixture.density;
				def.isSensor = b2Fixture.isSensor;
				def.filter.categoryBits = b2Fixture.categoryBits;
				def.filter.groupIndex = b2Fixture.groupIndex;
				def.filter.maskBits = b2Fixture.maskBits;
				if(b2Fixture instanceof b2CircleFixtureDefModel){
					CircleShape shape = new CircleShape();
					shape.setRadius(((b2CircleFixtureDefModel)b2Fixture).radius/Box2dObject.RADIO);
					def.shape = shape;
					b2Body.body.createFixture(def);
					shape.dispose();
				}else if(b2Fixture instanceof b2RectangleFixtureDefModel){
					PolygonShape shape = new PolygonShape();
					b2RectangleFixtureDefModel object = (b2RectangleFixtureDefModel)b2Fixture;
					shape.setAsBox(object.width/2/Box2dObject.RADIO, object.height/2/Box2dObject.RADIO);
					def.shape = shape;
					b2Body.body.createFixture(def);
					shape.dispose();
				}else if(b2Fixture instanceof b2PolygonFixtureDefModel){
					b2PolygonFixtureDefModel object = (b2PolygonFixtureDefModel)b2Fixture;
					if(object.vertices.size()>0){
						for(Vector2[] vv:object.vertices){
							if(vv.length>=3){
								PolygonShape shape = new PolygonShape();
								Vector2[] vvv = new Vector2[vv.length];
								for(int i=0;i<vv.length;i++){
									vvv[i] = new Vector2(vv[i]).div(Box2dObject.RADIO);
								}
								shape.set(vvv);
								def.shape = shape;
								b2Body.body.createFixture(def);
								shape.dispose();
							}
						}
					}
				}
				def = null;
			}
			bodyDef = null;
		}
	}
	
	
	public static void buildJoint(World world,b2JointDefModel b2Joint){
		if(b2Joint instanceof b2DistanceJointDefModel){
			b2DistanceJointDefModel b2Def = (b2DistanceJointDefModel)(b2Joint);
			DistanceJointDef def = new DistanceJointDef();
			def.collideConnected = b2Def.collideConnected;
			def.bodyA = b2Def.bodyA.body;
			def.bodyB = b2Def.bodyB.body;
			def.length = b2Def.length/Box2dObject.RADIO;
			def.localAnchorA.set(b2Def.localAnchorA).div(Box2dObject.RADIO);
			def.localAnchorB.set(b2Def.localAnchorB).div(Box2dObject.RADIO);
			def.frequencyHz = b2Def.frequencyHz;
			def.dampingRatio = b2Def.dampingRatio;
			b2Def.joint = world.createJoint(def);
			def = null;
		}else if(b2Joint instanceof b2FrictionJointDefModel){
			b2FrictionJointDefModel b2Def = (b2FrictionJointDefModel)(b2Joint);
			FrictionJointDef def = new FrictionJointDef();
			def.collideConnected = b2Def.collideConnected;
			def.bodyA = b2Def.bodyA.body;
			def.bodyB = b2Def.bodyB.body;
			def.localAnchorA.set(b2Def.localAnchorA).div(Box2dObject.RADIO);
			def.localAnchorB.set(b2Def.localAnchorB).div(Box2dObject.RADIO);
			def.maxForce = b2Def.maxForce;
			def.maxTorque = b2Def.maxTorque;
			b2Def.joint = world.createJoint(def);
			def = null;
		}else if(b2Joint instanceof b2PrismaticJointDefModel){
			b2PrismaticJointDefModel b2Def = (b2PrismaticJointDefModel)(b2Joint);
			PrismaticJointDef def = new PrismaticJointDef();
			def.collideConnected = b2Def.collideConnected;
			def.bodyA = b2Def.bodyA.body;
			def.bodyB = b2Def.bodyB.body;
			def.localAnchorA.set(b2Def.localAnchorA).div(Box2dObject.RADIO);
			def.localAnchorB.set(b2Def.localAnchorB).div(Box2dObject.RADIO);
			def.referenceAngle = b2Def.referenceDegrees*MathUtils.degreesToRadians;
			def.localAxisA.set(b2Def.localAxisA);
			def.enableLimit = b2Def.enableLimit;
			def.lowerTranslation = b2Def.lowerTranslation/Box2dObject.RADIO;
			def.upperTranslation = b2Def.upperTranslation/Box2dObject.RADIO;
			def.enableMotor = b2Def.enableMotor;
			def.maxMotorForce = b2Def.maxMotorForce;
			def.motorSpeed = b2Def.motorSpeed;
			b2Def.joint = world.createJoint(def);
			def = null;
		}else if(b2Joint instanceof b2RevoluteJointDefModel){
			b2RevoluteJointDefModel b2Def = (b2RevoluteJointDefModel)(b2Joint);
			RevoluteJointDef def = new RevoluteJointDef();
			def.collideConnected = b2Def.collideConnected;
			def.bodyA = b2Def.bodyA.body;
			def.bodyB = b2Def.bodyB.body;
			def.localAnchorA.set(b2Def.localAnchorA).div(Box2dObject.RADIO);
			def.localAnchorB.set(b2Def.localAnchorB).div(Box2dObject.RADIO);
			def.referenceAngle = b2Def.referenceDegrees*MathUtils.degreesToRadians;
			def.lowerAngle = b2Def.lowerDegrees*MathUtils.degreesToRadians;
			def.upperAngle = b2Def.upperDegrees*MathUtils.degreesToRadians;
			def.enableLimit = b2Def.enableLimit;
			def.enableMotor = b2Def.enableMotor;
			def.maxMotorTorque = b2Def.maxMotorTorque;
			def.motorSpeed = b2Def.motorSpeed;
			b2Def.joint = world.createJoint(def);
			def = null;
		}else if(b2Joint instanceof b2RopeJointDefModel){
			b2RopeJointDefModel b2Def = (b2RopeJointDefModel)(b2Joint);
			RopeJointDef def = new RopeJointDef();
			def.collideConnected = b2Def.collideConnected;
			def.bodyA = b2Def.bodyA.body;
			def.bodyB = b2Def.bodyB.body;
			def.localAnchorA.set(b2Def.localAnchorA).div(Box2dObject.RADIO);
			def.localAnchorB.set(b2Def.localAnchorB).div(Box2dObject.RADIO);
			def.maxLength = b2Def.maxLength/Box2dObject.RADIO;
			b2Def.joint = world.createJoint(def);
			def = null;
		}else if(b2Joint instanceof b2WeldJointDefModel){
			b2WeldJointDefModel b2Def = (b2WeldJointDefModel)(b2Joint);
			WeldJointDef def = new WeldJointDef();
			def.collideConnected = b2Def.collideConnected;
			def.bodyA = b2Def.bodyA.body;
			def.bodyB = b2Def.bodyB.body;
			def.localAnchorA.set(b2Def.localAnchorA).div(Box2dObject.RADIO);
			def.localAnchorB.set(b2Def.localAnchorB).div(Box2dObject.RADIO);
			def.referenceAngle = b2Def.referenceDegrees*MathUtils.degreesToRadians;
			b2Def.joint = world.createJoint(def);
			def = null;
		}else if(b2Joint instanceof b2WheelJointDefModel){
			b2WheelJointDefModel b2Def = (b2WheelJointDefModel)(b2Joint);
			WheelJointDef def = new WheelJointDef();
			def.collideConnected = b2Def.collideConnected;
			def.bodyA = b2Def.bodyA.body;
			def.bodyB = b2Def.bodyB.body;
			def.localAnchorA.set(b2Def.localAnchorA).div(Box2dObject.RADIO);
			def.localAnchorB.set(b2Def.localAnchorB).div(Box2dObject.RADIO);
			def.localAxisA.set(b2Def.localAxisA);
			def.enableMotor = b2Def.enableMotor;
			def.maxMotorTorque = b2Def.maxMotorTorque;
			def.motorSpeed = b2Def.motorSpeed;
			def.frequencyHz = b2Def.frequencyHz;
			def.dampingRatio = b2Def.dampingRatio;
			b2Def.joint = world.createJoint(def);
			def = null;
		}else if(b2Joint instanceof b2PulleyJointDefModel){
			b2PulleyJointDefModel b2Def = (b2PulleyJointDefModel)(b2Joint);
			PulleyJointDef def = new PulleyJointDef();
			def.collideConnected = b2Def.collideConnected;
			def.bodyA = b2Def.bodyA.body;
			def.bodyB = b2Def.bodyB.body;
			def.localAnchorA.set(b2Def.localAnchorA).div(Box2dObject.RADIO);
			def.localAnchorB.set(b2Def.localAnchorB).div(Box2dObject.RADIO);
			def.groundAnchorA.set(b2Def.groundAnchorA).div(Box2dObject.RADIO);
			def.groundAnchorB.set(b2Def.groundAnchorB).div(Box2dObject.RADIO);
			def.lengthA = b2Def.lengthA/Box2dObject.RADIO;
			def.lengthB = b2Def.lengthB/Box2dObject.RADIO;
			def.ratio = b2Def.ratio;
			b2Def.joint = world.createJoint(def);
			def = null;
		}else if(b2Joint instanceof b2GearJointDefModel){
			b2GearJointDefModel b2Def = (b2GearJointDefModel)(b2Joint);
			GearJointDef def = new GearJointDef();
			def.collideConnected = b2Def.collideConnected;
			def.bodyA = b2Def.bodyA.body;
			def.bodyB = b2Def.bodyB.body;
			def.joint1 = b2Def.joint1.joint;
			def.joint2 = b2Def.joint2.joint;
			def.ratio = b2Def.ratio;
			b2Def.joint = world.createJoint(def);
			def = null;
		}
	}
}
