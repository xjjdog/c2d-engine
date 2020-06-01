package info.u250.c2d.engine.resources.rules;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.resources.AliasResourceManager.LoadResourceRule;
/**
 * @author lycying@gmail.com
 */
public class RuleTextureAtlas implements LoadResourceRule {
	@Override
	public boolean match(FileHandle file) {
		boolean result = file.extension().equals("atlas") ;
		if(result) Engine.getAssetManager().load(file.path().replace("\\", "/"),TextureAtlas.class);
		return  result;
	}
}