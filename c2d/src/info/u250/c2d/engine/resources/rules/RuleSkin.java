package info.u250.c2d.engine.resources.rules;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.resources.AliasResourceManager.LoadResourceRule;

import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
/**
 * @author lycying@gmail.com
 */
public class RuleSkin  implements LoadResourceRule{
	@Override
	public boolean match(FileHandle file) {
		//find the same name of the skin and subfix is png
		String texturePath = file.parent().path().replace("\\", "/");
		if(texturePath.endsWith("/")){
			texturePath += file.nameWithoutExtension()+".atlas";
		}else{
			texturePath += ("/"+file.nameWithoutExtension()+".atlas");
		}
		boolean result = file.extension().equals("json") && file.path().contains("skin");
		if(result) Engine.getAssetManager().load(file.path().replace("\\", "/"),Skin.class,new SkinLoader.SkinParameter(texturePath));
		return  result;
	}
}
