package info.u250.c2d.engine.resources.rules;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.resources.AliasResourceManager.LoadResourceRule;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
/**
 * @author lycying@gmail.com
 */
public class RuleMusic  implements LoadResourceRule{
	@Override
	public boolean match(FileHandle file) {
		boolean result = (
				file.extension().equals("ogg") || 
				file.extension().equals("wav") || 
				file.extension().equals("mp3"))&&
				(
						file.path().contains("music")
						||
						file.path().contains("musics")
						);
		if(result) Engine.getAssetManager().load(file.path().replace("\\", "/"),Music.class);
		return  result;
	}

}
