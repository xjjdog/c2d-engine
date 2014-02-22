package info.u250.c2d.engine;

import info.u250.c2d.accessors.C2dCameraAccessor;
import info.u250.c2d.accessors.FloatValueAccessor;
import info.u250.c2d.accessors.FloatValueAccessor.FloatValue;
import info.u250.c2d.accessors.MeshMaskAccessor;
import info.u250.c2d.accessors.SpriteAccessor;
import info.u250.c2d.engine.CoreProvider.CoreEvents;
import info.u250.c2d.engine.CoreProvider.TransitionType;
import info.u250.c2d.engine.EngineDrive.EngineOptions;
import info.u250.c2d.engine.events.EventManager;
import info.u250.c2d.engine.events.EventManagerImpl;
import info.u250.c2d.engine.load.Loading.LoadingComplete;
import info.u250.c2d.engine.load.in.InGameLoading;
import info.u250.c2d.engine.load.startup.SimpleLoading;
import info.u250.c2d.engine.load.startup.StartupLoading;
import info.u250.c2d.engine.resources.AliasResourceManager;
import info.u250.c2d.engine.resources.LanguagesManager;
import info.u250.c2d.engine.resources.MusicManager;
import info.u250.c2d.engine.resources.SoundManager;
import info.u250.c2d.engine.transitions.TransitionFactory;
import info.u250.c2d.graphic.FadeMask;
import info.u250.c2d.graphic.surfaces.TriangleSurfaces;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
/**
 * The main game engine . it supply many usefully static method to access the managers of the game.
 * you game should begin here , and simply extends this.
 * @author lycying@gmail.com
 */
public abstract class Engine extends ApplicationAdapter{
	private static Engine instance = null;
	public Engine(){
		instance = this;
		this.engineDrive = this.onSetupEngineDrive();
		this.engineConfig = engineDrive.onSetupEngine();
	}
	@SuppressWarnings("unchecked")
	public static final <T extends Engine> T get(){
		return (T)instance;
	}
	private EngineCallback engineCallback = new DefaultEngineCallback();
	public static void setEngineCallback(EngineCallback engineCallback){
		instance.engineCallback = engineCallback;
	}
	public static EngineCallback getEngineCallback(){
		return instance.engineCallback;
	}
	
	private ShapeRenderer shapeRenderer;
	/** the game logic */
	private EngineDrive engineDrive;
	/** the game configure */
	private EngineOptions engineConfig;
	/** the event manager */
	private EventManager eventManager;
	/** the core asset manager of Libgdx */
	private AssetManager assetManager ;
	/** default bitmap font */
	private BitmapFont defaultFont;
	/** default sprite batch */
	private SpriteBatch spriteBatch ;
	/** default camera */
	private C2dCamera defaultCamera;
	/** the loadding screen */
	private StartupLoading startupLoading ;
	/** the in game loading */
	private InGameLoading ingameLoading;
	/** the music manager */
	private MusicManager musicManager;
	/** the sound manager */
	private SoundManager soundManager;
	/** the resources alias manager */
	private AliasResourceManager<String> aliasResourceManager;
	/** LanguagesManager */
	private LanguagesManager languagesManager;
	/** Preferences */
	private Preferences preferences;
	/** the fps label */
	private C2dFps fps;
	/** the main game scene to show the graphic */
	private Scene mainScene;
	/** the transition scene to switch two scene */
	private Transition transitionScene;
	/** its a event manager that [Aurelien Ribon]  supply  */
	private TweenManager tweenManager;
	/** if the game is running */
	private boolean running = true; 
	/**
	 * This is one of the most important methods. 
	 * The typical, all the game logic and rendering entry will go from here. 
	 * We do this purpose, main is to let business operation independent, 
	 * and you may no longer care about many things such as resources destroyed, load control etc,
	 *  and to focus on the game itself.
	 */
	protected abstract EngineDrive onSetupEngineDrive();
	
	/**
	 * Set the main of operation, played the role of the page. You should never use this method
	 */
	protected final static void _setMainScene(Scene mainScene){
		instance.mainScene = mainScene;
	}
	
	/**
	 * When in the scene when switching between. 
	 * Will cause a series of gradual movement. 
	 * The last. New rendering methods will replace the old
	 */
	public final static void setMainScene(Scene mainScene){
		setMainScene(mainScene, TransitionType.Fade);
	}
	public final static void setMainScene(Scene mainScene,TransitionType type){
		Engine.setMainScene(mainScene, type, 500);
	}
	public final static void setMainScene(Scene mainScene,TransitionType type,int halfDurationMillis){
		Engine.doResume();
		if(instance.transitionScene.isTransiting()) return;
		if(instance.mainScene == mainScene ) return ;
		instance.transitionScene = TransitionFactory.getTransitionScene(type);
		Gdx.input.setInputProcessor(null);
		instance.transitionScene.transition(instance.mainScene, mainScene,halfDurationMillis);
	}
	/**
	 * You may want to know what's the main scene . Call this
	 */
	public final static Scene getMainScene(){
		return Engine.instance.mainScene;
	}
	
