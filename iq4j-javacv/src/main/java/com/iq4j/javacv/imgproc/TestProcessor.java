package com.iq4j.javacv.imgproc;

import static com.googlecode.javacpp.Loader.sizeof;
import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;

import com.googlecode.javacv.cpp.opencv_core.CvContour;
import com.googlecode.javacv.cpp.opencv_core.CvMemStorage;
import com.googlecode.javacv.cpp.opencv_core.CvPoint;
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
	
	static double angle(CvPoint pt1, CvPoint pt2, CvPoint pt0) {
		double dx1 = pt1.x() - pt0.x();
		double dy1 = pt1.y() - pt0.y();
		double dx2 = pt2.x() - pt0.x();
		double dy2 = pt2.y() - pt0.y();
		return (dx1 * dx2 + dy1 * dy2) / Math.sqrt((dx1 * dx1 + dy1 * dy1) * (dx2 * dx2 + dy2 * dy2) + 1e-10);
	}
	
	/* (non-Javadoc)
	 * @see com.iq4j.javacv.imgproc.ImageProcessor#process(com.googlecode.javacv.cpp.opencv_core.IplImage, com.googlecode.javacv.cpp.opencv_core.CvMemStorage)
	 */
	public IplImage process(IplImage source, CvMemStorage storage) {
		

		
		ImgProc.getCannySettings();
		IplImage grayImage = ImgProc.prConvertToGray(source);
		ImgProc.prMedianBlur(grayImage, grayImage);		
		ImgProc.prCanny(grayImage, grayImage);
		ImgProc.prGaussianBlur(grayImage, grayImage);		
		
		
		CvSeq contours = new CvContour(null);
		ImgProc.prFindContours(grayImage, storage, contours);
		
		
		
		double maxArea = -1;	    
	    CvSeq largestContour = null;
	    CvSeq approx = null;
	    
	    while (contours != null && !contours.isNull()) {
	    	if(contours.elem_size() > 0) {
	    		
	    		approx = cvApproxPoly( contours, sizeof(CvContour.class), storage, CV_POLY_APPROX_DP, cvContourPerimeter(contours)*0.03, 0 );
	    		
	    		double area = cvContourArea(approx, CV_WHOLE_SEQ, 0);	
	    		if(approx.total() == 4 && cvCheckContourConvexity(approx) != 0 && area > 1000) {

	    			
	    			if(area > maxArea ) {
	    				   System.out.println(area);
	                    	maxArea = area;
	                    	largestContour = cvCloneSeq(contours, storage);	    			
	    			}
	  	    			
	    		}
	    	
	    	}
	    	
	    	contours = contours.h_next();
		}
	    
	    if(largestContour != null) {	    		    	
	    	cvDrawContours(source, largestContour, CvScalar.GREEN, CvScalar.GREEN, 1, 2, 8);
	    	cvDrawContours(grayImage, largestContour, CvScalar.WHITE, CvScalar.WHITE, 1, 2, 8);
	    }
	    
	  
	    
//	    MatOfPoint temp_contour = contours.get(0); //the largest is at the index 0 for starting point
//	    MatOfPoint2f approxCurve = new MatOfPoint2f();
//	    Mat largest_contour = contours.get(0);
//	    List<MatOfPoint> largest_contours = new ArrayList<MatOfPoint>();
//	    for (int idx = 0; idx < contours.size(); idx++) {
//	        temp_contour = contours.get(idx);
//	        double contourarea = Imgproc.contourArea(temp_contour);
//	        //compare this contour to the previous largest contour found
//	        if (contourarea > maxArea) {
//	            //check if this contour is a square
//	            MatOfPoint2f new_mat = new MatOfPoint2f( temp_contour.toArray() );
//	            int contourSize = (int)temp_contour.total();
//	            Imgproc.approxPolyDP(new_mat, approxCurve, contourSize*0.05, true);
//	            if (approxCurve.total() == 4) {
//	                maxArea = contourarea;
//	                maxAreaIdx = idx;
//	                largest_contours.add(temp_contour);
//	                largest_contour = temp_contour;
//	            }
//	        }
//	    }
//	    MatOfPoint temp_largest = largest_contours.get(largest_contours.size()-1);
//	    largest_contours = new ArrayList<MatOfPoint>();
//	    largest_contours.add(temp_largest);
//
//	    Imgproc.cvtColor(imgSource, imgSource, Imgproc.COLOR_BayerBG2RGB);
//	    Imgproc.drawContours(imgSource, largest_contours, -1, new Scalar(0, 255, 0), 1);
//
//	    //create the new image here using the largest detected square
//
//	    Toast.makeText(getApplicationContext(), "Largest Contour: ", Toast.LENGTH_LONG).show();
//
//	    return imgSource;
		
		
		
		return grayImage;
	}

}
