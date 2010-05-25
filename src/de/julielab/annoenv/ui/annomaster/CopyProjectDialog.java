/** 
 * CopyProjectDialog.java
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

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;

import de.julielab.annoenv.db.annodata.AnnoCore;

import de.julielab.annoenv.db.sql.SQLDatabaseManager;
import de.julielab.annoenv.db.sql.SQLFunctions;
import de.julielab.annoenv.db.annodata.User;
import de.julielab.annoenv.ui.GUIMessages;
import de.julielab.annoenv.utils.AnnoEnvLogger;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.xml.parsers.ParserConfigurationException;

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
 * This dialog allows for making a copy of a project, linking it to a different
 * user.
 * 
 */
public class CopyProjectDialog extends javax.swing.JDialog {

	private static Logger logger = AnnoEnvLogger
			.getLogger("de.julielab.annoenv.ui.annomaster.CopyProjectDialog");

	private JPanel jPanel1;

	private JComboBox UserBox;

	private JButton CancelButton;

	private JButton OKButton;

	private JLabel NameLabel;

	private JLabel UserLabel;

	private JTextField ProjectNameField;

	private JCheckBox DowngradeCheckBox;

	private AnnoCore project;

	public CopyProjectDialog(SQLDatabaseManager sdm, AnnoCore project) {
		this.project = project;
		initGUI(sdm);
	}

	private void initGUI(final SQLDatabaseManager sdm) {
		try {
			{
				this.setResizable(false);
			}
			{
				ArrayList<User> Users;
				SQLFunctions AM = new SQLFunctions(sdm);
				Users = AM.retrieveAllUsers();
				Object[] data = Users.toArray();

				this.setModal(true);
				this.setTitle("Copy project: " + project.getName());
				{
					jPanel1 = new JPanel();
					getContentPane().add(jPanel1, BorderLayout.CENTER);
					AnchorLayout jPanel1Layout = new AnchorLayout();
					jPanel1.setLayout(jPanel1Layout);
					jPanel1.setPreferredSize(new java.awt.Dimension(392, 231));
					{
						UserBox = new JComboBox(data);
						jPanel1.add(UserBox, new AnchorConstraint(79, 431, 143,
								70, AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
						jPanel1.add(UserBox, new AnchorConstraint(67, 927, 198,
								286, AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
						UserBox
								.setPreferredSize(new java.awt.Dimension(252,
										28));
					}
					{
						CancelButton = new JButton();
						jPanel1.add(CancelButton, new AnchorConstraint(820,
								891, 950, 660, AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
						CancelButton.setText("Cancel");
						CancelButton.setPreferredSize(new java.awt.Dimension(
								91, 28));
						CancelButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								dispose();
								// TODO add your code for
								// CancelButton.actionPerformed
							}
						});
					}

					OKButton = new JButton();
					jPanel1.add(OKButton, new AnchorConstraint(820, 500, 950,
							268, AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					OKButton.setText("OK");
					OKButton.setPreferredSize(new java.awt.Dimension(91, 28));
					OKButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							if (ProjectNameField.getText().equals(""))
								GUIMessages.warnMessage("missing data!");
							else {
								try {

									String mode = project.getMode();
									if (DowngradeCheckBox.isSelected())
										mode = "default";

									new SQLFunctions(sdm).copyProject(project
											.getProjectID(), ((User) UserBox
											.getSelectedItem()).getUserID(),
											ProjectNameField.getText(), mode);
									GUIMessages
											.infoMessage("Project has been copied!");
									dispose();
								} catch (RuntimeException e) {
									GUIMessages.exceptionMessage(e);
									logger.log(Level.SEVERE, "", e);
								}
							}
						}
					});

					{
						NameLabel = new JLabel();
						jPanel1.add(NameLabel, new AnchorConstraint(296, 268,
								394, 54, AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
						NameLabel.setText("Project name:");
						NameLabel.setPreferredSize(new java.awt.Dimension(84,
								21));
					}
					{
						UserLabel = new JLabel();
						jPanel1.add(UserLabel, new AnchorConstraint(100, 215,
								198, 54, AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
						UserLabel.setText("User:");
						UserLabel.setPreferredSize(new java.awt.Dimension(63,
								21));
					}
					{
						ProjectNameField = new JTextField();
						jPanel1.add(ProjectNameField, new AnchorConstraint(264,
								927, 394, 286, AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
						ProjectNameField
								.setPreferredSize(new java.awt.Dimension(252,
										28));
					}
					{
						DowngradeCheckBox = new JCheckBox();
						jPanel1.add(DowngradeCheckBox, new AnchorConstraint(
								525, 891, 656, 286,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
						DowngradeCheckBox.setText("convert to Non-AL-Project");
						DowngradeCheckBox
								.setPreferredSize(new java.awt.Dimension(238,
										28));
						DowngradeCheckBox.setEnabled(project.isAl());
					}
				}
			}
			this.setSize(400, 251);
		} catch (RuntimeException e) {
			GUIMessages.exceptionMessage(e);
			logger.log(Level.SEVERE, "", e);
		}
	}

	/**
	 * main method for debugging
	 */
	public static void main(String[] args) throws InstantiationException,
			IllegalAccessException, SQLException, ClassNotFoundException,
			ParserConfigurationException {
		JFrame frame = new JFrame();
		SQLDatabaseManager sdm = new SQLDatabaseManager();
		SQLFunctions sf = new SQLFunctions(sdm);
		CopyProjectDialog inst = new CopyProjectDialog(sdm, null);
		inst.setVisible(true);
	}

}
