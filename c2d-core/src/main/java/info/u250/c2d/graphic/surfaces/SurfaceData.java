package info.u250.c2d.graphic.surfaces;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class SurfaceData {
    public String name = "";
    /**
     * the points of the mesh
     */
    public Array<Vector2> points = new Array<>();
    /**
     * the texture which used to fill the mesh
     */
    public String texture = "";
    /**
     * the drawing method
     */
    public int primitiveType = GL20.GL_TRIANGLE_FAN;
    /**
     * if the surface follow the camera's position , currently only support GLES2.x
     * TODO : gl1.x support
     */
    public boolean followCamera = true;
}
