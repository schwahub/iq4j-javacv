package com.iq4j.javacv.controls;

import org.iq4j.webcam.CanvasPanel;
import org.iq4j.webcam.GrabEvent;
import org.iq4j.webcam.GrabEventListener;
import org.iq4j.webcam.SelectedWebcam;
import org.iq4j.webcam.SelectedWebcam.SelectedWebcamStateChangedEvent;
import org.iq4j.webcam.SelectedWebcam.SelectedWebcamStateChangedEventListener;

import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.iq4j.javacv.imgproc.ImageProcessor;
import com.iq4j.javacv.imgproc.TestProcessor;

/**
 * @author Sertac ANADOLLU ( anatolian )
 *
 */
public class ProcessedImagePlayer extends CanvasPanel implements GrabEventListener, SelectedWebcamStateChangedEventListener{

	private static final long serialVersionUID = 1L;
	
	private ImageProcessor processor = new TestProcessor();
	
	public ProcessedImagePlayer() {
		SelectedWebcam.addListener(this);
		subscribe();
	}
	
	public void dispose(){
		SelectedWebcam.removeListener(this);
		unsubscribe();
	}
	
	public ImageProcessor getProcessor() {
		return processor;
	}

	public void setProcessor(ImageProcessor processor) {
		this.processor = processor;
	}

	protected void subscribe() {
		if(SelectedWebcam.getDevice() != null) {
			SelectedWebcam.getDevice().addListener(this);
		}
	}
	
	protected void unsubscribe() {
		if(SelectedWebcam.getDevice() != null) {
			SelectedWebcam.getDevice().removeListener(this);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.iq4j.webcam.SelectedWebcam.SelectedWebcamStateChangedEventListener#onStart()
	 */
	public void onStart() {
	}

	/* (non-Javadoc)
	 * @see org.iq4j.webcam.SelectedWebcam.SelectedWebcamStateChangedEventListener#onStop()
	 */
	public void onStop() {
	}

	/* (non-Javadoc)
	 * @see org.iq4j.webcam.GrabEventListener#handle(org.iq4j.webcam.GrabEvent)
	 */
	public void handle(GrabEvent event) {
		
			IplImage processedImage = processor != null 
							? processor.process(event.getSource(), event.getStorage())
							: event.getSource();
		    showImage(processedImage);
	}

	/* (non-Javadoc)
	 * @see org.iq4j.webcam.SelectedWebcam.SelectedWebcamStateChangedEventListener#beforeSelectionChanged(org.iq4j.webcam.SelectedWebcam.SelectedWebcamStateChangedEvent)
	 */
	public void beforeSelectionChanged(SelectedWebcamStateChangedEvent event) {
		unsubscribe();
	}
	
	/* (non-Javadoc)
	 * @see org.iq4j.webcam.SelectedWebcam.SelectedWebcamStateChangedEventListener#afterSelectionChanged(org.iq4j.webcam.SelectedWebcam.SelectedWebcamStateChangedEvent)
	 */
	public void afterSelectionChanged(SelectedWebcamStateChangedEvent event) {
		subscribe();
	}

}
