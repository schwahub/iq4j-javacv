package com.iq4j.javacv.controls;

import com.iq4j.javacv.imgproc.ImgProc;
import com.iq4j.javacv.imgproc.Values;

/**
 * @author Sertac ANADOLLU ( anatolian )
 *
 */
public class CannySettingsPanel extends SettingsPanel {

	private static final long serialVersionUID = -8170447276251874573L;
	
	public CannySettingsPanel() {
		super("Canny Settings");
	}

	@Override
	protected void setupInputs() {
		
		addInput("thresh1", new ComboBox<Double>("thresh1",  true, Values.THRESHOLD_VALUES), true );
		addInput("thresh2", new ComboBox<Double>("thresh2",  true, Values.THRESHOLD_VALUES), true );
		addInput("apertureSize", new ComboBox<Integer>("apertureSize", false, 3));
		
	}

	@Override
	protected void load() {
		setValue("thresh1", ImgProc.cs_thresh1);
		setValue("thresh2", ImgProc.cs_thresh2);
		setValue("apertureSize", ImgProc.cs_aperture_size);
		setValue("active", ImgProc.cs_active);
		
	}
	
	@Override
	protected void save() {
		
		ImgProc.cs_active = getBooleanValue("active");
		ImgProc.cs_thresh1 = getDoubleValue("thresh1");
		ImgProc.cs_thresh2 = getDoubleValue("thresh2");
		ImgProc.cs_aperture_size = getIntegerValue("apertureSize");
	}

}
