/** 
 * CumulatedEntitiesInternalFrame.java
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
 * Creation date: February, 2007 
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

import de.julielab.annoenv.statistics.CumulatedEntityStatistics;
import de.julielab.annoenv.ui.GUIMessages;
import de.julielab.annoenv.utils.AnnoEnvLogger;

/**
 * Statistics for anno projects. Shows the cumulated (overall and distinct)
 * entities annotated so far.
 */
public class CumulatedEntitiesInternalFrame extends InternalAnnoMasterFrame {

	private static final String FRAME_TITLE = "Cumulated Entities Statistics";

	private static Logger logger = AnnoEnvLogger
			.getLogger("de.julielab.annoenv.ui.annomaster.CumulatedEntitiesInternalFrame");

	private int projectID;

	private ImageViewer view;

	public CumulatedEntitiesInternalFrame(int projectID, SQLDatabaseManager sdm) {
		super(sdm);
		this.projectID = projectID;
		initGUI();
	}

	private void initGUI() {
		try {
			view = new ImageViewer();
			this.setClosable(true);
			setPreferredSize(new Dimension(400, 300));
			this.setBounds(25, 25, 400, 300);
			setVisible(true);
			this.setTitle(FRAME_TITLE);
			CumulatedEntityStatistics stats = new CumulatedEntityStatistics(
					projectID, this.sqlDatabaseM);
			File f = stats.makePlot();//TODO projectID);
			this.getContentPane().add(view);
			view.setImage(f);
		} catch (RuntimeException e) {
			GUIMessages.exceptionMessage(e);
			logger.log(Level.SEVERE, "",e);
			this.dispose();
		}
	}

	/**
	 * main method for debugging purposes
	 */
	public static void main(String[] args) {
		try {
			JFrame frame = new JFrame();
			CumulatedEntitiesInternalFrame inst = null;
			int projectID = 162;
			inst = new CumulatedEntitiesInternalFrame(projectID,
					new SQLDatabaseManager());
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

}
