package info.u250.c2d.graphic.surfaces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import info.u250.c2d.engine.Engine;

/**
 * default drawable of the mesh
 * ChangeLog :
 * 2012/02/29  support opengl2.0,use the shader language
 *
 * @author xjjdog
 */
public class TriangleSurfaces extends CurveSurfaces {
    static ShaderProgram shader = null;

    //used by engine dispose
    public static void disposeShader() {
        if (null != shader) {
            shader.dispose();
        }
    }

    private ShaderProgram createShader() {
        ShaderProgram shader = new ShaderProgram("#ifdef GL_ES\r\n" +
                "#define LOWP lowp\r\n" +
                "precision mediump float;\r\n" +
                "#else\r\n" +
                "#define LOWP \r\n" +
                "#endif\r\n" +
                "attribute vec4 a_position;\r\n" +
                "attribute vec2 a_texCoord0;\r\n" +
                "uniform mat4 u_projectionViewMatrix;\r\n" +
                "varying vec2 v_tex0;\r\n" +
                "void main() {\r\n" +
                "   gl_Position = u_projectionViewMatrix * a_position;\r\n" +
                "   v_tex0 = a_texCoord0;\r\n" +
                "}",

                "#ifdef GL_ES\r\n" +
                        "#define LOWP lowp\r\n" +
                        "precision mediump float;\r\n" +
                        "#else\r\n" +
                        "#define LOWP \r\n" +
                        "#endif\r\n" +
                        "varying vec2 v_tex0;\r\n" +
                        "uniform sampler2D u_texture0;\r\n" +
                        "uniform vec4 u_color;\r\n" +
                        "void main() {\r\n" +
                        " if (u_color.r == 0.0 && u_color.g == 0.0 && u_color.b == 0.0 && u_color.a == 0.0 ) { \r\n" +
                        " gl_FragColor = vec4(1, 0, 0, 0.5);\r\n" +
                        "} else { \r\n" +
                        " gl_FragColor = u_color; \r\n" +
                        "} gl_FragColor = gl_FragColor *  texture2D(u_texture0,  v_tex0);\r\n" +
                        "}");
        return shader;

    }

    protected TriangleSurfaces() {
    }

    public TriangleSurfaces(SurfaceData data) {
        super(data);
        if (null == shader) {
            shader = createShader();
        }
    }

    Vector3 tmp = new Vector3(
            Engine.getDefaultCamera().position.x - Engine.getWidth() / 2,
            Engine.getDefaultCamera().position.y - Engine.getHeight() / 2,
            0);

    @Override
    protected void doRender(float delta) {
        if (null != mesh) {
            GL20 gl = Gdx.gl20;
            gl.glActiveTexture(GL20.GL_TEXTURE0 + 0);
            gl.glEnable(GL20.GL_TEXTURE_2D);

            gl.glDisable(GL20.GL_BLEND);
            gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            texture.bind();
            shader.begin();
            if (data.followCamera) {
                shader.setUniformMatrix("u_projectionViewMatrix", Engine.getDefaultCamera().combined);
            } else {
                shader.setUniformMatrix("u_projectionViewMatrix", Engine.getDefaultCamera().combined.translate(tmp));
            }
            shader.setUniformf("u_color", 1, 1, 1, 1);
            shader.setUniformi("u_texture" + 0, 0);
            mesh.render(shader, data.primitiveType);
            shader.end();
        }
    }

    @Override
    protected void doBuild() {
        mesh = new Mesh(true, data.points.size, data.points.size,
                VertexAttribute.Position(),
                VertexAttribute.TexCoords(0));
        mesh.setVertices(verticesForUnscaledTexture(texture, data.points));
        short[] indices = new short[data.points.size];
        for (int i = 0; i < data.points.size; i++) {
            indices[i] = (short) i;
        }
        mesh.setIndices(indices);
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    protected float[] verticesForUnscaledTexture(Texture texture,
                                                 Array<Vector2> vertices) {
        float[] result = new float[5 * vertices.size];
        float u = ((float) 1 / texture.getWidth());
        float v = ((float) 1 / texture.getHeight());
        for (int i = 0; i < vertices.size; i++) {
            result[5 * i + 0] = vertices.get(i).x;
            result[5 * i + 1] = vertices.get(i).y;
            result[5 * i + 2] = 0;
            result[5 * i + 3] = +u * vertices.get(i).x;
            result[5 * i + 4] = -v * vertices.get(i).y;
        }
        return result;
    }
}
