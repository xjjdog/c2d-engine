package info.u250.c2d.box2deditor.adapter;

import java.util.Iterator;

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
import info.u250.c2d.box2deditor.gdx.PhysicalWorld;

public class SceneModelAdapter extends b2Scene{
	private static final long serialVersionUID = 1L;
	int CircleFixture_INDEX = 1;
	int PolygonFixture_INDEX = 1;
	int RectangleFixture_INDEX = 1;
	int Body_INDEX = 1;
	
	int Distance_JOINT_INDEX = 1;
	int Friction_JOINT_INDEX = 1;
	int Prismatic_JOINT_INDEX= 1;
	int Revolute_JOINT_INDEX = 1;
	int RopeJoint_JOINT_INDEX= 1;
	int WeldJoint_JOINT_INDEX =1;
	int Wheel_JOINT_INDEX	  =1;
	int Pulley_JOINT_INDEX	  =1;
	int Gear_JOINT_INDEX	  =1;
	public String pathHandel = "";
	public void addFixture(b2FixtureDefModel model){
		if("".equals(model.name)){
			if(model instanceof b2CircleFixtureDefModel){
				model.name = "CircleFixture"+CircleFixture_INDEX++;
			}else if(model instanceof b2PolygonFixtureDefModel){
				model.name = "PolygonFixture"+PolygonFixture_INDEX++;
			}else if(model instanceof b2RectangleFixtureDefModel){
				model.name = "RectangleFixture"+RectangleFixture_INDEX++;
			}
		}
		this.fixtureDefModels.add(model);
	}
	public void addBody(b2BodyDefModel model){
		if("".equals(model.name))model.name = "Body"+Body_INDEX++;
		this.bodyDefModels.add(model);
	}
	public void addJoint(b2JointDefModel b2Joint){
		if("".equals(b2Joint.name)){
			if(b2Joint instanceof b2DistanceJointDefModel){
				b2Joint.name = "DistanceJoint"+Distance_JOINT_INDEX++;
			}else if(b2Joint instanceof b2FrictionJointDefModel){
				b2Joint.name = "FrictionJoint"+Friction_JOINT_INDEX++;
			}else if(b2Joint instanceof b2PrismaticJointDefModel){
				b2Joint.name = "PrismaticJoint"+Prismatic_JOINT_INDEX++;
			}else if(b2Joint instanceof b2RevoluteJointDefModel){
				b2Joint.name = "RevoluteJoint"+Revolute_JOINT_INDEX++;
			}else if(b2Joint instanceof b2RopeJointDefModel){
				b2Joint.name = "RopeJoint"+RopeJoint_JOINT_INDEX++;
			}else if(b2Joint instanceof b2WeldJointDefModel){
				b2Joint.name = "WeldJoint"+WeldJoint_JOINT_INDEX++;
			}else if(b2Joint instanceof b2WheelJointDefModel){
				b2Joint.name = "WheelJoint"+Wheel_JOINT_INDEX++;
			}else if(b2Joint instanceof b2PulleyJointDefModel){
				b2Joint.name = "PulleyJoint"+Pulley_JOINT_INDEX++;
			}else if(b2Joint instanceof b2GearJointDefModel){
				b2Joint.name = "GearJoint"+Gear_JOINT_INDEX++;
			}
		}
		this.jointDefModels.add(b2Joint);
	}
	
	public void removeJoint(b2JointDefModel joint){
		this.jointDefModels.remove(joint);
	}
	public void removeBody(b2BodyDefModel body){
		removeDependJoint(body);
		if(body.body!=null){
			PhysicalWorld.WORLD.destroyBody(body.body);
			body.body = null;
			this.bodyDefModels.remove(body);
		}
	}
	void removeDependJoint(b2BodyDefModel body){
		Iterator<b2JointDefModel> itr = this.jointDefModels.iterator();
		while(itr.hasNext()){
			b2JointDefModel joint = itr.next();
			if(joint.bodyA == body || joint.bodyB==body){
				itr.remove();
			}
		}
	}
	public void removeFixture(b2FixtureDefModel fixture){
		Iterator<b2BodyDefModel> bodyItr = this.bodyDefModels.iterator();
		while(bodyItr.hasNext()){
			b2BodyDefModel body = bodyItr.next();
			if(body.fixtures.contains(fixture)){
				removeDependJoint(body);
				if(body.body!=null){
					PhysicalWorld.WORLD.destroyBody(body.body);
					body.body = null;
					bodyItr.remove();
				}
			}
		}
		fixtureDefModels.remove(fixture);
	}
}
