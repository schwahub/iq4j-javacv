package com.iq4j.javacv.controls;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JComponent;

/**
 * @author Sertac ANADOLLU ( anatolian )
 *
 */
public class CheckBox extends ValueInput<Boolean> {

	private static final long serialVersionUID = 3518044226568034800L;
	private JCheckBox checkbox;
	/**
	 * @param name
	 * @param editable
	 * @param availableValues
	 */
	public CheckBox(String name, Boolean value) {
		super(name, true);
		setValue(value);
	}

	@Override
	public JComponent setupInput() {
		checkbox = new JCheckBox();
		checkbox.addItemListener( new ItemListener() {	
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED || e.getStateChange() == ItemEvent.DESELECTED) {
					onValueChanged();
				}
			}
		});
		
		return checkbox;
	}

	@Override
	public Boolean getValue() {
		return checkbox.isSelected();
	}

	@Override
	public void setValue(Boolean value) {
		value = value != null ? value : Boolean.FALSE;
		checkbox.setSelected(value);
	}

}
