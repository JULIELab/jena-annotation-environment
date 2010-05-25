/** 
 * HelpFrame.java
 * 
 * Copyright (c) 2007, JULIE Lab. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0 
 *
 * Author: Klaue
 * 
 * Current version: 2.0
 * Since version:   0.9
 *
 * Creation date: June, 2007 
 * 

 **/

package de.julielab.annoenv.ui.annomaster;

import java.awt.BorderLayout;

import java.io.File;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JDesktopPane;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.text.html.HTMLEditorKit;

import de.julielab.annoenv.ui.GUIMessages;
import de.julielab.annoenv.utils.AnnoEnvLogger;
import de.julielab.annoenv.utils.settings.AnnoMasterSettings;

/**
 * Displays an html file: either documentation or information about AnnoMaster.
 * 
 */
public class HelpFrame extends javax.swing.JInternalFrame {

	private static Logger logger = AnnoEnvLogger
			.getLogger("de.julielab.annoenv.ui.annomaster.HelpFrame");

	private JEditorPane jEditorPane1;

	private JScrollPane jScrollPane1;

	private String type;

	/**
	 * main method for debugging
	 */
	public static void main(String[] args) {

		AnnoMasterSettings basicSettings = null;
		basicSettings = new AnnoMasterSettings();
		JFrame frame = new JFrame();
		HelpFrame inst = new HelpFrame(basicSettings.ANNOMASTER_ABOUT_HTML);
		JDesktopPane jdp = new JDesktopPane();
		jdp.add(inst);
		jdp.setPreferredSize(inst.getPreferredSize());
		frame.setContentPane(jdp);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public HelpFrame(String type) {
		super();
		this.type = type;
		// type -> the html file to show
		initGUI();
	}

	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(590, 392));
			this.setBounds(25, 25, 590, 392);
			setVisible(true);
			this.setClosable(true);
			this.setResizable(true);
			{
				jScrollPane1 = new JScrollPane();
				getContentPane().add(jScrollPane1, BorderLayout.CENTER);
				{
					jEditorPane1 = new JEditorPane();
					jScrollPane1.setViewportView(jEditorPane1);
					jEditorPane1.setEditorKit(new HTMLEditorKit());
					File f = new File(type);
					URL url = f.toURL();
					// do not want to use jEditorPane1.read(,) seems bugged
					jEditorPane1.setPage(url);
					jEditorPane1.setEditable(false);
				}
			}
		} catch (Exception e) {
			GUIMessages.exceptionMessage(e);
			logger.log(Level.SEVERE, "", e);
		}
	}

}
