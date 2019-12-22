#include <cstdlib>
#include <iostream>
#include "SVGRenderer.h"

namespace agg
{
namespace svg
{
	renderer::renderer():
			color_utils()
		{
		}

	agg::svg::svg_pixmap* renderer::new_svg_pixmap(uint32_t width, uint32_t height, uint32_t format){
		agg::svg::svg_pixmap* pixmap  = (agg::svg::svg_pixmap*)malloc(sizeof(agg::svg::svg_pixmap));

		pixmap->width 				  = width;
		pixmap->height 				  = height;
		pixmap->format 				  = format;
		pixmap->pixels 				  = (unsigned char*)malloc(width * height * format);

		return pixmap;
	}

	jlong renderer::start_path(){
		agg::svg::path_render* render   = new agg::svg::path_render();

		return (jlong)render;
	}


	void renderer::end_path(agg::svg::svg_pixmap* pixmap, agg::svg::path_render* path, double min_x, double min_y, double max_x, double max_y, double scale)
	{
		typedef agg::pixfmt_rgba32 pixfmt;
		typedef agg::renderer_base<pixfmt> renderer_base;
		typedef agg::renderer_scanline_aa_solid<renderer_base> renderer_solid;

		agg::rasterizer_scanline_aa<> rasterizer;
		agg::rendering_buffer 		  buffer;
		agg::scanline_p8              sl;

		agg::trans_affine mtx = path->transform;

		//mtx *= agg::trans_affine_translation((min_x + max_x) * -0.5, (min_y + max_y) * -0.5);
		mtx *= agg::trans_affine_scaling(scale);
		mtx *= agg::trans_affine_skewing(0, 0);
		// mtx *= agg::trans_affine_translation((min_x + max_x) * 0.5 + m_x, (m_min_y + m_max_y) * 0.5 + m_y + 30);

		type_curve 			curve(path->path);
		type_stroke 		stroke(path->path);
		type_trans_stroke 	trans_stroke(stroke, mtx);
		type_trans_curve 	trans_curve(curve, mtx);
		type_curved_contour curved_contour(trans_curve);


	//	path->path.arrange_orientations_all_paths(agg::path_flags_cw);

		buffer.attach((unsigned char*)pixmap->pixels, pixmap->width, pixmap->height, pixmap->width * pixmap->format);

		pixfmt pixf(buffer);

		renderer_base rb(pixf);
		renderer_solid ren(rb);

		rasterizer.clip_box(0, 0, pixmap->width, pixmap->height);

		rgba8 color;

		if(path->fill_flag)
		   {
				rasterizer.filling_rule(path->even_odd_flag ? fill_even_odd : fill_non_zero);

				if(fabs(curved_contour.width()) < 0.0001)
				{
					rasterizer.add_path(trans_curve);
				}
				else
				{
					curved_contour.miter_limit(path->miter_limit);
					rasterizer.add_path(curved_contour, 0);
				}

				color = path->fill_color;
				color.opacity(color.opacity());
				ren.color(color);
				agg::render_scanlines(rasterizer, sl, ren);
		  }

		  if(path->stroke_flag)
		   {
			  stroke.width(path->stroke_width);
			  stroke.line_join(path->line_join);
			  stroke.line_cap(path->line_cap);
			  stroke.miter_limit(path->miter_limit);
			  stroke.inner_join(inner_round);
			  stroke.approximation_scale(1.0);

			  //---------------------
			  if( path->stroke_width > 1.0 )
			  {
				  curve.angle_tolerance(0.2);
			  }

			  rasterizer.reset();
			  rasterizer.filling_rule(fill_non_zero);
			  rasterizer.add_path(trans_stroke);

			  color = path->stroke_color;
			  color.opacity(color.opacity());
			  ren.color(color);

			  agg::render_scanlines(rasterizer, sl, ren);
		   }

		  delete path;
		  //todo ver se relamente foi limpada
	 }

	void renderer::circle(agg::svg::path_render* path, double cx, double cy, double raio){
		//TODO implementar
	}

	void renderer::rect(agg::svg::path_render* path, double x, double y, double width, double height, double rx, double ry)
	 {//rx and the ry attributes rounds the corners of the rectangle
		//TODO implementar rx e ry
		path->path.move_to(x,     y);
		path->path.line_to(x + width, y);
		path->path.line_to(x + width, y + height);
		path->path.line_to(x,     y + height);
	 }

	void renderer::line(agg::svg::path_render* path, double x1, double y1, double x2, double y2)
	 {
		path->path.move_to(x1, y1);
		path->path.line_to(x2, y2);
	 }

