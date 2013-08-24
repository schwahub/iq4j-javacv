package com.iq4j.javacv;

import java.awt.EventQueue;

import javax.swing.UIManager;

import org.iq4j.webcam.JavaCvDriver;

import com.github.sarxos.webcam.Webcam;

public class Application {
	
	private static MainFrame mainFrame;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Webcam.setDriver(JavaCvDriver.class);
					Webcam.setAutoOpenMode(false);
					mainFrame = new MainFrame();
					mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void exit() {
		mainFrame.dispose();
	}
	
	public static MainFrame getMainFrame() {
		return mainFrame;
	}


}
