package info.u250.c2d.engine;

import info.u250.c2d.engine.resources.AliasResourceManager;

/**
 * A game must has a {@link EngineDrive} to supply a game logic . You game should begin here.
 *
 * @author lycying@gmail.com
 */
public interface EngineDrive extends com.badlogic.gdx.utils.Disposable {
    /**
     * setup the engine's config , its the config panel here
     */
    EngineOptions onSetupEngine();

    /**
     * the game init should be here , you may init the game scene and sence group
     */
    void onLoadedResourcesCompleted();

    /**
     * the rescources's full path is a long key , we make its alias to identify it . such as use RES instead of "data/res/res/res/res/haha.png"
     */
    void onResourcesRegister(AliasResourceManager<String> reg);


    /**
     * the game config
     *
     * @author lycying@gmail.com
     */
    final class EngineOptions {
        public boolean catchBackKey = false;

        /**
         * load all the resources atomically
         * its a "String[]", so you can supply a dictionary or a full path of the resources
         * or  exclude  some resources paths
         *
         * @see {@link AliasResourceManager}
         */
        public String[] assets = new String[]{"data/"};

        /**
         * the scene width and scene height . we support two android devices very well :800x480,480x320
         */
        public float width = 480;
        /**
         * the scene height
         */
        public float height = 320;

        /**
         * if the global debug flag , if it is true , we will always show the debuginfomation label on the bottom of the screen
         */
        public boolean debug = true;

        /**
         * auto resume manager the Engine's running state . If you set it false , you must call
         * {@link Engine#doResume() } manually to resume the game or it will keep pause
         */
        public boolean autoResume = false;
        /**
         * if set true , we will reset the engine's {@link #width} and {@link #height}
         */
        public boolean resizeSync = false;

        /**
         * the game config xml file ,to store some attributes such as the sound state
         */
        public String configFile = "c2d.temp.xml";

        public boolean useGL20 = false;

        public EngineOptions(String[] assets, float width, float height) {
            this.assets = assets;
            this.width = width;
            this.height = height;
        }
    }
}
