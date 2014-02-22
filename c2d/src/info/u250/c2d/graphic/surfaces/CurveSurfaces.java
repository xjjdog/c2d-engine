package info.u250.c2d.graphic.surfaces;

import info.u250.c2d.engine.Engine;

import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
/**
 * Cure surfaces is a mesh group use the draw method FAN or STRIP of opengles .
 * which defined some base points and draw them ordered ,
 * @author lycying@gmail.com
 */
public abstract class CurveSurfaces implements com.badlogic.gdx.utils.Disposable{
	/**
	 * The render interface rounding the method {@link CurveSurfaces#render(float)}, used to give more controls 
	 * @author lycying@gmail.com
	 */
	public interface CurveSurfacesRender{
		/** before render */
		public void preRender(float delta);
		/** after render */
		public void postRender(float delta);
	}
	private CurveSurfacesRender bRender;
	protected CurveSurfaces(){}
	public void setbRender(CurveSurfacesRender bRender) {
		this.bRender = bRender;
	}
	public SurfaceData data;
	
	
	public Texture texture;
	/**the main mesh used to draw */
	public Mesh mesh;
	
	public CurveSurfaces(SurfaceData data){
		this.data = data;
		this.build();
	}
	
	/**the main build method , we build the mesh and the texture and also the physical object
	 */
	public final void build(){
		texture = Engine.resource(data.texture,Texture.class);
		if(null!=this.texture){
			this.texture.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
			this.texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		}
		if(data.points.size>2 && this.texture !=null)this.doBuild();
	}
	/** to draw it */
	public void render(float delta){
		if(null!=bRender){
			bRender.preRender(delta);
		}
		this.doRender(delta);
		if(null!=bRender){
			bRender.postRender(delta);
		}
	}
	/**the draw method is implement at subclass */
	protected abstract void doRender(float delta);
	/**the build method is implement at subclass */
	protected abstract void doBuild();
	
	public void dispose(){
		if(null!=mesh){
			mesh.dispose();
			mesh = null;
		}
	}
}