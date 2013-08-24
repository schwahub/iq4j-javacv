package com.iq4j.javacv.imgproc;

/**
 * @author Sertac ANADOLLU ( anatolian )
 * 
 */
public class Values {

	public static final Double[] THRESHOLD_VALUES;
	public static final Integer[] ODD_VALUES = {0,1,3,5,7,9,11,13,15};

	static {
		THRESHOLD_VALUES = new Double[25];
		for (int i = 0; i < 25; i++) {
			THRESHOLD_VALUES[i] = new Double((i + 1) * 10);
		}
	}

	public static synchronized Integer[] generateIntegers(int min, int max) {
		int count = max - min + 1;
		if (count < 1) {
			return new Integer[0];
		} else {			
			Integer[] result = new Integer[count];
			for (int j = 0; j < count; j++) {
				result[j] = Integer.valueOf(min + j);
			}
			return result;
		}
	}
	
    public static synchronized Double[] generateDoubles(double min, double max) {
    	Integer[] intValues = generateIntegers(Double.valueOf(min).intValue(), Double.valueOf(max).intValue());
    	return generateDoubles(intValues);
    }
    
    public static synchronized Double[] generateDoubles(Integer[] integers) {
    	
    	if(integers.length > 0) {
    		Double[] result = new Double[integers.length];
    		for (int i = 0; i < integers.length; i++) {
				result[i] = Double.valueOf(integers[i]);
			}
    		return result;
    	} else {
    		return new Double[0];
    	}
    	
    }

}
