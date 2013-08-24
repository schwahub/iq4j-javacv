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
public abstract class ValueInput<T> extends JPanel {
	
	
	private static final long serialVersionUID = -7320364999159095267L;
	private T[] availableValues ;
	private boolean editable = false;
	private final JLabel lblName = new JLabel();
	private ValueChangedListener valueChangedListener;
	
	
	public ValueInput(String name, boolean editable, T... availableValues){
		this(name, null, editable, availableValues);
	}
	
	public ValueInput(String name, T value, boolean editable,  T... availableValues){
		this(name, value, editable, null, availableValues);
	}
	
	public ValueInput(String name, T value, boolean editable, ValueChangedListener valueChangedListener,  T... availableValues){
		if(name != null) {
			lblName.setText(name);
		}
		Dimension size = new Dimension(120, 25);
		this.valueChangedListener = valueChangedListener;
		lblName.setPreferredSize(size);
		this.editable = editable;
		this.availableValues = availableValues;
		initialize();
		setValue(value);
	}
	
	public abstract JComponent setupInput();
	public abstract T getValue();
	public abstract void setValue(T value);
	
	protected void onValueChanged() {
		if(valueChangedListener != null) {
			valueChangedListener.handle(getValue());
		}
	}
	
	private void initialize() {
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(this.lblName);
		add(new JLabel(":"));
		add(setupInput());
	}

	public String getName() {
		return lblName.getText();
	}
	
	public T[] getAvailableValues() {
		return availableValues;
	}

	public boolean isEditable() {
		return editable;
	}

	public ValueChangedListener getValueChangedListener() {
		return valueChangedListener;
	}

	public void setValueChangedListener(ValueChangedListener valueChangedListener) {
		this.valueChangedListener = valueChangedListener;
	}
	
	
	
}
