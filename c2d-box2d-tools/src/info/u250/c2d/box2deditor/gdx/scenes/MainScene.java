package info.u250.c2d.box2deditor.gdx.scenes;

import info.u250.c2d.box2d.model.b2BodyDefModel;
import info.u250.c2d.box2deditor.gdx.CallUI;
import info.u250.c2d.box2deditor.gdx.CallUIImpl;
import info.u250.c2d.box2deditor.gdx.PhysicalWorld;
import info.u250.c2d.box2deditor.gdx.support.AbstractBox2dHelper;
import info.u250.c2d.box2deditor.gdx.support.BuildWorld;
import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.SceneGroup;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
@SuppressWarnings({"rawtypes","unchecked"})
public class MainScene extends SceneGroup {
	public static MainScene INSTANCE = null;

	public RightClickHelper rightClickHelper ;
//	public SceneModelAdapter currentSceneModelAdapter = null;
	public CallUI callUI = new CallUIImpl();
	public SimulationScene simulationScene;
	
	private Map<Class<?>,AbstractBox2dHelper> helpers = new HashMap<Class<?>, AbstractBox2dHelper>();
	public MainScene(){
		INSTANCE = this;
		simulationScene = new SimulationScene();
		rightClickHelper = new RightClickHelper(this);
		
		
		final CircleHelper circleHelper = new CircleHelper(this);
		final RectangleHelper rectangleHelper = new RectangleHelper(this);
		final PolygonHelper polygonHelper = new PolygonHelper(this);
		final SceneHelper senceHelper = new SceneHelper(this);
		
		register(circleHelper);
		register(rectangleHelper);
		register(polygonHelper);
		register(senceHelper);
	}
	void register(AbstractBox2dHelper helper){
		helpers.put(helper.getType(), helper);
	}
	public void bind(Object o){
		this.clear();
		this.add(rightClickHelper);
		AbstractBox2dHelper helper = helpers.get(o.getClass());
		if(null!=helper){
			this.add(helper);
			helper.bind(o);
		}else{
			helper = helpers.get(PhysicalWorld.MODEL.getClass());
			this.add(helper);
			helper.bind(PhysicalWorld.MODEL);
			if(o instanceof b2BodyDefModel){
				((SceneHelper)helper).setData(b2BodyDefModel.class.cast(o));
			}
		}	
		Gdx.input.setInputProcessor(getInputProcessor());
	}
	
	public void simulation(){
		Engine.doResume();
		this.clear();
		this.add(rightClickHelper);
		this.add(simulationScene);
		simulationScene.addAction(Actions.run(new Runnable() {
			@Override
			public void run() {
				simulationScene.simulation();
				Gdx.input.setInputProcessor(getInputProcessor());
			}
		}));
	}
	public void stopSimulation(){
		Engine.doResume();
		simulationScene.addAction(Actions.run(new Runnable() {
			@Override
			public void run() {
				simulationScene.stopSimulation();
				bind(PhysicalWorld.MODEL);
				BuildWorld.buildBodys();
			}
		}));	
	}
}
