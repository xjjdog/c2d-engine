package info.u250.c2d.engine.resources;

import info.u250.c2d.engine.Engine;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ObjectMap;
/**
 * @author lycying@gmail.com
 */
public class SoundManager implements Disposable{
	ObjectMap<String,Sound> sounds = new ObjectMap<String,Sound>();
	
	float soundVolume = 1f;
	public float getSoundVolume(){
		return soundVolume;
	}
	
	public void setVolume(float volume){
		this.soundVolume = volume;
	}

	public void stopSound(final String res){
		this.sounds.get(res).stop();
	}
	public long playSound(final String res){
		return this.playSound(res, soundVolume);
	}
	public long playSound(final String res,final float soundVolume){
		final Sound soundFromPool = this.sounds.get(res);
		if(null==soundFromPool){
			final Sound soundFromAssets = Engine.resource(res);
			if(null!=soundFromAssets){
				this.sounds.put(res, soundFromAssets);
				return this.playSound(soundFromAssets, soundVolume);
			}
		}else{
			return this.playSound(soundFromPool, soundVolume);
		}
		return -1;
	}
	private long playSound(final Sound sound,final float soundVolume){
		return sound.play(soundVolume);
	}
	
	public void dispose(){
		for(Sound sound:sounds.values()){
			if(null!=sound)sound.dispose();
		}
		this.sounds.clear();
	}
}