/** 
 * DisagreementStatisticsInternalFrame.java
 * 
 * Copyright (c) 2007, JULIE Lab. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0 
 *
 * Author: Klaue
 * 
 * Current version: 2.0
 * Since version:   0.8
 *
 * Creation date: December, 2006 
 * 

 **/

package de.julielab.annoenv.ui.annomaster;

import java.awt.Dimension;
import java.io.File;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;

import javax.swing.WindowConstants;


import de.julielab.annoenv.db.sql.SQLDatabaseManager;

import de.julielab.annoenv.statistics.DisagreementStatistics;
import de.julielab.annoenv.ui.GUIMessages;
import de.julielab.annoenv.utils.AnnoEnvLogger;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */

/**
 * Statistics for anno projects. It displays annotation correlation.
 */
public class DisagreementStatisticsInternalFrame extends
		InternalAnnoMasterFrame {
	
	
	private static Logger logger = AnnoEnvLogger
	.getLogger("de.julielab.annoenv.ui.annomaster.DisagreementStatisticsInternalFrame");
	private int projectID;

	private ImageViewer view;

	private int type;

	private String title;

	/**
	 * main method for debugging
	 */
	public static void main(String[] args) {

		try {
			JFrame frame = new JFrame();
			DisagreementStatisticsInternalFrame inst = null;
			inst = new DisagreementStatisticsInternalFrame(1, 1,
					new SQLDatabaseManager(), "my title");
			JDesktopPane jdp = new JDesktopPane();
			jdp.add(inst);
			jdp.setPreferredSize(inst.getPreferredSize());
			frame.setContentPane(jdp);
			frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			frame.pack();
			frame.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	public DisagreementStatisticsInternalFrame(int projectID, int type,
			SQLDatabaseManager sdm, String title) {
		super(sdm);
		this.projectID = projectID;
		this.type = type;
		this.title = title;
		initGUI();
	}

	private void initGUI() {
		try {
			view = new ImageViewer();
			this.setClosable(true);
			setPreferredSize(new Dimension(400, 320));
			this.setBounds(25, 25, 400, 320);
			setVisible(true);
			this.setTitle(title);
			DisagreementStatistics disagreementStatistics = new DisagreementStatistics(this.sqlDatabaseM);
			File f = disagreementStatistics.makePlot(projectID, type);
			this.getContentPane().add(view);
			view.setImage(f);
			this.setVisible(true);
		} catch (RuntimeException e) {
			GUIMessages.exceptionMessage(e);
			logger.log(Level.SEVERE, "", e);
			this.dispose();
		}
	}
}
