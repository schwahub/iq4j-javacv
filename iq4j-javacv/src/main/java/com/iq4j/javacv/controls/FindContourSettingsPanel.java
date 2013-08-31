package com.iq4j.javacv.controls;

import com.iq4j.javacv.imgproc.FindContoursSettings;
import com.iq4j.javacv.imgproc.ImgProc;


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
		super(null);
		FindContoursSettings fcs = ImgProc.getDefaultFindContoursSettings();
		add(InputGenerator.checkBox(fcs, "active", "Active"));
		add(InputGenerator.slider(fcs, "mode", "Mode", null, 0, 5));
		add(InputGenerator.slider(fcs, "method", "Method", null, 0, 5));
	}

}
