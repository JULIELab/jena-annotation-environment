/** 
 * AnnotatorAgreementInternalFrame.java
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

import de.julielab.annoenv.db.annodata.AnnoCore;
import de.julielab.annoenv.db.annodata.AnnoFull;
import de.julielab.annoenv.db.sql.SQLDatabaseManager;
import de.julielab.annoenv.db.sql.SQLFunctions;

import de.julielab.annoenv.statistics.FlatInterAnnotatorAgreement;
import de.julielab.annoenv.ui.GUIMessages;
import de.julielab.annoenv.utils.AnnoEnvLogger;

import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
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
 * Statistics for anno projects. It displays inter-annotator disagreement.
 */
public class AnnotatorAgreementInternalFrame extends InternalAnnoMasterFrame {

	private static final String BUTTON_TEXT = "calculate IAA";

	private static final String FRAME_TITLE = "Flat Inter-Annotator Agreement";

	private static Logger logger = AnnoEnvLogger
			.getLogger("de.julielab.annoenv.ui.annomaster.AnnotatorAgreementInternalFrame");

	private JPanel jPane1;

	private JScrollPane jScrollPane1;

	private JComboBox secondProjectBox;

	private JButton showButton;

	private JComboBox firstProjectBox;

	private JTextArea outputArea;

	private ArrayList<AnnoCore> defaultProjects;

	public AnnotatorAgreementInternalFrame(SQLDatabaseManager sdm) {
		super(sdm);
	
		try {
			SQLFunctions sf = new SQLFunctions(sdm);
			defaultProjects = sf.getDefaultCoreProjects();

			/*
			 * now set the display mode of the projects to NICE_DISPLAY to also
			 * show the name of the project owner in the combo box KT, 5.10.2006
			 */

			for (int i = 0; i < defaultProjects.size(); i++) {
				defaultProjects.get(i)
						.setDisplayType(AnnoCore.NICE_DISPLAY, sf);
			}

		} catch (RuntimeException e) {
			GUIMessages.exceptionMessage(e);
			logger.log(Level.SEVERE, "", e);
		}
		initGUI();
	}

	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(519, 346));
			this.setBounds(25, 25, 519, 346);
			setVisible(true);
			this.setTitle(FRAME_TITLE);
			this.setClosable(true);
			{
				jPane1 = new JPanel();
				jPane1.setLayout(null);
				getContentPane().add(jPane1, BorderLayout.CENTER);
				jPane1.setPreferredSize(new java.awt.Dimension(304, 274));
				{
					showButton = new JButton();
					jPane1.add(showButton);
					showButton.setText(BUTTON_TEXT);
					showButton
							.setPreferredSize(new java.awt.Dimension(133, 28));
					showButton.setBounds(350, 231, 133, 28);
					showButton
							.addActionListener(new showButtonActionListener());
				}
				{

					secondProjectBox = new JComboBox(defaultProjects.toArray());
					jPane1.add(secondProjectBox);
					secondProjectBox.setPreferredSize(new java.awt.Dimension(
							290, 28));
					secondProjectBox.setBounds(42, 273, 252, 28);
					secondProjectBox.addItemListener(new boxItemListener());
				}
				{
					firstProjectBox = new JComboBox(defaultProjects.toArray());
					jPane1.add(firstProjectBox);
					firstProjectBox.setPreferredSize(new java.awt.Dimension(
							290, 28));
					firstProjectBox.setBounds(42, 231, 252, 28);
					firstProjectBox.addItemListener(new boxItemListener());
				}
				{
					jScrollPane1 = new JScrollPane();
					jPane1.add(jScrollPane1);
					jScrollPane1
							.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
					jScrollPane1.setPreferredSize(new java.awt.Dimension(476,
							196));
					jScrollPane1.setBounds(21, 14, 476, 196);
					{
						outputArea = new JTextArea();
						jScrollPane1.setViewportView(outputArea);
						outputArea.setLineWrap(true);
						outputArea.setEditable(false);
					}
				}
			}
		} catch (RuntimeException e) {
			GUIMessages.exceptionMessage(e);
			logger.log(Level.SEVERE, "", e);
		}
	}

	private class boxItemListener implements ItemListener {
		public void itemStateChanged(ItemEvent evt) {
			JComboBox box = (JComboBox) evt.getSource();
			box.setToolTipText(box.getSelectedItem().toString());
		}
	}

	private class showButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			try {
				DecimalFormat df = new DecimalFormat("0.00");
				outputArea.setText("");
				String output = "";
				SQLFunctions sf = new SQLFunctions(
						AnnotatorAgreementInternalFrame.this.sqlDatabaseM);

				AnnoFull project1 = sf
						.getFullProject(((AnnoCore) firstProjectBox
								.getSelectedItem()).getProjectID());
				AnnoFull project2 = sf
						.getFullProject(((AnnoCore) secondProjectBox
								.getSelectedItem()).getProjectID());

				FlatInterAnnotatorAgreement iaa = new FlatInterAnnotatorAgreement(
						project1, project2);
				iaa
						.calculateIAA(AnnotatorAgreementInternalFrame.this.sqlDatabaseM);
				
				// make output string to be shown in frame
				output += "project 1: " + project1.getName() + " ("
						+ project1.getUserName(sf) + ")\n";
				output += "project 2: " + project2.getName() + " ("
						+ project2.getUserName(sf) + ")\n";
				output += "\n";
				output += "P/R/F: " + df.format(iaa.getPrecision()) + "/"
						+ df.format(iaa.getRecall()) + "/"
						+ df.format(iaa.getFscore()) + "\n";
				output += "ans/ref/corr: " + iaa.getNumans() + "/"
						+ iaa.getNumref() + "/" + iaa.getNumcorr() + "\n\n";
				outputArea.append(output);
				output = iaa.getConsideredBD().toString();
				outputArea.append("Considered Basedata: " + output + "\n");
				output = iaa.getMissedBD().toString();
				outputArea.append("Missed Basedata: " + output + "\n");
				
				
			} catch (RuntimeException e) {
				GUIMessages.exceptionMessage(e);
				logger.log(Level.SEVERE, "", e);
			}
		}
	}

	/**
	 * main method for debugging purposes
	 */
	public static void main(String[] args) {
		try {
			JFrame frame = new JFrame();
			AnnotatorAgreementInternalFrame inst = null;
			inst = new AnnotatorAgreementInternalFrame(new SQLDatabaseManager());
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
