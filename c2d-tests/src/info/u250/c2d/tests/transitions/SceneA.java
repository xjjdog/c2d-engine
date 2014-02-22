package info.u250.c2d.tests.transitions;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.Scene;
import info.u250.c2d.graphic.AdvanceSprite;

public class SceneA implements Scene {
	AdvanceSprite sprite ;
	private SceneA(){
		sprite = new AdvanceSprite(Engine.resource("A",Texture.class));
		sprite.setSize(Engine.getWidth(), Engine.getHeight());
		sprite.setColor(Color.RED);
	}
	private static SceneA instance ;
	public static SceneA getInstance(){
		if(null == instance) {
			instance = new SceneA();
		}
		return instance;
	}

	@Override
	public void update(float delta) {}

	@Override
	public void render(float delta) {
		Engine.getSpriteBatch().begin();
		sprite.render(delta);
		Engine.getDefaultFont().draw(Engine.getSpriteBatch(), "This is scene A", 200, 200);
		Engine.getSpriteBatch().end();
	}

	@Override
	public void show() {}

	@Override
	public void hide() {}

	@Override
	public InputProcessor getInputProcessor() {
		return new InputAdapter(){
			@Override
			public boolean touchUp(int x, int y, int pointer, int button) {
				Engine.setMainScene(SceneB.getInstance(), ((AbstractTransiton)Engine.get()).a2b());
				return super.touchUp(x, y, pointer, button);
			}
		};
	}

}
