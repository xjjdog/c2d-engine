#include <info.u250.svg.glutils.SVGData.h>

//@line:24

	#include "agg/svg/SVGUtils.h"
	#include "agg/svg/SVGRenderer.h"


JNIEXPORT jobject JNICALL Java_info_u250_svg_glutils_SVGData_newSvgPixmap
(JNIEnv *env, jclass cls, jlongArray nativeData, jint width, jint height, jint format)
{
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
}

JNIEXPORT void JNICALL Java_info_u250_svg_glutils_SVGData_free
(JNIEnv *env, jclass cls, jlong pixmap)
{
    free((void*)((agg::svg::svg_pixmap*)pixmap)->pixels);
    free((void*)(agg::svg::svg_pixmap*)pixmap);
}


JNIEXPORT jlong JNICALL Java_info_u250_svg_glutils_SVGData_startPath
(JNIEnv *env, jclass cls)
{
    agg::svg::renderer renderer = agg::svg::renderer();
    return renderer.start_path();
}

JNIEXPORT void JNICALL Java_info_u250_svg_glutils_SVGData_endPath
(JNIEnv *env, jclass cls, jlong pixmap, jlong path, jdouble min_x, jdouble min_y, jdouble max_x, jdouble max_y, jdouble scale )
{
    agg::svg::renderer renderer = agg::svg::renderer();
    renderer.end_path((agg::svg::svg_pixmap*)pixmap,(agg::svg::path_render*)path, min_x, min_y, max_x, max_y, scale);
}

JNIEXPORT void JNICALL Java_info_u250_svg_glutils_SVGData_circle
(JNIEnv *env, jclass cls, jlong path, jdouble cx, jdouble cy, jdouble raio)
{
    agg::svg::renderer renderer = agg::svg::renderer();
    renderer.circle((agg::svg::path_render*)path,cx,cy,raio);
}

JNIEXPORT void JNICALL Java_info_u250_svg_glutils_SVGData_rect
(JNIEnv *env, jclass cls, jlong path, jdouble x, jdouble y, jdouble width, jdouble height, jdouble rx, jdouble ry)
{
    agg::svg::renderer renderer = agg::svg::renderer();
    renderer.rect((agg::svg::path_render*)path,x,y,width,height,rx,ry);
}

JNIEXPORT void JNICALL Java_info_u250_svg_glutils_SVGData_line
(JNIEnv *env, jclass cls, jlong path, jdouble x1, jdouble y1, jdouble x2, jdouble y2)
{
    agg::svg::renderer renderer = agg::svg::renderer();
    renderer.line((agg::svg::path_render*)path,x1,y1,x2,y2);
}

JNIEXPORT void JNICALL Java_info_u250_svg_glutils_SVGData_polygon
(JNIEnv *env, jclass cls, jlong path, jdoubleArray arg, jint lenght)
{
    double* array = env->GetDoubleArrayElements(arg,NULL);
    
    agg::svg::renderer renderer = agg::svg::renderer();
    renderer.polygon((agg::svg::path_render*)path,array,lenght);
    
    env->ReleaseDoubleArrayElements(arg, array, 0);
}

JNIEXPORT void JNICALL Java_info_u250_svg_glutils_SVGData_moveTo
(JNIEnv *env, jclass cls, jlong path, jdouble x, jdouble y, jboolean rel)
{
    agg::svg::renderer renderer = agg::svg::renderer();
    renderer.move_to((agg::svg::path_render*)path,x,y,rel);
}

JNIEXPORT void JNICALL Java_info_u250_svg_glutils_SVGData_lineTo
(JNIEnv *env, jclass cls, jlong path, jdouble x,  jdouble y, jboolean rel)
{
    agg::svg::renderer renderer = agg::svg::renderer();
    renderer.line_to((agg::svg::path_render*)path,x,y,rel);
}

JNIEXPORT void JNICALL Java_info_u250_svg_glutils_SVGData_hlineTo
(JNIEnv *env, jclass cls, jlong path, jdouble x, jboolean rel)
{
    agg::svg::renderer renderer = agg::svg::renderer();
    renderer.hline_to((agg::svg::path_render*)path,x,rel);
}

JNIEXPORT void JNICALL Java_info_u250_svg_glutils_SVGData_vlineTo
(JNIEnv *env, jclass cls, jlong path, jdouble y, jboolean rel)
{
    agg::svg::renderer renderer = agg::svg::renderer();
    renderer.vline_to((agg::svg::path_render*)path,y,rel);
}

