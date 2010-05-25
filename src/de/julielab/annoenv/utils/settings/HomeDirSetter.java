package de.julielab.annoenv.utils.settings;

import java.io.File;


import de.julielab.annoenv.utils.Constants;

public class HomeDirSetter {

	public static File getHomeDirectory() {
		String homeDir = System.getProperty(Constants.HOME_DIR_ENV_VARIABLE);
		if (homeDir==null)
			return null;
		else return new File(homeDir);
	}

	public static String getSettingsFullPath(String settingsRelativePath) {
		File homeDir = getHomeDirectory();
		if (homeDir==null)
			return settingsRelativePath;
		else
			return homeDir.getAbsolutePath() + File.separator +  settingsRelativePath;
	}
	
	/**
	 * main method for debugging purposes
	 */
	public static void main (String[] args) {
		getHomeDirectory();
	}
}
