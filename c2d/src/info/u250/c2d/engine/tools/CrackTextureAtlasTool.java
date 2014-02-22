package info.u250.c2d.engine.tools;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.TextureAtlasData;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.TextureAtlasData.Region;
/**
 * <pre>
 public class ExportPackerTool extends ApplicationAdapter{
	@Override
	public void create() {
		try {
			ExportTextureAtlasTool.decode("E:/codes/dig/digs-desktop/assets/data/all.atlas","C:/Users/Administrator/Desktop/test/");
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.create();
	}
	public static void main(String args[]){
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 10;
		config.height= 10;
		new LwjglApplication(new ExportPackerTool(),config);
	}
}
 * </pre>
 *
 */
public class CrackTextureAtlasTool{
	//absolute path
	public static void crack(String srcAtlas,String dstDir) throws Exception{
		FileHandle fh = Gdx.files.absolute(srcAtlas);
		TextureAtlasData data = new TextureAtlasData(fh, fh.parent(),false);
		File dir = new File(dstDir);
		if(!dir.exists()){
			dir.mkdirs();
			System.out.println("mkdirs:"+dstDir);
		}
		for(Region region:data.getRegions()){
			File file = region.page.textureFile.file();
			BufferedImage root = ImageIO.read(file);
			String fileName = region.name ;
			int sizeWidth = region.originalWidth;
			int sizeHeight= region.originalHeight;
			int width = region.width;
			int height= region.height;
			int x = region.left ;
			int y = region.top ;
			int offsetX = (int)region.offsetX;
			int offsetY = (int)region.offsetY;

			BufferedImage canvas = null;
			
			if(region.rotate){
				canvas = new BufferedImage(height, width, BufferedImage.TYPE_INT_ARGB);
				canvas.getGraphics().drawImage(root, 0, 0, height, width, x, y, x+height, y+width, null);
			}else{
				canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
				canvas.getGraphics().drawImage(root, 0, 0, width, height, x, y, x+width, y+height, null);
			}
			
			if(offsetX!=0 || offsetY!=0){
				BufferedImage canvas2 = canvas;
				canvas = new BufferedImage(sizeWidth, sizeHeight, BufferedImage.TYPE_INT_ARGB);
				canvas.getGraphics().drawImage(canvas2, offsetX, offsetY, width, height, 0, 0, width, height, null);
			}
			if(region.rotate){
				canvas = rotate(canvas, Math.toRadians(90));
			}
			ImageIO.write(canvas, "png", new File(dstDir+fileName+".png"));
			
			
			System.out.println("Proccess to "+dstDir+fileName +".png" + " offsetX:"+region.offsetX+",offsetY:"+region.offsetY +" rotate:"+region.rotate);
		}
	}
	private  static BufferedImage rotate(BufferedImage image, double angle) {
	    double sin = Math.abs(Math.sin(angle)), cos = Math.abs(Math.cos(angle));
	    int w = image.getWidth(), h = image.getHeight();
	    int neww = (int)Math.floor(w*cos+h*sin), newh = (int)Math.floor(h*cos+w*sin);
	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    GraphicsDevice[] gd = ge.getScreenDevices();
	    GraphicsConfiguration gc = gd[0].getDefaultConfiguration();
	    BufferedImage result = gc.createCompatibleImage(neww, newh, Transparency.TRANSLUCENT);
	    Graphics2D g = result.createGraphics();
	    g.translate((neww-w)/2, (newh-h)/2);
	    g.rotate(angle, w/2, h/2);
	    g.drawRenderedImage(image, null);
	    g.dispose();
	    return result;
	}
}
