package info.u250.c2d.box2deditor.gdx.scenes;

import info.u250.c2d.box2d.Box2dObject;
import info.u250.c2d.box2d.model.b2BodyDefModel;
import info.u250.c2d.box2d.model.b2FixtureDefModel;
import info.u250.c2d.box2d.model.b2JointDefModel;
import info.u250.c2d.box2d.model.fixture.b2CircleFixtureDefModel;
import info.u250.c2d.box2d.model.fixture.b2RectangleFixtureDefModel;
import info.u250.c2d.box2deditor.adapter.DistanceJointDefModel;
import info.u250.c2d.box2deditor.adapter.FrictionJointDefModel;
import info.u250.c2d.box2deditor.adapter.PolygonFixtureDefModel;
import info.u250.c2d.box2deditor.adapter.PrismaticJointDefModel;
import info.u250.c2d.box2deditor.adapter.PulleyJointDefModel;
import info.u250.c2d.box2deditor.adapter.RevoluteJointDefModel;
import info.u250.c2d.box2deditor.adapter.RopeJointDefModel;
import info.u250.c2d.box2deditor.adapter.SceneModelAdapter;
import info.u250.c2d.box2deditor.adapter.WeldJointDefModel;
import info.u250.c2d.box2deditor.adapter.WheelJointDefModel;
import info.u250.c2d.box2deditor.gdx.PhysicalWorld;
import info.u250.c2d.box2deditor.gdx.support.AbstractBox2dHelper;
import info.u250.c2d.box2deditor.gdx.support.DefaultLabel;
import info.u250.c2d.box2deditor.gdx.support.Geometry;
import info.u250.c2d.box2deditor.gdx.support.LeftTopImage;
import info.u250.c2d.engine.Engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;

public class SceneHelper extends AbstractBox2dHelper<SceneModelAdapter> {
	private b2BodyDefModel data = null;
	private b2BodyDefModel secondData = null;
	private Vector2 dragTemp = new Vector2();
	private Vector2 startPoint = new Vector2();
	final Vector2 box2dAABBTestPoint = new Vector2();
	InputMultiplexer input = new InputMultiplexer();
	LeftTopImage bodyMode,jointModel;
	private boolean rotateOn = false;
	
	private Color colorInner = new Color(1f,1,1f,0.2f);
	private Color colorOuter = new Color(0,0,0,0.2f);
	
