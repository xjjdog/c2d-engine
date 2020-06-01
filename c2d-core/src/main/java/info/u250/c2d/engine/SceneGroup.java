package info.u250.c2d.engine;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.Array;

/**
 * Sometimes you may want to has muti layer of the scenes , that's what the {@link #SceneGroup()} do .
 * Such as a HUD scene on top of a Game scene . Its simply draw them together and all is up to you .
 * @author lycying@gmail.com
 */
public class SceneGroup extends Array<Scene> implements Scene {
	final private InputMultiplexer input = new InputMultiplexer();
	@Override
	public void update(float delta) {
		for(Scene scene:this){
			scene.update(delta);
		}
	}

	@Override
	public void render(float delta) {
		for(Scene scene:this){
			scene.render(delta);
		}
	}

	@Override
	public void show() {
		for(Scene scene:this){
			scene.show();
		}
	}

	@Override
	public void hide() {
		for(Scene scene:this){
			scene.hide();
		}
	}

	@Override
	public InputProcessor getInputProcessor() {
		if(this.size>0){
			input.clear();
			for(int i=this.size-1;i>=0;i--){
				if(null!=this.get(i).getInputProcessor()){
					input.addProcessor(this.get(i).getInputProcessor());
				}
			}
			return input;
		}
		return null;
	}

}
