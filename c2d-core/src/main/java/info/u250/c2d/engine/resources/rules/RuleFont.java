package info.u250.c2d.engine.resources.rules;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.resources.AliasResourceManager.LoadResourceRule;

/**
 * @author xjjdog
 */
public class RuleFont implements LoadResourceRule {
    @Override
    public boolean match(FileHandle file) {
        boolean result = file.extension().equals("fnt");
        if (result) {
            Engine.getAssetManager().load(file.path().replace("\\", "/"), BitmapFont.class);
        }
        return result;
    }
}
