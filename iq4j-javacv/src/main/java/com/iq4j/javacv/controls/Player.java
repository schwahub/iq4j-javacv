package com.iq4j.javacv.controls;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import org.iq4j.webcam.SelectedWebcam;
import org.iq4j.webcam.WebcamUtils;
import org.iq4j.webcam.SelectedWebcam.SelectedWebcamStateChangedEvent;
import org.iq4j.webcam.SelectedWebcam.SelectedWebcamStateChangedEventListener;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;

/**
 * @author Sertac ANADOLLU ( anatolian )
 *
 */
public class Player extends JPanel implements SelectedWebcamStateChangedEventListener {
	

	private static final long serialVersionUID = 6114063385415182395L;
	private WebcamPanel webcamPanel;
	
	/**
	 * Create the panel.
	 */
	public Player() {
		SelectedWebcam.addListener(this);
		setLayout(new BorderLayout());	
		resetWebcamPanel();
	}
	
	private void resetWebcamPanel() {
		
		destroyWebcamPanel();
		createWebcamPanel();	
		validate();
	}
	
	private void createWebcamPanel() {

		if(WebcamUtils.isWebcamFound()) {			
			Webcam webcam = SelectedWebcam.get();
			webcamPanel = new WebcamPanel(webcam, webcam.isOpen());
			add(webcamPanel);
		} 
		
	}
	
	private void destroyWebcamPanel() {
		
		if(webcamPanel != null) {
			remove(webcamPanel);
			SelectedWebcam.get().removeWebcamListener(webcamPanel);
			webcamPanel = null;
		} 

	}
	
	/**
	 * This is a disposer method. Do not forget to call this method on window close.
	 */
	public void dispose() {
		destroyWebcamPanel();
		SelectedWebcam.removeListener(this);
	}
	
	// -- SelectedWebcamStateChangedEventListener
	public void beforeSelectionChanged(SelectedWebcamStateChangedEvent event) {
		destroyWebcamPanel();
		validate();
	}
	
	/* (non-Javadoc)
	 * @see org.iq4j.webcam.SelectedWebcam.SelectedWebcamStateChangedEventListener#afterSelectionChanged(org.iq4j.webcam.SelectedWebcam.SelectedWebcamStateChangedEvent)
	 */
	public void afterSelectionChanged(SelectedWebcamStateChangedEvent event) {
		createWebcamPanel();
		validate();
	}

	public void onStart() {
	}

	public void onStop() {
	}

}
