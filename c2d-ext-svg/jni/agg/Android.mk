GDX_SRC_FILES += agg/src/agg_arc.cpp \
agg/src/agg_image_filters.cpp \
agg/src/agg_line_aa_basics.cpp \
agg/src/agg_line_profile_aa.cpp \
agg/src/agg_trans_warp_magnifier.cpp \
agg/src/agg_vcgen_contour.cpp \
agg/src/agg_vcgen_markers_term.cpp \
agg/src/agg_vpgen_clip_polygon.cpp \
agg/src/agg_vpgen_clip_polyline.cpp \
agg/src/agg_vpgen_segmentator.cpp \
agg/src/agg_rounded_rect.cpp \
agg/src/agg_trans_affine.cpp \
agg/src/agg_trans_single_path.cpp \
agg/src/agg_bspline.cpp \
agg/src/agg_vcgen_bspline.cpp \
agg/src/agg_vcgen_stroke.cpp \
agg/src/agg_vcgen_smooth_poly1.cpp \
agg/src/agg_vcgen_dash.cpp \
agg/src/agg_trans_double_path.cpp \
agg/src/agg_sqrt_tables.cpp \
agg/src/agg_bezier_arc.cpp \
agg/src/agg_curves.cpp \
agg/src/agg_gsv_text.cpp \
agg/src/agg_embedded_raster_fonts.cpp \
agg/gpc/gpc.c \
agg/svg/SVGUtils.cpp\
agg/svg/SVGRenderer.cpp \
agg/svg/SVGData.cpp

LOCAL_C_INCLUDES += $(LOCAL_PATH) $(LOCAL_PATH)/../agg 
