package info.u250.c2d.box2deditor.gdx.scenes;

import info.u250.c2d.box2d.model.fixture.b2CircleFixtureDefModel;
import info.u250.c2d.box2deditor.gdx.support.AbstractBox2dHelper;
import info.u250.c2d.box2deditor.gdx.support.Click;
import info.u250.c2d.box2deditor.gdx.support.Geometry;
import info.u250.c2d.engine.Engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.math.Vector2;

public class CircleHelper extends AbstractBox2dHelper<b2CircleFixtureDefModel> {
	
	final Vector2 secondPoint = new Vector2();
	Click click = Click.NONE;
	final private InputAdapter input = new InputAdapter() {
		@Override
		public boolean touchDown(int x, int y, int pointer, int button) {
			if(button == Buttons.LEFT){
				if (Click.NONE == click) {
					secondPoint.set(Engine.screenToWorld(x, y));
					click = Click.FIRST;
					// do start
				} else if (Click.FIRST == click) {
					secondPoint.set(Engine.screenToWorld(x, y));
					if (worldCenter.dst(secondPoint) > 0) {
						model.radius = worldCenter.dst(secondPoint);
						updateToUI();
						click = Click.NONE;
						// do end
					}
				}
			}
			return super.touchDown(x, y, pointer, button);
		}
		@Override
		public boolean mouseMoved(int x, int y) {
			if (Click.FIRST == click) {
				// do move
				secondPoint.set(Engine.screenToWorld(x, y));
				model.radius = worldCenter.dst(secondPoint);
				updateToUI();
			}
			return super.mouseMoved(x, y);
		}
	};
	
	public CircleHelper(MainScene adapter) {
		super(adapter);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glEnable(GL10.GL_BLEND);
		Geometry.renderCircle(model, worldCenter,0 ,false);
		Gdx.gl.glDisable(GL10.GL_BLEND);
	}

	@Override
	public InputProcessor getInputProcessor() {
		return input;
	}


	@Override
	public Class<b2CircleFixtureDefModel> getType() {
		return b2CircleFixtureDefModel.class;
	}
}
