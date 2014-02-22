package info.u250.c2d.engine;

import com.badlogic.gdx.InputProcessor;

import info.u250.c2d.graphic.C2dStage;

public class SceneStage extends C2dStage implements Scene {

	@Override
	public void update(float delta) {
		this.act(delta);
	}

	@Override
	public void render(float delta) {
		this.draw();
	}

	@Override
	public void show() {}

	@Override
	public void hide() {}

	@Override
	public InputProcessor getInputProcessor() {
		return this;
	}

}
