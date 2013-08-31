package com.iq4j.javacv.imgproc;

import static com.googlecode.javacpp.Loader.*;
import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;
import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;
import static com.googlecode.javacv.cpp.opencv_calib3d.*;
import static com.googlecode.javacv.cpp.opencv_objdetect.*;
import static com.googlecode.javacv.cpp.opencv_highgui.*;

import com.googlecode.javacv.cpp.opencv_core.IplImage;

@SuppressWarnings("unused")
public class ImgProc {

	private static volatile GaussianBlurSettings _gbs = new GaussianBlurSettings(); 
	private static volatile MedianBlurSettings _mbs = new MedianBlurSettings();
	private static volatile CannySettings _cs = new CannySettings(true, 50.0, 50.0, 3);
	private static volatile FindContoursSettings _fcs = new FindContoursSettings();
	
	
	public static synchronized GaussianBlurSettings getGaussianBlurSettings() {
		return _gbs;
	}

	public static synchronized  MedianBlurSettings getMedianBlurSettings() {
		return _mbs;
	}
	
	public static synchronized CannySettings getCannySettings() {
		return _cs;
	}
	
	public static synchronized FindContoursSettings getDefaultFindContoursSettings() {
		return _fcs;
	}
	
	public static synchronized void setGaussianBlurSettings(GaussianBlurSettings gbs) {
		ImgProc._gbs = gbs;
	}

	public static synchronized void setMedianBlurSettings(MedianBlurSettings mbs) {
		ImgProc._mbs = mbs;
	}

	public static synchronized void setCannySettings(CannySettings cs) {
		ImgProc._cs = cs;
	}

	public static synchronized void setFindContourSettings(FindContoursSettings fcs) {
		ImgProc._fcs = fcs;
	}

	public static synchronized void prGaussianBlur(IplImage src, IplImage dst) {
		if(_gbs.isActive()) {			
			GaussianBlur(src, dst, _gbs.getKSize(), _gbs.getSigmaX(), _gbs.getSigmaY(), _gbs.getBorderType());
		}
	}
	
	public static synchronized IplImage prConvertToGray(IplImage src) {
		IplImage grayImage    = IplImage.create(src.width(), src.height(), IPL_DEPTH_8U, 1);
		cvCvtColor(src, grayImage, CV_BGR2GRAY);
		return grayImage;
	}
	
	public static synchronized void prMedianBlur(IplImage src, IplImage dst) {
		if(_mbs.isActive()) {
			medianBlur(src, dst, _mbs.getkSize());
		}
	}
	
	public static synchronized void prCanny(IplImage src, IplImage dst) {
		if(_cs.isActive()) {
			cvCanny(src, dst, _cs.getThresh1(), _cs.getThresh2(), _cs.getAperture_size());
		}		
	}
	
	public static synchronized void prFindContours(IplImage src, CvMemStorage storage, CvSeq contours) {
		if(_fcs.isActive()){
			cvFindContours(src, storage, contours, sizeof(CvContour.class),  _fcs.getMode(), _fcs.getMethod());
		}
	}
	
}