	void renderer::polygon(agg::svg::path_render* path, double paths[], unsigned length)
	 {
		int i;
		double x = paths[0];
		double y = paths[1];

		path->path.move_to(x, y);

		for(i = 2; i<length; i++)
		{
			x = paths[i];
			y = paths[++i];
			path->path.line_to(x, y);
		}

		path->path.close_polygon();

	 }

	void renderer::move_to(agg::svg::path_render* path, double x, double y, bool rel)
	 {
		if(rel) path->path.rel_to_abs(&x, &y);
		path->path.move_to(x, y);
	 }

	void renderer::line_to(agg::svg::path_render* path, double x,  double y, bool rel)
	 {
		if(rel) path->path.rel_to_abs(&x, &y);
		path->path.line_to(x, y);
	 }

	void renderer::hline_to(agg::svg::path_render* path, double x, bool rel)
	 {
		double x2 = 0.0;
		double y2 = 0.0;
		if(path->path.total_vertices())
		{
			path->path.vertex(path->path.total_vertices() - 1, &x2, &y2);
			if(rel) x += x2;
			path->path.line_to(x, y2);
		}
	 }

	void renderer::vline_to(agg::svg::path_render* path, double y, bool rel)
	 {
		double x2 = 0.0;
		double y2 = 0.0;
		if(path->path.total_vertices())
		{
			path->path.vertex(path->path.total_vertices() - 1, &x2, &y2);
			if(rel) y += y2;
			path->path.line_to(x2, y);
		}
	 }

	void renderer::curve3(agg::svg::path_render* path, double x1, double y1, double x,  double y, bool rel)
	 {
		if(rel)
		{
			path->path.rel_to_abs(&x1, &y1);
			path->path.rel_to_abs(&x,  &y);
		}
		path->path.curve3(x1, y1, x, y);
	 }

	void renderer::curve3(agg::svg::path_render* path, double x, double y, bool rel)
	 {
		if(rel)
		{
			path->path.curve3_rel(x, y);
		} else
		{
			path->path.curve3(x, y);
		}
	 }

	void renderer::curve4(agg::svg::path_render* path, double x1, double y1, double x2, double y2, double x,  double y, bool rel)
	 {
		if(rel)
		{
			path->path.rel_to_abs(&x1, &y1);
			path->path.rel_to_abs(&x2, &y2);
			path->path.rel_to_abs(&x,  &y);
		}
		path->path.curve4(x1, y1, x2, y2, x, y);
	 }

	void renderer::curve4(agg::svg::path_render* path, double x2, double y2, double x,  double y, bool rel)
	 {
		if(rel)
		{
			path->path.curve4_rel(x2, y2, x, y);
		} else
		{
			path->path.curve4(x2, y2, x, y);
		}
	 }

	 void renderer::attribute(agg::svg::path_render* path, const char* name, const char* strValue, double numberValue)
	  {
	        if(strcmp(name, "fill") == 0)
	        {
	            if(strcmp(strValue, "none") == 0)
	            {
	            	path->fill_flag = false;
	            }
	            else
	            {
	            	path->fill_color =  renderer::color_utils.parse_color(strValue);
	            	path->fill_flag  = true;
	            }
	        }
	        else
	        if(strcmp(name, "fill-opacity") == 0)
	        {
	        	path->fill_color.opacity(numberValue);
	        }
	        else
	        if(strcmp(name, "stroke") == 0)
	        {
	            if(strcmp(strValue, "none") == 0)
	            {
	            	path->stroke_flag = false;
	            }
	            else
	            {
	            	path->stroke_color = renderer::color_utils.parse_color(strValue);
	            	path->stroke_flag = true;
	            }
	        }
	        else
	        if(strcmp(name, "stroke-width") == 0)
	        {
	        	path->stroke_width = numberValue;
	        }
	        else
	        if(strcmp(name, "stroke-linecap") == 0)
	        {
	            if(strcmp(strValue, "butt") == 0)     	 path->line_cap = butt_cap;
	            else if(strcmp(strValue, "round") == 0)  path->line_cap = round_cap;
	            else if(strcmp(strValue, "square") == 0) path->line_cap = square_cap;
	        }
	        else
	        if(strcmp(name, "stroke-linejoin") == 0)
	        {
	            if(strcmp(strValue, "miter") == 0)   	 path->line_join = miter_join;
	            else if(strcmp(strValue, "round") == 0)  path->line_join = round_join;
	            else if(strcmp(strValue, "bevel") == 0)  path->line_join = bevel_join;
	        }
	        else
	        if(strcmp(name, "stroke-miterlimit") == 0)
	        {
	        	path->miter_limit = numberValue;
	        }
	        else
	        if(strcmp(name, "stroke-opacity") == 0)
	        {
	        	path->stroke_color.opacity(numberValue);
	        }
	        else
	        if(strcmp(name, "fill-rule") == 0)
	        {
	        	  if(strcmp(strValue, "nonzero") == 0)
					{
						path->even_odd_flag = false;
					}
	        	  else
	        	   {
	        		    path->even_odd_flag = true;
	        	   }
	        }
	   }