	QueryCallback callback = new QueryCallback() {
		@Override
		public boolean reportFixture (Fixture fixture) {
			// if the hit point is inside the fixture of the body
			// we report it
			if (fixture.testPoint(box2dAABBTestPoint.x, box2dAABBTestPoint.y)) {
				Body hitBody = fixture.getBody();
				for(b2BodyDefModel bodyModel : PhysicalWorld.MODEL.bodyDefModels){
					if(hitBody == bodyModel.body){
						data = bodyModel;
						adapter.callUI.updateToUI(bodyModel);
						return false;
					}
				}
				data = null;
				secondData = null;
				return false;
			} else{
				secondData = null;
				return true;
			}
		}
	};
	InputAdapter jointSelectInput = new InputAdapter(){
		MakeJointsMenu jointsMenu = new MakeJointsMenu();
		QueryCallback callbackSecond = new QueryCallback() {
			@Override
			public boolean reportFixture (Fixture fixture) {
				if(fixture==null)return true;
				// if the hit point is inside the fixture of the body
				// we report it
				if (fixture.testPoint(box2dAABBTestPoint.x, box2dAABBTestPoint.y)) {
					Body hitBody = fixture.getBody();
					for(b2BodyDefModel bodyModel :  PhysicalWorld.MODEL.bodyDefModels){
						if(hitBody == bodyModel.body){
							//not the same body
							if(data==bodyModel)return true;
							
							secondData = bodyModel;
							adapter.callUI.updateToUI(secondData);
							if(!Gdx.input.isButtonPressed(Buttons.LEFT)){
								jointsMenu.setScale(Engine.getDefaultCamera().zoom);
								jointsMenu.setPosition(worldCenter.x-100,worldCenter.y-100);
								addActor(jointsMenu);
							}
							return false;
						}
					}
					secondData = null;
					return false;
				} else{
					return true;
				}
			}
		};
		public boolean touchDown(int screenX, int screenY, int pointer, int button) {
			if(button == Buttons.LEFT){
				jointsMenu.remove();
				dragTemp.set(Engine.screenToWorld(screenX, screenY));
				startPoint.set(dragTemp);
				data = null;
				secondData = null;
				//find the first data //
				if(button == Buttons.LEFT){
					box2dAABBTestPoint.set(dragTemp).div(Box2dObject.RADIO);
					PhysicalWorld.WORLD.QueryAABB(callback, box2dAABBTestPoint.x - 0.1f, box2dAABBTestPoint.y - 0.1f, box2dAABBTestPoint.x + 0.1f, box2dAABBTestPoint.y + 0.1f);
				}
			}
			return super.touchDown(screenX, screenY, pointer, button);
		}
		public boolean touchDragged(int screenX, int screenY, int pointer) {
			secondData = null;
			dragTemp.set(Engine.screenToWorld(screenX, screenY));
			//find the first data //
			box2dAABBTestPoint.set(dragTemp).div(Box2dObject.RADIO);
			PhysicalWorld.WORLD.QueryAABB(callbackSecond, box2dAABBTestPoint.x - 0.1f, box2dAABBTestPoint.y - 0.1f, box2dAABBTestPoint.x + 0.1f, box2dAABBTestPoint.y + 0.1f);
			return super.touchDragged(screenX, screenY, pointer);
		};
		@Override
		public boolean touchUp(int x, int y, int pointer, int button) {
			if(button == Buttons.LEFT){
				secondData = null;
				dragTemp.set(Engine.screenToWorld(x, y));
				//find the first data //
				box2dAABBTestPoint.set(dragTemp).div(Box2dObject.RADIO);
				PhysicalWorld.WORLD.QueryAABB(callbackSecond, box2dAABBTestPoint.x - 0.1f, box2dAABBTestPoint.y - 0.1f, box2dAABBTestPoint.x + 0.1f, box2dAABBTestPoint.y + 0.1f);
			}
			return false;
		}
		public boolean keyDown(int keycode) {
			if(keycode==Keys.SPACE){
				jointsMenu.remove();
				data = null;
				secondData = null;
				input.clear();
				input.addProcessor(SceneHelper.this);
				input.addProcessor(bodySelectInput);
				jointModel.remove();
				addActor(bodyMode);
			}else if(keycode == Keys.F5){
				jointModel.remove();
				adapter.simulation();
			}
			return super.keyDown(keycode);
		}
	};
	InputAdapter bodySelectInput = new InputAdapter() {
		@Override
		public boolean touchDown(int screenX, int screenY, int pointer, int button) {
			dragTemp.set(Engine.screenToWorld(screenX, screenY));			
			secondData = null;
			float dst = 0;
			if(data!=null){
				dst = data.position.dst(dragTemp);
			}
			if(dst>100 && dst<150){
				rotateOn = true;
			}else{
				data = null;
				//find the first data //
				if(button == Buttons.LEFT){
					box2dAABBTestPoint.set(dragTemp).div(Box2dObject.RADIO);
					PhysicalWorld.WORLD.QueryAABB(callback, box2dAABBTestPoint.x - 0.1f, box2dAABBTestPoint.y - 0.1f, box2dAABBTestPoint.x + 0.1f, box2dAABBTestPoint.y + 0.1f);
				}
			}
			return super.touchDown(screenX, screenY, pointer, button);
		}
		@Override
		public boolean touchDragged(int x, int y, int pointer) {
			if(null!=data){
				Vector2 current = Engine.screenToWorld(x, y);
				if(rotateOn){
					float angle_append = current.cpy().sub(data.position).angle() - dragTemp.sub(data.position).angle();
					data.degrees+=angle_append;
					adapter.callUI.updateToUI(data);
				}else{
					secondData = null;
					Vector2 offset = current.cpy().sub(dragTemp);
					data.position.add(offset.x,offset.y );
					adapter.callUI.updateToUI(data);
				}
				dragTemp.set(current);
				
			}
			return super.touchDragged(x, y, pointer);
		}
		public boolean touchUp(int screenX, int screenY, int pointer, int button) {
			rotateOn = false;
			return false;
		};
		public boolean keyDown(int keycode) {
			if(keycode==Keys.SPACE){
				data = null;
				secondData = null;
				input.clear();
				input.addProcessor(SceneHelper.this);
				input.addProcessor(jointSelectInput);
				bodyMode.remove();
				addActor(jointModel);
			}else if(keycode == Keys.F5){
				jointModel.remove();
				adapter.simulation();
			}
			return super.keyDown(keycode);
		}
	};

	

	public SceneHelper(final MainScene adapter) {
		super(adapter);
		TextureAtlas atlas = Engine.resource("TA");
		bodyMode = new LeftTopImage(atlas.findRegion("bodyMode"));
		jointModel = new LeftTopImage(atlas.findRegion("jointMode"));
		this.addActor(bodyMode);
	}
	
