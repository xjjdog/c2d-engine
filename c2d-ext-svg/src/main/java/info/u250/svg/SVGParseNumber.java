package info.u250.svg;

public class SVGParseNumber {

	private static final String REGEX = "[\\s,]+"; 
	
	public static double[] parseNumbers(String val) {
		if ( val != null ){
			String[] parts   = val.split(REGEX);
			double[] numbers = new double[parts.length];
			
			for(int i = parts.length - 1; i >= 0; i--) {
				numbers[i] = Double.parseDouble(parts[i]);
			}
			
			return numbers;
		}
		return null;
	}

	public static int[] parseInts(String val) {
		if ( val != null ){
			String[] parts 	= val.split(REGEX);
			int[] numbers 	= new int[parts.length];
			
			for(int i = parts.length - 1; i >= 0; i--) {
				numbers[i] = Integer.parseInt(parts[i]);
			}
			
			return numbers;
		}
		return null;
	}

}
