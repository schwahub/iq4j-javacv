package org.iq4j.webcam;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComboBox;

import com.github.sarxos.webcam.Webcam;
import com.iq4j.resources.icons.Icons;

/**
 * @author Sertac ANADOLLU ( anatolian )
 *
 */
public class SelectedWebcam {

	public static  final Action START = new AbstractAction("Start", Icons.PLAYER_START) {

		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent e) {	
			SelectedWebcam.start();
		}
	};

	public static final Action STOP = new AbstractAction("Stop", Icons.PLAYER_STOP) {

		private static final long serialVersionUID = 1L;
		public void actionPerformed(ActionEvent e) {
			SelectedWebcam.stop();
		}
	};
	
	public static final ItemListener WEBCAM_SELECT_LISTENER = new ItemListener() {
		@SuppressWarnings("rawtypes")
		public void itemStateChanged(ItemEvent e) {
			if(e.getStateChange() == ItemEvent.SELECTED) {
				JComboBox source = (JComboBox)e.getSource();
				SelectedWebcam.set(source.getSelectedIndex());
			}
		}
	};
	
	public static final class SelectedWebcamStateChangedEvent implements Serializable {

		private static final long serialVersionUID = -6081532316072115650L;

		private Webcam source;
		
		public SelectedWebcamStateChangedEvent(Webcam newWebcam) {
			this.source = newWebcam;
		}
		
		public Webcam getSource() {
			return source;
		}
	}
	
	public static interface SelectedWebcamStateChangedEventListener{
		public void beforeSelectionChanged(SelectedWebcamStateChangedEvent event);
		public void afterSelectionChanged(SelectedWebcamStateChangedEvent event);
		public void onStart();
		public void onStop();
	}
	
	private static List<SelectedWebcamStateChangedEventListener> listeners = Collections.synchronizedList(new ArrayList<SelectedWebcamStateChangedEventListener>());
	private static volatile Webcam instance = Webcam.getDefault();
	
	public synchronized static void addListener(SelectedWebcamStateChangedEventListener l) {
		listeners.add(l);
	}
	
	public synchronized static void removeListener(SelectedWebcamStateChangedEventListener l) {
		listeners.remove(l);
	}
	
	public synchronized static Webcam get() {
		return instance;
	}
	
	public synchronized static JavaCvDevice getDevice() {
		if(isSet()) {			
			try {
				return (JavaCvDevice) get().getDevice();
			} catch (Exception e) {}
		}
		return null;
	}
	
	public synchronized static void set(Webcam selection) {
		
		boolean equals = isSet() ? instance.equals(selection) : selection == null;
		
		if(!equals) {
			if(isSet()) {
				stop();
			}
			fireBeforeWebcamChangedEvent();
			instance = selection != null ? selection : Webcam.getDefault();
			fireAfterWebcamChangedEvent();
		}
	}
	
	public synchronized static void set(int cameraIndex) {
		set(WebcamUtils.get(cameraIndex));
	}
	
	public synchronized static boolean isSet() {
		return instance != null;
	}
	
	public synchronized static void start() {
		if(isSet()) {
			instance.open();
			fireStartedEvent();
		}
	}
	
	public synchronized static void stop() {
		if(isSet()) {
			instance.close();
			fireStoppedEvent();
		}
	}
	
	private static void fireBeforeWebcamChangedEvent() {
		SelectedWebcamStateChangedEvent e = new SelectedWebcamStateChangedEvent(instance);
		for (SelectedWebcamStateChangedEventListener l : listeners) {
			l.beforeSelectionChanged(e);
		}
	}
	
	private static void fireAfterWebcamChangedEvent() {
		SelectedWebcamStateChangedEvent e = new SelectedWebcamStateChangedEvent(instance);
		for (SelectedWebcamStateChangedEventListener l : listeners) {
			l.afterSelectionChanged(e);
		}
	}
	
	private static void fireStartedEvent() {
		for (SelectedWebcamStateChangedEventListener l : listeners) {
			l.onStart();
		}
	}
	
	private static void fireStoppedEvent() {
		for (SelectedWebcamStateChangedEventListener l : listeners) {
			l.onStop();
		}

	}
	
}