	@Override
	public void render(float delta) {
		boolean bodySelectMode = input.getProcessors().contains(bodySelectInput, true);
		Gdx.gl.glEnable(GL10.GL_BLEND);
		if(bodySelectMode && null!=data){
			render.setColor(colorOuter);
			render.begin(ShapeType.Filled);
			render.circle(data.position.x, data.position.y, 150);
			render.end();
			render.setColor(colorInner);
			render.begin(ShapeType.Filled);
			render.circle(data.position.x, data.position.y, 100);
			render.end();
			render.setColor(colorOuter);
			render.begin(ShapeType.Filled);
			render.triangle(
					data.position.x+100*MathUtils.cosDeg(data.degrees-30), 
					data.position.y+100*MathUtils.sinDeg(data.degrees-30),
					data.position.x+100*MathUtils.cosDeg(data.degrees+90), 
					data.position.y+100*MathUtils.sinDeg(data.degrees+90), 
					data.position.x+100*MathUtils.cosDeg(data.degrees+210), 
					data.position.y+100*MathUtils.sinDeg(data.degrees+210));
			render.end();
		}
		try{
			for(b2BodyDefModel b2:model.bodyDefModels){
				final Vector2 position = b2.position;
				final float angle = b2.degrees;
			
				for(b2FixtureDefModel obj:b2.fixtures){
					if(obj instanceof PolygonFixtureDefModel){
						Geometry.renderPolygon(PolygonFixtureDefModel.class.cast(obj), position,angle,!bodySelectMode && b2==data||b2==secondData);
					}else if(obj instanceof b2CircleFixtureDefModel){
						Geometry.renderCircle(b2CircleFixtureDefModel.class.cast(obj), position,angle,!bodySelectMode &&b2==data||b2==secondData);
					}else if(obj instanceof b2RectangleFixtureDefModel){
						Geometry.renderBox(b2RectangleFixtureDefModel.class.cast(obj), position,angle,!bodySelectMode &&b2==data||b2==secondData);
					}
				}
				if(null!=b2.body){
					render.begin(ShapeType.Filled);
					render.setColor(Color.RED);
					render.circle(b2.position.x, b2.position.y, 5);
					render.end();
				}
			
			}
		}catch(Exception ex){
			Gdx.app.error("Exception", ex.getMessage());
		}
		// now we draw the joints
		for(b2JointDefModel b2Joint:model.jointDefModels){
			Geometry.renderJoint(b2Joint);
		}
		if(!bodySelectMode && Gdx.input.isButtonPressed(Buttons.LEFT)){
			render.begin(ShapeType.Line);
			render.line(startPoint.x, startPoint.y, dragTemp.x, dragTemp.y);
			render.end();
		}
		Gdx.gl.glDisable(GL10.GL_BLEND);
		
		this.act(delta);
		this.draw();
	}

	
	@Override
	public InputProcessor getInputProcessor() {
		input.clear();
		input.addProcessor(this);
		input.addProcessor(bodySelectInput);
		return input;
	}
	
	@Override
	public Class<SceneModelAdapter> getType() {
		return SceneModelAdapter.class;
	}

	public void setData(b2BodyDefModel data) {
		this.data = data;
	}
	
	
	
