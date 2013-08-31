package com.iq4j.javacv.controls;

import com.iq4j.javacv.imgproc.CannySettings;
import com.iq4j.javacv.imgproc.ImgProc;


/**
 * @author Sertac ANADOLLU ( anatolian )
 *
 */
public class CannySettingsPanel extends SettingsPanel {

	private static final long serialVersionUID = -8170447276251874573L;
	
	public CannySettingsPanel() {
		super(null);
		CannySettings cs = ImgProc.getCannySettings();
		add(InputGenerator.checkBox(cs, "active", "Active"));
		add(InputGenerator.slider(cs, "thresh1", "thresh1", InputGenerator.doubleToIntConverter, 0, 255));
		add(InputGenerator.slider(cs, "thresh2", "thresh2", InputGenerator.doubleToIntConverter, 0, 255));
		add(InputGenerator.combobox(cs, "aperture_size", "aperture size", 3));
		
	}


}
