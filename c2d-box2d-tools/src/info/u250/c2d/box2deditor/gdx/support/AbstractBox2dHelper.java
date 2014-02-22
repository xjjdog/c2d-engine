package info.u250.c2d.box2deditor.gdx.support;

import info.u250.c2d.box2deditor.gdx.scenes.MainScene;
import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.Scene;
import info.u250.c2d.graphic.C2dStage;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public abstract class AbstractBox2dHelper<T> extends C2dStage implements Scene {
	final protected ShapeRenderer render;
	final protected Vector2 worldCenter = new Vector2();
	final protected MainScene adapter;
	public AbstractBox2dHelper(MainScene adapter){
		this.adapter = adapter;
		render = Engine.getShapeRenderer();
	}
	@Override
	public void update(float delta) {
		worldCenter.set(Engine.getWidth()/2,Engine.getHeight()/2);
	}
	@Override
	public void show() {}

	@Override
	public void hide() {}
	
	
	public abstract Class<T> getType();
	public void bind(T o){
		model = o;
	}
	protected T model = null;
	
	protected void updateToUI(){
		adapter.callUI.updateToUI(model);
	}

}
