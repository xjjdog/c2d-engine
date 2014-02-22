package info.u250.c2d.box2deditor.gdx.scenes;

import info.u250.c2d.box2deditor.adapter.PolygonFixtureDefModel;
import info.u250.c2d.box2deditor.gdx.support.AbstractBox2dHelper;
import info.u250.c2d.box2deditor.gdx.support.Click;
import info.u250.c2d.box2deditor.gdx.support.Geometry;
import info.u250.c2d.engine.Engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class PolygonHelper extends AbstractBox2dHelper<PolygonFixtureDefModel> {
	private Vector2 snapPoint = null;
	Click click = Click.NONE;
	InputAdapter input = new InputAdapter() {
		@Override
		public boolean touchDown(int x, int y, int pointer, int button) {
			if(button == Buttons.LEFT){
				if(button == Buttons.LEFT){
					if(snapPoint == null){
						Vector2 newPoint = new Vector2(Engine.screenToWorld(x, y));
						snapPoint = newPoint;
						model.polygon.add(newPoint);
						updateToUI();
					}
				}
			}else if(button == Buttons.RIGHT){
				if(null!=snapPoint){
					if(model.polygon.size()>3){
						model.polygon.remove(snapPoint);
						updateToUI();
					}
				}
			}
			return super.touchDown(x, y, pointer, button);
		}
		@Override
		public boolean mouseMoved(int x, int y) {
			snapPoint = null;
			for(Vector2 v:model.polygon){
				if(Engine.screenToWorld(x, y).dst(v)<10){
					snapPoint = v;
					break;
				}
			}
			return super.mouseMoved(x, y);
		}
		public boolean touchDragged(int screenX, int screenY, int pointer) {
			if(null!=snapPoint){
				Vector2 vv = Engine.screenToWorld(screenX, screenY);
				for(Vector2 v:model.polygon){
					if(vv.dst(v)<10){
						return false;
					}
				}
				snapPoint.set(vv);
				updateToUI();
				
				if(vv.x>Engine.getDefaultCamera().position.x + Engine.getDefaultCamera().viewportWidth/2){
					Engine.getDefaultCamera().position.x = vv.x-Engine.getDefaultCamera().viewportWidth/2;
				}else if(vv.x<Engine.getDefaultCamera().position.x - Engine.getDefaultCamera().viewportWidth/2){
					Engine.getDefaultCamera().position.x = vv.x+Engine.getDefaultCamera().viewportWidth/2;
				}
				if(vv.y>Engine.getDefaultCamera().position.y + Engine.getDefaultCamera().viewportHeight/2){
					Engine.getDefaultCamera().position.y = vv.y-Engine.getDefaultCamera().viewportHeight/2;
				}else if(vv.y<Engine.getDefaultCamera().position.y - Engine.getDefaultCamera().viewportHeight/2){
					Engine.getDefaultCamera().position.y = vv.y+Engine.getDefaultCamera().viewportHeight/2;
				}
			}
			
			return super.touchDragged(screenX, screenY, pointer);
		}
	};

	public PolygonHelper(MainScene adapter) {
		super(adapter);
	}

	Vector2 tmp = new Vector2();
	@Override
	public void render(float delta) {
		Gdx.gl.glEnable(GL10.GL_BLEND);
		Geometry.renderPolygon(model, tmp , 0 ,false);
		drawSnap();
		Gdx.gl.glDisable(GL10.GL_BLEND);
	}
	
	void drawSnap(){
		final float SPACE = 5;
		for(int i=0;i<model.polygon.size();i++){
			render.begin(ShapeType.Line);
			render.setColor(Color.BLUE);
			render.rect(model.polygon.get(i).x-SPACE, model.polygon.get(i).y-SPACE, 2*SPACE, 2*SPACE);
			render.end();
		}
		if(null!=snapPoint){
			render.begin(ShapeType.Filled);
			render.setColor(Color.BLUE);
			render.rect(snapPoint.x-SPACE, snapPoint.y-SPACE, 2*SPACE, 2*SPACE);
			render.end();
		}
		Engine.getSpriteBatch().begin();
		for(int i=0;i<model.polygon.size();i++){
			Engine.getDefaultFont().setColor(Color.RED);
			Engine.getDefaultFont().draw(Engine.getSpriteBatch(), ""+(i+1), model.polygon.get(i).x, model.polygon.get(i).y);
		}
		Engine.getSpriteBatch().end();
	}

	@Override
	public InputProcessor getInputProcessor() {
		return input;
	}

	@Override
	public Class<PolygonFixtureDefModel> getType() {
		return PolygonFixtureDefModel.class;
	}
	
}
