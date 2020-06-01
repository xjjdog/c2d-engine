package info.u250.svg.glutils;

import com.badlogic.gdx.jnigen.AntScriptGenerator;
import com.badlogic.gdx.jnigen.BuildConfig;
import com.badlogic.gdx.jnigen.BuildTarget;
import com.badlogic.gdx.jnigen.BuildTarget.TargetOs;
import com.badlogic.gdx.jnigen.NativeCodeGenerator;

public class SvgBuild {
	public static void main (String[] args) throws Exception {
		String[] excludes = { };
		String[] headers = { 
				
				};
		String[] sources = { 
				"agg/gpc/gpc.c",
				"agg/src/agg_arc.cpp",
				"agg/src/agg_image_filters.cpp",
				"agg/src/agg_line_aa_basics.cpp",
				"agg/src/agg_line_profile_aa.cpp",
				"agg/src/agg_trans_warp_magnifier.cpp",
				"agg/src/agg_vcgen_contour.cpp",
				"agg/src/agg_vcgen_markers_term.cpp",
				"agg/src/agg_vpgen_clip_polygon.cpp",
				"agg/src/agg_vpgen_clip_polyline.cpp",
				"agg/src/agg_vpgen_segmentator.cpp",
				"agg/src/agg_rounded_rect.cpp",
				"agg/src/agg_trans_affine.cpp",
				"agg/src/agg_trans_single_path.cpp",
				"agg/src/agg_bspline.cpp",
				"agg/src/agg_vcgen_bspline.cpp",
				"agg/src/agg_vcgen_stroke.cpp",
				"agg/src/agg_vcgen_smooth_poly1.cpp",
				"agg/src/agg_vcgen_dash.cpp",
				"agg/src/agg_trans_double_path.cpp",
				"agg/src/agg_sqrt_tables.cpp",
				"agg/src/agg_bezier_arc.cpp",
				"agg/src/agg_curves.cpp",
				"agg/src/agg_gsv_text.cpp",
				"agg/src/agg_embedded_raster_fonts.cpp",
				"agg/gpc/gpc.c",
				"agg/svg/SVGUtils.cpp",
				"agg/svg/SVGRenderer.cpp",
				"info.u250.svg.glutils.SVGData.cpp"
//				"agg/svg/SVGData.cpp",
		};
		
		BuildTarget win32home = BuildTarget.newDefaultTarget(TargetOs.Windows, false);
		win32home.compilerPrefix = "";
		win32home.buildFileName = "build-windows32home.xml";
		win32home.excludeFromMasterBuildFile = true;
		win32home.cExcludes = win32home.cppExcludes = excludes;
		win32home.cFlags += " -DHAVE_CONFIG_H";
		win32home.headerDirs = headers;
		win32home.cIncludes = sources;
		win32home.preCompileTask = "";
		win32home.headerDirs = new String[]{"agg/include",};
		
		BuildTarget win32 = BuildTarget.newDefaultTarget(TargetOs.Windows, false);
		win32.cExcludes = win32.cppExcludes = excludes;
		win32.headerDirs = headers;
		win32.cFlags += " -DHAVE_CONFIG_H";
		win32.cIncludes = sources;
		
		BuildTarget win64 = BuildTarget.newDefaultTarget(TargetOs.Windows, true);
		win64.cExcludes = win64.cppExcludes = excludes;
		win64.headerDirs = headers;
		win64.cFlags += " -DHAVE_CONFIG_H";
		win64.cIncludes = sources;
		
		BuildTarget lin32 = BuildTarget.newDefaultTarget(TargetOs.Linux, false);
		lin32.cExcludes = lin32.cppExcludes = excludes;
		lin32.headerDirs = headers;
		lin32.cFlags += " -DHAVE_CONFIG_H";
		lin32.cIncludes = sources;
		
		BuildTarget lin64 = BuildTarget.newDefaultTarget(TargetOs.Linux, true);
		lin64.cExcludes = lin64.cppExcludes = excludes;
		lin64.headerDirs = headers;
		lin64.cFlags += " -DHAVE_CONFIG_H";
		lin64.cIncludes = sources;
		
//		BuildTarget mac = BuildTarget.newDefaultTarget(TargetOs.MacOsX, false);
//		mac.cExcludes = mac.cppExcludes = excludes;
//		mac.headerDirs = headers;
//		mac.cFlags += " -DHAVE_CONFIG_H";
//		mac.cIncludes = sources;
		
		BuildTarget android = BuildTarget.newDefaultTarget(TargetOs.Android, false);
		android.cExcludes = android.cppExcludes = excludes;
		android.headerDirs = headers;
		android.cFlags += " -DHAVE_CONFIG_H";
		android.cIncludes = sources;
		
		new NativeCodeGenerator().generate();
		new AntScriptGenerator().generate(new BuildConfig("c2d-svg"), win32home, android);
//		BuildExecutor.executeAnt("jni/build-windows32home.xml", "-v clean");
//		BuildExecutor.executeAnt("jni/build-windows32home.xml", "-v");
//		BuildExecutor.executeAnt("jni/build-windows32home.xml", "-v");
//		BuildExecutor.executeAnt("jni/build.xml", "pack-natives -v");
	}
}
