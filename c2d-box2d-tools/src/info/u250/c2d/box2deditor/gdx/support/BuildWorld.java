package info.u250.c2d.box2deditor.gdx.support;

import info.u250.c2d.box2d.DefaultBuilder;
import info.u250.c2d.box2d.model.b2BodyDefModel;
import info.u250.c2d.box2d.model.b2FixtureDefModel;
import info.u250.c2d.box2d.model.b2JointDefModel;
import info.u250.c2d.box2deditor.adapter.PolygonFixtureDefModel;
import info.u250.c2d.box2deditor.adapter.SceneModelAdapter;
import info.u250.c2d.box2deditor.gdx.PhysicalWorld;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class BuildWorld {
	public static void stopSimulation(){
		if(PhysicalWorld.WORLD!=null){
			//destroy all joints 
			Array<Joint> joints = new Array<Joint>();
			PhysicalWorld.WORLD.getJoints(joints);
			for(Joint j:joints){
				try{
					PhysicalWorld.WORLD.destroyJoint(j);
				}catch(Exception ex){
					//do nothing
				}
			}
			//destroy all body
			Array<Body> bodys = new Array<Body>();
			PhysicalWorld.WORLD.getBodies(bodys);
			for(Body body:bodys){
				try{
					PhysicalWorld.WORLD.destroyBody(body);
				}catch(Exception ex){
					//do nothing
				}
			}
			
		}
		PhysicalWorld.WORLD.dispose();
		PhysicalWorld.WORLD = new World(new Vector2(0,-10), false);
		//set all handle to null;
		SceneModelAdapter model = PhysicalWorld.MODEL;
		for(b2JointDefModel b2Joint:model.jointDefModels){
			b2Joint.joint = null;
		}
		for(b2BodyDefModel b2Body:model.bodyDefModels){
			b2Body.body = null;
		}
	}

	public static void buildBody(b2BodyDefModel b2Body){
		DefaultBuilder.buildBody(PhysicalWorld.WORLD, b2Body);
	}
	public static void buildBodys(){
		SceneModelAdapter model = PhysicalWorld.MODEL;
		
		for(b2FixtureDefModel b2:model.fixtureDefModels){
			if(b2 instanceof PolygonFixtureDefModel){
				PolygonFixtureDefModel object = (PolygonFixtureDefModel)b2;
				Geometry.splitPolygon(object);
			}
		}
		
		for(b2BodyDefModel b2Body:model.bodyDefModels){
			buildBody(b2Body);
		}
	}
	
	public static void buildJoint(b2JointDefModel b2Joint){
		DefaultBuilder.buildJoint(PhysicalWorld.WORLD, b2Joint);
	}
	public static void buildJoints(){
		SceneModelAdapter model = PhysicalWorld.MODEL;
		//now for the joints
		for(b2JointDefModel b2:model.jointDefModels){
			Geometry.ajustJoint(b2);
		}
		
		for(b2JointDefModel b2Joint:model.jointDefModels){
			buildJoint(b2Joint);
		}
	}
}
