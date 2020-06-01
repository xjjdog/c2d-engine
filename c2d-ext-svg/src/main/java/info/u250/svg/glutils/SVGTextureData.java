package info.u250.svg.glutils;

import info.u250.svg.elements.SVGRootElement;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.Gdx2DPixmap;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class SVGTextureData implements TextureData{
	Pixmap pixmap;
	SVGRootElement rootElement;
	SVGData data;
	
	boolean isPrepared = false;
	
	public SVGTextureData(SVGRootElement element){
		this.rootElement = element;
	}
	
	@Override
	public TextureDataType getType() {
		return TextureDataType.Pixmap;
	}

	@Override
	public boolean isPrepared() {
		return isPrepared;
	}

	@Override
	public void prepare() {
		
		if ( !isPrepared ){
				data	    	   = new SVGData(rootElement);
				long[] nativeData  = new long[]{data.basePtr,rootElement.width,rootElement.height,rootElement.format};
				Gdx2DPixmap gdx2d  = new Gdx2DPixmap(data.svgData,nativeData){
					@Override
					public void dispose () {
						data.dispose();
					}
				};
				pixmap	    = new Pixmap(gdx2d);
				isPrepared  = true;
		}
		
	}

	@Override
	public Pixmap consumePixmap() {
		return pixmap;
	}

	@Override
	public boolean disposePixmap() {
		return true;
	}

	@Override
	public void consumeCustomData(int target) {

	}


	@Override
	public int getWidth() {
		return pixmap.getWidth();
	}

	@Override
	public int getHeight() {
		return pixmap.getHeight();
	}

	@Override
	public Format getFormat() {
		return pixmap.getFormat();
	}

	@Override
	public boolean useMipMaps() {
		return false;
	}

	@Override
	public boolean isManaged() {
		return true;
	}

	public void consumeCompressedData(int target) {
		throw new GdxRuntimeException("This TextureData implementation does not upload data itself");
	}
	
}
