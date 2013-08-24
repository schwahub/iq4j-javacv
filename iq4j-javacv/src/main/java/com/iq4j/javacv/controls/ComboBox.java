package com.iq4j.javacv.controls;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JComponent;


/**
 * @author Sertac ANADOLLU ( anatolian )
 *
 */
public class ComboBox<T> extends ValueInput<T> {

	private static final long serialVersionUID = 6085645905643679443L;
	private JComboBox<T> comboBox;
	
	/**
	 * @param name
	 * @param editable
	 * @param availableValues
	 */
	public ComboBox(String name, boolean editable, T... availableValues) {
		super(name, editable, availableValues);
	}
	
	/**
	 * @param name
	 * @param editable
	 * @param value
	 * @param availableValues
	 */
	public ComboBox(String name,  T value, boolean editable, T... availableValues) {
		super(name, value, editable, availableValues);
	}
	

	@Override
	public JComponent setupInput() {
		comboBox = new JComboBox<T>(getAvailableValues());
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					onValueChanged();
				}
			}
		});
		
		comboBox.setEditable(isEditable());
		return comboBox;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getValue() {
		return (T)comboBox.getSelectedItem();
	}

	@Override
	public void setValue(T value) {
		comboBox.setSelectedItem(value);
	}

}
