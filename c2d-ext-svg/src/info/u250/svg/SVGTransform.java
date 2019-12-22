package info.u250.svg;

public class SVGTransform {
	
	public String name;
	public double[] values;
	
	public SVGTransform(){}
	
	public SVGTransform(String name, double[] values){
		this.name   = name;
		this.values = values;
	}
	
}