	/**
	 * First of all, load resources. 
	 * When resource loading was finished,
	 *  we  will enter the game initialization operation.
	 */
	@Override
	public final void create () {
		try{
			this.shapeRenderer = new ShapeRenderer();
			//set up the FPS
			if(engineConfig.debug)this.fps = new C2dFps();
			if(engineConfig.catchBackKey)Gdx.input.setCatchBackKey(true);
			//set up the TweenEngine 
			this.setupTweenEngine();
			//set up the camera 
			this.setupCamera();
			//the resource manager
			this.assetManager = new AssetManager();
			this.assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
			this.aliasResourceManager = new AliasResourceManager<String>();
			this.soundManager = new SoundManager();
			this.musicManager = new MusicManager();
			this.languagesManager = new LanguagesManager();
			//the event manager
			this.eventManager = new EventManagerImpl();
			//set up the sprite batch
			this.spriteBatch = new SpriteBatch();
			//set up the default font
			if(Gdx.app.getType()==ApplicationType.WebGL || Gdx.app.getType() == ApplicationType.iOS){
				this.defaultFont = new BitmapFont(Gdx.files.internal("data/font-c2d.fnt"),false);
			}else{
				this.defaultFont = new BitmapFont();
			}
			//set up the default preferences
			this.preferences = Gdx.app.getPreferences(engineConfig.configFile);
			if(null!=engineCallback){
				engineCallback.preLoad(Gdx.graphics.getDesktopDisplayMode(),engineConfig.assets);
			}
			//loading screen
			this.setupLoading();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	protected StartupLoading getStartupLoading(){
		return new SimpleLoading();
	}
	protected InGameLoading getInGameLoading(){
		return new info.u250.c2d.engine.load.in.SimpleLoading();
	}
	private void setupLoading() {
		this.startupLoading = getStartupLoading();
		this.ingameLoading = getInGameLoading();
		this.startupLoading.setLoaded(false);
		this.ingameLoading.setLoaded(true);
		this.startupLoading.setLoadingComplete(new LoadingComplete() {
			@Override
			public void onReady(AliasResourceManager<String> alias) {
				transitionScene = TransitionFactory.getTransitionScene(TransitionType.Fade);
				engineDrive.onResourcesRegister(aliasResourceManager);
				engineDrive.onLoadedResourcesCompleted();
				if(null!=engineCallback){
					engineCallback.postLoad();
				}
			}
		});
	}
	
	private void setupTweenEngine(){
		this.tweenManager = new TweenManager();
		Tween.registerAccessor(Sprite.class,new SpriteAccessor());
		Tween.registerAccessor(C2dCamera.class, new C2dCameraAccessor());
		Tween.registerAccessor(FadeMask.class, new MeshMaskAccessor());
		Tween.registerAccessor(FloatValue.class, new FloatValueAccessor());
	}
	private void setupCamera(){
		this.defaultCamera = new C2dCamera(this.engineConfig.width,this.engineConfig.height);
	}
	@Override
	public final void pause() {
		doPause();
		eventManager.fire(CoreEvents.SystemPause, this);
		super.pause();
	}
	@Override
	public final void resume() {
		if(engineConfig.autoResume)
			doResume();
		eventManager.fire(CoreEvents.SystemResume, this);
		super.resume();
	}
	
	
	public final static void doPause() {
		instance.running = false;
		instance.tweenManager.pause();
	}
	public final static void doResume() {
		instance.running = true;
		instance.tweenManager.resume();
	}
	public final static boolean isPause(){
		return !instance.running;
	}
	
	@Override
	public final void render() {
		if(null == startupLoading)return;
		
		if(Gdx.graphics.isGL20Available()){
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		}else{
			Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		}
		final float delta = Engine.getDeltaTime();
		this.defaultCamera.update();
		if(Gdx.graphics.isGL11Available()){
			this.defaultCamera.apply(Gdx.graphics.getGL11());
		}
		this.spriteBatch.setProjectionMatrix(this.defaultCamera.combined);
		if(startupLoading.isLoaded()){
			if(running){
				if(null == mainScene){
					Gdx.app.log("Error", "you must supply a main Scene , Set it at EngineDriveer#onLoadedResourcesCompleted()");
					System.exit(-1);
				}
				tweenManager.update(delta*1000);
				mainScene.update(delta);
			}
			eventManager.update(delta);
			if(transitionScene.isTransiting()){
				transitionScene.render(delta);
			}else{
				mainScene.render(delta);
			}
			//the in game loading
			if(!ingameLoading.isLoaded()){
				ingameLoading.render(delta);
			}
			
			if(engineConfig.debug)fps.render(delta);
		}else{
			startupLoading.render(delta);
		}
	}

	/**
	 * In game load.
	 */
	public final static void load(String[] assets,LoadingComplete loadingComplete){
		if(null == assets || 0 == assets.length) return ;
	
		instance.ingameLoading.setLoadingComplete(loadingComplete);
		instance.ingameLoading.setLoaded(false);
		for(final String path:assets){
			instance.aliasResourceManager.load(path);
		}
	}
	
	public final static <T> T resource(String key){
		return instance.aliasResourceManager.get(key);
	}
	public final static <T> T resource(String key,Class<T> type){
		return instance.aliasResourceManager.get(key);
	}
	
	//Change to OrthographicCamera
//	private static Ray ray = null;
//	private final static Plane xzPlane = new Plane(new Vector3(0, 1, 0), new Vector3(1, 0, 0),new Vector3(1, 1, 0));
//	private final static Vector3 intersection = new Vector3();
	private final static Vector3 unproject = new Vector3();
	public final static Vector2 screenToWorld(float x, float y) {	
//		ray = instance.defaultCamera.getPickRay(x, y);
//		Intersector.intersectRayPlane(ray, xzPlane, intersection);
		instance.defaultCamera.unproject(unproject.set(x, y, 0));
//		screenCoords.x = Vector3.tmp.x;
//		screenCoords.y = Vector3.tmp.y;
		return new Vector2(unproject.x,unproject.y);
	}
	
	
	public final static void debugInfo(String str){
		getSpriteBatch().begin();
		getDefaultFont().drawMultiLine(getSpriteBatch(), str, 0, getHeight());
		getSpriteBatch().end();
	}
	public final static float getDeltaTime(){
		return Math.min(1f/60f,Gdx.graphics.getDeltaTime());
	}

	public final static MusicManager getMusicManager() {
		return instance.musicManager;
	}

	public final static AliasResourceManager<String> getAliasResourceManager(){
		return instance.aliasResourceManager;
	}
	public final static SoundManager getSoundManager() {
		return instance.soundManager;
	}

	/** you should never use this method , its only needed in Engine */
	public final static AssetManager getAssetManager() {
		return instance.assetManager;
	}
	
	
	public final static SpriteBatch getSpriteBatch() {
		return instance.spriteBatch;
	}

	public final static C2dCamera getDefaultCamera() {
		return instance.defaultCamera;
	}
	
	public final static float getHeight(){
		return instance.engineConfig.height;
	}
	
	public final static boolean useGL20(){
		return instance.engineConfig.useGL20;
	}
	
	public final static float getWidth(){
		return instance.engineConfig.width;
	}
	
	public final static BitmapFont getDefaultFont(){
		return instance.defaultFont;
	}
	
	public final static EventManager getEventManager(){
		return instance.eventManager;
	}
	public final static TweenManager getTweenManager(){
		return instance.tweenManager;
	}
	public final static LanguagesManager getLanguagesManager(){
		return instance.languagesManager;
	}
	
	public final static Preferences getPreferences(){
		return instance.preferences;
	}
	
	public final static ShapeRenderer getShapeRenderer(){
		return instance.shapeRenderer;
	}
	@Override
	public void resize(int width, int height) {
		if(this.engineConfig.resizeSync){
			this.engineConfig.width = width;
			this.engineConfig.height = height;
			this.defaultCamera.resize(width, height);
		}
	}
	
	@Override
	public void dispose() {
		try{
			TriangleSurfaces.disposeShader();//dispose the shader
			
			this.tweenManager.killAll();
			engineDrive.dispose();
			if(null!=shapeRenderer){
				shapeRenderer.dispose();
				shapeRenderer = null;
			}
			if(null!=defaultFont){
				defaultFont.dispose();
				defaultFont = null;
			}
			if(null!=soundManager){
				soundManager.dispose();
				soundManager = null;
			}
			if(null!=musicManager){
				musicManager.dispose();
				musicManager = null;
			}
			if(startupLoading!=null){
				if(!startupLoading.finished()){
					this.assetManager.finishLoading();
				}
				startupLoading.dispose();
				startupLoading = null;
			}
			if(null!=spriteBatch){
				this.spriteBatch.dispose();
				spriteBatch = null;
			}
			if(null!=preferences){
				preferences.flush();
			}
			this.assetManager = null;
			this.defaultCamera = null;
			instance = null;
			super.dispose();
		}catch (Exception ex){
			//ignore
			ex.printStackTrace();
		}
	}
	
	
}