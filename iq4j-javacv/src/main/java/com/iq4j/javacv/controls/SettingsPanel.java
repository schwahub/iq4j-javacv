package com.iq4j.javacv.controls;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

/**
 * @author Sertac ANADOLLU ( anatolian )
 * 
 */
public abstract class SettingsPanel extends JPanel implements ValueChangedListener {

	private static final long serialVersionUID = -6168088336353747836L;
	
	@SuppressWarnings("rawtypes")
	private Map<String, ValueInput> inputs = new LinkedHashMap<String, ValueInput>();

	private JPanel inputPanel;
	private final JButton btnSave = new JButton("Save");

	/**
	 * Create the panel.
	 */
	public SettingsPanel(String title) {
		
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), title, TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new BorderLayout(5, 5));
		JPanel bottomPanel = new JPanel();
		((FlowLayout)bottomPanel.getLayout()).setAlignment(FlowLayout.LEFT);
		
		this.btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		
		bottomPanel.add(this.btnSave);
		
		add(bottomPanel, BorderLayout.SOUTH);
		addInput("active", new CheckBox("Active", true), true);
		setupInputs();
		setupInputPanel();
		load();
		
	}

	/**
	 * Create needed valueInputs for Settings
	 */
	protected abstract void setupInputs();
	
	/**
	 * Loads settings values and updates inputs;
	 */
	protected abstract void load();
	
	/**
	 * Save settings values
	 */
	protected abstract void save();
	
	/* (non-Javadoc)
	 * @see com.iq4j.javacv.controls.ValueChangedListener#handle(java.lang.Object)
	 */
	public void handle(Object value) {
	
		save();
		
	}
	

	
	public void addInput(String valueName, ValueInput<?> input) {
		addInput(valueName, input, false);
	}
	public void addInput(String valueName, ValueInput<?> input, boolean listenValueChanges) {
		if(listenValueChanges) {
			input.setValueChangedListener(this);
		}
		inputs.put(valueName, input);
	}

	public void removeInput(ValueInput<?> input) {
		inputs.remove(input);
	}
	
	public Object getValue(String valueName) {
		ValueInput<?> vi = inputs.get(valueName);
		return vi != null ? vi.getValue() : null;
	}

	/**
	 * @param valueInput
	 * @return Boolean result if valueInput found. If result is null then return true; 
	 */
	public Boolean getBooleanValue(String valueInput) {
		Boolean value = getValue(Boolean.class, valueInput);
		return value != null ? value : Boolean.TRUE;
	}
	
	/**
	 * @param valueInput
	 * @return Integer result if valueInput found. If result is null then return 0; 
	 */
	public Integer getIntegerValue(String valueInput) {
		Integer value = getValue(Integer.class, valueInput);
		return value != null ? value : Integer.valueOf(0);
		
	}
	
	/**
	 * @param valueInput
	 * @return Double result if valueInput found. If result is null then return 0; 
	 */
	public Double getDoubleValue(String valueName) {
		Double value = getValue(Double.class, valueName);
		return value != null ? value : Double.valueOf(0);
	}
	
	/**
	 * @param valueInput
	 * @return String result if valueInput found. Otherwise return null. 
	 */
	public String getStringValue(String valueName) {
		return getValue(String.class, valueName);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getValue(Class<T> clazz, String valueName) {
		Object value = getValue(valueName);
		if(clazz.isInstance(value)) {
			return (T)value;
		}
		return null;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setValue(String valueName, Object value) {
		ValueInput vi = inputs.get(valueName);
		if (vi != null) {
			vi.setValue(value);
		}
	}

	protected void setupInputPanel() {
		if(inputPanel != null) {			
			remove(inputPanel);
		}
		int i = 0;
		inputPanel = new JPanel(new MigLayout("", "[grow]"));
		for (ValueInput<?> vi : inputs.values()) {
			inputPanel.add(vi, "cell 0 " + i);
			i++;
		}
		add(inputPanel, BorderLayout.CENTER);
		
	}

}
