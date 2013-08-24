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
		super("Gaussian Blur Settings");
	}

	@Override
	protected void setupInputs() {
		
		addInput("ksizeX", new ComboBox<Integer>("Ksize.X", false, Values.ODD_VALUES), true);
		addInput("ksizeY", new ComboBox<Integer>("Ksize.Y",  false, Values.ODD_VALUES), true);
		addInput("sigmaX", new ComboBox<Double>("sigma.X", false, Values.generateDoubles(0, 10)), true);
		addInput("sigmaY", new ComboBox<Double>("sigma.Y", false, Values.generateDoubles(0, 0)));
		addInput("borderType", new ComboBox<Integer>("borderType", false, 0,1,2,3,4,5,16), true);
	}

	/* (non-Javadoc)
	 * @see com.iq4j.javacv.controls.SettingsPanel#load()
	 */
	@Override
	protected void load() {
		GaussianBlurSettings gbs = ImgProc.getGaussianBlurSettings();
		setValue("ksizeX", gbs.getKsizeX());
		setValue("ksizeY", gbs.getKsizeY());
		setValue("sigmaX", gbs.getSigmaX());
		setValue("sigmaY", gbs.getSigmaY());
		setValue("borderType", gbs.getBorderType());
		setValue("active", gbs.isActive());
		
	}
	
	@Override
	protected void save() {
		GaussianBlurSettings gbs = new GaussianBlurSettings(getBooleanValue("active"),
															getIntegerValue("ksizeX"), 
															getIntegerValue("ksizeY"), 
															getDoubleValue("sigmaX"), 
															getDoubleValue("sigmaY"), 
															getIntegerValue("borderType"));
		ImgProc.setGaussianBlurSettings(gbs);
	}

}
