package info.u250.c2d.box2deditor.gdx;

import info.u250.c2d.box2d.model.b2BodyDefModel;
import info.u250.c2d.box2d.model.fixture.b2RectangleFixtureDefModel;
import info.u250.c2d.box2deditor.Main;
import info.u250.c2d.box2deditor.adapter.SceneModelAdapter;
import info.u250.c2d.box2deditor.gdx.scenes.MainScene;
import info.u250.c2d.box2deditor.gdx.support.BuildWorld;
import info.u250.c2d.box2deditor.io.IO;
import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.EngineDrive;

import java.io.File;

import com.badlogic.gdx.math.Vector2;

public class EditorGdx extends Engine {
	@Override
	protected EngineDrive onSetupEngineDrive() {
		return new EditorEngineDrive();
	}
	float tScale = 1;
	Vector2 m_offset = new Vector2(320.0f , 250 );
	SceneModelAdapter model = new SceneModelAdapter();
	b2BodyDefModel m_wheel;
	b2BodyDefModel m_chassis;

	void make(){
		SceneModelAdapter model = new SceneModelAdapter();
		b2RectangleFixtureDefModel def = new b2RectangleFixtureDefModel();
		def.width = def.height = 20;
		model.addFixture(def);
		for(int i=0;i<10;i++){
			for(int j=0;j<15;j++){
				b2BodyDefModel body = new b2BodyDefModel();
				body.fixtures.add(def);
				model.addBody(body);
				body.position.set(400+30*i,10+20*j);
			}
		}
		PhysicalWorld.MODEL = model;
		IO.INSTANCE.save(new File("D:\\test.txt"));
		IO.INSTANCE.read(new File("D:\\test.txt"));
		MainScene.INSTANCE.callUI.setupModel();
		BuildWorld.buildBodys();
		try{
			Main.bind(PhysicalWorld.MODEL.bodyDefModels.get(0));
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
//	void test(){
//		
//
//	
//		Vector2 pivot = new Vector2(0.0f, -24.0f / tScale);
//
//		
//		

//		{
//			b2RectangleFixtureDefModel pd = new b2RectangleFixtureDefModel();
////			pd.density = 1.0;
//			pd.width = 75*2/tScale;
//			pd.height = 30*2 /tScale;
////			pd.filter.groupIndex = -1;
//			m_chassis = new b2BodyDefModel();
//			//bd.position = pivot + m_offset;
//			m_chassis.fixedRotation = true;
//			m_chassis.position .set( pivot.cpy().add(m_offset));
//			m_chassis.fixtures.add(pd);
//			model.bodyDefModels.add(m_chassis);
//		}
//
//		{
//			b2CircleFixtureDefModel cd = new b2CircleFixtureDefModel();
//			cd.density = 1.0f;
//			cd.radius = 48 / tScale;
////			cd.filter.groupIndex = -1;
//			m_wheel = new b2BodyDefModel();
//			//bd.position = pivot + m_offset;
//			m_wheel.position .set( pivot.cpy().add(m_offset ));
//			m_wheel.fixtures.add(cd);
//			model.bodyDefModels.add(m_wheel);
//		}
//
//		{
//			RevoluteJointDefModel jd = new RevoluteJointDefModel();
//			jd.bodyA = m_wheel;
//			jd.bodyB = m_chassis;
//			jd.anchor = pivot.cpy().add(m_offset);
//			model.jointDefModels.add(jd);
//		}
//
//		Vector2 wheelAnchor = new Vector2();
//
//		//wheelAnchor = pivot + b2Vec2(0.0f, -0.8);
//		wheelAnchor = new Vector2(0.0f, 24f/ tScale);
//		wheelAnchor.add(pivot);
//
//		CreateLeg(-1.0f, wheelAnchor);
//		CreateLeg(1.0f, wheelAnchor);
//		
//		wheelAnchor.x = MathUtils.sin(120)*24;
//		wheelAnchor.y = MathUtils.cos(120)*24;
//
////		m_wheel.SetXForm(m_wheel.GetPosition(), 120.0 * Math.PI / 180.0);
//		CreateLeg(-1.0f, wheelAnchor);
//		CreateLeg(1.0f, wheelAnchor);
//
//		wheelAnchor.x = MathUtils.sin(-120)*24;
//		wheelAnchor.y = MathUtils.cos(-120)*24;
////		m_wheel.SetXForm(m_wheel.GetPosition(), -120.0 * Math.PI / 180.0);
//		CreateLeg(-1.0f, wheelAnchor);
//		CreateLeg(1.0f, wheelAnchor);
//		((SerializableIO)IO.INSTANCE).model = this.model;
//		IO.INSTANCE.save(new File("D:\\test.txt"));
//	}
//	private void CreateLeg(float s, Vector2 wheelAnchor)
//	{
//
//		Vector2 p1 = new Vector2(162 * s / tScale, 183 / tScale);
//		Vector2 p2 = new Vector2(216 * s / tScale, 36 / tScale);
//		Vector2 p3 = new Vector2(129 * s / tScale, 57 / tScale);
//		Vector2 p4 = new Vector2(93 * s / tScale, -24 / tScale);
//		Vector2 p5 = new Vector2(180 * s / tScale, -45 / tScale);
//		Vector2 p6 = new Vector2(75 * s / tScale, -111 / tScale);
//
//		//b2PolygonDef sd1, sd2;
//		PolygonFixtureDefModel sd1 = new PolygonFixtureDefModel();
//		PolygonFixtureDefModel sd2 = new PolygonFixtureDefModel();
////		sd1.vertexCount = 3;
////		sd2.vertexCount = 3;
////		sd1.filter.groupIndex = -1;
////		sd2.filter.groupIndex = -1;
////		sd1.density = 1.0;
////		sd2.density = 1.0;
//
//		Vector2 x = new Vector2(0,0);
//		if (s > 0.0f)
//		{
//			sd1.polygon.clear();
//			sd2.polygon.clear();
//			sd1.polygon.add(p3.cpy().add(x));
//			sd1.polygon.add(p2.cpy().add(x));
//			sd1.polygon.add(p1.cpy().add(x));
//			sd2.polygon.add(p6.cpy().sub(p4).add(x));
//			sd2.polygon.add(p5.cpy().sub(p4).add(x));
//			sd2.polygon.add(new Vector2().add(x));
//		}
//		else
//		{
//			sd1.polygon.clear();
//			sd2.polygon.clear();
//			sd1.polygon.add(p2.cpy().add(x));
//			sd1.polygon.add(p3.cpy().add(x));
//			sd1.polygon.add(p1.cpy().add(x));
//			sd2.polygon.add(p5.cpy().sub(p4).add(x));
//			sd2.polygon.add(p6.cpy().sub(p4).add(x));
//			sd2.polygon.add(new Vector2().add(x));
//		}
//
//		//b2BodyDef bd1, bd2;
//		b2BodyDefModel bd1 = new b2BodyDefModel();
//		b2BodyDefModel bd2 = new b2BodyDefModel();
//		
//		bd1.position.set(m_offset);
//		bd2.position .set( p4.cpy().add(m_offset) );
//
////		bd1.angularDamping = 10.0;
////		bd2.angularDamping = 10.0;
//
//		bd1.fixtures.add(sd1);
//		bd2.fixtures.add(sd2);
//		
//		model.fixtureDefModels.add(sd1);
//		model.fixtureDefModels.add(sd2);
//		model.bodyDefModels.add(bd1);
//		model.bodyDefModels.add(bd2);
//		BuildWorld.buildBody(bd1);
//		BuildWorld.buildBody(bd2);
//		
//		DistanceJointDefModel djd = new DistanceJointDefModel();
//		djd.bodyA = bd1;
//		djd.bodyB = bd2;
//		djd.localAnchorA.set( bd1.body.getLocalPoint(p2.cpy().add(m_offset).div(Box2dObject.RADIO)).mul(Box2dObject.RADIO));
//		djd.localAnchorB.set( bd2.body.getLocalPoint(p5.cpy().add(m_offset).div(Box2dObject.RADIO)).mul(Box2dObject.RADIO));
//		
//		model.jointDefModels.add(djd);
//
//		djd = new DistanceJointDefModel();
//		djd.bodyA = bd1;
//		djd.bodyB = bd2;
//		djd.localAnchorA.set( bd1.body.getLocalPoint(p3.cpy().add(m_offset).div(Box2dObject.RADIO)).mul(Box2dObject.RADIO));
//		djd.localAnchorB.set( bd2.body.getLocalPoint(p4.cpy().add(m_offset).div(Box2dObject.RADIO)).mul(Box2dObject.RADIO));
//		
//		model.jointDefModels.add(djd);
//
//		djd  = new DistanceJointDefModel();
//		djd.bodyA = bd1;
//		djd.bodyB = m_wheel;
//		djd.localAnchorA.set( bd1.body.getLocalPoint(p3.cpy().add(m_offset).div(Box2dObject.RADIO)).mul(Box2dObject.RADIO));
//		djd.localAnchorB.set( bd2.body.getLocalPoint(wheelAnchor.cpy().add(m_offset).div(Box2dObject.RADIO)).mul(Box2dObject.RADIO));
//		
//		model.jointDefModels.add(djd);
//		
//		djd  = new DistanceJointDefModel();
//		djd.bodyA = bd2;
//		djd.bodyB = m_wheel;
//		djd.localAnchorA.set( bd1.body.getLocalPoint(p6.cpy().add(m_offset).div(Box2dObject.RADIO)).mul(Box2dObject.RADIO));
//		djd.localAnchorB.set( bd2.body.getLocalPoint(wheelAnchor.cpy().add(m_offset).div(Box2dObject.RADIO)).mul(Box2dObject.RADIO));
//		
//		model.jointDefModels.add(djd);
//
//		RevoluteJointDefModel rjd = new RevoluteJointDefModel();
//		rjd.bodyA = bd2;
//		rjd.bodyB = m_chassis;
//		rjd.anchor.set( p4.cpy().add(m_offset));
//		model.jointDefModels.add(djd);
//
//	}

}
