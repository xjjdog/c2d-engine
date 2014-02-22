package info.u250.c2d.engine.resources;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.resources.looper.LoopLoader;
import info.u250.c2d.engine.resources.looper.LoopLoaderDesktop;
import info.u250.c2d.engine.resources.looper.LoopLoaderGeneral;
import info.u250.c2d.engine.resources.looper.LoopLoaderWebGL;
import info.u250.c2d.engine.resources.rules.RuleFont;
import info.u250.c2d.engine.resources.rules.RuleMusic;
import info.u250.c2d.engine.resources.rules.RuleParticleEffect;
import info.u250.c2d.engine.resources.rules.RuleSkin;
import info.u250.c2d.engine.resources.rules.RuleSound;
import info.u250.c2d.engine.resources.rules.RuleTexture;
import info.u250.c2d.engine.resources.rules.RuleTextureAtlas;
import info.u250.c2d.engine.resources.rules.RuleTmxMap;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;

/**
 * @author lycying@gmail.com
 */
public class AliasResourceManager<K>  {
	public AliasResourceManager(){
		if(Gdx.app.getType() == ApplicationType.Desktop){
			loopLoader = new LoopLoaderDesktop();
		}else if(Gdx.app.getType() == ApplicationType.WebGL){
			loopLoader = new LoopLoaderWebGL();
		}else{
			loopLoader = new LoopLoaderGeneral();
		}
	}
	private LoopLoader loopLoader ;
	public void setLoopLoader(LoopLoader loopLoader) {
		this.loopLoader = loopLoader;
	}
	/**the map to hold all resources*/
	private Map<K, Object>  resources = new HashMap<K, Object>();
	/**the map to hold all resources's alias*/
	private Map<K, String>  resources_alias = new HashMap<K, String>();
	/**remove the resources use the key */
	public synchronized void unload(String key){
		String key_alias = resources_alias.get(key);
		if(null!=key_alias){
			Engine.getAssetManager().unload(key_alias);
			resources_alias.remove(key);
		}
		resources.remove(key);
	}
	
	/** a simple method to get the real resources */
	@SuppressWarnings("unchecked")
	public <T> T get(K id) {
		return (T)resources.get(id);
	}	
	/** quick access texture*/
	public void texture(K key,String res){
		this.resources.put(key, Engine.getAssetManager().get(res, Texture.class));
		this.resources_alias.put(key, res);
	}
	/** quick access sound resource */
	public void sound(K key,String res){
		this.resources.put(key, Engine.getAssetManager().get(res, Sound.class));
		this.resources_alias.put(key, res);
	}
	/** quick access music resource */
	public void music(K key,String res){
		this.resources.put(key, Engine.getAssetManager().get(res, Music.class));
		this.resources_alias.put(key, res);
	}
	/** quick access texture atlas */
	public void textureAtlas(K key,String res){
		this.resources.put(key, Engine.getAssetManager().get(res, TextureAtlas.class));
		this.resources_alias.put(key, res);
	}
	/** quick access bitmap font */
	public void font(K key,String res){
		this.resources.put(key, Engine.getAssetManager().get(res, BitmapFont.class));
		this.resources_alias.put(key, res);
	}
	/** quick access skin */
	public void skin(K key,String res){
		this.resources.put(key, Engine.getAssetManager().get(res, Skin.class));
		this.resources_alias.put(key, res);
	}
	/** quick access particle */
	public void particleEffect(K key,String res){
		this.resources.put(key, Engine.getAssetManager().get(res, ParticleEffect.class));
		this.resources_alias.put(key, res);
	}
	/** quickly put any type resource object */
	public <T> void object(K key,T res){
		this.resources.put(key, res);
	}
	
	/**load resources*/
	public void load(String dataDir){
		loopLoader.loadResource(dataDir);
	}

	public static Array<LoadResourceRule>  RULES = new Array<LoadResourceRule>();
	static{
		RULES.add(new RuleTexture());
		RULES.add(new RuleSound());
		RULES.add(new RuleMusic());
		RULES.add(new RuleTextureAtlas());
		RULES.add(new RuleFont());
		RULES.add(new RuleSkin());
		RULES.add(new RuleTmxMap());
		RULES.add(new RuleParticleEffect());
	}
	public void addRule(LoadResourceRule rule){
		RULES.add(rule);
	}
	/**
	 * @author lycying@gmail.com
	 * this is a rule to load resources , if the file's suffix or contains dictionary match the load rule , then load it .
	 * such as the texture has the suffix ".png"
	 */
	public interface LoadResourceRule{
		public boolean match(FileHandle file);
	}

}