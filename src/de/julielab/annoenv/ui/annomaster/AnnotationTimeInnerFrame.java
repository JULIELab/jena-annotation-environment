/** 
 * AnnotationTimeInnerFrame.java
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

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;

import de.julielab.annoenv.db.sql.SQLDatabaseManager;
import de.julielab.annoenv.db.sql.SQLFunctions;
import de.julielab.annoenv.ui.GUIMessages;
import de.julielab.annoenv.utils.AnnoEnvLogger;

import java.awt.BorderLayout;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.WindowConstants;

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
 * Statistics for anno projects. It displays the annotation time.
 */
public class AnnotationTimeInnerFrame extends InternalAnnoMasterFrame {

	private static final String FRAME_TITLE = "Annotation Time (in minutes)";

	private static Logger logger = AnnoEnvLogger
			.getLogger("de.julielab.annoenv.ui.annomaster.AnnotationTimeInnerFrame");

	private JPanel jPanel1;

	private JList jList1;

	private JScrollPane jScrollPane1;

	private JLabel TimeLabel;

	private JLabel AverageLabel;

	private int projectID;

	public AnnotationTimeInnerFrame(int projectID, SQLDatabaseManager sdm) {
		super(sdm);
		this.projectID = projectID;
		initGUI();
	}

	private void initGUI() {
		try {
			this.setClosable(true);
			this.setMaximumSize(this.getPreferredSize());
			;
			this.setPreferredSize(new java.awt.Dimension(371, 361));
			this.setBounds(0, 0, 371, 361);
			setVisible(true);
			this.setTitle(FRAME_TITLE);
			{
				jPanel1 = new JPanel();
				getContentPane().add(jPanel1, BorderLayout.CENTER);
				AnchorLayout jPanel1Layout = new AnchorLayout();
				jPanel1.setLayout(jPanel1Layout);
				{
					jScrollPane1 = new JScrollPane();
					jPanel1.add(jScrollPane1, new AnchorConstraint(43, 881,
							774, 77, AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					jScrollPane1.setPreferredSize(new java.awt.Dimension(294,
							245));
					{
						SQLFunctions sql = new SQLFunctions(sqlDatabaseM);
						ArrayList listInput = sql
								.getAnnotationTimes(this.projectID);
						ListModel jList1Model = new DefaultComboBoxModel(
								listInput.toArray());
						jList1 = new JList();
						jScrollPane1.setViewportView(jList1);
						jList1.setModel(jList1Model);
						jList1.setSelectionModel(new ToggleSelectionModel());
						jList1.setPreferredSize(new java.awt.Dimension(0, 0));
					}
				}
				{
					AverageLabel = new JLabel();
					jPanel1.add(AverageLabel, new AnchorConstraint(837, 575,
							920, 96, AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					AverageLabel.setText("avg. anno time in minutes:");
					AverageLabel.setPreferredSize(new java.awt.Dimension(175,
							28));
				}
				{
					TimeLabel = new JLabel();
					jPanel1.add(TimeLabel, new AnchorConstraint(833, 705, 919,
							627, AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					TimeLabel.setText(new Float((new SQLFunctions(sqlDatabaseM)
							.getAverageAnnotationTime(projectID))).toString());
					TimeLabel.setPreferredSize(new java.awt.Dimension(100, 28));
				}
			}
		} catch (RuntimeException e) {
			GUIMessages.exceptionMessage(e);
			logger.log(Level.SEVERE, "error getting annotation times", e);
		}
	}

	/**
	 * main method for debugging purposes
	 */
	public static void main(String[] args) {
		try {
			int projectID = 2;
			JFrame frame = new JFrame();
			AnnotationTimeInnerFrame inst = null;
			inst = new AnnotationTimeInnerFrame(projectID,
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
