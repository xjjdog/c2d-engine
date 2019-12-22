package info.u250.svg.elements;

public class SVGElementLine extends SVGElement{

	public double x1; 
	public double y1; 
	public double x2; 
	public double y2;
	
	public SVGElementLine() {
		this(0d,0d,0d,0d);
	}
	
	public SVGElementLine(double x1, double y1, double x2, double y2) {
		super("line");
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	@Override
	public void addAttribute(String attribute, double value){
		if ( "x1".equals(attribute) ){
			x1  = value;
		}else if ( "y1".equals(attribute) ){
			y1 = value;
		}else if ( "x2".equals(attribute) ){
			x2 = value;
		}else if ( "y2".equals(attribute) ){
			y2 = value;
		}
	}
	
	@Override
	public SVGElement deepCopy(){
		return super.deepCopy(new SVGElementLine(x1, y1, x2, y2));
	}
	
}
