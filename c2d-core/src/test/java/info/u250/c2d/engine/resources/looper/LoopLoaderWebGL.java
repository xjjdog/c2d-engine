package info.u250.c2d.engine.resources.looper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class LoopLoaderWebGL extends LoopLoader {

	@Override
	public void loadResource(String dataDir)  {
		FileHandle file = Gdx.files.internal(dataDir);
		loadFile(file);
	}

}
