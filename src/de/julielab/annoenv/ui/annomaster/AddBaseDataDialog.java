/** 
 * AddBaseDataDialog.java
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
 * Creation date: September, 2006 
 * 
 * A dialog that asks for user input to add BaseData files to a project.
 * This dialog can only be accessed during project creation.
 * @see CreateProject
 **/

package de.julielab.annoenv.ui.annomaster;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;

import de.julielab.annoenv.db.annodata.BaseDataFull;
import de.julielab.annoenv.db.annodata.Markable;
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
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import javax.swing.JFileChooser;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
public class AddBaseDataDialog extends javax.swing.JDialog implements
		DirectoryAccess {

	private static final String FRAME_TITLE = "Base Data Input!";

	private static final String BROWSE_BUTTON_TEXT = "Browse";

	private static Logger logger = AnnoEnvLogger
			.getLogger("de.julielab.annoenv.ui.annomaster.AddBaseDataDialog");

	private JPanel jPanel1;

	private JButton AddMarkableButton;

	private JButton BrowseButtonStyleFile;

	private JList MList;

	private JScrollPane jScrollPane1;

	private JTextField UriField;

	private JButton BrowseButtonBaseDataFile;

	private JList MarkList;

	private JLabel LevelNameLabel;

	private JScrollPane jScrollPane2;

	private JTextField TidsidField;

	private JLabel TidsidLabel;

	private JPanel jPanel2;

	private JButton TidsidButton;

	private JButton OKButton;

	private JButton CancelButton;

	private JTextField SFileField;

	private JTextField BFileField;

	private JLabel MarkablesLabel;

	private JLabel UriLabel;

	private JLabel SFileButton;

	private JLabel BFileLabel;

	private JFileChooser SFileChooser;

	private JFileChooser BFileChooser;

	private JFileChooser MFileChooser;

	private JFileChooser TidsidFileChooser;

	private Vector mmaxDataVector;

	private Vector<Markable> markablesVector;

	private Vector substitutionVector;

	private BaseDataFull B = null;

	private File bData;

	private File sFile;

	private File tFile;

	private String URi;

	private SQLDatabaseManager sdm;

	private boolean isAL;

	private File currentPath;

	public AddBaseDataDialog(Vector mmaxDataVector, boolean isAL,
			SQLDatabaseManager sdm) {
		this.mmaxDataVector = mmaxDataVector;
		this.substitutionVector = new Vector(); // dummies
		this.markablesVector = new Vector<Markable>();
		for (int i = 0; i < this.mmaxDataVector.size(); i++) {
			this.substitutionVector.add(i, "-");
			this.markablesVector.add(i, new Markable());
		}
		this.isAL = isAL;
		this.sdm = sdm;
		try {
			initGUI();
		} catch (Exception e) {
			GUIMessages.exceptionMessage(e);
			logger.log(Level.SEVERE, "", e);
		}
	}

	private void initGUI() {
		try {
			{
				B = new BaseDataFull();
				SFileChooser = new JFileChooser();
				BFileChooser = new JFileChooser();
				MFileChooser = new JFileChooser();
				TidsidFileChooser = new JFileChooser();
				XMLFileFilter filter = new XMLFileFilter();
				BFileChooser.setFileFilter(filter);
				MFileChooser.setFileFilter(filter);
				this.setTitle(FRAME_TITLE);
				{
					jPanel1 = new JPanel();
					getContentPane().add(jPanel1, BorderLayout.CENTER);
					AnchorLayout jPanel1Layout = new AnchorLayout();
					jPanel1.setLayout(jPanel1Layout);
					jPanel1.setPreferredSize(new java.awt.Dimension(525, 427));
					{
						LevelNameLabel = new JLabel();
						jPanel1.add(LevelNameLabel, new AnchorConstraint(408,
								240, 476, 43, AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
						LevelNameLabel.setText("Levels :");
						LevelNameLabel.setPreferredSize(new java.awt.Dimension(
								98, 28));
					}
					{
						jScrollPane2 = new JScrollPane();
						jPanel1.add(jScrollPane2, new AnchorConstraint(476,
								904, 884, 621, AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
						jScrollPane2.setPreferredSize(new java.awt.Dimension(
								140, 168));
						{
							ListModel MarkListModel = new DefaultComboBoxModel();
							MarkList = new JList();
							jScrollPane2.setViewportView(MarkList);
							MarkList.setModel(MarkListModel);
							MarkList.setListData(substitutionVector);
							MarkList.setFocusable(false);
						}
					}
					{
						jPanel2 = new JPanel();
						AnchorLayout jPanel2Layout = new AnchorLayout();
						jPanel2.setLayout(jPanel2Layout);
						jPanel1.add(jPanel2, new AnchorConstraint(226, 807,
								295, 43, AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
						jPanel2
								.setPreferredSize(new java.awt.Dimension(378,
										28));
						jPanel2.setEnabled(isAL);
						{
							TidsidField = new JTextField();
							jPanel2.add(TidsidField, new AnchorConstraint(17,
									612, 1017, 260,
									AnchorConstraint.ANCHOR_REL,
									AnchorConstraint.ANCHOR_REL,
									AnchorConstraint.ANCHOR_REL,
									AnchorConstraint.ANCHOR_REL));
							TidsidField
									.setPreferredSize(new java.awt.Dimension(
											133, 28));
							TidsidField.setEditable(false);
							if (!isAL) {
								TidsidField.setBackground(new java.awt.Color(
										191, 191, 191));
								TidsidField.setText("n/a");
							}
						}
						{
							TidsidLabel = new JLabel();
							jPanel2.add(TidsidLabel, new AnchorConstraint(17,
									303, 1017, 1, AnchorConstraint.ANCHOR_REL,
									AnchorConstraint.ANCHOR_REL,
									AnchorConstraint.ANCHOR_REL,
									AnchorConstraint.ANCHOR_REL));
							TidsidLabel.setText("seed: ");
							TidsidLabel
									.setPreferredSize(new java.awt.Dimension(
											112, 28));
						}
						{
							TidsidButton = new JButton();
							jPanel2.add(TidsidButton, new AnchorConstraint(17,
									982, 1017, 723,
									AnchorConstraint.ANCHOR_REL,
									AnchorConstraint.ANCHOR_REL,
									AnchorConstraint.ANCHOR_REL,
									AnchorConstraint.ANCHOR_REL));
							TidsidButton.setText(BROWSE_BUTTON_TEXT);
							TidsidButton
									.setPreferredSize(new java.awt.Dimension(
											98, 28));
							if (!isAL) {
								TidsidButton.setEnabled(false);
								TidsidButton.setBackground(new java.awt.Color(
										229, 229, 229));
							}
							TidsidButton
									.addActionListener(new ActionListener() {
										public void actionPerformed(
												ActionEvent evt) {
											try {
												TidsidFileChooser
														.setCurrentDirectory(currentPath);
												int returnVal = TidsidFileChooser
														.showOpenDialog(AddBaseDataDialog.this);

												if (returnVal == JFileChooser.APPROVE_OPTION) {
													File TidsidFile = TidsidFileChooser
															.getSelectedFile();
													TidsidField
															.setText(TidsidFile
																	.getName());
													tFile = TidsidFile;
													currentPath = TidsidFile
															.getParentFile();
												} else {
													TidsidField.setText("");
													tFile = null;
												}
											} catch (Exception e) {
												GUIMessages.exceptionMessage(e);
												logger.log(Level.SEVERE, "", e);
											}
										}
									});
						}
					}
					{
						CancelButton = new JButton();
						jPanel1.add(CancelButton, new AnchorConstraint(919,
								664, 991, 509, AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
						CancelButton.setText("Cancel");
						CancelButton.setPreferredSize(new java.awt.Dimension(
								77, 28));
						CancelButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								AddBaseDataDialog.this.dispose();
							}
						});
					}
					{
						OKButton = new JButton();
						jPanel1.add(OKButton, new AnchorConstraint(919, 494,
								991, 339, AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
						OKButton.setText("OK");
						OKButton
								.setPreferredSize(new java.awt.Dimension(77, 28));
						OKButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								try {
									if (SFileField.getText().equals("")
											|| BFileField.getText().equals("")
											|| UriField.getText().equals("")
											|| TidsidField.getText().equals("")
											|| !isSet()) {
										GUIMessages
												.warnMessage("missing data!");
									} else {
										B.setMarkables(new ArrayList<Markable>(
												markablesVector));
										B.setBasedataFile(bData);
										B.setUri(UriField.getText());
										B.setStyleFile(sFile);
										B.setTidsidFile(tFile); // eingef?gt KT
										// (19.07.2006)
										AddBaseDataDialog.this.dispose();
									}
								} catch (Exception e) {
									GUIMessages.exceptionMessage(e);
									logger.log(Level.SEVERE, "", e);
								}
							}
						});
					}
					{
						BrowseButtonStyleFile = new JButton();
						jPanel1.add(BrowseButtonStyleFile,
								new AnchorConstraint(139, 792, 209, 594,
										AnchorConstraint.ANCHOR_REL,
										AnchorConstraint.ANCHOR_REL,
										AnchorConstraint.ANCHOR_REL,
										AnchorConstraint.ANCHOR_REL));
						BrowseButtonStyleFile.setText(BROWSE_BUTTON_TEXT);
						BrowseButtonStyleFile
								.setPreferredSize(new java.awt.Dimension(98, 28));
						BrowseButtonStyleFile
								.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent evt) {
										try {
											SFileChooser
													.setCurrentDirectory(currentPath);
											int returnVal = SFileChooser
													.showOpenDialog(AddBaseDataDialog.this);

											if (returnVal == JFileChooser.APPROVE_OPTION) {
												File CFile = SFileChooser
														.getSelectedFile();
												SFileField.setText(CFile
														.getName());
												sFile = CFile;
												currentPath = CFile
														.getParentFile();
											} else {
												SFileField.setText("");
												sFile = null;
											}
										} catch (Exception e) {
											GUIMessages.exceptionMessage(e);
											logger.log(Level.SEVERE, "", e);
										}
									}
								});
					}
					{
						jScrollPane1 = new JScrollPane();
						jPanel1.add(jScrollPane1, new AnchorConstraint(476,
								325, 884, 43, AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
						jScrollPane1.setPreferredSize(new java.awt.Dimension(
								140, 168));
						{
							ListModel MListModel = new DefaultComboBoxModel();
							MList = new JList();
							jScrollPane1.setViewportView(MList);
							MList.setModel(MListModel);
							MList.setListData(mmaxDataVector);
							MList
									.addListSelectionListener(new ListSelectionListener() {
										public void valueChanged(
												ListSelectionEvent evt) {
											if (!MList.isSelectionEmpty()) {
												AddMarkableButton
														.setEnabled(true);
												MarkList.setSelectedIndex(MList
														.getSelectedIndex());
											} else {
												AddMarkableButton
														.setEnabled(false);
											}
										}
									});
						}
					}
					{
						UriField = new JTextField();
						jPanel1.add(UriField, new AnchorConstraint(325, 509,
								397, 240, AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
						UriField.setPreferredSize(new java.awt.Dimension(133,
								28));
					}
					{
						SFileField = new JTextField();
						jPanel1.add(SFileField, new AnchorConstraint(139, 510,
								209, 241, AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
						SFileField.setPreferredSize(new java.awt.Dimension(133,
								28));
						SFileField.setEditable(false);
					}
					{
						BFileField = new JTextField();
						jPanel1.add(BFileField, new AnchorConstraint(53, 510,
								122, 241, AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
						BFileField.setPreferredSize(new java.awt.Dimension(133,
								28));
						BFileField.setEditable(false);
					}
					{
						MarkablesLabel = new JLabel();
						jPanel1.add(MarkablesLabel, new AnchorConstraint(408,
								819, 476, 621, AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
						MarkablesLabel.setText("Markables' Files:");
						MarkablesLabel.setPreferredSize(new java.awt.Dimension(
								98, 28));
					}
					{
						UriLabel = new JLabel();
						jPanel1.add(UriLabel, new AnchorConstraint(325, 170,
								397, 43, AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
						UriLabel.setText("URi:");
						UriLabel
								.setPreferredSize(new java.awt.Dimension(63, 28));
					}
					{
						SFileButton = new JLabel();
						jPanel1.add(SFileButton, new AnchorConstraint(139, 198,
								209, 43, AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
						SFileButton.setText("Style File:");
						SFileButton.setPreferredSize(new java.awt.Dimension(77,
								28));
					}
					{
						BFileLabel = new JLabel();
						jPanel1.add(BFileLabel, new AnchorConstraint(55, 283,
								127, 43, AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
						BFileLabel.setText("Basedata File:");
						BFileLabel.setPreferredSize(new java.awt.Dimension(119,
								28));
					}
					{
						AddMarkableButton = new JButton();
						jPanel1.add(AddMarkableButton, new AnchorConstraint(
								476, 593, 544, 339,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
						AddMarkableButton.setText("Add Markable");
						AddMarkableButton
								.setPreferredSize(new java.awt.Dimension(126,
										28));
						AddMarkableButton.setEnabled(false);
						AddMarkableButton
								.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent evt) {
										try {
											MFileChooser
													.setCurrentDirectory(currentPath);
											int returnVal = MFileChooser
													.showOpenDialog(AddBaseDataDialog.this);

											if (returnVal == JFileChooser.APPROVE_OPTION) {
												try {
													File MFile = MFileChooser
															.getSelectedFile();
													MmaxData selected = (MmaxData) MList
															.getSelectedValue();
													int i = MList
															.getSelectedIndex();

													Markable mark = new Markable();
													mark.setMarkableFile(MFile);
													mark.setLevelName(selected
															.getLevelName());

													markablesVector
															.insertElementAt(
																	mark, i);
													markablesVector
															.removeElementAt(i + 1);

													substitutionVector
															.insertElementAt(
																	MFile, i);
													substitutionVector
															.removeElementAt(i + 1);

													MarkList
															.setListData(substitutionVector);
													MarkList.repaint();
													MList
															.setListData(mmaxDataVector);
													MList.repaint();

													currentPath = MFile
															.getParentFile();
												} catch (RuntimeException e) {
													GUIMessages
															.exceptionMessage(e);
													logger.log(Level.SEVERE,
															"", e);
												}
											}
										} catch (Exception e) {
											GUIMessages.exceptionMessage(e);
											logger.log(Level.SEVERE, "", e);
										}
									}
								});
					}
					{
						BrowseButtonBaseDataFile = new JButton();
						jPanel1.add(BrowseButtonBaseDataFile,
								new AnchorConstraint(55, 791, 127, 593,
										AnchorConstraint.ANCHOR_REL,
										AnchorConstraint.ANCHOR_REL,
										AnchorConstraint.ANCHOR_REL,
										AnchorConstraint.ANCHOR_REL));
						BrowseButtonBaseDataFile.setText(BROWSE_BUTTON_TEXT);
						BrowseButtonBaseDataFile
								.setPreferredSize(new java.awt.Dimension(98, 28));
						BrowseButtonBaseDataFile
								.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent evt) {
										try {
											BFileChooser
													.setCurrentDirectory(currentPath);
											int returnVal = BFileChooser
													.showOpenDialog(AddBaseDataDialog.this);

											if (returnVal == JFileChooser.APPROVE_OPTION) {
												File BFile = BFileChooser
														.getSelectedFile();
												BFileField.setText(BFile
														.getName());
												bData = BFile;
												currentPath = BFile
														.getParentFile();
											} else {
												BFileField.setText("");
												bData = null;
											}
										} catch (Exception e) {
											GUIMessages.exceptionMessage(e);
											logger.log(Level.SEVERE, "", e);
										}
									}
								});
					}
				}
			}
			this.setSize(502, 441);

			AnnoMasterTooltips amTooltips = AnnoMasterTooltips
					.instantiateAnnoMasterTooltips();
			amTooltips.readTooltips(this);
		} catch (RuntimeException e) {
			GUIMessages.exceptionMessage(e);
			logger.log(Level.SEVERE, "", e);
		}
	}

	private boolean isSet() {
		boolean b = true;
		for (Enumeration E = substitutionVector.elements(); E.hasMoreElements();) {
			b &= (E.nextElement() instanceof File);
		}
		return b;
	}

	public BaseDataFull getBaseData() {
		return B;
	}

	public Vector<Markable> getMarkableVector() {
		return markablesVector;
	}

	public void setCurrentPath(File currentPath) {
		this.currentPath = currentPath;
	}

	public File getCurrentPath() {
		return this.currentPath;
	}

	/**
	 * main method for debugging
	 */
	public static void main(String[] args) {
		try {
			AddBaseDataDialog inst = new AddBaseDataDialog(new Vector(), true,
					new SQLDatabaseManager());
			inst.setVisible(true);
		} catch (Exception e) {
			GUIMessages.exceptionMessage(e);
			logger.log(Level.SEVERE, "", e);
		}
	}

}
