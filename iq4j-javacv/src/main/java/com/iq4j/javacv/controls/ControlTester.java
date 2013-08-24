package com.iq4j.javacv.controls;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;

/**
 * @author Sertac ANADOLLU ( anatolian )
 *
 */
public class ControlTester {

	private JFrame frame;
	private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ControlTester window = new ControlTester();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ControlTester() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(this.tabbedPane, BorderLayout.CENTER);
		CannySettingsPanel csp = new CannySettingsPanel();
		FindContourSettingsPanel fcs = new FindContourSettingsPanel();
		GaussianBlurSettingsPanel gbs = new GaussianBlurSettingsPanel();
		MedianBlurSettingsPanel mbs = new MedianBlurSettingsPanel();
		this.tabbedPane.add(csp);
		this.tabbedPane.setTitleAt(0, "Canny");
		this.tabbedPane.add(fcs);
		this.tabbedPane.setTitleAt(1, "Find Contour");
		this.tabbedPane.add(gbs);
		this.tabbedPane.setTitleAt(2, "Gaussian Blur");
		this.tabbedPane.add(mbs);
		this.tabbedPane.setTitleAt(3, "Median Blur");
		frame.pack();
	}

}
