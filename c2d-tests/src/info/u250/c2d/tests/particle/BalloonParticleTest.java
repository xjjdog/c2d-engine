package info.u250.c2d.tests.particle;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.EngineDrive;
import info.u250.c2d.engine.SceneStage;
import info.u250.c2d.engine.resources.AliasResourceManager;
import info.u250.c2d.graphic.ParticleEffectActor;
import info.u250.c2d.graphic.WebColors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;


public class BalloonParticleTest extends Engine {
	@Override
	protected EngineDrive onSetupEngineDrive() {
		return new EngineX();
	}
	@Override
	public void dispose () {
		super.dispose();
	}
	
	private class EngineX implements EngineDrive{
		@Override
		public void onResourcesRegister(AliasResourceManager<String> reg) {
		}
		@Override
		public void dispose() {}
		@Override
		public EngineOptions onSetupEngine() {
			final EngineOptions opt = new EngineOptions(new String[]{},800,480);
			return opt;
		}

		@Override
		public void onLoadedResourcesCompleted() {
			final ParticleEffect particleEffect = new ParticleEffect();
			particleEffect.load(Gdx.files.internal("data/particles/D.pp"), Gdx.files.internal("data/particles/"));
			
			SceneStage stage = new SceneStage();
			{
				final ParticleEffectActor actor = new ParticleEffectActor(particleEffect, "balloon");
				actor.setColor(WebColors.GREEN_YELLOW.get());
				actor.setPosition(100, -100);
				stage.addActor(actor);
			}
			{
				final ParticleEffectActor actor = new ParticleEffectActor(particleEffect, "balloon");
				actor.setColor(WebColors.RED.get());
				actor.setPosition(100, -100);
				stage.addActor(actor);
			}
			Engine.setMainScene(stage);	
		}
	}
}
