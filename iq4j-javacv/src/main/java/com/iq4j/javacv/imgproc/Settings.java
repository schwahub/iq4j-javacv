package com.iq4j.javacv.imgproc;

public abstract class Settings {
	
	private boolean active = true;

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public abstract Settings clone();
	
	@SuppressWarnings("unchecked")
	protected  <T> T defaultedValue(T value, Object defaultValue) {
		if(value != null) {
			return value;
		} else {
			return (T)defaultValue;
		}
	}
	
}
