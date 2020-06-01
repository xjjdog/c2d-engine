package info.u250.c2d.tests;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;

public class C2dTestTexturePacker {
    public static void main(String[] args) {
        Settings settings = new Settings();
        settings.maxHeight = 1024;
        settings.maxHeight = 1024;
        settings.stripWhitespaceX = settings.stripWhitespaceY = false;
        settings.rotation = false;


        TexturePacker.process(settings,
                "/Users/lycying/codes/game/c2d-engine/c2d-tests-desktop/assets-raw/turkey",
                "/Users/lycying/codes/game/c2d-engine/c2d-tests-desktop/resources/data/animationsprite",
                "turkey");
    }
}
