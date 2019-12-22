package info.u250.svg;

import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseUtils implements SVGConstants{
	
	private static final Pattern MULTITRANSFORM_PATTERN = Pattern.compile("(\\w+\\([\\d\\s\\-eE,]*\\))");
	
	public static double extractNumberAttribute(String val) {
		if ( val != null ) {
			try {
				if ( val.endsWith(UNIT_PX) ) {
					return Double.parseDouble(val.substring(0, val.length() - 2));
				} else {
					return Double.parseDouble(val);
				}
			} catch (final NumberFormatException e) {
				return 0d;
			}
		}
		return 0d;
	}
	
	public static double[] extractPoints(String property) {
		String[] strPoints = property.trim().split(" ");
		int pointsLenght   = strPoints.length;	
		double[] points    = new double[pointsLenght*2];
		
		int index = 0;
		
		for ( int x=0; x<pointsLenght; x++ ){
			String[] temp    = strPoints[x].split(",");
			
			points[index]	 = extractNumberAttribute(temp[0]);
			points[++index]	 = extractNumberAttribute(temp[1]);
			
			index++;
		}
		
		
		return points;
		
	}
	
	public static SVGPath[] extractPathMetadata(String property) {
		property = property.replaceAll("([MmAaCcSsLlHhVvQqTtZz])",",$1,");    
		property = property.replaceAll("[^eE]-",",-");
		property = property.replaceAll("\\s+",",");
		property = property.replaceAll(",{2,}",",");
		property = property.replaceAll("^,", "");
		property = property.replaceAll(",$", "");
		 
		String[] temp     		= property.split(",");
		int length 	     		= temp.length;
		
		Vector<SVGPath> retorno = new Vector<SVGPath>();
		
		for ( int x=0; x<length; x++ ){
			
			String command = temp[x];
			
			if ( isCharacter(command) ){
				
				Vector<Double> values = new Vector<Double>();
				
				if ( !"z".equals(command) && !"Z".equals(command) ){
					while( x < (length-1) && !isCharacter(temp[++x]) ){
						values.add(Double.parseDouble(temp[x]));
					}
					x--;
					
					retorno.add(new SVGPath(command.charAt(0), vectorToDoubleArray(values)));
				}
				
			}
			
		}
		
		return retorno.toArray(new SVGPath[retorno.size()]);
	}
	
	private static double[] vectorToDoubleArray(Vector<Double> vector){
		int length		 = vector.size();
		double[] retorno = new double[length];
	
		for ( int x=0; x<length; x++ ){
			retorno[x] = vector.get(x);
		}
		
		return retorno;
	}

	private static boolean isCharacter(String value){
		return value.matches("[a-zA-Z]{1}");  
	}
	
	public static SVGTransform[] extractTransform(String property){
		
		final boolean singleTransform = property.indexOf(')') == property.lastIndexOf(')');
		
		if(singleTransform) {
			
			return new SVGTransform[]{extractFieldTransform(property)};
			
		} else {
			final Matcher matcher 				= MULTITRANSFORM_PATTERN.matcher(property);
			
			SVGTransform[] transform	 		= new  SVGTransform[matcher.groupCount()];
			int index							= 0;
			
			while( matcher.find() ) {
				transform[index] 			= extractFieldTransform(matcher.group(1));
				index++;
			}
			
			return transform;
		}
		
		
	}
	
	private static SVGTransform extractFieldTransform(String property){
		SVGTransform transform = new SVGTransform();
		
		if (property.startsWith(ATTRIBUTE_TRANSFORM_VALUE_MATRIX)) {
			
			transform.name = ATTRIBUTE_TRANSFORM_VALUE_MATRIX;
			
		} else if (property.startsWith(ATTRIBUTE_TRANSFORM_VALUE_TRANSLATE)) {
			
			transform.name = ATTRIBUTE_TRANSFORM_VALUE_TRANSLATE;
			
		} else if (property.startsWith(ATTRIBUTE_TRANSFORM_VALUE_SCALE)) {
			
			transform.name = ATTRIBUTE_TRANSFORM_VALUE_SCALE;
			
		} else if (property.startsWith(ATTRIBUTE_TRANSFORM_VALUE_SKEW_X)) {
			
			transform.name = ATTRIBUTE_TRANSFORM_VALUE_SKEW_X;
			
		} else if (property.startsWith(ATTRIBUTE_TRANSFORM_VALUE_SKEW_Y)) {
			
			transform.name = ATTRIBUTE_TRANSFORM_VALUE_SKEW_Y;
			
		} else if (property.startsWith(ATTRIBUTE_TRANSFORM_VALUE_ROTATE)) {
			
			transform.name = ATTRIBUTE_TRANSFORM_VALUE_ROTATE;
			
		} else {
			throw new RuntimeException("Unexpected transform type: '" + property + "' ");
		}
		
		transform.values			  = SVGParseNumber.parseNumbers(property.substring(transform.name.length() + 1, property.indexOf(')')));
		
		return transform;
	}
	
	public static SVGStyle[] extractStyleProperty(String style) {
		String[] styles 	= style.split(";");
		int length      	= styles.length;
		
		SVGStyle[] retorno  = new SVGStyle[length];	
		
		for ( int x=0; x < length; x++ ){
			String[] properties = styles[x].split(":");
			
			if ( ATTRIBUTE_FILL.equals(properties[0])  || 
				 ATTRIBUTE_STROKE.equals(properties[0])|| 
				 ATTRIBUTE_STROKE_LINEJOIN_VALUE_.equals(properties[0])||
				 ATTRIBUTE_STROKE_LINECAP.equals(properties[0]) ){
				
				retorno[x] = new SVGStyle(properties[0], properties[1], 0d);
				
			}else if ( ATTRIBUTE_FILLRULE.equals(properties[0]) ||
					   ATTRIBUTE_FILL_OPACITY.equals(properties[0]) || 
					   ATTRIBUTE_STROKE_WIDTH.equals(properties[0]) ||
					   ATTRIBUTE_STROKE_MITER_LIMIT.equals(properties[0]) ||
					   ATTRIBUTE_STROKE_OPACITY.equals(properties[0]) ){
				
				retorno[x] = new SVGStyle(properties[0], "", extractNumberAttribute(properties[1]));
			
			}
			
		}
		
		return retorno;
	}
	
	public static SVGStyle[] concatenateStyle(SVGStyle[] style1, SVGStyle[] style2) {
		
		if ( style2 == null || style2.length == 0 ){
			return style1;
		}
		
		int style1Lenght = style1.length;
		int style2Lenght = style2.length;
		
		SVGStyle[] ret = new SVGStyle[style1Lenght + style2Lenght];
		
		System.arraycopy(style1, 0, ret, 0, style1Lenght);
		System.arraycopy(style2, 0, ret, style1Lenght, style2Lenght);
		
		return ret;
	}
	
	public static SVGTransform[] concatenateTransform(SVGTransform[] transform1, SVGTransform[] transform2) {
		
		if ( transform2 == null || transform2.length == 0 ){
			return transform1;
		}
		
		int trans1Lenght = transform1.length;
		int trans2Lenght = transform2.length;
		
		SVGTransform[] ret = new SVGTransform[trans1Lenght + trans2Lenght];
		
		System.arraycopy(transform1, 0, ret, 0, trans1Lenght);
		System.arraycopy(transform2, 0, ret, trans1Lenght, trans2Lenght);
		
		return ret;
	}
}
