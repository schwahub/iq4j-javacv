package com.iq4j.javacv.controls;

import com.iq4j.javacv.imgproc.GaussianBlurSettings;
import com.iq4j.javacv.imgproc.ImgProc;
import com.iq4j.javacv.imgproc.Values;

/**
 * @author Sertac ANADOLLU ( anatolian )
 * 
 */
public class GaussianBlurSettingsPanel extends SettingsPanel {

	private static final long serialVersionUID = -2503150511930276299L;

	public GaussianBlurSettingsPanel() {
		super(null);
		GaussianBlurSettings gbs = ImgProc.getGaussianBlurSettings();
		add(InputGenerator.checkBox(gbs, "active", "Active"));
		add(InputGenerator.combobox(gbs, "ksizeX", "ksize.X", Values.ODD_VALUES));
		add(InputGenerator.combobox(gbs, "ksizeY", "ksize.Y", Values.ODD_VALUES));
		add(InputGenerator.slider(gbs, "sigmaX", "sigmaX", InputGenerator.doubleToIntConverter, 0, 10));
		add(InputGenerator.slider(gbs, "sigmaY", "sigmaY", InputGenerator.doubleToIntConverter, 0, 10));
		add(InputGenerator.combobox(gbs, "borderType", "borderType", 0, 1, 2, 3, 4, 5, 16));

	}

}
