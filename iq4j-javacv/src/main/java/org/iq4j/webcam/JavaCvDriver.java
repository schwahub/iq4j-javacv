package org.iq4j.webcam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.github.sarxos.webcam.WebcamDevice;
import com.github.sarxos.webcam.WebcamDriver;
import com.googlecode.javacv.cpp.videoInputLib.videoInput;


/**
 * Webcam driver using JavaCV interface to OpenCV. OpenCV (Open Source Computer
 * Vision Library) is library of programming functions for real time computer
 * vision. JavaCV provides wrappers to commonly used libraries for OpenCV and
 * few others.
 * 
 * UNSTABLE, EXPERIMENTALL STUFF !!!
 * 
 * @author Bartosz Firyn (SarXos)
 * @author Sertac ANADOLLU ( anatolian )
 */
public class JavaCvDriver implements WebcamDriver {

	private static JavaCvDevice[] cameras = findCameras();
	
	private static JavaCvDevice[] findCameras() {
		int n = videoInput.listDevices();
		JavaCvDevice[] result = new JavaCvDevice[n];
		for (int i = 0; i < n; i++) {
			result[i] = new JavaCvDevice(i);
		}
		return result;
	}
	
	public synchronized static JavaCvDevice[] getCameras() {
		return cameras;
	}
	
	public synchronized List<WebcamDevice> getDevices() {
		return new ArrayList<WebcamDevice>(Arrays.asList(cameras));
	}
	
	public boolean isThreadSafe() {
		return false;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName();
	}
	
}
