package info.u250.svg.elements;

public class SVGElementRect extends SVGElement{

	public double x; 
	public double y; 
	public double width; 
	public double height; 
	public double rx; 
	public double ry;
	
	public SVGElementRect() {
		this(0d,0d,0d,0d,0d,0d);
	}
	
	public SVGElementRect(double x, double y, double width, double height, double rx, double ry) {
		super("rect");
		this.x 		= x;
		this.y 		= y;
		this.width  = width;
		this.height = height;
		this.rx 	= rx;
		this.ry 	= ry;
	}
	
	@Override
	public void addAttribute(String attribute, double value){
		if ( "x".equals(attribute) ){
			x  = value;
		}else if ( "y".equals(attribute) ){
			y = value;
		}else if ( "width".equals(attribute) ){
			width = value;
		}else if ( "height".equals(attribute) ){
			height = value;
		}else if ( "rx".equals(attribute) ){
			rx = value;
		}else if ( "ry".equals(attribute) ){
			ry = value;
		}
	}
	
	@Override
	public SVGElement deepCopy(){
		return super.deepCopy(new SVGElementRect(x, y, width, height, rx, ry));
	}
}