	/**
	 * The menu for make new joints
	 *
	 */
	class MakeJointsMenu extends Group {
		public MakeJointsMenu(){
			TextureAtlas atlas = Engine.resource("TA");
			Table menuTable = new Table();

			menuTable.pad(20);
			menuTable.setBackground(new NinePatchDrawable(atlas.createPatch("dialog")));

			this.addActor(menuTable);
			
			
			addNewHandle(menuTable, "b2DistanceJointDefModel", "DistanceJoint",new ClickListener(){
				@Override
				public void clicked(InputEvent event, float x, float y) {
					DistanceJointDefModel def = new DistanceJointDefModel();
					def.bodyA = data;
					def.bodyB = secondData;
					def.localAnchorA.set(def.bodyA.body.getLocalPoint(startPoint.cpy().div(Box2dObject.RADIO))).scl(Box2dObject.RADIO);
					def.localAnchorB.set(def.bodyB.body.getLocalPoint(dragTemp.cpy().div(Box2dObject.RADIO))).scl(Box2dObject.RADIO);
					def.length = startPoint.dst(dragTemp);
					Geometry.ajustJoint(def);
					PhysicalWorld.MODEL.addJoint(def);
					adapter.callUI.addModelToLeft(def);
					remove();
				}
			});
			addNewHandle(menuTable, "b2PrismaticJointDefModel", "PrismaticJoint",new ClickListener(){
				@Override
				public void clicked(InputEvent event, float x, float y) {
					PrismaticJointDefModel def = new PrismaticJointDefModel();
					def.bodyA = data;
					def.bodyB = secondData;
					def.anchor.set(startPoint).add(dragTemp).div(2);
					Geometry.ajustJoint(def);
					PhysicalWorld.MODEL.addJoint(def);
					adapter.callUI.addModelToLeft(def);
					remove();
				}
			});
			addNewHandle(menuTable, "b2RevoluteJointDefModel", "RevoluteJoint",new ClickListener(){
				@Override
				public void clicked(InputEvent event, float x, float y) {
					RevoluteJointDefModel def = new RevoluteJointDefModel();
					def.bodyA = data;
					def.bodyB = secondData;
					def.anchor.set(startPoint).add(dragTemp).div(2);
					Geometry.ajustJoint(def);
					PhysicalWorld.MODEL.addJoint(def);
					adapter.callUI.addModelToLeft(def);
					remove();
				}
			});
			addNewHandle(menuTable, "b2RopeJointDefModel", "RopeJoint",new ClickListener(){
				@Override
				public void clicked(InputEvent event, float x, float y) {
					RopeJointDefModel def = new RopeJointDefModel();
					def.bodyA = data;
					def.bodyB = secondData;
					def.localAnchorA.set(def.bodyA.body.getLocalPoint(startPoint.cpy().div(Box2dObject.RADIO))).scl(Box2dObject.RADIO);
					def.localAnchorB.set(def.bodyB.body.getLocalPoint(dragTemp.cpy().div(Box2dObject.RADIO))).scl(Box2dObject.RADIO);
					Geometry.ajustJoint(def);
					PhysicalWorld.MODEL.addJoint(def);
					adapter.callUI.addModelToLeft(def);
					remove();
				}
			});
			addNewHandle(menuTable, "b2WeldJointDefModel", "WeldJointDefModel",new ClickListener(){
				@Override
				public void clicked(InputEvent event, float x, float y) {
					WeldJointDefModel def = new WeldJointDefModel();
					def.bodyA = data;
					def.bodyB = secondData;
					def.anchor.set(startPoint).add(dragTemp).div(2);
					Geometry.ajustJoint(def);
					PhysicalWorld.MODEL.addJoint(def);
					adapter.callUI.addModelToLeft(def);
					remove();
				}
			});
			addNewHandle(menuTable, "b2WheelJointDefModel", "WheelJointDefModel",new ClickListener(){
				@Override
				public void clicked(InputEvent event, float x, float y) {
					WheelJointDefModel def = new WheelJointDefModel();
					def.bodyA = data;
					def.bodyB = secondData;
					def.anchor.set(startPoint).add(dragTemp).div(2);
					Geometry.ajustJoint(def);
					PhysicalWorld.MODEL.addJoint(def);
					adapter.callUI.addModelToLeft(def);
					remove();
				}
			});
			addNewHandle(menuTable, "b2FrictionJointDefModel", "FrictionJoint",new ClickListener(){
				@Override
				public void clicked(InputEvent event, float x, float y) {
					FrictionJointDefModel def = new FrictionJointDefModel();
					def.bodyA = data;
					def.bodyB = secondData;
					def.anchor.set(startPoint).add(dragTemp).div(2);
					Geometry.ajustJoint(def);
					PhysicalWorld.MODEL.addJoint(def);
					adapter.callUI.addModelToLeft(def);
					remove();
				}
			});
			addNewHandle(menuTable, "b2PulleyJointDefModel", "PulleyJoint",new ClickListener(){
				@Override
				public void clicked(InputEvent event, float x, float y) {
					PulleyJointDefModel def = new PulleyJointDefModel();
					def.bodyA = data;
					def.bodyB = secondData;
					def.localAnchorA.set(def.bodyA.body.getLocalPoint(startPoint.cpy().div(Box2dObject.RADIO))).scl(Box2dObject.RADIO);
					def.localAnchorB.set(def.bodyB.body.getLocalPoint(dragTemp.cpy().div(Box2dObject.RADIO))).scl(Box2dObject.RADIO);
					def.groundAnchorA.set(startPoint.x, 500);
					def.groundAnchorB.set(dragTemp.x, 500);
					def.lengthA = startPoint.dst(def.groundAnchorA);
					def.lengthB = dragTemp.dst(def.groundAnchorB);
					Geometry.ajustJoint(def);
					PhysicalWorld.MODEL.addJoint(def);
					adapter.callUI.addModelToLeft(def);
					remove();
				}
			});
			menuTable.pack();
		}
		
		
		void addNewHandle(Table table,String region,String txt,ClickListener l){
			TextureAtlas atlas = Engine.resource("TA");
			DefaultLabel label = DefaultLabel.getDefaultLabel(txt+"   ");
			label.addListener(l);
			table.add(new Image(new TextureRegionDrawable(atlas.findRegion(region)),Scaling.none)).height(30).spaceRight(10);
			table.add(label).right().spaceRight(10);
			table.row();
		}
	}
}
