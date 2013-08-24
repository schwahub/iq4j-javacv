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
		this("Median Blur");
	}
	
	public MedianBlurSettingsPanel(String title) {
		super(title);
	}

	@Override
	protected void setupInputs() {
		addInput("ksize", new ComboBox<Integer>("ksize", false, 3,5,7,9,11,13,15), true);
	}

	/* (non-Javadoc)
	 * @see com.iq4j.javacv.controls.SettingsPanel#load()
	 */
	@Override
	protected void load() {
		
		MedianBlurSettings mbs = ImgProc.getMedianBlurSettings();
		setValue("ksize", mbs.getkSize());
		setValue("active", mbs.isActive());		
	}
	
	@Override
	protected void save() {
		MedianBlurSettings mbs = new MedianBlurSettings(getBooleanValue("active"), getIntegerValue("ksize"));
		ImgProc.setMedianBlurSettings(mbs);
	}

}
