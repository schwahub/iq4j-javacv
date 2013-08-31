package com.iq4j.javacv.controls;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import net.miginfocom.swing.MigLayout;

import com.iq4j.javacv.imgproc.FindContoursSettings;
import com.iq4j.javacv.imgproc.Values;

/**
 * @author Sertac ANADOLLU ( anatolian )
 *
 */
public class InputTester {

	private JFrame frame;
	/**
	 * @wbp.nonvisual location=60,369
	 */
	private final FindContoursSettings findContoursSettings = new FindContoursSettings();
	private final JButton btnTest = new JButton("test");
	private final JLabel lblValue = new JLabel("value");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InputTester window = new InputTester();
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
	public InputTester() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 599, 407);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[grow]", "[][][][][][][][]"));
//		InputDecorator checkBox = InputGenerator.checkBox(findContoursSettings, "active", "Active");
//		InputDecorator input = InputGenerator.combobox(findContoursSettings, "mode", "Mode", Values.ODD_VALUES);
//		InputDecorator spinner = InputGenerator.spinner(findContoursSettings, "method", "Method", 0,1,2,3,4,5);
//		InputDecorator slider = InputGenerator.slider(findContoursSettings, "method", "Method", 0,5);
//		InputDecorator label = InputGenerator.label(findContoursSettings, "method", "Method");
//		InputDecorator label2 = InputGenerator.label(findContoursSettings, "mode", "Mode");
//		frame.getContentPane().add(checkBox , "cell 0 0");
//		frame.getContentPane().add(input, "cell 0 1");
//		frame.getContentPane().add(spinner, "cell 0 2");
//		frame.getContentPane().add(slider, "cell 0 3");
//		frame.getContentPane().add(label, "cell 0 4");
//		frame.getContentPane().add(label2, "cell 0 5");
//		
		this.btnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblValue.setText(findContoursSettings.getMode()+" " + findContoursSettings.getMethod());		
			}
		});
		
		frame.getContentPane().add(this.btnTest, "flowx,cell 0 7");
		
		frame.getContentPane().add(this.lblValue, "cell 0 7");
	}
}
