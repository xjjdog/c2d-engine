package info.u250.c2d.tests.mesh;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.EngineDrive;
import info.u250.c2d.engine.Scene;
import info.u250.c2d.engine.resources.AliasResourceManager;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;


public class JumpyLineTest extends Engine {
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

		private float _myAmplitude = 0;
		private float _myFrequency = 1;
		private float _myPhase = 0;
		private float _myTimer = 0;
		
		@Override
		public void onLoadedResourcesCompleted() {
			final int width = (int)Engine.getWidth();
			Engine.setMainScene(new Scene() {
				@Override
				public void render(float delta) {
					Engine.getShapeRenderer().begin(ShapeType.Point);
					Engine.getShapeRenderer().setColor(Color.YELLOW);
					
					for(int x = 0; x < width;x++){
						float blend = 1 - Math.abs(width/2 - x)/(float)width/2f;
						blend =(float) Math.pow(blend, 3);
						float y = MathUtils.cosDeg(x / (float)width * 360 * 2 * 5 + _myPhase) * _myAmplitude * blend;
						Engine.getShapeRenderer().point(x,y+Engine.getHeight()/2,0);
					}
					Engine.getShapeRenderer().end();
				}
				@Override
				public InputProcessor getInputProcessor() {
					return null;
				}
				@Override
				public void update(float delta) {	
					_myTimer += delta;
					_myFrequency = Interpolation.bounceIn.apply(_myTimer / 5) * 4;
					_myPhase = Interpolation.bounceOut.apply(_myTimer / 20) * 360 * 10;
					_myAmplitude = (MathUtils.cosDeg(_myTimer * 360 * _myFrequency + 360) + 1)/2 * 100 * Interpolation.bounceOut.apply(1 - _myTimer / 5);
					
					if(_myTimer > 5){
						_myTimer = 0;
						_myFrequency = 1;
					}
				}
				@Override
				public void hide() {	
				}
				@Override
				public void show() {
				}
			});	
		}
	}
}
