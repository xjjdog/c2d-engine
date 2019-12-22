package info.u250.svg.elements;

import info.u250.svg.SVGStyle;
import info.u250.svg.SVGTransform;

import java.util.ArrayList;

import com.badlogic.gdx.utils.Array;

public class SVGElement{
	
	private String 	 		name;
	private String 	 		id;
	private String 	 		hRef;
	private SVGStyle[] 		style; 
	private SVGTransform[]  transforms; 
	private StringBuilder   styleInline;
	
	protected ArrayList<SVGElement> child;
	
	public SVGElement(String name){
		this.name=name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String gethRef() {
		return hRef;
	}

	public void sethRef(String hRef) {
		if ( hRef != null ){
		   if ( hRef.startsWith("#") ){	
			   this.hRef = hRef.substring(1);//#link
		   }else{
			   this.hRef = hRef;
		   }
		}
	}

	public SVGStyle[] getStyle() {
		return style;
	}

	public void setStyle(SVGStyle[] style) {
		this.style = style;
	}

	public SVGTransform[] getTransforms() {
		return transforms;
	}

	public void setTransforms(SVGTransform[] transforms) {
		this.transforms = transforms;
	}

	public ArrayList<SVGElement> getChild() {
		return child;
	}
	
	public void addStyleInLine(String key, String value){
		if ( styleInline == null ){
			styleInline = new StringBuilder();
		}
		styleInline.append(key).append(":").append(value);
	}
	
	public void setStyleInline(StringBuilder styleInline){
		this.styleInline=styleInline;
	}
	
	public String getStyleInLine(){
		return styleInline != null ? styleInline.toString() : null;
	}
	
	public void addChild(SVGElement element) {
		if ( child == null ){
			child = new ArrayList<SVGElement>();
		}
		child.add(element);
	}
	
	public void addAttribute(String attribute, double value){
		
	}
	
	@Override
	public boolean equals(Object value){
		return name.equals(value);
	}
	
	public SVGElement deepCopy(){
		return deepCopy(new SVGElement(name));
	}
	
	public SVGElement deepCopy(SVGElement element){
		element.setId(id);
		element.sethRef(hRef);
		element.setStyleInline(styleInline);
		
		if ( style != null ){
			SVGStyle[] styleCopy = new SVGStyle[style.length];
			System.arraycopy(style, 0, styleCopy, 0, style.length);
			element.setStyle(styleCopy);
		}
		
		if ( transforms != null ){
			SVGTransform[] transformCopy = new SVGTransform[transforms.length];
			System.arraycopy(transforms, 0, transformCopy, 0, transforms.length);
			element.setTransforms(transformCopy);
		}
		
		if ( child != null ){
			int length = child.size();
			for ( int x=0; x<length; x++){
				element.addChild(child.get(x).deepCopy());
			}
		}
		
		return element;
	}
}
