package info.u250.svg.elements;

public class SVGElementEllipse extends SVGElementCircle{


	public SVGElementEllipse() {
		this(0d,0d,0d);
	}
	
	public SVGElementEllipse(double cx, double cy, double raio) {
		super("ellipse", cx, cy, raio);
	}
	
	
	@Override
	public SVGElement deepCopy(){
		return super.deepCopy(new SVGElementEllipse(cx, cy, raio));
	}
}
