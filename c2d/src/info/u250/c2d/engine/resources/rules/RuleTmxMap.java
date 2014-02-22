package info.u250.c2d.engine.resources.rules;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.maps.tiled.TiledMap;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.resources.AliasResourceManager.LoadResourceRule;

public class RuleTmxMap implements LoadResourceRule {

	@Override
	public boolean match(FileHandle file) {
		if(file.extension().equals("tmx")){
			Engine.getAssetManager().load(file.path().replace("\\", "/"),TiledMap.class);
			return true;
		}
		return false;
	}

}
