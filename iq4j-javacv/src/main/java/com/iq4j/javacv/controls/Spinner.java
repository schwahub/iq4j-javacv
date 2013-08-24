package com.iq4j.javacv.controls;

import javax.swing.JComponent;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * @author Sertac ANADOLLU ( anatolian )
 *
 */
public class Spinner<T> extends ValueInput<T> {

	private static final long serialVersionUID = -7156490785602912964L;
	private JSpinner spinner;
	/**
	 * @param name
	 * @param editable
	 * @param availableValues
	 */
	public Spinner(String name,  boolean editable, T... availableValues) {
		super(name, editable, availableValues);		
	}
	
	/**
	 * @param name
	 * @param value
	 * @param editable
	 * @param availableValues
	 */
	public Spinner(String name, T value, boolean editable, T... availableValues) {
		super(name, value, editable, availableValues);
	}




	@Override
	public JComponent setupInput() {
		spinner = new JSpinner(new SpinnerListModel(getAvailableValues()));
		spinner.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent e) {
				onValueChanged();
			}
		});;
 		return spinner;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T getValue() {
		try {			
			return (T)spinner.getValue();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void setValue(T value) {
		spinner.setValue(value);
	}


}
