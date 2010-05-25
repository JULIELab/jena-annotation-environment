/** 
 * AddMultipleBaseDataDialog.java
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
 * Creation date: August, 2006 
 * 

 **/

package de.julielab.annoenv.ui.annomaster;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;

import de.julielab.annoenv.db.annodata.MmaxData;
import de.julielab.annoenv.db.sql.SQLDatabaseManager;

import de.julielab.annoenv.ui.GUIMessages;
import de.julielab.annoenv.utils.AnnoEnvLogger;
import de.julielab.annoenv.utils.XMLFileFilter;
import de.julielab.annoenv.utils.settings.AnnoMasterTooltips;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

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
 * This dialog is used to add schema files to a project, when creating it.
 * 
 * @see CreateProject
 */
public class AddSchemataDialog extends javax.swing.JDialog {

	private static Logger logger = AnnoEnvLogger
			.getLogger("de.julielab.annoenv.ui.annomaster.AddSchemataDialog");

	private JPanel jPanel1;

	private JButton CancelButton;

	private JButton OKButton;

	private JButton BrowseButtonCustomF;

	private JButton BrowseButtonSchemaF;

	private JTextField LvlNameField;

	private JTextField CFileField;

	private JTextField SFileField;

	private JRadioButton MLvlRButton;

	private JLabel LvlNameLabel;

	private JLabel CFileLabel;

	private JLabel SFileLabel;

	private JFileChooser SFileChooser;

	private JFileChooser CFileChooser;

	private boolean mainLvlIsAlreadySet;

	private MmaxData M;

	private File customFile;

	private File schemaFile;

	private Vector lvlNames;

	private static File currentPath;

	private SQLDatabaseManager sdm;

	public AddSchemataDialog(boolean mainLvlIsAlreadySet, Vector lvlNames,
			File currentPath, SQLDatabaseManager sdm) {
		this.mainLvlIsAlreadySet = mainLvlIsAlreadySet;
		this.lvlNames = lvlNames;
		this.currentPath = currentPath;
		this.sdm = sdm;
		initGUI();
	}

