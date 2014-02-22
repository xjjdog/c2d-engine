package info.u250.c2d.engine.load;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.resources.AliasResourceManager;
import com.badlogic.gdx.assets.AssetManager;
/**All games has loading screen , to load many resources such as textures ,sounds , musics .
 * this may take some times . So its important to supply a smoothly loading screen .<br/>
 * Libgdx supply a method to load resources , which called {@link AssetManager}  . We call get the loading percent and complete event from it.<br/>
 * Its no use to known how it be , all you must do is how to draw it . The simple example is {@link SimpleLoading} <br/>
 * To make new loading screen . extends this class . and set the attribute {@link info.u250.c2d.engine.EngineDrive.EngineOptions#loading} to the full class name
 * @author lycying@gmail.com
 */
public abstract class Loading  implements com.badlogic.gdx.utils.Disposable{
	/**when the resources loaded , the onReady will be called 
	 * @author lycying@gmail.com*/
	public interface LoadingComplete{
		public void onReady(AliasResourceManager<String> reg);
	}
	/**if you make a complex loading screen , it must load many resources and quickly it will be no use in the game logic .
	 * you can destroy these resources here */
	protected abstract void finishLoadingCleanup() ;
	/**Draw the loadding screen . such as the percent or a loading graphic . You even leave it blank if you want show nothing here .*/
	protected abstract void inLoadingRender(float delta) ;
	/**@see info.u250.c2d.engine.load.startup.StartupLoading.LoadingComplete*/
	protected LoadingComplete loadingComplete;
	/**the asset manager to load resources*/
	protected AssetManager manager;
	private boolean loaded = false;
	
	public boolean isLoaded() {
		return loaded;
	}
	public void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}
	public Loading(){
		manager = Engine.getAssetManager();
	}
	public void setLoadingComplete(LoadingComplete loadingComplete) {
		this.loadingComplete = loadingComplete;
	}
	
	/**the percent of the progress*/
	public float percent(){
		return this.manager.getProgress();
	}
	public boolean finished(){
		return this.manager.getProgress() == 1;
	}

	public void render(float delta){
		if(this.finished()){
			this.finishLoadingCleanup();
			if(null!=loadingComplete){
				loadingComplete.onReady(Engine.getAliasResourceManager());
				this.loaded = true;
			}
		}else{
			this.manager.update();
			this.inLoadingRender(delta);
		}
	}
	public void dispose(){
		this.finishLoadingCleanup();
		if(null!=manager){
			this.manager.dispose();
		}
	}
}