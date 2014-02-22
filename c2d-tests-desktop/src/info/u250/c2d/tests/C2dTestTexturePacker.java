package info.u250.c2d.tests;

import com.badlogic.gdx.tools.imagepacker.TexturePacker2;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2.Settings;

public class C2dTestTexturePacker {
	public static void main(String[] args) {
		Settings settings = new Settings();
		settings.maxHeight = 1024;
		settings.maxHeight = 1024;
		settings.stripWhitespaceX = settings.stripWhitespaceY = false;
		settings.rotation = false;
		

		TexturePacker2.process(settings, 
				"assets-raw/turkey", 
				"../c2d-tests-android/assets/data/animationsprite",
				"turkey");
	}
}