	  void renderer::matrix(agg::svg::path_render* path, double val1, double val2, double val3, double val4, double val5, double val6)
	   {
		  path->transform.premultiply(trans_affine(val1, val2, val3, val4, val5, val6));
	   }

	  void renderer::translate(agg::svg::path_render* path, double val1, double val2)//caso tenha somente um valor o val1 deve ser 0
	   {
		  path->transform.premultiply(trans_affine_translation(val1, val2));
	   }

	  void renderer::rotate(agg::svg::path_render* path, double val1)
	  {
		  path->transform.premultiply(trans_affine_rotation(deg2rad(val1)));
	  }

	  void renderer::rotate(agg::svg::path_render* path, double val1, double val2, double val3)
	  {
		  trans_affine t = trans_affine_translation(-val2, -val3);
		  t *= trans_affine_rotation(deg2rad(val1));
		  t *= trans_affine_translation(val2, val3);

		  path->transform.premultiply(t);
	  }

	  void renderer::scale(agg::svg::path_render* path, double val1, double val2)//se somente 1 ambos deve ser igual
	  {
		  path->transform.premultiply(trans_affine_scaling(val1, val2));
	  }

	  void renderer::skew_x(agg::svg::path_render* path, double value)
	  {
		  path->transform.premultiply(trans_affine_skewing(deg2rad(value), 0.0));
	  }

	  void renderer::skew_y(agg::svg::path_render* path, double value)
	  {
		  path->transform.premultiply(trans_affine_skewing(0.0,deg2rad(value)));
	  }

	  void renderer::close_subpath(agg::svg::path_render* path)
	  {
		  path->path.end_poly(path_flags_close);
	  }

	  void renderer::pathD(agg::svg::path_render* path, char key, double arg[], unsigned length)
	  {
		  int i;

		  double x  = 0.0;
		  double y  = 0.0;
		  double x1 = 0.0;
		  double y1 = 0.0;
		  double x2 = 0.0;
		  double y2 = 0.0;

		  switch(key)
		  {
			  case 'M': case 'm':
					  x = arg[0];
					  y = arg[1];

					  move_to(path, x, y, key == 'm');

				  break;
			  case 'L': case 'l':

				  for(i = 0; i<length; i++)//TODO verificar se pode ter v�rias chamadas simultaneas o L e l
				 	{
					  x = arg[i];
					  y = arg[++i];
					  line_to(path, x, y, key == 'l');
				 	}

				  break;

			  case 'V': case 'v':
				  y = arg[0];

				  vline_to(path, y, key == 'v');

				  break;

			  case 'H': case 'h':
				  x = arg[0];

				  hline_to(path, x, key == 'h');

				  break;

			  case 'Q': case 'q':

				  for(i = 0; i<length; i++)
				   {
					  x  = arg[i];
					  y  = arg[++i];
					  x1 = arg[++i];
					  y1 = arg[++i];

					  curve3(path, x, y, x1, y1, key == 'q');
				   }
				  break;

			  case 'T': case 't':
				  x = arg[0];
				  y = arg[1];

				  curve3(path, x, y, key == 't');
				  break;

			  case 'C': case 'c':

				  for(i = 0; i<length; i++)
				   {
					  x = arg[i];
					  y = arg[++i];
					  x1 = arg[++i];
					  y1 = arg[++i];
					  x2 = arg[++i];
					  y2 = arg[++i];

					  curve4(path, x, y, x1, y1, x2, y2, key == 'c');
				   }
				  break;

			  case 'S': case 's':

				  for(i = 0; i<length; i++)
				   {
					  x = arg[i];
					  y = arg[++i];
					  x1 = arg[++i];
					  y1 = arg[++i];

					  curve4(path, x, y, x1, y1, key == 's');
				   }
				  break;
		//	  case 'Z': case 'z':
		//		  close_subpath(path);//TODO erro
			  default:
				  break;

		  }
	  }
}
}
