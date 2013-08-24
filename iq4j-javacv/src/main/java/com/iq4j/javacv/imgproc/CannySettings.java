package com.iq4j.javacv.imgproc;

public class CannySettings extends Settings {

	private volatile double thresh1 = 50; // =0~255
	private volatile double thresh2 = 100; // =0~255
	private volatile int aperture_size = 3; // =3

	public CannySettings() {
		//this(true, 50.0, 100.0, Integer.valueOf(3));
	}

	/**
	 * @param thresh1
	 * @param thresh2
	 * @param aperture_size
	 */
	public CannySettings(boolean active, Double thresh1, Double thresh2, Integer aperture_size) {
		super();
		setActive(active);
		this.thresh1 = thresh1;
		this.thresh2 = thresh2;
		this.aperture_size = aperture_size;
	}

	public CannySettings(CannySettings source) {
		this.aperture_size = Integer.valueOf(source.aperture_size);
		this.thresh1 = Double.valueOf(source.thresh1);
		this.thresh2 = Double.valueOf(source.thresh2);
		this.setActive(Boolean.valueOf(source.isActive()));
	}
	
	/* (non-Javadoc)
	 * @see com.iq4j.javacv.imgproc.Settings#clone()
	 */
	@Override
	public CannySettings clone() {
		return new CannySettings(isActive(), thresh1, thresh2, aperture_size);
	}
	
	public double getThresh1() {
		return thresh1;
	}

	public void setThresh1(double thresh1) {
		this.thresh1 = thresh1;
	}

	public double getThresh2() {
		return thresh2;
	}

	public void setThresh2(double thresh2) {
		this.thresh2 = thresh2;
	}

	public int getAperture_size() {
		return aperture_size;
	}

	public void setAperture_size(int aperture_size) {
		this.aperture_size = aperture_size;
	}
	


}
