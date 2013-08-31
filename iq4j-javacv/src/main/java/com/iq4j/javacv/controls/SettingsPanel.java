package com.iq4j.javacv.controls;

import java.awt.Component;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

/**
 * @author Sertac ANADOLLU ( anatolian )
 * 
 */
public abstract class SettingsPanel extends JPanel {

	private static final long serialVersionUID = -6168088336353747836L;


	/**
	 * Create the panel.
	 */
	public SettingsPanel(String title, JComponent... components) {
		
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), title, TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new MigLayout("", "[grow]"));
		for (JComponent jComponent : components) {
			add(jComponent);
		}
	}
	
	/* (non-Javadoc)
	 * @see java.awt.Container#add(java.awt.Component)
	 */
	@Override
	public Component add(Component comp) {
		int i = getComponentCount();
		super.add(comp, "cell 0 " + i );
		return getComponent(i);
	}

}
