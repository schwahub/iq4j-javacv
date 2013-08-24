package com.iq4j.javacv;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import com.iq4j.javacv.controls.CannySettingsPanel;
import com.iq4j.javacv.controls.FindContourSettingsPanel;
import com.iq4j.javacv.controls.GaussianBlurSettingsPanel;
import com.iq4j.javacv.controls.MedianBlurSettingsPanel;
import com.iq4j.javacv.controls.Player;
import com.iq4j.javacv.controls.ProcessedImagePlayer;
import java.awt.Color;

/**
 * @author Sertac ANADOLLU ( anatolian )
 *
 */
public class TestPanel extends JPanel {
	public TestPanel() {
		initialize();
	}
	private void initialize() {
		setPreferredSize(new Dimension(800, 600));
		setMinimumSize(new Dimension(800, 600));
		setLayout(null);
		this.player.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		this.player.setMinimumSize(new Dimension(320, 240));
		this.player.setPreferredSize(new Dimension(320, 240));
		this.player.setBounds(470, 33, 320, 240);
		
		add(this.player);
		this.tabbedPane.setBounds(10, 11, 450, 578);
		
		add(this.tabbedPane);
		
		this.tabbedPane.addTab("Gaussian Blur", null, this.gaussianBlurSettingsPanel, null);
		
		this.tabbedPane.addTab("Median Blur", null, this.medianBlurSettingsPanel, null);
		
		this.tabbedPane.addTab("Canny Settings", null, this.cannySettingsPanel, null);
		
		this.tabbedPane.addTab("Find Contours", null, this.findContourSettingsPanel, null);
		this.processedImagePlayer.setBounds(470, 284, 320, 240);
		
		add(this.processedImagePlayer);
		
		//((JavaCvDevice)SelectedWebcam.get().getDevice()).addListener(canvasPanel);
		
	}

	private static final long serialVersionUID = -1833266358327183802L;
	private final Player player = new Player();
	private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	private final CannySettingsPanel cannySettingsPanel = new CannySettingsPanel();
	private final GaussianBlurSettingsPanel gaussianBlurSettingsPanel = new GaussianBlurSettingsPanel();
	private final MedianBlurSettingsPanel medianBlurSettingsPanel = new MedianBlurSettingsPanel();
	private final FindContourSettingsPanel findContourSettingsPanel = new FindContourSettingsPanel();
	private final ProcessedImagePlayer processedImagePlayer = new ProcessedImagePlayer();
}
