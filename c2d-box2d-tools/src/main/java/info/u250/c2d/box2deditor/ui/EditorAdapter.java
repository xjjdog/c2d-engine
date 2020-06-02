package info.u250.c2d.box2deditor.ui;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import info.u250.c2d.box2deditor.gdx.EditorGdx;
import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.cmd.JarExportableCmd;

import java.awt.*;

public class EditorAdapter {
    public static void setupCanvas(Canvas canvas) {
        final Engine engine = new EditorGdx();
        final LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 1024;
        config.height = 600;

        new LwjglApplication(engine, config, canvas);
        JarExportableCmd.process();
    }
}