JNIEXPORT void JNICALL Java_info_u250_svg_glutils_SVGData_curve3
(JNIEnv *env, jclass cls, jlong path, jdoubleArray arg, jboolean rel)
{
    jsize len = env->GetArrayLength(arg);
    
    double* array = env->GetDoubleArrayElements(arg,NULL);
    
    agg::svg::renderer renderer = agg::svg::renderer();
    
    if ( len == 2){
        renderer.curve3((agg::svg::path_render*)path,array[0],array[1],rel);
    }else{
        renderer.curve3((agg::svg::path_render*)path,array[0],array[1],array[2],array[3],rel);
    }
    
    env->ReleaseDoubleArrayElements(arg, array, 0);
}

JNIEXPORT void JNICALL Java_info_u250_svg_glutils_SVGData_curve4
(JNIEnv *env, jclass cls, jlong path, jdoubleArray arg, jboolean rel)
{
    jsize len = env->GetArrayLength(arg);
    
    double* array = env->GetDoubleArrayElements(arg,NULL);
    
    agg::svg::renderer renderer = agg::svg::renderer();
    
    if ( len == 4){
        renderer.curve4((agg::svg::path_render*)path,array[0],array[1],array[2],array[3],rel);
    }else{
        renderer.curve4((agg::svg::path_render*)path,array[0],array[1],array[2],array[3],array[4],array[5],rel);
    }
    
    env->ReleaseDoubleArrayElements(arg, array, 0);
    
}

JNIEXPORT void JNICALL Java_info_u250_svg_glutils_SVGData_attribute
(JNIEnv *env, jclass cls, jlong path, jstring name, jstring strValue, jdouble numberValue)
{
    agg::svg::renderer renderer = agg::svg::renderer();
    
    const char *attributeName = env->GetStringUTFChars(name, NULL);
    const char *attributeValue= env->GetStringUTFChars(strValue, NULL);
    
    renderer.attribute((agg::svg::path_render*)path,attributeName,attributeValue,numberValue);
    
    env->ReleaseStringUTFChars(name, attributeName);
    env->ReleaseStringUTFChars(strValue, attributeValue);
}

JNIEXPORT void JNICALL Java_info_u250_svg_glutils_SVGData_matrix
(JNIEnv *env, jclass cls, jlong path, jdouble val1, jdouble val2, jdouble val3, jdouble val4, jdouble val5, jdouble val6)
{
    agg::svg::renderer renderer = agg::svg::renderer();
    renderer.matrix((agg::svg::path_render*)path,val1,val2,val3,val4,val5,val6);
}

JNIEXPORT void JNICALL Java_info_u250_svg_glutils_SVGData_translate
(JNIEnv *env, jclass cls, jlong path, jdouble val1, jdouble val2)
{
    agg::svg::renderer renderer = agg::svg::renderer();
    renderer.translate((agg::svg::path_render*)path,val1,val2);
}

JNIEXPORT void JNICALL Java_info_u250_svg_glutils_SVGData_rotate
(JNIEnv *env, jclass cls, jlong path, jdoubleArray arg)
{
    jsize len 	  = env->GetArrayLength(arg);
    
    double* array = env->GetDoubleArrayElements(arg,NULL);
    
    agg::svg::renderer renderer = agg::svg::renderer();
    
    if ( len == 1){
        renderer.rotate((agg::svg::path_render*)path,array[0]);
    }else{
        renderer.rotate((agg::svg::path_render*)path,array[0],array[1],array[2]);
    }
    
    env->ReleaseDoubleArrayElements(arg, array, 0);
}

JNIEXPORT void JNICALL Java_info_u250_svg_glutils_SVGData_scale
(JNIEnv *env, jclass cls, jlong path, jdouble val1, jdouble val2)
{
    agg::svg::renderer renderer = agg::svg::renderer();
    renderer.scale((agg::svg::path_render*)path,val1,val2);
}

JNIEXPORT void JNICALL Java_info_u250_svg_glutils_SVGData_skewX
(JNIEnv *env, jclass cls, jlong path, jdouble value)
{
    agg::svg::renderer renderer = agg::svg::renderer();
    renderer.skew_x((agg::svg::path_render*)path,value);
}

JNIEXPORT void JNICALL Java_info_u250_svg_glutils_SVGData_skewY
(JNIEnv *env, jclass cls, jlong path, jdouble value)
{
    agg::svg::renderer renderer = agg::svg::renderer();
    renderer.skew_y((agg::svg::path_render*)path,value);
}

JNIEXPORT void JNICALL Java_info_u250_svg_glutils_SVGData_pathD
(JNIEnv *env, jclass cls, jlong path, jchar key, jdoubleArray arg, jint length)
{
    double* array = env->GetDoubleArrayElements(arg,NULL);
    
    agg::svg::renderer renderer = agg::svg::renderer();
    renderer.pathD((agg::svg::path_render*)path,key,array,length);
    
    env->ReleaseDoubleArrayElements(arg, array, 0);
}


