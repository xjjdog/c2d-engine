package info.u250.c2d.engine.resources.rules;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.resources.AliasResourceManager.LoadResourceRule;

/**
 * @author xjjdog
 */
public class RuleTexture implements LoadResourceRule {
    @Override
    public boolean match(FileHandle file) {
        boolean result =
                file.extension().contains("png") ||
                        file.extension().contains("gif") ||
                        file.extension().contains("jpg") ||
                        file.extension().contains("bmp");
        if (result) {
            Engine.getAssetManager().load(file.path().replace("\\", "/"), Texture.class);
        }
        return result;
    }

}