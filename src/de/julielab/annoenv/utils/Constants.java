package de.julielab.annoenv.utils;

public class Constants {

	public final static String OUTSIDE_LABEL = "O";
	public final static String BEGIN_LABEL = "B";
	public final static String INSIDE_LABEL = "I";
	
	
	public static final String POS_MODEL = "POS.bin.gz";

	public static final String POS_DICTIONARY = "POS.dict";
	
	
	public static final String HOME_DIR_ENV_VARIABLE = "annoenv.home";
	
	/*
	 * AL Project Status
	 */
	
	public static final String AL_PROJECT_STATUS_ERROR = "error";
	public static final String AL_PROJECT_STATUS_RUNNING = "running";
	public static final String AL_PROJECT_STATUS_IDLE = "idle";
	
	/*
	 * MMAX stuff
	 */
	public static final String MMAX_STYLE_CONTEXT_COLOR = "gray";
	// choose one out of these: black, blue, cyan, darkGray, gray, green, lightGray, magenta, orange, pink, red, white, yellow
	// all other colors will cause an error when MMAX starts up!
	
}
