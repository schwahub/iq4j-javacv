package com.iq4j.javacv.controls;

import java.awt.Dimension;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.Converter;

/**
 * @author Sertac ANADOLLU ( anatolian )
 *
 */
public class InputGenerator {

	public static final Converter<Double, Integer> doubleToIntConverter = new Converter<Double, Integer>() {
		
		@Override
		public Double convertReverse(Integer value) {
			return value != null ? Double.valueOf(value) : null;
		}
		
		@Override
		public Integer convertForward(Double value) {
			return value != null ? value.intValue() : null;
		}
	};
	
	public static <T,E> InputDecorator combobox(T bean, String propertyPath, String label, E... availableValues) {		
		JComboBox<E> comboBox = new JComboBox<E>(availableValues);
		return decorateAndBind(bean, propertyPath, comboBox, "selectedItem", label, UpdateStrategy.READ_WRITE, null);
	}

	public static <T,E> InputDecorator spinner(T bean, String propertyPath, String label, @SuppressWarnings("rawtypes") Converter converter, E... availableValues) {		
		JSpinner spinner = new JSpinner(new SpinnerListModel(availableValues));
		return decorateAndBind(bean, propertyPath, spinner, "value", label, UpdateStrategy.READ_WRITE, converter);
	}

	public static <T> InputDecorator slider(T bean, String propertyPath, String label, @SuppressWarnings("rawtypes") Converter converter, int min, int max) {		
		JSlider slider = new JSlider(min, max);
		slider.setPreferredSize(new Dimension(150, 25));
		return decorateAndBind(bean, propertyPath, slider, "value", label, UpdateStrategy.READ_WRITE, converter);
	}

	public static <T> InputDecorator checkBox(T bean, String propertyPath, String label) {		
		JCheckBox checkBox = new JCheckBox();
		return decorateAndBind(bean, propertyPath, checkBox, "selected", label, UpdateStrategy.READ_WRITE, null);
	}

	public static <T> InputDecorator label(T bean, String propertyPath, String label) {		
		JLabel labelComponent = new JLabel();
		return decorateAndBind(bean, propertyPath, labelComponent, "text", label, UpdateStrategy.READ, null);
	}

	@SuppressWarnings("unchecked")
	public static <T,C extends JComponent> InputDecorator decorateAndBind(T bean, String propertyPath, C input, String inputProperty, String label, UpdateStrategy strategy, @SuppressWarnings("rawtypes") Converter converter) {
		JLabel valueLabel = null;
		if(bean != null) {			
			BeanProperty<T, Object> bp = BeanProperty.create(propertyPath);
			BeanProperty<C, Object> wbp = BeanProperty.create(inputProperty);
			AutoBinding<T, Object, C, Object> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, bean, bp,  input, wbp);
			if(converter != null) {				
				autoBinding.setConverter(converter);
			}
			autoBinding.bind();
			if(input instanceof JSlider) {
				valueLabel = new JLabel();
				BeanProperty<JLabel, String> lp = BeanProperty.create("text");
				AutoBinding<T, Object, JLabel, String> labelBinding = Bindings.createAutoBinding(UpdateStrategy.READ, bean, bp, valueLabel, lp);
				labelBinding.bind();
			}
		}
		
		
		InputDecorator decorator = new InputDecorator(label,  input, valueLabel);
		return decorator;
	}
	
}
