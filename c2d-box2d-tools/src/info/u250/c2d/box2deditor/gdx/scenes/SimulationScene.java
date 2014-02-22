package info.u250.c2d.box2deditor.gdx.scenes;

import info.u250.c2d.box2d.Box2dObject;
import info.u250.c2d.box2d.MouseJointInput;
import info.u250.c2d.box2d.PolygonActor;
import info.u250.c2d.box2d.model.b2BodyDefModel;
import info.u250.c2d.box2d.model.b2FixtureDefModel;
import info.u250.c2d.box2d.model.fixture.b2CircleFixtureDefModel;
import info.u250.c2d.box2d.model.fixture.b2PolygonFixtureDefModel;
import info.u250.c2d.box2d.model.fixture.b2RectangleFixtureDefModel;
import info.u250.c2d.box2deditor.adapter.SceneModelAdapter;
import info.u250.c2d.box2deditor.gdx.PhysicalWorld;
import info.u250.c2d.box2deditor.gdx.support.Box2dStage;
import info.u250.c2d.box2deditor.gdx.support.BuildWorld;
import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.Scene;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class SimulationScene extends Box2dStage implements Scene {
	private Body screenBox = null;
	private final Box2DDebugRenderer box2dRender = new Box2DDebugRenderer();
	private List<PolygonActor> meshs = new ArrayList<PolygonActor>();
	final Random random = new Random();
	MouseJointInput mouseJointInput = new MouseJointInput();

	
	@Override
	public void update(float delta) {
		this.act(delta);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		this.draw();
		if(MainScene.INSTANCE.callUI.isDebug()){
			if(PhysicalWorld.WORLD!=null){
				try{
					box2dRender.render(PhysicalWorld.WORLD, Engine.getDefaultCamera().combined.scl(Box2dObject.RADIO));
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		}
	}

	public void stopSimulation(){
		for(PolygonActor actor : meshs){
			if(actor!=null){
				actor.dispose();
			}
		}
		meshs.clear();
		
		//clear all bodys
		this.clear();
		if(null!=screenBox)PhysicalWorld.WORLD.destroyBody(screenBox);
		screenBox = null;
		BuildWorld.stopSimulation();
	}
	public void simulation(){
		stopSimulation();
		screenBox = createScreenBox(new Rectangle(0,0, Engine.getWidth()*2, Engine.getHeight()*2));
		BuildWorld.buildBodys();
		BuildWorld.buildJoints();
		//make relation of the physical objects
		TextureAtlas atlas = Engine.resource("TA");
		SceneModelAdapter model = PhysicalWorld.MODEL;
		for(b2BodyDefModel b2Body:model.bodyDefModels){
			Box2dObject obj = new Box2dObject(b2Body);
			for(b2FixtureDefModel b2Def:b2Body.fixtures){
				if(b2Def instanceof b2CircleFixtureDefModel){
					b2CircleFixtureDefModel tmp = (b2CircleFixtureDefModel)b2Def;
					Image image = new Image(atlas.findRegion("circle"));
					image.setSize(tmp.radius*2, tmp.radius*2);
					image.setOrigin(tmp.radius, tmp.radius);
					image.setPosition(b2Body.drawableOffsetX-tmp.radius, b2Body.drawableOffsetY-tmp.radius);
					image.setColor(generateColor());
					obj.addActor(image);
				}else if(b2Def instanceof b2RectangleFixtureDefModel){
					b2RectangleFixtureDefModel tmp = (b2RectangleFixtureDefModel)b2Def;
					Image image = new Image(atlas.findRegion("box"));
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
					PolygonActor actor = new PolygonActor(Engine.resource("T"+(random.nextInt(3)+1),Texture.class), tmp.vertices,b2Body.drawableOffsetX,b2Body.drawableOffsetY);
					actor.setPosition(b2Body.drawableOffsetX-lower.x,b2Body.drawableOffsetY-lower.y);
					obj.addActor(actor);
					meshs.add(actor);
				}
			}
			this.addActor(obj);
		}
	}
	Color generateColor() {
	    final int threshold = 250;
	    int r1, g1, b1;
	    while (true) {
	        r1 = random.nextInt(256);
	        g1 = random.nextInt(256);
	        b1 = random.nextInt(256);
	        if (r1+g1+b1 > threshold) break;
	    }
	    Color c =  new Color(r1/255f, g1/255f, b1/255f,1);
	    return c;
	}
	
	@Override
	public InputProcessor getInputProcessor() {
		if(null==screenBox){
			return null;
		}
		mouseJointInput.setFixBody(screenBox);
		mouseJointInput.setWorld(PhysicalWorld.WORLD);
		return mouseJointInput;
	}
	@Override
	public void show() {}
	@Override
	public void hide() {}
}