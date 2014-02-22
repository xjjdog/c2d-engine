package info.u250.c2d.box2deditor.gdx.scenes;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.Scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class RightClickHelper implements Scene {
	final protected MainScene adapter;

	public RightClickHelper(MainScene adapter){
		this.adapter = adapter;
	}
	public final Vector2 snapPoint = new Vector2();
	@Override
	public void update(float delta) {}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		
		ShapeRenderer render = Engine.getShapeRenderer();
		render.setProjectionMatrix(Engine.getDefaultCamera().combined);
		// draw the x,y axis
		render.begin(ShapeType.Line);
		render.setColor(Color.GREEN);
		render.line(0, 0, Engine.getDefaultCamera().viewportWidth * Engine.getDefaultCamera().zoom, 0);
		render.line(Engine.getDefaultCamera().viewportWidth * Engine.getDefaultCamera().zoom - 10, -5, Engine.getDefaultCamera().viewportWidth * Engine.getDefaultCamera().zoom, 0);
		render.line(Engine.getDefaultCamera().viewportWidth * Engine.getDefaultCamera().zoom - 10, +5, Engine.getDefaultCamera().viewportWidth * Engine.getDefaultCamera().zoom, 0);
		render.setColor(Color.RED);
		render.line(0, 0, 0, Engine.getDefaultCamera().viewportHeight * Engine.getDefaultCamera().zoom);
		render.line(5, Engine.getDefaultCamera().viewportHeight * Engine.getDefaultCamera().zoom - 10, 0, Engine.getDefaultCamera().viewportHeight * Engine.getDefaultCamera().zoom);
		render.line(-5, Engine.getDefaultCamera().viewportHeight * Engine.getDefaultCamera().zoom - 10, 0,Engine.getDefaultCamera().viewportHeight * Engine.getDefaultCamera().zoom);
		render.end();

		
		render.begin(ShapeType.Line);
		render.setColor(Color.BLACK);
		for (int i = 0; i < (int) Engine.getDefaultCamera().viewportWidth / 10 * Engine.getDefaultCamera().zoom + 3; i++) {
			float x = Engine.getDefaultCamera().position.x - Engine.getDefaultCamera().viewportWidth / 2 * Engine.getDefaultCamera().zoom + i * 10;
			float y = Engine.getDefaultCamera().position.y - Engine.getDefaultCamera().viewportHeight / 2 * Engine.getDefaultCamera().zoom;

			int length = 3;
			if (i > 2) {
				if (i % 10 == 0) {
					length = 10;
					Engine.getSpriteBatch().begin();
					Engine.getDefaultFont().setColor(Color.YELLOW);
					Engine.getDefaultFont().draw(Engine.getSpriteBatch(), (int) x + "", x - 10, y + 20* Engine.getDefaultCamera().zoom);
					Engine.getSpriteBatch().end();
				} else if (i % 5 == 0) {
					length = 7;
				}
				render.line(x, y, x, y + length* Engine.getDefaultCamera().zoom);
			}
		}
		render.end();

		
		// draw the y number
		render.begin(ShapeType.Line);
		render.setColor(Color.BLACK);
		for (int i = 0; i < (int) Engine.getDefaultCamera().viewportHeight / 10 * Engine.getDefaultCamera().zoom + 3; i++) {
			float x = Engine.getDefaultCamera().position.x - Engine.getDefaultCamera().viewportWidth / 2 * Engine.getDefaultCamera().zoom;
			float y = Engine.getDefaultCamera().position.y - Engine.getDefaultCamera().viewportHeight / 2* Engine.getDefaultCamera().zoom + i * 10;

			int length = 3;
			if (i > 2) {
				if (i % 10 == 0) {
					length = 10;
					Engine.getSpriteBatch().begin();
					Engine.getDefaultFont().setColor(Color.YELLOW);
					Engine.getDefaultFont().draw(Engine.getSpriteBatch(), (int) y + "", x + 10, y + 10);
					Engine.getSpriteBatch().end();
				} else if (i % 5 == 0) {
					length = 7;
				}
				render.line(x, y, x + length * Engine.getDefaultCamera().zoom, y);
			}
		}

		Engine.getSpriteBatch().begin();
		Engine.getDefaultFont().setScale(Engine.getDefaultCamera().zoom);
		Engine.getDefaultFont().setColor(Color.YELLOW);
		Engine.getDefaultFont().draw(Engine.getSpriteBatch(), "(0,0)", 
				Engine.getDefaultCamera().position.x - Engine.getDefaultCamera().viewportWidth / 2 * Engine.getDefaultCamera().zoom, 
				Engine.getDefaultCamera().position.y - (Engine.getDefaultCamera().viewportHeight / 2-20)* Engine.getDefaultCamera().zoom );
		Engine.getSpriteBatch().end();
		render.end();
	}

	@Override
	public void show() {}

	@Override
	public void hide() {}

	@Override
	public InputProcessor getInputProcessor() {
		return new InputAdapter(){
			boolean moveCam = false;
			Vector2  beginPosition = new Vector2();
			@Override
			public boolean mouseMoved(int x, int y) {
				snapPoint.set(Engine.screenToWorld(x, y));
				return super.mouseMoved(x, y);
			}

			@Override
			public boolean touchDragged(int x, int y, int pointer) {
				snapPoint.set(Engine.screenToWorld(x, y));
				if(moveCam){
					Vector2 now = new Vector2(x,y);
					Engine.getDefaultCamera().position.x += beginPosition.x-x;
					Engine.getDefaultCamera().position.y += -(beginPosition.y-y);
					beginPosition = now;
					adapter.callUI.updateCameraInfo();
				}
				return super.touchDragged(x, y, pointer);
			}

			@Override
			public boolean touchDown(int x, int y, int pointer, int button) {
				if(button == Buttons.RIGHT){
					beginPosition.set(x,y);
					moveCam = true;
				}
				return super.touchDown(x, y, pointer, button);
			}

			@Override
			public boolean touchUp(int x, int y, int pointer, int button) {
				moveCam = false;
//				float offsetx = (Engine.getDefaultCamera().position.x-Engine.getDefaultCamera().viewportWidth/2)%16;
//				float offsety = (Engine.getDefaultCamera().position.x-Engine.getDefaultCamera().viewportWidth/2)%16;
//				if(offsetx!=0){
//					Engine.getDefaultCamera().position.x -= offsetx;
//				}
//				if(offsety!=0){
//					Engine.getDefaultCamera().position.y -= offsety;
//				}
				return super.touchUp(x, y, pointer, button);
			}
			@Override
			public boolean scrolled(int amount) {
				if(amount>0){
					Engine.getDefaultCamera().zoom -= 0.05f;
				}else{
					Engine.getDefaultCamera().zoom += 0.05f;
				}
				
				if(Engine.getDefaultCamera().zoom<0.1f){
					Engine.getDefaultCamera().zoom = 0.1f;
				}
				
				if(Engine.getDefaultCamera().zoom>3f){
					Engine.getDefaultCamera().zoom = 3f;
				}
				adapter.callUI.updateCameraInfo();
				return super.scrolled(amount);
			}
		};
	}
	
}
