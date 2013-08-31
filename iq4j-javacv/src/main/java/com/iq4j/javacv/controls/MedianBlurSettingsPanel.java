package com.iq4j.javacv.controls;

import com.iq4j.javacv.imgproc.ImgProc;
import com.iq4j.javacv.imgproc.MedianBlurSettings;


/**
 * @author Sertac ANADOLLU ( anatolian )
 *
 */
public class MedianBlurSettingsPanel extends SettingsPanel {

	
	private static final long serialVersionUID = 8761892205724964052L;

	public MedianBlurSettingsPanel() {
		super("Median Blur");
		MedianBlurSettings mbs = ImgProc.getMedianBlurSettings();
		add(InputGenerator.checkBox(mbs, "active", "Active"));
		add(InputGenerator.combobox(mbs, "kSize", "kSize", 3,5,7,9,11,13,15,17,19));
	}
	
}
