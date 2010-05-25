/** 
 * ExceptionMessageUIFunctions.java
 * 
 * Copyright (c) 2007, JULIE Lab. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0 
 *
 * Author: tomanek
 * 
 * Current version: 2.0
 * Since version:   2.0
 *
 * Creation date: March, 2010
 * 
 **/

package de.julielab.annoenv.ui;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.swing.JOptionPane;

import de.julielab.annoenv.utils.ExceptionUtils;

/**
 * helper class to show exceptions in the GUI
 */

public class GUIMessages {

	public static void infoMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Information",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public static void warnMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Warning",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public static void exceptionMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Error",
				JOptionPane.ERROR_MESSAGE);
	}

	public static void exceptionMessage(Throwable t) {
		exceptionMessage("", t);
	}

	public static void exceptionMessage(String msg, Throwable t) {
		
		if (t == null) {
			// if no throwable object available
			msg = msg + "\n" + t.getMessage();
			JOptionPane.showMessageDialog(null, msg, "Error",
					JOptionPane.ERROR_MESSAGE);
		} else {

			String exceptionMessage = ExceptionUtils.getCausesFromException(t);
			if (msg!=null && msg.length()>0)
				msg += "\n"+ exceptionMessage;
			else
				msg = exceptionMessage;
			// add stacktrace option
			Object[] options = { "OK", "show exception stacktrace" };
			int chosenOption = JOptionPane.showOptionDialog(null, msg, "Error", JOptionPane.DEFAULT_OPTION,
					JOptionPane.ERROR_MESSAGE, null, options, options[0]);
			if (chosenOption == 1) { // show stacktrace if chosen
				StringWriter sw = new StringWriter();
				t.printStackTrace(new PrintWriter(sw));
				String m = sw.toString();
				JOptionPane.showMessageDialog(null, m, "Exception Stacktrace",
						JOptionPane.ERROR_MESSAGE);

			}

		}
	}


}
