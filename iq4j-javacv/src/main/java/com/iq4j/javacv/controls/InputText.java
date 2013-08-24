package com.iq4j.javacv.controls;

import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * @author Sertac ANADOLLU ( anatolian )
 *
 */
public class InputText extends ValueInput<String> {

	private static final long serialVersionUID = 2142016754076351352L;
	private JTextField textField;
	
	/**
	 * @param name
	 * @param editable
	 * @param availableValues
	 */
	public InputText(String name, String value) {
		super(name, true);
		setValue(value);
	}

	@Override
	public JComponent setupInput() {
		textField = new JTextField(40);
		textField.getDocument().addDocumentListener(new DocumentListener() {
			
			public void removeUpdate(DocumentEvent e) {
				onValueChanged();
			}
			
			public void insertUpdate(DocumentEvent e) {
				onValueChanged();
			}
			
			public void changedUpdate(DocumentEvent e) {
				
				onValueChanged();
			}
		});
		return textField;
	}

	@Override
	public String getValue() {
		return textField.getText();
	}

	public void setValue(String value) {
		textField.setText(value);
	}

}
