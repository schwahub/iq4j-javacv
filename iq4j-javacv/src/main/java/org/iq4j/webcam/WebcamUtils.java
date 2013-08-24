package org.iq4j.webcam;

import com.github.sarxos.webcam.Webcam;

public class WebcamUtils {
		
	public static Webcam get(int cameraIndex) {
		if(isWebcamExist(cameraIndex)) {
			return Webcam.getWebcams().get(cameraIndex);
		} else  {
			return Webcam.getDefault();
		}
	}
	
	public static int getWebcamCount() {
		return Webcam.getWebcams().size();
	}
	
	public static boolean isWebcamFound() {
		return getWebcamCount() > 0;
	}
	
	public static boolean isWebcamExist(int cameraIndex) {
		return cameraIndex > -1 && cameraIndex < getWebcamCount();
	}
	
}
