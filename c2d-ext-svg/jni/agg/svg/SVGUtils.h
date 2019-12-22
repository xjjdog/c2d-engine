#ifndef SVGUTILS_INCLUDED
#define SVGUTILS_INCLUDED

#include <iostream>
#include <string>

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "../include/agg_basics.h"
#include "../include/agg_color_rgba.h"

using namespace std;

namespace agg
{
namespace svg
{

class utils{

	public:
		utils();
		rgba8 parse_color(const char* str);
};

}
}
#endif
