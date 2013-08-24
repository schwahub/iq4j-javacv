package org.iq4j.webcam;

import java.util.EventObject;

import com.googlecode.javacv.cpp.opencv_core.CvMemStorage;
import com.googlecode.javacv.cpp.opencv_core.IplImage;

/**
 * @author Sertac ANADOLLU ( anatolian )
 *
 */
public class GrabEvent extends EventObject {
	
	private static final long serialVersionUID = 1L;

	private CvMemStorage storage;

	public GrabEvent(IplImage source, CvMemStorage storage) {
		super(source);
		this.storage = storage;
	}

	@Override
	public IplImage getSource() {
		return (IplImage) super.getSource();
	}

	public CvMemStorage getStorage() {
		return storage;
	}

}
