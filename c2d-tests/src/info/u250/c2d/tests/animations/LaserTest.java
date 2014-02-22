package info.u250.c2d.tests.animations;

import info.u250.c2d.engine.Engine;
import info.u250.c2d.engine.EngineDrive;
import info.u250.c2d.engine.Scene;
import info.u250.c2d.engine.resources.AliasResourceManager;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;


public class LaserTest extends Engine {
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
			reg.texture("S1", "data/laser/start1.png");
			reg.texture("S2", "data/laser/start2.png");
			reg.texture("M1", "data/laser/mid1.png");
			reg.texture("M2", "data/laser/mid2.png");
			reg.texture("E1", "data/laser/end1.png");
			reg.texture("E2", "data/laser/end2.png");
			
			reg.object("Begin1",new Sprite(Engine.resource("S1",Texture.class)));
			reg.object("Begin2",new Sprite(Engine.resource("S2",Texture.class)));
			reg.object("Mid1",new Sprite(Engine.resource("M1",Texture.class)));
			reg.object("Mid2",new Sprite(Engine.resource("M2",Texture.class)));
			reg.object("End1",new Sprite(Engine.resource("E1",Texture.class)));
			reg.object("End2",new Sprite(Engine.resource("E2",Texture.class)));
			
		}
		@Override
		public void dispose() {}
		@Override
		public EngineOptions onSetupEngine() {
			final EngineOptions opt = new EngineOptions(new String[]{"data/laser/"},800,480);
			opt.useGL20 = true;
			return opt;
		}

		
		@Override
		public void onLoadedResourcesCompleted() {
			


			final Laser laser = new Laser();
			laser.begin1 = Engine.resource("Begin1");
			laser.begin2 = Engine.resource("Begin2");
			laser.mid1 = Engine.resource("Mid1");
			laser.mid2 = Engine.resource("Mid2");
			laser.end1 = Engine.resource("End1");
			laser.end2 = Engine.resource("End2");
			laser.positon.set(600,100);
			
			final Laser laser2 = new Laser();
			laser2.begin1 = Engine.resource("Begin1");
			laser2.begin2 = Engine.resource("Begin2");
			laser2.mid1 = Engine.resource("Mid1");
			laser2.mid2 = Engine.resource("Mid2");
			laser2.end1 = Engine.resource("End1");
			laser2.end2 = Engine.resource("End2");
			laser2.positon.set(0,200);
			laser2.color = Color.GREEN;
			laser2.degrees = -90;
			
			final Laser laser3 = new Laser();
			laser3.begin1 = Engine.resource("Begin1");
			laser3.begin2 = Engine.resource("Begin2");
			laser3.mid1 = Engine.resource("Mid1");
			laser3.mid2 = Engine.resource("Mid2");
			laser3.end1 = Engine.resource("End1");
			laser3.end2 = Engine.resource("End2");
			laser3.positon.set(400,Engine.getHeight());
			laser3.color = Color.YELLOW;
			laser3.degrees = 180;
			
			final Laser laser4 = new Laser();
			laser4.begin1 = Engine.resource("Begin1");
			laser4.begin2 = Engine.resource("Begin2");
			laser4.mid1 = Engine.resource("Mid1");
			laser4.mid2 = Engine.resource("Mid2");
			laser4.end1 = Engine.resource("End1");
			laser4.end2 = Engine.resource("End2");
			laser4.positon.set(400,200);
			laser4.color = Color.CYAN;
			laser4.degrees = 45;
			laser4.len = 100;
			
			Engine.setMainScene(new Scene() {
				float add ;
				@Override
				public void render(float delta) {
					add+=delta;
					Engine.getSpriteBatch().begin();
					laser.len = (300-add*100%300);
					laser.color.a = (1-add*100%300/300);
					laser.rayColor.a = (1-add*100%300/300);
					laser.render(delta);
					
					laser2.len = (10*add*100%700);
					laser2.render(delta);
					
					laser3.len = (2*add*100%400);
					laser3.render(delta);
					
					laser4.render(delta);
					laser4.degrees = 10*add;
					Engine.getSpriteBatch().end();
					
					Engine.debugInfo("the laser use 6 sprite and GL10.GL_ONE attribute");
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
class Laser {
	public Vector2 positon = new Vector2() ;
	public float len;
	public Color color = new Color(Color.RED);
	public Color rayColor = new Color(Color.WHITE);
	public float degrees;

	public Sprite begin1,begin2,mid1,mid2,end1,end2;
	
	
	public void render(float delta) {
		begin1.setColor(color);
		begin2.setColor(rayColor);
		mid1.setColor(color);
		mid2.setColor(rayColor);
		end1.setColor(color);
		end2.setColor(rayColor);
		
		mid1.setSize(mid1.getWidth(), len);
		mid2.setSize(mid1.getWidth(), len);
		
		begin1.setPosition(positon.x, positon.y);
		begin2.setPosition(positon.x, positon.y);
		
		mid1.setPosition(begin1.getX(), begin1.getY()+begin1.getHeight());
		mid2.setPosition(begin1.getX(), begin1.getY()+begin1.getHeight());
		
		end1.setPosition(begin1.getX(), begin1.getY()+begin1.getHeight()+mid1.getHeight());
		end2.setPosition(begin1.getX(), begin1.getY()+begin1.getHeight()+mid1.getHeight());
		
		begin1.setOrigin(begin1.getWidth()/2, 0);
		begin2.setOrigin(begin1.getWidth()/2, 0);
		
		
		mid1.setOrigin(mid1.getWidth()/2, -begin1.getHeight());
		mid2.setOrigin(mid2.getWidth()/2, -begin1.getHeight());
		end1.setOrigin(mid1.getWidth()/2, -begin1.getHeight()-mid1.getHeight());
		end2.setOrigin(mid2.getWidth()/2, -begin1.getHeight()-mid2.getHeight());
		
		
		begin1.setRotation(degrees);
		begin2.setRotation(degrees);
		mid1.setRotation(degrees);
		mid2.setRotation(degrees);
		end1.setRotation(degrees);
		end2.setRotation(degrees);
		
		
		Engine.getSpriteBatch().setBlendFunction(GL10.GL_SRC_ALPHA, GL10.GL_ONE);
		begin1.draw(Engine.getSpriteBatch());
		begin2.draw(Engine.getSpriteBatch());
		
		
		mid1.draw(Engine.getSpriteBatch());
		
		mid2.draw(Engine.getSpriteBatch());
		
		end1.draw(Engine.getSpriteBatch());
		end2.draw(Engine.getSpriteBatch());
		Engine.getSpriteBatch().setBlendFunction(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		
		
	}

}
