package info.u250.c2d.tests.box2d;

import info.u250.c2d.box2d.Box2dObject;
import info.u250.c2d.box2d.DefaultBuilder;
import info.u250.c2d.box2d.IOXml;
import info.u250.c2d.box2d.MouseJointInput;
import info.u250.c2d.box2d.PolygonActor;
import info.u250.c2d.box2d.model.b2BodyDefModel;
import info.u250.c2d.box2d.model.b2FixtureDefModel;
import info.u250.c2d.box2d.model.b2Scene;
import info.u250.c2d.box2d.model.fixture.b2CircleFixtureDefModel;
import info.u250.c2d.box2d.model.fixture.b2PolygonFixtureDefModel;
import info.u250.c2d.box2d.model.fixture.b2RectangleFixtureDefModel;
import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.EngineDrive;
import info.u250.c2d.engine.SceneStage;
import info.u250.c2d.engine.resources.AliasResourceManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;


public class Box2dC2dTest extends Engine {
	Random random = new Random();
	private List<PolygonActor> meshs = new ArrayList<PolygonActor>();
	World world ;
	private Box2DDebugRenderer box2dRender ;
	MouseJointInput input;
	
	
	@Override
	protected EngineDrive onSetupEngineDrive() {
		return new EngineX();
	}
	@Override
	public void dispose () {
		for(PolygonActor actor:meshs){
			actor.dispose();
		}
		if(world!=null){
			world.dispose();
		}
		super.dispose();
	}
	
	private class EngineX implements EngineDrive{
		@Override
		public void onResourcesRegister(AliasResourceManager<String> reg) {
			reg.texture("Polygon", "data/textures/default.png");
			reg.texture("Circle", "data/box2d/circle.png");
			reg.texture("Box", "data/box2d/box.png");
		}
		@Override
		public void dispose() {}
		@Override
		public EngineOptions onSetupEngine() {
			final EngineOptions opt = new EngineOptions(new String[]{"data/box2d/circle.png","data/box2d/box.png","data/textures/default.png"},800,480);
			return opt;
		}

		@Override
		public void onLoadedResourcesCompleted() {
			try {
				world = new World(new Vector2(0,-9.8f), false);
				box2dRender = new Box2DDebugRenderer();
				b2Scene model = IOXml.parse(Gdx.files.internal("data/box2d/model-c2d.xml"));
				DefaultBuilder.buildAll(world, model);
				input = new MouseJointInput();
				input.setWorld(world);
				input.setFixBody(model.bodyDefModels.iterator().next().body);
				SceneStage stage = new SceneStage(){
					@Override
					public void update(float delta) {
						world.step(delta, 3, 3);
						super.update(delta);
					}
					@Override
					public void render(float delta) {
						super.render(delta);
						box2dRender.render(world, Engine.getDefaultCamera().combined.scl(Box2dObject.RADIO));
					}
					@Override
					public InputProcessor getInputProcessor() {
						return input;
					}
				};
			
				setup(model, stage);
			    
				Engine.setMainScene(stage);	
			} catch (IOException e) {
				e.printStackTrace();
			}
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
	
	
	void setup(b2Scene model,Stage stage){
		
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
}
