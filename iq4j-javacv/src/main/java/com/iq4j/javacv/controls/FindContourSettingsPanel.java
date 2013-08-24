package com.iq4j.javacv.controls;

import com.iq4j.javacv.imgproc.FindContoursSettings;
import com.iq4j.javacv.imgproc.ImgProc;
import com.iq4j.javacv.imgproc.Values;

/**
 * @author Sertac ANADOLLU ( anatolian )
 *
 */
public class FindContourSettingsPanel extends SettingsPanel {

	private static final long serialVersionUID = -4876660184590037834L;

	/**
	 * @param title
	 */
	public FindContourSettingsPanel() {
		super("Find Contour Settings");
	}

	@Override
	protected void setupInputs() {
		
		Integer[] values = Values.generateIntegers(0, 5);
		addInput("method", new ComboBox<Integer>("method",  false, values), true);
		addInput("mode", new ComboBox<Integer>("mode",  false, values), true);
	}
	
	/* (non-Javadoc)
	 * @see com.iq4j.javacv.controls.SettingsPanel#load()
	 */
	@Override
	protected void load() {
		FindContoursSettings fcs = ImgProc.getDefaultFindContoursSettings();
		setValue("method", fcs.getMethod());
		setValue("mode", fcs.getMode());
		setValue("active" , fcs.isActive());
	}

	@Override
	protected void save() {
		FindContoursSettings fcs = new FindContoursSettings(getBooleanValue("active"), getIntegerValue("method"), getIntegerValue("mode"));
		ImgProc.setFindContourSettings(fcs);
	}

}
