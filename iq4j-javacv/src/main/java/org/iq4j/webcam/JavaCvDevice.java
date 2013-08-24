package org.iq4j.webcam;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.github.sarxos.webcam.WebcamDevice;
import com.github.sarxos.webcam.WebcamException;
import com.googlecode.javacv.FrameGrabber;
import com.googlecode.javacv.FrameGrabber.Exception;
import com.googlecode.javacv.cpp.opencv_core.CvMemStorage;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.googlecode.javacv.cpp.videoInputLib.videoInput;

import static  com.googlecode.javacv.cpp.opencv_core.*;

/**
 * This is modified version of  JavaCvDevice implementation 
 * 
 * @author bfiryn
 * @author Sertac ANADOLLU ( anatolian )
 */
public class JavaCvDevice implements WebcamDevice { 
	
	private volatile List<GrabEventListener> listeners = Collections.synchronizedList(new ArrayList<GrabEventListener>());
	private volatile int address;
	private volatile String name = null;
	private FrameGrabber grabber = null;
	private Dimension resolution;
	private volatile boolean open = false;
	private volatile boolean disposed = false;
	private IplImage grabbedImage;
	
	private volatile CvMemStorage storage;
	
	public JavaCvDevice(int address) {
		this.address = address;
		this.name = videoInput.getDeviceName(address);
	}
	
	public int getAddress() {
		return address;
	}
	
	public String getName() {
		return name;
	}

	public Dimension[] getResolutions() {
		return Dimensions.ALL;
	}

	public Dimension getResolution() {
		if(resolution == null) {
			return Dimensions.DEFAULT_SIZE;
		}
		return resolution;
	}

	public void setResolution(Dimension size) {
		this.resolution = size;
	}

	public IplImage grab() {
		
		if(!open) {
			throw new WebcamException("Cannot grab image - webcam device is not open");
		} 
		
		try {
			grabbedImage = grabber.grab();
			
		} catch (Exception e) {
			if(open) {				
				throw new WebcamException("Cannot grab image...");
			}
		}
		fireGrabEvent(grabbedImage);		
		return grabbedImage;
	}
	
	public BufferedImage getImage() {
		return grab().getBufferedImage();
	}

	public void open() {

		if (open || disposed) {
			return;
		}
		
		try {
			
			storage = CvMemStorage.create();
			grabber = FrameGrabber.createDefault(address);
			grabber.setImageWidth(getResolution().width);
			grabber.setImageHeight(getResolution().height);
			grabber.start();
			open = true;
			
		} catch (com.googlecode.javacv.FrameGrabber.Exception e) {
			release();
			throw new WebcamException(e);
		}
	}

	private void release() {
		if(storage != null) {
			storage.release();
		}
		if (grabber != null) {
			try {
				grabber.release();
			} catch (com.googlecode.javacv.FrameGrabber.Exception e) {
				throw new WebcamException(e);
			}
		}
	}

	public void close() {

		if (!open) {
			return;
		}

		try {
			storage.release();
			grabber.stop();
			open = false;
		} catch (com.googlecode.javacv.FrameGrabber.Exception e) {
			throw new WebcamException(e);
		} 
	}

	public void dispose() {
		disposed = true;
	}

	public boolean isOpen() {
		return open;
	}
	
	@Override
	public String toString() {
		return getAddress() + " - " + getName();
	}
	

	public synchronized void addListener(GrabEventListener l) {
		listeners.add(l);
	}

	public synchronized void removeListener(GrabEventListener l) {
		listeners.remove(l);
	}
	
	protected void fireGrabEvent(IplImage source) {
		for(GrabEventListener l :listeners) {			
			cvClearMemStorage(storage);
			l.handle(new GrabEvent(source, storage));
		}
	}
}
