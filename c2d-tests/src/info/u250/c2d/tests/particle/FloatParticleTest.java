package info.u250.c2d.tests.particle;

import java.util.Random;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.EngineDrive;
import info.u250.c2d.engine.SceneStage;
import info.u250.c2d.engine.resources.AliasResourceManager;
import info.u250.c2d.graphic.ParticleEffectActor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.math.Vector2;


public class FloatParticleTest extends Engine {
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
				 ParticleEffectActor p = new ParticleEffectActor(particleEffect,"level-screen"){
                     float timeDelta = 0;
                     Random r = new Random();
                     Vector2 speed = new Vector2(400,200);
                     Vector2 direction = new Vector2(1,1).nor();
                     float SPEED = 400;
                     @Override
                     public void act(float delta) {
                             timeDelta += delta;
                             if(timeDelta>0.5f){
                                     timeDelta = 0;
                                     direction.set(r.nextFloat()*r.nextFloat(),r.nextFloat()).nor();
                             }

                             if(getX()>920){
                                     speed.x = -SPEED*2 ;
                             }
                             if(getY()>500){
                                     speed.y = -SPEED ;
                             }
                             if(getX()<10){
                                     speed.x = SPEED *2;
                             }
                             if(getY()<10){
                                     speed.y = SPEED ;
                             }

                             this.moveBy(direction.x*speed.x*delta,direction.y*speed.y*delta);
                     }
             };
             p.setPosition(100, 370);

			
				stage.addActor(p);
			}
		
			Engine.setMainScene(stage);	
		}
	}
}
