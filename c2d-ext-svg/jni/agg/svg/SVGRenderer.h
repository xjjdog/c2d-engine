#ifndef SVGRENDERER_INCLUDED
#define SVGRENDERER_INCLUDED

#include <string>
#include <string.h>
#include <stdint.h>
#include <jni.h>

#include "../include/agg_path_storage.h"
#include "../include/agg_math_stroke.h"
#include "../include/agg_basics.h"
#include "../include/agg_rendering_buffer.h"
#include "../include/agg_rasterizer_scanline_aa.h"
#include "../include/agg_rasterizer_outline.h"
#include "../include/agg_scanline_p.h"
#include "../include/agg_scanline_bin.h"
#include "../include/agg_renderer_scanline.h"
#include "../include/agg_renderer_primitives.h"
#include "../include/agg_conv_transform.h"
#include "../include/agg_conv_stroke.h"
#include "../include/agg_conv_contour.h"
#include "../include/agg_conv_curve.h"
#include "../include/agg_color_rgba.h"
#include "../include/agg_bounding_rect.h"

#include "SVGUtils.h"

#define AGG_BGR24
#include "../include/agg_pixfmt_rgba.h"

using namespace std;

namespace agg
{
namespace svg
{

	struct svg_pixmap{
		uint32_t width;
		uint32_t height;
		uint32_t format;
		const unsigned char* pixels;
	};

	struct path_render{
		rgba8        		fill_color;
		rgba8        		stroke_color;
		bool         		fill_flag;
		bool         		stroke_flag;
		bool         		even_odd_flag;
		line_join_e  		line_join;
		line_cap_e   		line_cap;
		double       		miter_limit;
		double       		stroke_width;
		trans_affine 		transform;
		path_storage  		path;

		path_render() :
		fill_color(rgba(0,0,0)),
		stroke_color(rgba(0,0,0)),
		fill_flag(true),
		stroke_flag(false),
		even_odd_flag(false),
		line_join(miter_join),
		line_cap(butt_cap),
		miter_limit(4.0),
		stroke_width(1.0),
		transform(),
		path()
		{
		}
	};

	class renderer{

		public:
			typedef utils  svg_utils;
			typedef conv_curve<agg::path_storage> type_curve;
			typedef conv_stroke<agg::path_storage> type_stroke;

			typedef conv_transform<type_stroke> type_trans_stroke;
			typedef conv_transform<type_curve> type_trans_curve;

			typedef conv_contour<type_trans_curve> type_curved_contour;

			renderer();
			svg_pixmap* new_svg_pixmap(uint32_t width, uint32_t height, uint32_t format);
			jlong start_path();
			void end_path(agg::svg::svg_pixmap* pixmap, agg::svg::path_render* path, double min_x, double min_y, double max_x, double max_y, double scale);
			void circle(path_render* path, double cx, double cy, double raio);
			void rect(path_render* path, double x, double y, double width, double height, double rx, double ry);
			void line(path_render* path, double x1, double y1, double x2, double y2);
			void polygon(path_render* path, double paths[], unsigned lenght);
			void move_to(path_render* path, double x, double y, bool rel);
			void line_to(path_render* path, double x,  double y, bool rel);
			void hline_to(path_render* path, double x, bool rel);
			void vline_to(path_render* path, double y, bool rel);
			void curve3(path_render* path, double x1, double y1, double x,  double y, bool rel);
			void curve3(path_render* path, double x, double y, bool rel);
			void curve4(path_render* path, double x1, double y1, double x2, double y2, double x,  double y, bool rel);
			void curve4(path_render* path, double x2, double y2, double x,  double y, bool rel);
			void attribute(path_render* path, const char* name, const char* strValue, double numberValue);
			void matrix(path_render* path, double val1, double val2, double val3, double val4, double val5, double val6);
			void translate(path_render* path, double val1, double val2);
			void rotate(path_render* path, double val1);
			void rotate(path_render* path, double val1, double val2, double val3);
			void scale(path_render* path, double val1, double val2);
			void skew_x(path_render* path, double value);
			void skew_y(path_render* path, double value);
			void pathD(path_render* path, char key, double arg[],unsigned length);
			void close_subpath(path_render* path);
		private:
			path_storage 		pathStorage;
			svg_utils 			color_utils;
	};
}
}
#endif
