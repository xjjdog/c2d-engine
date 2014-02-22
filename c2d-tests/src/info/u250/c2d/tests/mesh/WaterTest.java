package info.u250.c2d.tests.mesh;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.EngineDrive;
import info.u250.c2d.engine.Scene;
import info.u250.c2d.engine.resources.AliasResourceManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class WaterTest extends Engine {
	@Override
	protected EngineDrive onSetupEngineDrive() {
		return new EngineX();
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	private class EngineX implements EngineDrive {
		@Override
		public void onResourcesRegister(AliasResourceManager<String> reg) {
			reg.texture("W", "data/water.png");
		}

		@Override
		public void dispose() {
		}

		@Override
		public EngineOptions onSetupEngine() {
			final EngineOptions opt = new EngineOptions(new String[] {"data/water.png"}, 800,480);
			opt.useGL20 = true;
			return opt;
		}

		@Override
		public void onLoadedResourcesCompleted() {
			final Water water = new Water(new TextureRegion(Engine.resource("W",Texture.class)),200,240,Color.BLUE,Color.CYAN);
			Engine.setMainScene(new Scene() {
				@Override
				public void render(float delta) {
					water.render(delta);
				}

				@Override
				public InputProcessor getInputProcessor() {
					return new InputAdapter() {
						@Override
						public boolean touchDown(int screenX, int screenY,
								int pointer, int button) {
							water.splash(200, 120+new Random().nextFloat() * 100);
							return super.touchDown(screenX, screenY, pointer,
									button);
						}
					};
				}

				@Override
				public void update(float delta) {
					water.update();
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

class WaterColumn {
	public float TargetHeight;
	public float Height;
	public float Speed;

	public void Update(float dampening, float tension) {
		float x = TargetHeight - Height;
		Speed += tension * x - Speed * dampening;
		Height += Speed;
	}
}
class Particle
{
	public Vector2 Position;
	public Vector2 Velocity;
	public float Orientation;

	public Particle(Vector2 position, Vector2 velocity, float orientation)
	{
		Position = position;
		Velocity = velocity;
		Orientation = orientation;
	}
}

class Water {
	public Water(TextureRegion particleTexture,int segment,float height,Color topColor,Color endColor) {
		this.particleTexture = particleTexture;
		columns = new WaterColumn[segment];

		for (int i = 0; i < columns.length; i++) {
			columns[i] = new WaterColumn();
			columns[i].Height = height;
			columns[i].TargetHeight = height;
			columns[i].Speed = 0;

		}
		scale = Engine.getWidth() / (columns.length -1);
		this.color1 = endColor;
		this.color2 = topColor;
		
		fbo = new FrameBuffer(Pixmap.Format.RGBA8888, (int)Engine.getWidth(), (int)Engine.getHeight(), false);
	}
	Color color1 = new Color(0.2f, 0.7f, 1f, 0.9f);
	Color color2 = new Color(0.2f, 0.5f, 1f, 0.8f);
	public float tension = 0.025f;
	public float dampening = 0.025f;
	public float spread = 0.25f;
	int tinkness = 2; 
	TextureRegion particleTexture;
	float scale = 0;
	Random rnd = new Random();
	WaterColumn[] columns = null;
	List<Particle> particles = new ArrayList<Particle>();
	FrameBuffer fbo ;
	
	public void update() {
		for (int i = 0; i < columns.length; i++)columns[i].Update(dampening, tension);

		float[] lDeltas = new float[columns.length];
		float[] rDeltas = new float[columns.length];

		// do some passes where columns pull on their neighbours
		for (int j = 0; j < 8; j++) {
			for (int i = 0; i < columns.length; i++) {
				if (i > 0) {
					lDeltas[i] = spread * (columns[i].Height - columns[i - 1].Height);
					columns[i - 1].Speed += lDeltas[i];
				}
				if (i < columns.length - 1) {
					rDeltas[i] = spread * (columns[i].Height - columns[i + 1].Height);
					columns[i + 1].Speed += rDeltas[i];
				}
			}

			for (int i = 0; i < columns.length; i++) {
				if (i > 0)
					columns[i - 1].Height += lDeltas[i];
				if (i < columns.length - 1)
					columns[i + 1].Height += rDeltas[i];
			}
		}

		for(Particle particle : particles){
			updateParticle(particle);
		}
	}
	public void render(float delta) {
		Gdx.gl.glEnable(GL20.GL_BLEND);
		final ImmediateModeRenderer renderer = Engine.getShapeRenderer().getRenderer();
		Engine.getShapeRenderer().begin(ShapeType.Filled);
		for (int i = 1; i < columns.length; i++) {

			Vector2 p1 = new Vector2((i - 1) * scale, 0);
			Vector2 p2 = new Vector2((i - 1) * scale, columns[i - 1].Height);
			Vector2 p3 = new Vector2(i * scale, columns[i].Height);
			Vector2 p4 = new Vector2(i * scale, 0);

			renderer.color(color1.r, color1.g, color1.b, color1.a);
			renderer.vertex(p1.x, p1.y, 0);
			renderer.color(color2.r, color2.g, color2.b, color2.a);
			renderer.vertex(p2.x, p2.y, 0);
			renderer.color(color2.r, color2.g, color2.b,color2.a);
			renderer.vertex(p3.x, p3.y, 0);

			renderer.color(color2.r, color2.g, color2.b, color2.a);
			renderer.vertex(p3.x, p3.y, 0);
			renderer.color(color1.r, color1.g, color1.b, color1.a);
			renderer.vertex(p4.x, p4.y, 0);
			renderer.color(color1.r, color1.g, color1.b,color1.a);
			renderer.vertex(p1.x, p1.y, 0);
			
			
			renderer.color(color1.r, color1.g, color1.b, color1.a*0.3f);
			renderer.vertex(p1.x, p2.y-tinkness, 0);
			renderer.color(color2.r, color2.g, color2.b, color2.a*0.3f);
			renderer.vertex(p2.x, p2.y, 0);
			renderer.color(color2.r, color2.g, color2.b,color2.a*0.3f);
			renderer.vertex(p3.x, p3.y, 0);

			renderer.color(color2.r, color2.g, color2.b, color2.a*0.3f);
			renderer.vertex(p3.x, p3.y, 0);
			renderer.color(color1.r, color1.g, color1.b, color1.a*0.3f);
			renderer.vertex(p4.x, p3.y-tinkness, 0);
			renderer.color(color1.r, color1.g, color1.b,color1.a*0.3f);
			renderer.vertex(p1.x, p2.y-tinkness, 0);
		}
		Engine.getShapeRenderer().end();

	
		fbo.begin();
		Gdx.graphics.getGL20().glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
		Engine.getSpriteBatch().setColor(color2);
		Engine.getSpriteBatch().setBlendFunction(GL20.GL_SRC_ALPHA,GL20.GL_ONE);
		Engine.getSpriteBatch().begin();
		for(Particle p:particles){
			Engine.getSpriteBatch().draw(particleTexture, p.Position.x, p.Position.y,
					0, 0, particleTexture.getRegionWidth(), particleTexture.getRegionHeight(),
					1, 1, p.Orientation);
		}
		Engine.getSpriteBatch().end();
		Engine.getSpriteBatch().setShader(null);
		fbo.end();
		
		Engine.getSpriteBatch().setBlendFunction(GL11.GL_SRC_ALPHA,GL11.GL_ONE);
		Engine.getSpriteBatch().begin();
		Engine.getSpriteBatch().draw(fbo.getColorBufferTexture(),0,0,fbo.getColorBufferTexture().getWidth(),fbo.getColorBufferTexture().getHeight(),0,0,1,1);
		Engine.getSpriteBatch().end();
		Engine.getSpriteBatch().setColor(Color.WHITE);
		Engine.getSpriteBatch().setBlendFunction(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	}

	void updateParticle(Particle particle) {
		float Gravity = -0.3f;
		particle.Velocity.y += Gravity;
		particle.Position.add(particle.Velocity);
		particle.Orientation = GetAngle(particle.Velocity);
	}

	private float GetAngle(Vector2 vector) {
		return (float) Math.atan2(vector.y, vector.x);
	}

	public float GetHeight(float x) {
		if (x < 0 || x > 800)
			return 240;

		return columns[(int) (x / scale)].Height;
	}

	private void CreateParticle(Vector2 pos, Vector2 velocity) {
		particles.add(new Particle(pos, velocity, 0));
	}

	private Vector2 FromPolar(float angle, float magnitude) {
		return new Vector2((float) Math.cos(angle), (float) Math.sin(angle))
				.scl(magnitude);
	}

	private float GetRandomFloat(float min, float max) {
		return (float) rnd.nextDouble() * (max - min) + min;
	}

	private Vector2 GetRandomVector2(float maxLength) {
		return FromPolar(GetRandomFloat(-MathUtils.PI, -MathUtils.PI),
				GetRandomFloat(0, maxLength));
	}

	private void CreateSplashParticles(float xPosition, float speed) {
		float y = GetHeight(xPosition);

		if (speed > 120) {
			for (int i = 0; i < speed / 8; i++) {
				Vector2 pos = new Vector2(xPosition, y)
						.add(GetRandomVector2(40));
				Vector2 vel = FromPolar(GetRandomFloat(-150, -30)
						* MathUtils.degreesToRadians,
						GetRandomFloat(0, 0.5f * (float) Math.sqrt(speed)));
				vel.y *= -1;
				CreateParticle(pos, vel);
			}
		}
	}

	public void splash(float xPosition, float speed) {
		int index = (int) MathUtils.clamp(xPosition / scale, 0,
				columns.length - 1);
		for (int i = Math.max(0, index - 0); i < Math.min(columns.length - 1,
				index + 1); i++) {
			columns[index].Speed = speed;
		}
		CreateSplashParticles(xPosition, speed);

	}

}
