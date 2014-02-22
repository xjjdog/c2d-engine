package info.u250.c2d.tests.transitions;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.Scene;
import info.u250.c2d.graphic.AdvanceSprite;

public class SceneB implements Scene {
	AdvanceSprite sprite ;
	private SceneB(){
		sprite = new AdvanceSprite(Engine.resource("A",Texture.class));
		sprite.setSize(Engine.getWidth(), Engine.getHeight());
		sprite.setColor(Color.YELLOW);
	}
	private static SceneB instance ;
	public static SceneB getInstance(){
		if(null == instance) {
			instance = new SceneB();
		}
		return instance;
	}

	@Override
	public void update(float delta) {}

	@Override
	public void render(float delta) {
		Engine.getSpriteBatch().begin();
		sprite.render(delta);
		Engine.getDefaultFont().draw(Engine.getSpriteBatch(), "This is scene B", 200, 200);
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
				Engine.setMainScene(SceneA.getInstance(), ((AbstractTransiton)Engine.get()).b2a());
				return super.touchUp(x, y, pointer, button);
			}
		};
	}

}
