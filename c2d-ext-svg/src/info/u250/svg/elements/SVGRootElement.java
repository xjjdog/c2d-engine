package info.u250.svg.elements;

import java.util.ArrayList;

public class SVGRootElement extends SVGElement{
	
	ArrayList<SVGElement> defs;
	
	public SVGRootElement() {
		super("svg");
	}

	public int width;
	public int height;
	public int format;
	
	public float min_x;
    public float min_y;
    public float max_x;
    public float max_y;

    public float scale;
    
    private boolean childIsDef;
	
	public void setChildIsDef(boolean childIsDef) {
		this.childIsDef = childIsDef;
	}
	
	public boolean isChildIsDef() {
		return childIsDef;
	}

	public long getLongData(){
		return width * height * format;
	}
	
	@Override
	public void addChild(SVGElement element) {
		if ( !childIsDef ){
			super.addChild(element);
		}else{
			if ( defs == null ){
				defs = new ArrayList<SVGElement>();
			}
			defs.add(element);
		}
	}
}
