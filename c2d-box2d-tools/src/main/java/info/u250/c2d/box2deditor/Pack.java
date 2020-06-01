package info.u250.c2d.box2deditor;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;

public class Pack {
    public static void main(String args[]) throws Exception {
        Settings settings = new Settings();
        settings.alias = true;
        settings.edgePadding = false;
        settings.maxWidth = 1024;
        settings.maxHeight = 1024;

        TexturePacker
                .process(settings,
                        "/Users/lycying/codes/game/c2d-engine/c2d-box2d-tools/raw",
                        "/Users/lycying/codes/game/c2d-engine/c2d-box2d-tools/src/main/resources/data",
                        "cb2");
    }
}
