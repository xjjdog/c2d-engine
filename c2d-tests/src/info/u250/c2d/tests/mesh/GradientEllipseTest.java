package info.u250.c2d.tests.mesh;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.EngineDrive;
import info.u250.c2d.engine.Scene;
import info.u250.c2d.engine.resources.AliasResourceManager;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer10;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;


public class GradientEllipseTest extends Engine {
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
			final ImmediateModeRenderer10 renderer = new ImmediateModeRenderer10();
			final GradientEllipse _myGradientEllipse = new GradientEllipse(250,250, new Color(0,0,1,1), new Color(1,1,1,1), 100, 200);
			Engine.setMainScene(new Scene() {
				@Override
				public void render(float delta) {
					_myGradientEllipse.draw(renderer);
				}
				@Override
				public InputProcessor getInputProcessor() {
					return null;
				}
				@Override
				public void update(float delta) {	
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
class GradientEllipse {
	
	private float _myInnerRadius;
	private Color _myGradientColor;
	private Color _myColor;
	private Vector2 _myCenter;
	private float _myRadius;
	private float _myScale = 1;
	
	public GradientEllipse(Vector2 theCenter, Color theColor, Color theGradientColor, float theInnerRadius, float theRadius) {
		_myGradientColor = theGradientColor;
		_myInnerRadius = theInnerRadius;
		_myCenter = theCenter;
		_myRadius = theRadius;
		_myColor = theColor;
	}

	public GradientEllipse(float theCenterX, float theCenterY, Color theColor, Color theGradientColor, float theInnerRadius, float theRadius) {
		this(new Vector2(theCenterX, theCenterY), theColor, theGradientColor,theInnerRadius,theRadius);
	}
	
	public void draw(ImmediateModeRenderer render) {
	    int accuracy = (int)(4+Math.sqrt((_myRadius  +_myRadius) * _myScale)*3);
	    
	    float inc = 360 / accuracy;

	    float val = 0;

	    render.color(_myColor.r, _myColor.g, _myColor.b, _myColor.a);
	    render.begin(Engine.getDefaultCamera().combined,GL10.GL_TRIANGLE_FAN);
		render.normal(0, 0, 1);
		render.vertex(_myCenter.x,_myCenter.y,0);
		for (int i = 0; i < accuracy; i++) {
			render.vertex(
				_myCenter.x + MathUtils.cosDeg(val) * _myInnerRadius * _myScale, 
				_myCenter.y + MathUtils.sinDeg(val) * _myInnerRadius * _myScale,
				0
			);
			val += inc;
		}
		// back to the beginning
		render.vertex(_myCenter.x + _myInnerRadius * _myScale, _myCenter.y,0);
		render.end();
		
		render.begin(Engine.getDefaultCamera().combined,GL10.GL_TRIANGLE_STRIP);
		render.normal(0, 0, 1);
		for (int i = 0; i < accuracy; i++) {
			render.color(_myColor.r, _myColor.g, _myColor.b, _myColor.a);
			render.vertex(
				_myCenter.x + MathUtils.cosDeg(val) * _myInnerRadius * _myScale, 
				_myCenter.y + MathUtils.sinDeg(val) * _myInnerRadius * _myScale,
				0
			);
			render.color(_myGradientColor.r,_myGradientColor.g,_myGradientColor.b,_myGradientColor.a);
			render.vertex(
				_myCenter.x + MathUtils.cosDeg(val) * _myRadius * _myScale, 
				_myCenter.y + MathUtils.sinDeg(val) * _myRadius * _myScale,
				0
			);
			val += inc;
		}
		// back to the beginning
		render.color(_myColor.r,_myColor.g,_myColor.b,_myColor.a);
		render.vertex(_myCenter.x + _myInnerRadius * _myScale, _myCenter.y,0);
		render.color(_myGradientColor.r,_myGradientColor.g,_myGradientColor.b,_myGradientColor.a);
		render.vertex(_myCenter.x + _myRadius * _myScale, _myCenter.y,0);
		render.end();
	}

}