package info.u250.svg.elements;

import info.u250.svg.SVGPath;

public class SVGElementPolygon extends SVGElement{
	
	public double paths[];

	public SVGElementPolygon() {
		this(null);
	}
	
	public SVGElementPolygon(double paths[]) {
		super("polygon");
		this.paths = paths;
	}
	
	@Override
	public SVGElement deepCopy(){
		SVGElementPolygon element   = (SVGElementPolygon) super.deepCopy(new SVGElementPolygon());
		
		double pathCopy[] 	 		= new double[paths.length];
		System.arraycopy(paths, 0, pathCopy, 0, paths.length);
		
		element.paths 				= pathCopy;
		
		return element;
	}
	
}
