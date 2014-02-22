package info.u250.c2d.box2deditor;

import com.badlogic.gdx.tools.imagepacker.TexturePacker2;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2.Settings;

public class Pack {
	public static void main(String args[]) throws Exception {
		Settings settings = new Settings();
		settings.alias = true;
		settings.edgePadding=false;
		settings.maxWidth = 1024;
		settings.maxHeight = 1024;

		TexturePacker2
		.process(settings,
				"raw",
				"assets/data/",
				"cb2");
	}
}
