package info.u250.c2d.engine.resources.looper;

import info.u250.c2d.engine.resources.AliasResourceManager;
import info.u250.c2d.engine.resources.AliasResourceManager.LoadResourceRule;

import com.badlogic.gdx.files.FileHandle;

public abstract class LoopLoader {
	public abstract void loadResource(String dataDir) ;
	
	protected void loadDirectory(FileHandle dir){
		for(FileHandle handle :dir.list()){
			if(handle.isDirectory()){
				this.loadDirectory(handle);
			}else{
				this.loadFile(handle);
			}
		}
	}
	protected void loadFile(FileHandle file){
		for(LoadResourceRule rule:AliasResourceManager.RULES){
			if(rule.match(file)){
				return;
			}
		}
		
	}
}