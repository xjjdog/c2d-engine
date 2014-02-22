package info.u250.c2d.engine.resources.looper;

import java.io.File;

import com.badlogic.gdx.Gdx;

public class LoopLoaderDesktop extends LoopLoader {

	@Override
	public void loadResource(String dataDir) {
		loadDesktop(dataDir);
	}

	private void loadDesktop(String dataDir){
		File file = new File( "bin/"+dataDir );
		if(file.isDirectory()){
			for(String f:file.list()){
				loadDesktop((dataDir.endsWith("/")?dataDir:(dataDir+"/"))+f);
			}
		}else{
			loadFile(Gdx.files.internal(dataDir));
		}
	}
	
}
