package info.u250.svg.glutils;

import info.u250.svg.SVGConstants;
import info.u250.svg.SVGMetaData;
import info.u250.svg.SVGPath;
import info.u250.svg.SVGStyle;
import info.u250.svg.SVGTransform;
import info.u250.svg.elements.SVGElement;
import info.u250.svg.elements.SVGElementCircle;
import info.u250.svg.elements.SVGElementEllipse;
import info.u250.svg.elements.SVGElementLine;
import info.u250.svg.elements.SVGElementPath;
import info.u250.svg.elements.SVGElementPolyLine;
import info.u250.svg.elements.SVGElementPolygon;
import info.u250.svg.elements.SVGElementRect;
import info.u250.svg.elements.SVGRootElement;

import java.nio.ByteBuffer;

import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.SharedLibraryLoader;

public class SVGData implements Disposable, SVGConstants {
	/*JNI
	#include "agg/svg/SVGUtils.h"
	#include "agg/svg/SVGRenderer.h"
	 */	
	public final long basePtr;
	public final ByteBuffer svgData;
	static final long[] nativeData = new long[4];
	
	public SVGData(final SVGRootElement rootElement){
		new SharedLibraryLoader().load("c2d-svg");
		svgData = newSvgPixmap(nativeData, rootElement.width, rootElement.height, rootElement.format);
		basePtr = nativeData[0];
		
		new SVGMetaData(rootElement){
			
			@Override
			public void next(final SVGElement element){
				
				if ( element.equals(TAG_SVG) || element.equals(TAG_GROUP) ){
					return;
				}
				
				long path    = startPath();
				
				//Styles
				if ( element.getStyle() != null ){
					int lenght = element.getStyle().length;
					for ( int x=0; x<lenght; x++){
						SVGStyle style = element.getStyle()[x];
						if ( style != null ){//TODO style n„o implementado
							attribute(path,style.name,style.strValue,style.numberValue);
						}
					}
				}
				
				//Transform
				if ( element.getTransforms() != null ){
					int lenght = element.getTransforms().length;
					
					for ( int x=0; x<lenght; x++ ){
						SVGTransform transform = element.getTransforms()[x];
						
						if ( ATTRIBUTE_TRANSFORM_VALUE_ROTATE.equals(transform.name ) ){
							rotate(path,transform.values);
						}else if ( ATTRIBUTE_TRANSFORM_VALUE_SKEW_Y.equals(transform.name ) ){
							skewY(path,transform.values[0]);
						}else if ( ATTRIBUTE_TRANSFORM_VALUE_SKEW_X.equals(transform.name ) ){
							skewX(path,transform.values[0]);
						}else if ( ATTRIBUTE_TRANSFORM_VALUE_SCALE.equals(transform.name ) ){
							
							double scaleX = 0;
							double scaleY = 0;
							
							if ( transform.values.length == 1){
								scaleX = scaleY = transform.values[0];
							}else{
								scaleX = transform.values[0];
								scaleY = transform.values[1];
							}
							
							scale(path, scaleX, scaleY);
							
						}else if ( ATTRIBUTE_TRANSFORM_VALUE_TRANSLATE.equals(transform.name ) ){
							if ( transform.values.length == 2 ){
								translate(path,transform.values[0],transform.values[1]);
							}else{
								translate(path,transform.values[0],0);
							}
						}else if ( ATTRIBUTE_TRANSFORM_VALUE_MATRIX.equals(transform.name ) ){
							matrix(path,transform.values[0],transform.values[1],transform.values[2],transform.values[3],transform.values[4],transform.values[5]);
						}
						
					}
				}
				
				if ( element.equals(TAG_CIRCLE) ){
					
					SVGElementCircle elementCircle = (SVGElementCircle)element;
					circle(path, elementCircle.cx, elementCircle.cy, elementCircle.raio);
				
				}else if ( element.equals(TAG_ELLIPSE) ){
					
					SVGElementEllipse elementEllipse = (SVGElementEllipse)element;
					circle(path, elementEllipse.cx, elementEllipse.cy, elementEllipse.raio);
				
				}else if ( element.equals(TAG_LINE) ){
					
					SVGElementLine elementLine = (SVGElementLine)element;
					line(path, elementLine.x1, elementLine.y1, elementLine.x2, elementLine.y2);
				
				}else if ( element.equals(TAG_RECTANGLE) ){
					
					SVGElementRect elementRect = (SVGElementRect)element;
					rect(path, elementRect.x, elementRect.y, elementRect.width, elementRect.height, elementRect.rx, elementRect.ry);
				
				}else if ( element.equals(TAG_POLYGON) ){
				
					SVGElementPolygon elementPolygon = (SVGElementPolygon)element;
					polygon(path, elementPolygon.paths, elementPolygon.paths.length);
				
				}else if ( element.equals(TAG_POLYLINE) ){

					SVGElementPolyLine elementPolyLine = (SVGElementPolyLine)element;
					polygon(path, elementPolyLine.paths, elementPolyLine.paths.length);
					
				}else if ( element.equals(TAG_PATH) ){
					int sizePath = ((SVGElementPath)element).path.length;
					
					for ( int x=0; x<sizePath; x++ ){
						SVGPath d = ((SVGElementPath)element).path[x];
						pathD(path,d.attribute,d.patch,d.patch.length);
					}
					
				}
				
				
				endPath(basePtr, path, rootElement.min_x, rootElement.min_y, rootElement.max_x, rootElement.max_y, rootElement.scale);
				
			}
			
			@Override
			public void end(){
				
			}
			
		};
		
	}
	
