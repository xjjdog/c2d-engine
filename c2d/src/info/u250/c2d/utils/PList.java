package info.u250.c2d.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;
/**
 * Plist xml handling (only deserialization)
 * <p>
 * <em>The xml plist dtd can be found at http://www.apple.com/DTDs/PropertyList-1.0.dtd</em>
 * <p>
 * The plist spec handles 8 types of objects: booleans, real, integers, dates, binary data,
 * strings, arrays (lists) and dictionaries (maps).
 * <p>
 * The java Plist lib handles converting xml plists to a nested {@code Map<String, Object>}
 * that can be trivially read from java. It also provides a simple way to convert a nested
 * {@code Map<String, Object>} into an xml plist representation.
 * <p>
 * The following mapping will be done when converting from plist to <tt>Map</tt>:
 * <pre>
 * true/false -> Boolean
 * real -> Double
 * integer -> Integer/Long (depends on size, values exceeding an int will be rendered as longs)
 * data -> byte[]
 * string -> String
 * array -> List
 * dict -> Map
 * </pre>
 * <p>
 * When converting from Map -> plist the conversion is as follows:
 * <pre>
 * Boolean -> true/false
 * Float/Double -> real
 * Byte/Short/Integer/Long -> integer
 * byte[] -> data
 * List -> libgdx's array
 * Map -> dict
 * </pre> 
 *
 * @author lycying@gmail.com
 */
public class PList {
	@SuppressWarnings("unchecked")
	public static Map<String, Object> parse(FileHandle file) throws IOException{
		return (Map<String, Object>)parse(new XmlReader().parse(file).getChild(0));
	}
	private static Object parse(Element ele){
		final String rootType = ele.getName();
		if(rootType.equals("dict")){
			Map<String, Object> map = new HashMap<String,Object>();
			for(int i=0;i< ele.getChildCount()/2;i++){
				Element elementKey = ele.getChild(i*2);
				Element elementValue = ele.getChild(i*2+1);
				map.put(elementKey.getText(), parse(elementValue));
			}
			return map;
		}else if(rootType.equals("array")){
			Array<Object> list = new Array<Object>();
			for(int i=0;i<ele.getChildCount();i++){
				Element e = ele.getChild(i);
				list.add(parse(e));
			}
			return list;
		}else if(rootType.equals("real")){
			return Float.parseFloat(ele.getText());
		}else if(rootType.equals("integer")){
			return Integer.parseInt(ele.getText());
		}else if(rootType.equals("boolean")){
			return Boolean.parseBoolean(ele.getText());
		}
//		else if(rootType.equals("data")){
		//do not know how to achive this right now , you may help me ~
//		}
		else{
			return ele.getText();
		}	
	}
}
