package com.iq4j.javacv.imgproc;

import com.googlecode.javacv.cpp.opencv_core.CvSize;

public class GaussianBlurSettings  extends Settings {

	private int ksizeX = 5; //=0,1,3,5,7,9,11
	private int ksizeY = 5; //=0,1,3,5,7,9,11
	private double sigmaX = 5; 
	private double sigmaY = 0; // =0
	private int borderType = 4; // =0,1,2,3,4,5,16

	public GaussianBlurSettings() {
	}

	public GaussianBlurSettings(boolean active, int ksizeX, int ksizeY,	double sigmaX, double sigmaY, int borderType) {
		super();
		setActive(active);
		this.ksizeX = ksizeX;
		this.ksizeY = ksizeY;
		this.sigmaX = sigmaX;
		this.sigmaY = sigmaY;
		this.borderType = borderType;
	}

	/* (non-Javadoc)
	 * @see com.iq4j.javacv.imgproc.Settings#clone()
	 */
	@Override
	public GaussianBlurSettings clone() {
		return new GaussianBlurSettings(isActive(), ksizeX, ksizeY, sigmaX, sigmaY, borderType);
	}
	
	public CvSize getKSize() {
		return new CvSize(ksizeX, ksizeY);
	}

	public int getKsizeX() {
		return ksizeX;
	}

	public void setKsizeX(int ksizeX) {
		this.ksizeX = ksizeX;
	}

	public int getKsizeY() {
		return ksizeY;
	}

	public void setKsizeY(int ksizeY) {
		this.ksizeY = ksizeY;
	}

	public double getSigmaX() {
		return sigmaX;
	}

	public void setSigmaX(double sigmaX) {
		this.sigmaX = sigmaX;
	}

	public double getSigmaY() {
		return sigmaY;
	}

	public void setSigmaY(double sigmaY) {
		this.sigmaY = sigmaY;
	}

	public int getBorderType() {
		return borderType;
	}

	public void setBorderType(int borderType) {
		this.borderType = borderType;
	}

}