	private void initGUI() {
		try {
			M = new MmaxData();
			{
				SFileChooser = new JFileChooser();
				CFileChooser = new JFileChooser();
				SFileChooser.setFileFilter(new XMLFileFilter());
				CFileChooser.setFileFilter(new XMLFileFilter());
				jPanel1 = new JPanel();
				getContentPane().add(jPanel1, BorderLayout.CENTER);
				AnchorLayout jPanel1Layout = new AnchorLayout();
				jPanel1.setLayout(jPanel1Layout);
				{
					CancelButton = new JButton();
					jPanel1.add(CancelButton, new AnchorConstraint(803, 655,
							881, 478, AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					CancelButton.setText("Cancel");
					CancelButton
							.setPreferredSize(new java.awt.Dimension(91, 28));
					CancelButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							AddSchemataDialog.this.dispose();
						}
					});
				}
				{
					OKButton = new JButton();
					jPanel1.add(OKButton, new AnchorConstraint(803, 410, 881,
							232, AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					OKButton.setText("OK");
					OKButton.setPreferredSize(new java.awt.Dimension(91, 28));
					OKButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							if (SFileField.getText().equals("")
									|| CFileField.getText().equals("")
									|| LvlNameField.getText().equals("")) {
								GUIMessages
										.warnMessage("Missing data, check if all fields are set!");
							} else if (lvlNames
									.contains(LvlNameField.getText())) {
								GUIMessages
										.warnMessage("This level name does already exist! Choose another name");
							} else {
								try {
									M.setLevelName(LvlNameField.getText());
									M.setMainLevel(MLvlRButton.isSelected());
									M.setSchemaFile(schemaFile);
									M.setCustomFile(customFile);
								} catch (RuntimeException e) {
									GUIMessages
											.exceptionMessage(e);
									logger.log(Level.SEVERE, "", e);
								}
								AddSchemataDialog.this.dispose(); 
							}
						}
					});
				}
				{
					BrowseButtonCustomF = new JButton();
					jPanel1.add(BrowseButtonCustomF, new AnchorConstraint(275,
							846, 353, 669, AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					BrowseButtonCustomF.setText("Browse");
					BrowseButtonCustomF
							.setPreferredSize(new java.awt.Dimension(91, 28));
					BrowseButtonCustomF.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							CFileChooser.setCurrentDirectory(currentPath);
							int returnVal = CFileChooser
									.showOpenDialog(AddSchemataDialog.this);

							if (returnVal == JFileChooser.APPROVE_OPTION) {
								File CFile = CFileChooser.getSelectedFile();
								CFileField.setText(CFile.getName());
								customFile = CFile;
								currentPath = CFile.getParentFile();
							} else {
								CFileField.setText("");
							}
						}
					});
				}
				{
					BrowseButtonSchemaF = new JButton();
					jPanel1.add(BrowseButtonSchemaF, new AnchorConstraint(118,
							846, 196, 669, AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					BrowseButtonSchemaF.setText("Browse");
					BrowseButtonSchemaF
							.setPreferredSize(new java.awt.Dimension(91, 28));
					BrowseButtonSchemaF.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							SFileChooser.setCurrentDirectory(currentPath);
							int returnVal = SFileChooser
									.showOpenDialog(AddSchemataDialog.this);

							if (returnVal == JFileChooser.APPROVE_OPTION) {
								File SFile = SFileChooser.getSelectedFile();
								SFileField.setText(SFile.getName());
								schemaFile = SFile;
								currentPath = SFile.getParentFile();
							} else {
								SFileField.setText("");
							}
						}
					});
				}
				{
					LvlNameField = new JTextField();
					jPanel1.add(LvlNameField, new AnchorConstraint(412, 628,
							490, 287, AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					LvlNameField.setPreferredSize(new java.awt.Dimension(175,
							28));
				}
				{
					CFileField = new JTextField();
					jPanel1.add(CFileField, new AnchorConstraint(275, 628, 353,
							287, AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					CFileField
							.setPreferredSize(new java.awt.Dimension(175, 28));
					CFileField.setEditable(false);
				}
				{
					SFileField = new JTextField();
					jPanel1.add(SFileField, new AnchorConstraint(118, 628, 196,
							287, AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					SFileField
							.setPreferredSize(new java.awt.Dimension(175, 28));
					SFileField.setEditable(false);
				}
				{
					MLvlRButton = new JRadioButton();
					jPanel1.add(MLvlRButton, new AnchorConstraint(607, 492,
							685, 205, AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					MLvlRButton.setText("main level:");
					MLvlRButton
							.setPreferredSize(new java.awt.Dimension(147, 28));
					MLvlRButton.setEnabled(!mainLvlIsAlreadySet);
				}
				{
					LvlNameLabel = new JLabel();
					jPanel1.add(LvlNameLabel, new AnchorConstraint(412, 369,
							490, 96, AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					LvlNameLabel.setText("Level name:");
					LvlNameLabel.setPreferredSize(new java.awt.Dimension(140,
							28));
				}
				{
					CFileLabel = new JLabel();
					jPanel1.add(CFileLabel, new AnchorConstraint(275, 383, 353,
							96, AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					CFileLabel.setText("Custom File:");
					CFileLabel
							.setPreferredSize(new java.awt.Dimension(147, 28));
				}
				{
					SFileLabel = new JLabel();
					jPanel1.add(SFileLabel, new AnchorConstraint(138, 383, 216,
							96, AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					SFileLabel.setText("Schema File:");
					SFileLabel
							.setPreferredSize(new java.awt.Dimension(147, 28));
				}
			}
			this.setSize(519, 387);
			AnnoMasterTooltips amTooltips = AnnoMasterTooltips.instantiateAnnoMasterTooltips();
			amTooltips.readTooltips(this);
		} catch (RuntimeException e) {
			GUIMessages.exceptionMessage(e);
			logger.log(Level.SEVERE, "", e);
		}
	}

	public static MmaxData getValue(boolean mainLevel, Vector lvlNames,
			File currentPath, SQLDatabaseManager sdm) {
		AddSchemataDialog dlg = new AddSchemataDialog(mainLevel, lvlNames,
				currentPath, sdm);
		dlg.setModal(true);
		dlg.setVisible(true);
		return dlg.getReturnValue();
	}

	public static File getCurrentPath() {
		return currentPath;
	}

	private boolean checkLvlNames(String lvlname) {
		return (lvlNames.contains(lvlname));
	}

	private MmaxData getReturnValue() {
		MmaxData mmax = null;
		if (!M.getLevelName().equals("")) {
			mmax = M;
		}
		return mmax;
	}

	/**
	 * main method for debugging
	 */
	public static void main(String[] args) {

		try {
			JFrame frame = new JFrame();
			AddSchemataDialog inst = null;
			inst = new AddSchemataDialog(true, new Vector(), null,
					new SQLDatabaseManager());
			inst.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
