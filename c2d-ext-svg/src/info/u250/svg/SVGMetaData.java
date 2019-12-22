package info.u250.svg;

import info.u250.svg.elements.SVGElement;
import info.u250.svg.elements.SVGRootElement;

import java.util.ArrayList;


public class SVGMetaData implements SVGConstants{
	
	private ArrayList<SVGElement> defs;
	
	public SVGMetaData(SVGRootElement rootElement){
		this(rootElement, 10);
	}
	
	public SVGMetaData(SVGRootElement rootElement, int maxDefs){
		if ( defs != null ){
			defs = new ArrayList<SVGElement>(maxDefs);
		}
		parse(rootElement);
		end();
	}
	
	private void parse(SVGElement element){
		
		//Styles inLine
		String styleInLine = element.getStyleInLine();
		
		if ( styleInLine != null ){
				SVGStyle[] stylesInLine = ParseUtils.extractStyleProperty(styleInLine.toString());
				
				if ( stylesInLine.length > 0){
					
					if ( element.getStyle() != null ){
						
						element.setStyle(ParseUtils.concatenateStyle(stylesInLine, element.getStyle()));
						
					}else{
						
						element.setStyle(stylesInLine);
					
					}
				}
		}
		
		if ( !element.equals(TAG_DEFS) ){
			
			ArrayList<SVGElement> childs = element.getChild();
			
			if ( childs != null ){
				int length = childs.size();
				
				for ( int x=0; x<length; x++ ){
					SVGElement child = childs.get(x); 
					
					copyStyle(child, element);
					
					copyTransform(child, element);
					
					parse(child);
				}
			}
			
			if ( element.equals(TAG_USE) ){
				SVGElement refElement = getDefs(element.gethRef());
				
				SVGElement deepCopy   = refElement.deepCopy();
				
				copyStyle(deepCopy, element);
				
				copyTransform(deepCopy, element);
				
				parse(deepCopy);
				
			}else{
				next(element);
			}
			
		}else{
			defs = element.getChild();
		}
		
	}
	
	private void copyStyle(SVGElement dest, SVGElement org){
		if ( org.getStyle() != null && org.getStyle().length > 0 ){
			dest.setStyle(ParseUtils.concatenateStyle(org.getStyle(), dest.getStyle()));
		}
	}

	private void copyTransform(SVGElement dest, SVGElement org){
		if ( org.getTransforms() != null && org.getTransforms().length > 0 ){
			dest.setTransforms(ParseUtils.concatenateTransform(org.getTransforms(), dest.getTransforms()));
		}
	}
	
	public void next(SVGElement element){
	}
	
	public void end(){
		
	}
	
	private SVGElement getDefs(String hRef){
		int length = defs.size();
		
		for ( int x=0; x<length; x++ ){
			SVGElement element = defs.get(x);
			if ( hRef.equals(element.getId()) ){
				return element;
			}
		}
		
		return null;
	}
	
}
