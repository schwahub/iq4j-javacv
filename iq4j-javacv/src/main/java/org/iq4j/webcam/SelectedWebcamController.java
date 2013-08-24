package org.iq4j.webcam;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import com.github.sarxos.webcam.WebcamPicker;

/**
 * @author Sertac ANADOLLU ( anatolian )
 *
 */
public class SelectedWebcamController extends JPanel {

	private static final long serialVersionUID = 6114063385415182395L;
	private final JLabel label = new JLabel("Camera :");
	private final WebcamPicker webcamPicker = new WebcamPicker();
	private final JButton btnStart = new JButton(SelectedWebcam.START);
	private final JButton btnStop = new JButton(SelectedWebcam.STOP);

	/**
	 * Create the panel.
	 */
	public SelectedWebcamController() {
		
		initialize();
	}
	
	private void initialize() {
		
		setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		add(this.label);
		
		if(!WebcamUtils.isWebcamFound()) {
			this.label.setText("No Webcam Found. Please try again after a new webcam plugged.");
			return;
		}
		
		this.webcamPicker.setPreferredSize(new Dimension(200, 20));
		this.webcamPicker.setMinimumSize(new Dimension(200, 20));
		this.webcamPicker.addItemListener(SelectedWebcam.WEBCAM_SELECT_LISTENER);
		add(this.webcamPicker);
		
		this.btnStart.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.btnStart.setText("");
		this.btnStart.setToolTipText("Start");
		
		add(this.btnStart);
		this.btnStop.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.btnStop.setText("");
		this.btnStop.setToolTipText("Stop");
		
		add(this.btnStop);
	}
	
}
