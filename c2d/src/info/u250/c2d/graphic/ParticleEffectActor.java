package info.u250.c2d.graphic;

import info.u250.c2d.engine.Engine;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ParticleEffectActor extends Actor {

	private ParticleEffect emitter;
	private boolean pauseWithEngine = false;
	
	public ParticleEffectActor(ParticleEffect pemitter,String... name) {
		this.emitter = new ParticleEffect();
		for(String s :name){
			this.emitter.getEmitters().add(new ParticleEmitter(pemitter.findEmitter(s)));
		}
	}

	@Override
	public void setColor(Color color) {
		for(ParticleEmitter e:this.emitter.getEmitters()){
			if(e.getTint().getColors().length != 3)return;
			float[] colors = new float[e.getTint().getColors().length];
			for(int i=0;i<colors.length;i+=3){
				colors[i] = color.r;
				colors[i+1] = color.g;
				colors[i+2] = color.b;
			}
			e.getTint().setColors(colors);
		}
		super.setColor(color);
	}
	public ParticleEffect getEmitter() {
		return emitter;
	}

	public void setEmitter(ParticleEffect emitter) {
		this.emitter = emitter;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		if(this.isVisible()){
			if (parentAlpha == 1) {
				if(Engine.isPause() && pauseWithEngine){}else{
					this.emitter.setPosition(this.getX(), this.getY());
					this.emitter.draw(batch, Engine.getDeltaTime());
				}
			}
		}
	}

	public boolean isPauseWithEngine() {
		return pauseWithEngine;
	}

	public void setPauseWithEngine(boolean pauseWithEngine) {
		this.pauseWithEngine = pauseWithEngine;
	}
	
}
