LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)
 
LOCAL_MODULE    := c2d-svg
LOCAL_C_INCLUDES := 
 
LOCAL_CFLAGS := $(LOCAL_C_INCLUDES:%=-I%) -O2 -Wall -D__ANDROID__ -DHAVE_CONFIG_H
LOCAL_CPPFLAGS := $(LOCAL_C_INCLUDES:%=-I%) -O2 -Wall -D__ANDROID__
LOCAL_LDLIBS := -lm
LOCAL_ARM_MODE  := arm
 
LOCAL_SRC_FILES := agg/gpc/gpc.c\
	agg/src/agg_arc.cpp\
	agg/src/agg_arrowhead.cpp\
	agg/src/agg_bezier_arc.cpp\
	agg/src/agg_bspline.cpp\
	agg/src/agg_curves.cpp\
	agg/src/agg_embedded_raster_fonts.cpp\
	agg/src/agg_gsv_text.cpp\
	agg/src/agg_image_filters.cpp\
	agg/src/agg_line_aa_basics.cpp\
	agg/src/agg_line_profile_aa.cpp\
	agg/src/agg_rounded_rect.cpp\
	agg/src/agg_sqrt_tables.cpp\
	agg/src/agg_trans_affine.cpp\
	agg/src/agg_trans_double_path.cpp\
	agg/src/agg_trans_single_path.cpp\
	agg/src/agg_trans_warp_magnifier.cpp\
	agg/src/agg_vcgen_bspline.cpp\
	agg/src/agg_vcgen_contour.cpp\
	agg/src/agg_vcgen_dash.cpp\
	agg/src/agg_vcgen_markers_term.cpp\
	agg/src/agg_vcgen_smooth_poly1.cpp\
	agg/src/agg_vcgen_stroke.cpp\
	agg/src/agg_vpgen_clip_polygon.cpp\
	agg/src/agg_vpgen_clip_polyline.cpp\
	agg/src/agg_vpgen_segmentator.cpp\
	agg/src/ctrl/agg_bezier_ctrl.cpp\
	agg/src/ctrl/agg_cbox_ctrl.cpp\
	agg/src/ctrl/agg_gamma_ctrl.cpp\
	agg/src/ctrl/agg_gamma_spline.cpp\
	agg/src/ctrl/agg_polygon_ctrl.cpp\
	agg/src/ctrl/agg_rbox_ctrl.cpp\
	agg/src/ctrl/agg_scale_ctrl.cpp\
	agg/src/ctrl/agg_slider_ctrl.cpp\
	agg/src/ctrl/agg_spline_ctrl.cpp\
	agg/svg/SVGRenderer.cpp\
	agg/svg/SVGUtils.cpp\
	info.u250.svg.glutils.SVGData.cpp
 
include $(BUILD_SHARED_LIBRARY)
