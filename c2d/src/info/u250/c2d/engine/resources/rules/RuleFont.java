package info.u250.c2d.engine.resources.rules;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.resources.AliasResourceManager.LoadResourceRule;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
/**
 * @author lycying@gmail.com
 */
public class RuleFont  implements LoadResourceRule{
	@Override
	public boolean match(FileHandle file) {
		boolean result = file.extension().equals("fnt");
		if(result) Engine.getAssetManager().load(file.path().replace("\\", "/"),BitmapFont.class);
		return  result;
	}
}
