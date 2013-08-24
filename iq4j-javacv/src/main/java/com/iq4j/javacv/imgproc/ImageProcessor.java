package com.iq4j.javacv.imgproc;

import com.googlecode.javacv.cpp.opencv_core.CvMemStorage;
import com.googlecode.javacv.cpp.opencv_core.IplImage;

/**
 * @author Sertac ANADOLLU ( anatolian )
 *
 */
public interface ImageProcessor {
	
	String getName();
	IplImage process(IplImage source, CvMemStorage storage);
	
}
