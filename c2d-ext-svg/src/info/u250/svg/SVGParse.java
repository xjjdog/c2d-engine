package info.u250.svg;

import info.u250.svg.elements.SVGElement;
import info.u250.svg.elements.SVGElementCircle;
import info.u250.svg.elements.SVGElementEllipse;
import info.u250.svg.elements.SVGElementLine;
import info.u250.svg.elements.SVGElementPath;
import info.u250.svg.elements.SVGElementPolyLine;
import info.u250.svg.elements.SVGElementPolygon;
import info.u250.svg.elements.SVGElementRect;
import info.u250.svg.elements.SVGRootElement;

import java.io.IOException;
import java.util.Stack;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.XmlReader;

public class SVGParse implements SVGConstants{
	
	FileHandle file;
	
	public SVGParse(FileHandle file){
		this.file = file;
	}
	
	public void parse(SVGRootElement root){
		
		final Stack<SVGElement> stack    = new Stack<SVGElement>();
		
		stack.push(root);
		
		try {
			new XmlReader(){
				
				Stack<String> currElement = new Stack<String>();
				SVGElement currentRoot	  = stack.lastElement();
				SVGElement element;

				@Override
				protected void open (String name) {
					
					currElement.push(name);
					
					if(name.equals(TAG_RECTANGLE)) {
						
						element = new SVGElementRect();
						
					}else if(name.equals(TAG_LINE)) {
						
						element = new SVGElementLine();
						
					} else if(name.equals(TAG_CIRCLE)) {
						
						element = new SVGElementCircle();
						
					} else if(name.equals(TAG_ELLIPSE)) {
						
						element = new SVGElementEllipse();
						
					} else if(name.equals(TAG_POLYLINE)) {
						
						element = new SVGElementPolyLine();
						
					} else if(name.equals(TAG_POLYGON)) {
						
						element = new SVGElementPolygon();
						
					} else if(name.equals(TAG_PATH)) {
						
						element = new SVGElementPath();
						
					} else if(name.equals(TAG_USE)) {
						
						element = new SVGElement(TAG_USE);
						
					} else if ( name.equals(TAG_GROUP) || name.equals(TAG_DEFS) ){
						
						element = new SVGElement(name);
						
						currentRoot.addChild(element);
						
						stack.push(element);
						
						currentRoot = stack.lastElement();
						
					} else {
						element = null;
					}
					
				}
				
				@Override
				protected void attribute (String name, String value) {
					
					if ( element == null || name.startsWith(TAG_IGNORED_SODIPODI) || name.startsWith(TAG_IGNORED_INKSCAPE) ){
						return;
					}
					
					if ( name.equals(ATTRIBUTE_ID) ){
						
						element.setId(value);
						
					}else if ( name.equals(ATTRIBUTE_X) || name.equals(ATTRIBUTE_X2) || name.equals(ATTRIBUTE_Y)  || name.equals(ATTRIBUTE_Y2) || name.equals(ATTRIBUTE_X1) || name.equals(ATTRIBUTE_Y1) || name.equals(ATTRIBUTE_WIDTH) || name.equals(ATTRIBUTE_HEIGHT) || name.equals(ATTRIBUTE_RADIUS) || name.equals(ATTRIBUTE_RADIUS_X) ||  name.equals(ATTRIBUTE_RADIUS_Y) || name.equals(ATTRIBUTE_CENTER_X) || name.equals(ATTRIBUTE_CENTER_Y) ){
						
						element.addAttribute(name, ParseUtils.extractNumberAttribute(value));
						
					}else if ( name.equals(ATTRIBUTE_STYLE) ){
						
						element.setStyle(ParseUtils.extractStyleProperty(value));
						
					}else if ( name.endsWith(ATTRIBUTE_HREF) ){
						
						element.sethRef(value);
						
					}else if ( name.equals(ATTRIBUTE_TRANSFORM)){
						
						element.setTransforms(ParseUtils.extractTransform(value));
						
					}else if ( name.equals(ATTRIBUTE_POINTS)){
						
						((SVGElementPolygon)element).paths = ParseUtils.extractPoints(value);
						
					}else if ( name.equals(ATTRIBUTE_PATHDATA)){
						
						((SVGElementPath)element).path = ParseUtils.extractPathMetadata(value);
						
					}else if ( ATTRIBUTE_FILL.equals(name)  || 
							   ATTRIBUTE_FILLRULE.equals(name)|| 
							   ATTRIBUTE_STROKE.equals(name)|| 
							   ATTRIBUTE_STROKE_LINEJOIN_VALUE_.equals(name)||
							   ATTRIBUTE_STROKE_LINECAP.equals(name)  ||
						       ATTRIBUTE_FILL_OPACITY.equals(name) || 
							   ATTRIBUTE_STROKE_WIDTH.equals(name) ||
							   ATTRIBUTE_STROKE_MITER_LIMIT.equals(name) ||
							   ATTRIBUTE_STROKE_OPACITY.equals(name) ){
							
							   element.addStyleInLine(name,value);
						
						}
					
				}	
				
				@Override
				protected void text (String text) {
					
				}
				
				@Override
				protected void close () {
					
					String currentElement =  currElement.pop();
					
					if ( element != null && !TAG_GROUP.equals(element.getName()) ){
						currentRoot.addChild(element);
						element = null;
					}
					
					if ( TAG_GROUP.equals(currentElement) || TAG_DEFS.equals(currentElement) ){
						stack.pop();
						currentRoot = stack.lastElement();
					}
					
				}
				
			}.parse(file);
			
		} catch (IOException e) {
			throw new RuntimeException("Error Parsing TMX file "+e.getMessage());
		}
		
	}
	
}
