package com.iq4j.javacv.controls;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Sertac ANADOLLU ( anatolian )
 *
 */
public class InputDecorator extends JPanel {
	
	
	private static final long serialVersionUID = -7320364999159095267L;

	private final JLabel label = new JLabel();
	private final JLabel separator = new JLabel(":");
	
	/**
	 * @wbp.parser.constructor
	 */
	public InputDecorator() {
		this("Label");
	}
	
	
	public InputDecorator(String label, JComponent... inputs){
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		this.label.setPreferredSize(new Dimension(120,25));
		add(this.label);
		setLabel(label);
		add(separator);
		for (JComponent jComponent : inputs) {
			if(jComponent != null) {				
				add(jComponent);
			}
		}
	}	

	public String getLabel() {
		return label.getText();
	}

	public void setLabel(String label) {
		this.label.setText(label);
	}
	
}
