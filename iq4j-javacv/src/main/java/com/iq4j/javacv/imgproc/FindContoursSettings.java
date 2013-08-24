package com.iq4j.javacv.imgproc;

public class FindContoursSettings extends Settings {

	private int mode = 3; // =0,1,2,3,4,5
	private int method = 1; // =0,1,2,3,4,5
	
	public FindContoursSettings() {
	}

	/**
	 * @param mode
	 * @param method
	 */
	public FindContoursSettings(boolean active, int mode, int method) {
		super();
		setActive(active);
		this.mode = mode;
		this.method = method;
	}
	
	/* (non-Javadoc)
	 * @see com.iq4j.javacv.imgproc.Settings#clone()
	 */
	@Override
	public FindContoursSettings clone() {
		return new FindContoursSettings(isActive(), mode, method);
	}
	
	public int getMode() {
		return mode;
	}
	public void setMode(int mode) {
		this.mode = mode;
	}
	public int getMethod() {
		return method;
	}
	public void setMethod(int method) {
		this.method = method;
	}
	
	
}
