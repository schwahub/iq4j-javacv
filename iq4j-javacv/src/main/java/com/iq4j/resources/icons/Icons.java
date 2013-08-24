package com.iq4j.resources.icons;

import javax.swing.ImageIcon;

public class Icons {

	private static String[] extensions = { ".jpg", ".gif", ".png"};
	private static final String ICONS_CLASSPATH = "/com/iq4j/resources/icons/"; 
	
	private static boolean extensionExist(String iconName) {
		for (String extension : extensions) {
			if(iconName.endsWith(extension)) {
				return true;
			}
		}
		return false;
	}
	
	public static ImageIcon resource(String name) {
		return new ImageIcon( Icons.class.getResource(name));
	}
	
	public static ImageIcon get(String iconName) {
		if(!extensionExist(iconName)) {
			iconName = iconName + ".png";
		} 
		return resource( ICONS_CLASSPATH + iconName);
	}
	
	public static final ImageIcon SAVE = get("save");
	public static final ImageIcon EXIT = get("exit");
	public static final ImageIcon BROWSER = get("browser");
	public static final ImageIcon HELP = get("help");
	public static final ImageIcon APPLICATION = get("application");
	public static final ImageIcon CAMERA = get("camera");
	public static final ImageIcon STOP = get("stop");
	public static final ImageIcon STOP_CAMERA = get("stop_camera");
	public static final ImageIcon PRINT = get("print");
	public static final ImageIcon NEW = get("new");
	public static final ImageIcon FILTER = get("filter");
	public static final ImageIcon GRAPH_CIRCLE = get("graph_circle");
	public static final ImageIcon GRAPH_LINE = get("graph_line");
	public static final ImageIcon RUN = get("run");
	public static final ImageIcon PLAYER_START = get("player_play");
	public static final ImageIcon PLAYER_STOP = get("player_stop");
	
	
}