	@Override
	public void dispose() {
		free(basePtr);
	}
	
	private static native ByteBuffer newSvgPixmap(long[] nativeData, int width, int height, int format);/*
		agg::svg::renderer renderer = agg::svg::renderer();

		agg::svg::svg_pixmap* pixmap = renderer.new_svg_pixmap(width, height, format);

		if( pixmap==0 )
			return 0;

		jobject pixel_buffer = env->NewDirectByteBuffer((void*)pixmap->pixels, pixmap->width * pixmap->height * pixmap->format);
		jlong* p_native_data = (jlong*)env->GetPrimitiveArrayCritical(nativeData, 0);

		p_native_data[0] = (jlong)pixmap;
		p_native_data[1] = pixmap->width;
		p_native_data[2] = pixmap->height;
		p_native_data[3] = pixmap->format;

		env->ReleasePrimitiveArrayCritical(nativeData, p_native_data, 0);

		return pixel_buffer;
	*/
	private static native void free(long pixmap);/*
		free((void*)((agg::svg::svg_pixmap*)pixmap)->pixels);
		free((void*)(agg::svg::svg_pixmap*)pixmap);
	*/
	
	static native long startPath();/*
		agg::svg::renderer renderer = agg::svg::renderer();
		return renderer.start_path();
	*/
	static native void endPath(long pixmap, long path, double min_x, double min_y, double max_x, double max_y, double scale);/*
		agg::svg::renderer renderer = agg::svg::renderer();
		renderer.end_path((agg::svg::svg_pixmap*)pixmap,(agg::svg::path_render*)path, min_x, min_y, max_x, max_y, scale);
	*/
	static native void circle( long path, double cx, double cy, double raio);/*
		agg::svg::renderer renderer = agg::svg::renderer();
		renderer.circle((agg::svg::path_render*)path,cx,cy,raio);
	*/
	static native void rect( long path, double x, double y, double width, double height, double rx, double ry);/*
		agg::svg::renderer renderer = agg::svg::renderer();
		renderer.rect((agg::svg::path_render*)path,x,y,width,height,rx,ry);
	*/
	static native void line( long path, double x1, double y1, double x2, double y2);/*
		agg::svg::renderer renderer = agg::svg::renderer();
		renderer.line((agg::svg::path_render*)path,x1,y1,x2,y2);
	*/
	static native void polygon( long path, double[] arg, int lenght);/*
		double* array = env->GetDoubleArrayElements(arg,NULL);

		agg::svg::renderer renderer = agg::svg::renderer();
		renderer.polygon((agg::svg::path_render*)path,array,lenght);

		env->ReleaseDoubleArrayElements(arg, array, 0);
	*/
	static native void moveTo( long path, double x, double y, boolean rel);/*
		agg::svg::renderer renderer = agg::svg::renderer();
		renderer.move_to((agg::svg::path_render*)path,x,y,rel);
	*/
	static native void lineTo( long path, double x,  double y, boolean rel);/*
		agg::svg::renderer renderer = agg::svg::renderer();
		renderer.line_to((agg::svg::path_render*)path,x,y,rel);
	*/
	static native void hlineTo( long path, double x, boolean rel);/*
		agg::svg::renderer renderer = agg::svg::renderer();
		renderer.hline_to((agg::svg::path_render*)path,x,rel);
	*/
	static native void vlineTo( long path, double y, boolean rel);/*
		agg::svg::renderer renderer = agg::svg::renderer();
		renderer.vline_to((agg::svg::path_render*)path,y,rel);
	*/
	static native void curve3( long path, double[] arg, boolean rel);/*
		jsize len = env->GetArrayLength(arg);
		double* array = env->GetDoubleArrayElements(arg,NULL);
		agg::svg::renderer renderer = agg::svg::renderer();

		if ( len == 2){
			renderer.curve3((agg::svg::path_render*)path,array[0],array[1],rel);
		}else{
			renderer.curve3((agg::svg::path_render*)path,array[0],array[1],array[2],array[3],rel);
		}

		env->ReleaseDoubleArrayElements(arg, array, 0);
	*/
	static native void curve4( long path, double[] arg, boolean rel);/*
		jsize len = env->GetArrayLength(arg);

		double* array = env->GetDoubleArrayElements(arg,NULL);

		agg::svg::renderer renderer = agg::svg::renderer();

		if ( len == 4){
			renderer.curve4((agg::svg::path_render*)path,array[0],array[1],array[2],array[3],rel);
		}else{
			renderer.curve4((agg::svg::path_render*)path,array[0],array[1],array[2],array[3],array[4],array[5],rel);
		}

		env->ReleaseDoubleArrayElements(arg, array, 0);
	*/
	static native void attribute( long path, String name, String strValue, double numberValue);/*
		agg::svg::renderer renderer = agg::svg::renderer();

		const char *attributeName = env->GetStringUTFChars(name, NULL);
		const char *attributeValue= env->GetStringUTFChars(strValue, NULL);

		renderer.attribute((agg::svg::path_render*)path,attributeName,attributeValue,numberValue);

		env->ReleaseStringUTFChars(name, attributeName);
		env->ReleaseStringUTFChars(strValue, attributeValue);
	*/
	static native void matrix( long path, double val1, double val2, double val3, double val4, double val5, double val6);/*
		agg::svg::renderer renderer = agg::svg::renderer();
		renderer.matrix((agg::svg::path_render*)path,val1,val2,val3,val4,val5,val6);
	*/
	static native void translate( long path, double val1, double val2);/*
		agg::svg::renderer renderer = agg::svg::renderer();
		renderer.translate((agg::svg::path_render*)path,val1,val2);
	*/
	static native void rotate( long path, double[] arg);/*
		jsize len 	  = env->GetArrayLength(arg);

		double* array = env->GetDoubleArrayElements(arg,NULL);

		agg::svg::renderer renderer = agg::svg::renderer();

		if ( len == 1){
			renderer.rotate((agg::svg::path_render*)path,array[0]);
		}else{
			renderer.rotate((agg::svg::path_render*)path,array[0],array[1],array[2]);
		}

		env->ReleaseDoubleArrayElements(arg, array, 0);
	*/
	static native void scale( long path, double val1, double val2);/*
		agg::svg::renderer renderer = agg::svg::renderer();
		renderer.scale((agg::svg::path_render*)path,val1,val2);
	*/
	static native void skewX( long path, double value);/*
		agg::svg::renderer renderer = agg::svg::renderer();
		renderer.skew_x((agg::svg::path_render*)path,value);
	*/
	static native void skewY( long path, double value);/*
		agg::svg::renderer renderer = agg::svg::renderer();
		renderer.skew_y((agg::svg::path_render*)path,value);
	*/
	static native void pathD( long path, char key, double[] arg, int length);/*
		double* array = env->GetDoubleArrayElements(arg,NULL);

		agg::svg::renderer renderer = agg::svg::renderer();
		renderer.pathD((agg::svg::path_render*)path,key,array,length);

		env->ReleaseDoubleArrayElements(arg, array, 0);
	*/


}
