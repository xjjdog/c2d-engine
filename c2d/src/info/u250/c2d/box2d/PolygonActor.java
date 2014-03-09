package info.u250.c2d.box2d;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class PolygonActor extends Actor implements com.badlogic.gdx.utils.Disposable{
	Texture texture ;
	float[] vertices;
	Mesh mesh;
	List<Vector2[]> verticesBak;
	float u = 0;
	float v = 0;
	float offsetX;
	float offsetY=0;
	
	static public ShaderProgram createDefaultShader () {
		String vertexShader = "attribute vec4 " + ShaderProgram.POSITION_ATTRIBUTE + ";\n" //
			+ "attribute vec4 " + ShaderProgram.COLOR_ATTRIBUTE + ";\n" //
			+ "attribute vec2 " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0;\n" //
			+ "uniform mat4 u_projTrans;\n" //
			+ "varying vec4 v_color;\n" //
			+ "varying vec2 v_texCoords;\n" //
			+ "\n" //
			+ "void main()\n" //
			+ "{\n" //
			+ "   v_color = " + ShaderProgram.COLOR_ATTRIBUTE + ";\n" //
			+ "   v_texCoords = " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0;\n" //
			+ "   gl_Position =  u_projTrans * " + ShaderProgram.POSITION_ATTRIBUTE + ";\n" //
			+ "}\n";
		String fragmentShader = "#ifdef GL_ES\n" //
			+ "#define LOWP lowp\n" //
			+ "precision mediump float;\n" //
			+ "#else\n" //
			+ "#define LOWP \n" //
			+ "#endif\n" //
			+ "varying LOWP vec4 v_color;\n" //
			+ "varying vec2 v_texCoords;\n" //
			+ "uniform sampler2D u_texture;\n" //
			+ "void main()\n"//
			+ "{\n" //
			+ "  gl_FragColor = v_color * texture2D(u_texture, v_texCoords);\n" //
			+ "}";

		ShaderProgram shader = new ShaderProgram(vertexShader, fragmentShader);
		if (shader.isCompiled() == false) throw new IllegalArgumentException("Error compiling shader: " + shader.getLog());
		return shader;
	}
	
	private ShaderProgram shader;
	
	public PolygonActor(Texture texture,List<Vector2[]> vertices,float offsetX,float offsetY){
		shader = createDefaultShader();
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.texture = texture;
		this.verticesBak = vertices;
		this.texture.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
		u = ((float) 1 / texture.getWidth());
		v = ((float) 1 / texture.getHeight());
	
		int pointNumer = vertices.size()*3;
		mesh = new Mesh(false, pointNumer, pointNumer, 
				new VertexAttribute(Usage.Position, 3, ShaderProgram.POSITION_ATTRIBUTE), 
				new VertexAttribute(Usage.Color, 4, ShaderProgram.COLOR_ATTRIBUTE),
				new VertexAttribute(Usage.TextureCoordinates, 2, ShaderProgram.TEXCOORD_ATTRIBUTE+"0"));
		
		short[] indices = new short[pointNumer];
		for (int i = 0; i < pointNumer; i++) {
			indices[i] = (short) i;
		}
		mesh.setIndices(indices);
		
		this.vertices = new float[pointNumer*9];
		
		updateVertices();
		
	}
	
	void updateVertices(){
		Color c = getColor();
		int index = 0;
		for(Vector2[] vv:verticesBak){
			for(Vector2 p:vv){
				vertices[index++] = p.x+offsetX;
				vertices[index++] = p.y+offsetY;
				vertices[index++] = 0;
				vertices[index++] = c.r;
				vertices[index++] = c.g;
				vertices[index++] = c.b;
				vertices[index++] = c.a;
				vertices[index++] = u*(p.x+offsetX);
				vertices[index++] = -v*(p.y+offsetY);
			}
		}
		mesh.setVertices(vertices);
	}
	@Override
	public void draw(Batch batch, float parentAlpha) {
		updateVertices();
		GL20 gl = Gdx.gl20;
		gl.glEnable(GL20.GL_TEXTURE_2D);
		texture.bind();
		mesh.render(this.shader,GL20.GL_TRIANGLES);
	}

	@Override
	public void dispose() {
		if(null != mesh){
			mesh.dispose();
		}
	}
	
	
}
