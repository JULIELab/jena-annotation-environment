/** 
 * TimeInternalFrame.java
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
 * Statistics for anno projects. It displays selection time.
 */
public class TimeInternalFrame extends InternalAnnoMasterFrame {

	private static Logger logger = AnnoEnvLogger
			.getLogger("de.julielab.annoenv.ui.annomaster.TimeInternalFrame");

	public static final String ANNOTATIONTIME = "annotation";

	private JPanel jPanel1;

	private JScrollPane jScrollPane1;

	private JLabel AvrgLabel;

	private JLabel TimeLabel;

	private JList TimeList;

	public static final String SELECTIONTIME = "selection";

	private String type;

	private int projectID;

	public TimeInternalFrame(int projectID, String type, SQLDatabaseManager sdm) {
		super(sdm);
		this.type = type;
		this.projectID = projectID;
		this.setClosable(true);
		initGUI();
	}

	private void initGUI() {
		try {
			if (this.type.equals(TimeInternalFrame.ANNOTATIONTIME))
				this.setTitle("Annotation Time (minutes)");
			else if (this.type.equals(TimeInternalFrame.SELECTIONTIME))
				this.setTitle("Selection Time (minutes)");
			this.setPreferredSize(new java.awt.Dimension(478, 339));
			this.setBounds(0, 0, 478, 339);
			this.setVisible(true);
			this.setResizable(false);
			{
				jPanel1 = new JPanel();
				getContentPane().add(jPanel1, BorderLayout.CENTER);
				AnchorLayout jPanel1Layout = new AnchorLayout();
				jPanel1.setLayout(jPanel1Layout);
				{
					jScrollPane1 = new JScrollPane();
					int right = (type.equals(TimeInternalFrame.ANNOTATIONTIME)) ? 504
							: 820;
					jPanel1.add(jScrollPane1, new AnchorConstraint(68, right,
							963, 30, AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					jScrollPane1.setPreferredSize(new java.awt.Dimension(224,
							280));
					{
						SQLFunctions sql = new SQLFunctions(sqlDatabaseM);
						ArrayList listInput = null;
						if (this.type.equals(TimeInternalFrame.ANNOTATIONTIME))
							listInput = sql.getAnnotationTimes(this.projectID);
						else if (this.type
								.equals(TimeInternalFrame.SELECTIONTIME))
							listInput = sql.getALTimes(this.projectID);
						ListModel jList1Model = new DefaultComboBoxModel(
								listInput.toArray());
						TimeList = new JList();
						jScrollPane1.setViewportView(TimeList);
						TimeList.setModel(jList1Model);
					}
				}
				{
					AvrgLabel = new JLabel();
					jPanel1.add(AvrgLabel, new AnchorConstraint(493, 963, 583,
							548, AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					if (this.type.equals(TimeInternalFrame.ANNOTATIONTIME))
						AvrgLabel.setText(new Float((new SQLFunctions(
								sqlDatabaseM)
								.getAverageAnnotationTime(projectID)))
								.toString());
					// else
					// if(this.type.equals(TimeInternalFrame.SELECTIONTIME))
					// AvrgLabel.setText(new Float((new
					// SQLFunctions(sdm).estimateSelectionTime(projectID))).toString());
					AvrgLabel.setPreferredSize(new java.awt.Dimension(196, 28));
				}
				{
					TimeLabel = new JLabel();
					jPanel1.add(TimeLabel, new AnchorConstraint(247, 1273, 359,
							548, AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					TimeLabel.setPreferredSize(new java.awt.Dimension(343, 35));
					if (this.type.equals(TimeInternalFrame.ANNOTATIONTIME))
						TimeLabel.setText("average annotation time");
					else if (this.type.equals(TimeInternalFrame.SELECTIONTIME))
						// TimeLabel.setText("sel/train/traindata/test/testdata");
						;
				}
			}
		} catch (RuntimeException e) {
			GUIMessages.exceptionMessage(e);
			logger.log(Level.SEVERE, "", e);
		}
	}

	/**
	 * main method for debugging
	 */
	public static void main(String[] args) {

		try {
			JFrame frame = new JFrame();
			TimeInternalFrame inst = null;
			inst = new TimeInternalFrame(126, TimeInternalFrame.SELECTIONTIME,
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
