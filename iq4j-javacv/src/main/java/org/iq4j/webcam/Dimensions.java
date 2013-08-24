package org.iq4j.webcam;

import java.awt.Dimension;

public class Dimensions {
	
	public static final Dimension D_176x144 = new Dimension(176, 144); //QQVGA
	public static final Dimension D_320x240 = new Dimension(320, 240); //QVGA
	public static final Dimension D_480x400 = new Dimension(480, 400); //HVGA
	public static final Dimension D_640x480 = new Dimension(640, 480); //VGA
	public static final Dimension D_800x600 = new Dimension(800, 600); //SVGA

	public static final Dimension[] ALL = {D_176x144, D_320x240, D_480x400, D_640x480, D_800x600};
	
	public static Dimension DEFAULT_SIZE = D_320x240;
	
}
