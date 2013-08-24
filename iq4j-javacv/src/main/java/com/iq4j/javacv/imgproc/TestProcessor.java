package com.iq4j.javacv.imgproc;

import static com.googlecode.javacv.cpp.opencv_imgproc.cvCanny;

import com.googlecode.javacv.cpp.opencv_core.CvContour;
import com.googlecode.javacv.cpp.opencv_core.CvMemStorage;
import com.googlecode.javacv.cpp.opencv_core.CvSeq;
import com.googlecode.javacv.cpp.opencv_core.IplImage;

/**
 * @author Sertac ANADOLLU ( anatolian )
 *
 */
public class TestProcessor implements ImageProcessor {

	/* (non-Javadoc)
	 * @see com.iq4j.javacv.imgproc.ImageProcessor#getName()
	 */
	public String getName() {
		return "Test Processor";
	}

	public void prCanny(IplImage src, CannySettings _cs) {
		if(_cs.isActive()) {
			cvCanny(src, src, _cs.getThresh1(), _cs.getThresh2(), _cs.getAperture_size());
		}		
	}
	
	/* (non-Javadoc)
	 * @see com.iq4j.javacv.imgproc.ImageProcessor#process(com.googlecode.javacv.cpp.opencv_core.IplImage, com.googlecode.javacv.cpp.opencv_core.CvMemStorage)
	 */
	public IplImage process(IplImage source, CvMemStorage storage) {
		

		
		ImgProc.getCannySettings();
		IplImage grayImage = ImgProc.prConvertToGray(source);
		ImgProc.prGaussianBlur(grayImage, grayImage);
		ImgProc.prMedianBlur(grayImage, grayImage);		
		
		ImgProc.prCanny(grayImage, grayImage);
		
		//cvCanny(grayImage, grayImage, ImgProc.getCannySettings().getThresh1() ,ImgProc.getCannySettings().getThresh2(), 3);		
		//ImgProc.prCanny(grayImage, grayImage);
		CvSeq contours = new CvContour(null);
		ImgProc.prFindContours(grayImage, storage, contours);
		
		
		return grayImage;
	}

}
