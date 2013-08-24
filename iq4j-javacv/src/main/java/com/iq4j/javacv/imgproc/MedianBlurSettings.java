package com.iq4j.javacv.imgproc;

public class MedianBlurSettings extends Settings {

	private int kSize = 3; // more than 1 and odd  = 3,5,7,9,11,13,15,17,19 

	public MedianBlurSettings() {
	}
	
	/**
	 * @param kSize
	 */
	public MedianBlurSettings(boolean active, int kSize) {
		super();
		this.kSize = kSize;
		setActive(active);
	}

	/* (non-Javadoc)
	 * @see com.iq4j.javacv.imgproc.Settings#clone()
	 */
	@Override
	public Settings clone() {
		return new MedianBlurSettings(isActive(), kSize);
	}
	
	public int getkSize() {
		return kSize;
	}

	public void setkSize(int kSize) {
		this.kSize = kSize;
	}
	
	
}
