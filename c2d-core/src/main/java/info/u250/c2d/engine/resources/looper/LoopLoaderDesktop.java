package info.u250.c2d.engine.resources.looper;

import com.badlogic.gdx.Gdx;

import java.io.File;
import java.net.URL;

/**
 * @author xjjdog
 */
public class LoopLoaderDesktop extends LoopLoader {

    @Override
    public void loadResource(String dataDir) {
        loadDesktop(dataDir);
    }

    private void loadDesktop(String dataDir) {
        URL url = this.getClass().getClassLoader().getResource(dataDir);
        File file = new File(url.getFile());
        if (file.isDirectory()) {
            for (String f : file.list()) {
                loadDesktop((dataDir.endsWith("/") ? dataDir : (dataDir + "/")) + f);
            }
        } else {
            loadFile(Gdx.files.internal(dataDir));
        }
    }

}
