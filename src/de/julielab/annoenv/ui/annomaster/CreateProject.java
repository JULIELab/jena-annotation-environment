/** 
 * CreateProjectDialog.java
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

 */

package de.julielab.annoenv.ui.annomaster;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Date;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultComboBoxModel;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;

import de.julielab.annoenv.al.ALChecks;
import de.julielab.annoenv.db.annodata.AnnoCore;
import de.julielab.annoenv.db.annodata.AnnoFull;

import de.julielab.annoenv.db.annodata.BaseDataFull;
import de.julielab.annoenv.db.annodata.Markable;
import de.julielab.annoenv.db.annodata.MmaxData;
import de.julielab.annoenv.db.annodata.User;
import de.julielab.annoenv.db.sql.SQLDatabaseManager;
import de.julielab.annoenv.db.sql.SQLFunctions;

import de.julielab.annoenv.ui.GUIMessages;
import de.julielab.annoenv.utils.AnnoEnvLogger;
import de.julielab.annoenv.utils.XMLFileFilter;
import de.julielab.annoenv.utils.settings.AnnoMasterTooltips;

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
 * GUI to create projects with. Both kinds of projects, AL and default projects,
 * are created step by step.
 */
public class CreateProject extends InternalAnnoMasterFrame {

	private static Logger logger = AnnoEnvLogger
			.getLogger("de.julielab.annoenv.ui.annomaster.CreateProject");

	{
		// Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager
					.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			logger.log(Level.SEVERE, "error setting look and feel", e);
		}
	}

	private JTabbedPane Projekt;

	private JPanel ProjectPanel;

	private JPanel ALPanel;

	private JPanel SchemataPanel;

	private JTextField BINField;

	private JButton BrowseButtonALTagsFile;

	private JButton BrowseButtonCorpusTOK;

	private JButton PrioButton;

	private JTextField PriolistField;

	private JLabel PriolistLabel;

	private JButton AddMultipleButton;

	private JScrollPane jScrollPane3;

	private JTextArea DescriptionArea;

	private JLabel DescriptionLabel;

	private JLabel ALTagsFileLabel;

	private JButton BrowseButtonCorpusPOS;

	private JTextField CPosFileField;

	private JScrollPane jScrollPane2;

	private JScrollPane jScrollPane1;

	private JButton SubmitButton;

	private JTextField TrainingProportionField;

	private JTextField CommitteeField;

	private JLabel TrainingProportionLabel;

	private JLabel CommitteeLabel;

	private JTextField ALTagsFileField;

	private JTextField CorpusTokField;

	private JLabel CorpusPosLabel;

	private JLabel CorpusTokLabel;

	private JScrollPane UserScrollPane;

	private JList UserSelectionJList;

	private JButton PreviousButtonBaseData;

	private JButton PreviousButtonSchemata;

	private JButton PreviousButtonAL;

	private JButton NextButtonSchemata;

	private JButton RemoveButtonUriList;

	private JButton AddButtonUriList;

	private JList UriList;

	private JButton RemoveButtonSchemata;

	private JButton AddButtonSchemata;

	private JList LevelNameList;

	private JButton NextButtonAL;

	private JTextField FractionField;

	private JTextField BatchSizeField;

	private JButton BrowseButtonCorpusBIN;

	private JLabel FractionLabel;

	private JLabel BatchSizeLabe;

	private JLabel CorpusBinLabel;

	private JRadioButton ALRadioB;

	private JRadioButton DefaultRadioB;

	private JPanel BaseDataPanel;

	private JTextField NameField;

	private JButton NextButtonProject;

	private JLabel ModeLabel;

	private JLabel NameLabel;

	private JLabel UserLabel;

	private JFileChooser CorpusBinFileChooser;

	private JFileChooser CorpusPosFileChooser;

	private JFileChooser PrioFileChooser;

	private JFileChooser CorpusTokFileChooser;

	private JFileChooser StyleFileChooser;

	private JFileChooser TagsFileChooser;

	private File currentDirectory;

	// variables for creating projects
	private AnnoMasterTooltips amTooltips;

	private Object[] userCollection = null;

	private String projectName;

	private String batchSize;

	private String fraction;

	private String committee;

	private double trainingProp;

	private File corpus_BIN = null;

	private File prio_List = null;

	private File corpus_TOK = null;

	private File corpus_POS = null;

	private File tags_File = null;

	private Vector<MmaxData> schemataVector = new Vector<MmaxData>();

	private Vector<Markable> markableVector = new Vector<Markable>();

	private ArrayList<BaseDataFull> baseDataVector = new ArrayList<BaseDataFull>();

	private boolean isAL = false;

	private boolean mainLvlIsAlreadySet = false;

	private String description;

	public CreateProject(SQLDatabaseManager sdm) {
		super(sdm);
		try {
			initGUI(sdm);
		} catch (Exception e) {
			GUIMessages.exceptionMessage(e);
			logger.log(Level.SEVERE, "", e);
		}
	}

	private void initGUI(final SQLDatabaseManager sdm) {
		amTooltips = AnnoMasterTooltips.instantiateAnnoMasterTooltips();

		this.setClosable(true);
		currentDirectory = null;
		XMLFileFilter filter = new XMLFileFilter();
		CorpusBinFileChooser = new JFileChooser();
		CorpusPosFileChooser = new JFileChooser();
		CorpusTokFileChooser = new JFileChooser();
		PrioFileChooser = new JFileChooser();
		StyleFileChooser = new JFileChooser();
		TagsFileChooser = new JFileChooser();
		CorpusPosFileChooser
				.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		CorpusTokFileChooser
				.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		StyleFileChooser.setFileFilter(filter);
		this.setPreferredSize(new java.awt.Dimension(700, 530));
		this.setBounds(25, 25, 700, 530);
		setVisible(true);
		this.setTitle("Create new annotation project");
		this.addInternalFrameListener(new InternalFrameAdapter() {
			public void internalFrameClosing(InternalFrameEvent e) {
				int option = JOptionPane
						.showConfirmDialog(
								null,
								"All entries for new project will be lost when closing this frame. Really quit?",
								"Exit", JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					CreateProject.this.dispose();
				}
			}
		});
		{
			Projekt = new JTabbedPane();
			getContentPane().add(Projekt, BorderLayout.CENTER);
			Projekt.setPreferredSize(new java.awt.Dimension(552, 377));
			{
				ProjectPanel = new JPanel();
				Projekt.addTab("general settings", null, ProjectPanel, null);
				AnchorLayout ProjectPanelLayout = new AnchorLayout();
				ProjectPanel.setLayout(ProjectPanelLayout);
				ProjectPanel.setPreferredSize(new java.awt.Dimension(686, 490));
				{
					PrioButton = new JButton();
					ProjectPanel.add(PrioButton, new AnchorConstraint(430, 767,
							489, 624, AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					PrioButton.setText("Browse");
					PrioButton.setPreferredSize(new java.awt.Dimension(98, 28));
					PrioButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							try {
								PrioFileChooser
										.setCurrentDirectory(currentDirectory);
								int returnVal = PrioFileChooser
										.showOpenDialog(CreateProject.this);

								if (returnVal == JFileChooser.APPROVE_OPTION) {
									File filePrio = PrioFileChooser
											.getSelectedFile();
									PriolistField.setText(filePrio.getName());
									prio_List = filePrio;
									currentDirectory = filePrio.getParentFile();
								} else {
									PriolistField.setText("");
									prio_List = null;
								}
							} catch (RuntimeException e) {
								GUIMessages.exceptionMessage(e);
								logger.log(Level.SEVERE, "", e);
							}
						}
					});
				}
				{
					PriolistField = new JTextField();
					ProjectPanel.add(PriolistField, new AnchorConstraint(430,
							583, 489, 256, AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					PriolistField.setPreferredSize(new java.awt.Dimension(224,
							28));
					PriolistField.setEditable(false);
				}
				{
					PriolistLabel = new JLabel();
					ProjectPanel.add(PriolistLabel, new AnchorConstraint(430,
							205, 489, 113, AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					PriolistLabel.setText("Priolist :");
					PriolistLabel.setPreferredSize(new java.awt.Dimension(63,
							28));
				}
				{
					jScrollPane3 = new JScrollPane();
					ProjectPanel.add(jScrollPane3, new AnchorConstraint(667,
							583, 933, 245, AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					jScrollPane3.setPreferredSize(new java.awt.Dimension(231,
							126));
					jScrollPane3
							.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
					DescriptionArea = new JTextArea();
					jScrollPane3.setViewportView(DescriptionArea);
					DescriptionArea.setText("(no explicit description)");
					DescriptionArea.setLineWrap(true);
				}
				{
					DescriptionLabel = new JLabel();
					ProjectPanel.add(DescriptionLabel, new AnchorConstraint(
							652, 225, 711, 113, AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					DescriptionLabel.setText("Description:");
					DescriptionLabel.setPreferredSize(new java.awt.Dimension(
							77, 28));
				}
				{
					try {
						// --------------------------------------------------------------------
						ArrayList<User> Users;
						UserScrollPane = new JScrollPane();
						SQLFunctions AM = new SQLFunctions(sqlDatabaseM);
						Users = AM.retrieveAllUsers();
						Object[] data = Users.toArray();
						ListModel jList1Model = new DefaultComboBoxModel(data);
						UserSelectionJList = new JList();
						UserScrollPane.add(UserSelectionJList);
						UserSelectionJList.setModel(jList1Model);
						UserSelectionJList
								.setSelectionModel(new ToggleSelectionModel());
						UserScrollPane.setViewportView(UserSelectionJList);
						UserSelectionJList
								.addListSelectionListener(new ListSelectionListener() {
									public void valueChanged(
											ListSelectionEvent evt) {
										if (!UserSelectionJList
												.isSelectionEmpty()) {
											UserSelectionJList
													.setToolTipText(UserSelectionJList
															.getSelectedValue()
															.toString());
										}
									}
								});

						ProjectPanel.add(UserScrollPane, new AnchorConstraint(
								60, 583, 282, 235, AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
						UserScrollPane.setPreferredSize(new java.awt.Dimension(
								238, 105));
						UserScrollPane.setAutoscrolls(true);
						UserScrollPane.getVerticalScrollBar().setAutoscrolls(
								true);
						UserScrollPane.setFocusTraversalKeysEnabled(false);
						UserScrollPane.getViewport()
								.setView(UserSelectionJList);
						UserScrollPane.getHorizontalScrollBar()
								.setVisible(true);
						UserScrollPane
								.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
					} catch (RuntimeException e) {
						GUIMessages.exceptionMessage(e);
						logger.log(Level.SEVERE, "", e);
					}
				}

				{
					ALRadioB = new JRadioButton();
					ProjectPanel.add(ALRadioB, new AnchorConstraint(507, 607,
							588, 491, AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					ALRadioB.setText("AL");
					ALRadioB.setPreferredSize(new java.awt.Dimension(63, 28));
					ALRadioB.addChangeListener(new ChangeListener() {
						public void stateChanged(ChangeEvent evt) {
							if (ALRadioB.isSelected()) {
								DefaultRadioB.setSelected(false);
							} else {
								DefaultRadioB.setSelected(true);
							}
						}
					});
				}
				{
					DefaultRadioB = new JRadioButton();
					ProjectPanel.add(DefaultRadioB, new AnchorConstraint(507,
							440, 588, 285, AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					DefaultRadioB.setText("default");
					DefaultRadioB.setPreferredSize(new java.awt.Dimension(84,
							28));
					DefaultRadioB.setSelected(true);
					DefaultRadioB.addChangeListener(new ChangeListener() {
						public void stateChanged(ChangeEvent evt) {
							if (DefaultRadioB.isSelected()) {
								ALRadioB.setSelected(false);
							} else {
								ALRadioB.setSelected(true);
							}
						}
					});
					DefaultRadioB.setToolTipText(amTooltips
							.getSetting("DefaultRadioBR"));
				}
				{
					NameField = new JTextField();
					ProjectPanel.add(NameField, new AnchorConstraint(311, 583,
							371, 256, AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					NameField.setPreferredSize(new java.awt.Dimension(224, 28));
					// NameField;
				}
				{
					NextButtonProject = new JButton();
					ProjectPanel.add(NextButtonProject, new AnchorConstraint(
							813, 871, 907, 669, AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					NextButtonProject.setText("Next");
					NextButtonProject.setPreferredSize(new java.awt.Dimension(
							133, 42));
					NextButtonProject.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							try {
								if (UserSelectionJList.isSelectionEmpty()
										|| NameField.getText().equals("")
										|| PriolistField.getText().equals("")) {
									GUIMessages
											.warnMessage("data missing! please fill all fields.");
								} else {
									// -----------------THE
									// COLLECTOR---------------------
									userCollection = UserSelectionJList
											.getSelectedValues();
									projectName = NameField.getText();
									description = DescriptionArea.getText();
									// -----------------THE COLLECTOR leaves for
									// supper---
									if (ALRadioB.isSelected()) {
										isAL = true;
										Projekt.setEnabledAt(1, true);
										Projekt.setSelectedIndex(1);
									}
									if (DefaultRadioB.isSelected()) {
										isAL = false;
										Projekt.setEnabledAt(2, true);
										Projekt.setSelectedIndex(2);
									}
									Projekt.setEnabledAt(0, false);
								}
							} catch (RuntimeException e) {
								GUIMessages.exceptionMessage(e);
								logger.log(Level.SEVERE, "", e);
							}

						}
					});
				}
				{
					ModeLabel = new JLabel();
					ProjectPanel.add(ModeLabel, new AnchorConstraint(504, 225,
							593, 113, AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					ModeLabel.setText("Mode :");
					ModeLabel.setPreferredSize(new java.awt.Dimension(77, 42));
					ModeLabel
							.setToolTipText(amTooltips.getSetting("ModeLabel"));
				}
				{
					NameLabel = new JLabel();
					ProjectPanel.add(NameLabel, new AnchorConstraint(297, 225,
							385, 113, AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					NameLabel.setText("Name :");
					NameLabel.setPreferredSize(new java.awt.Dimension(77, 42));
					NameLabel
							.setToolTipText(amTooltips.getSetting("NameLabel"));
				}
				{
					UserLabel = new JLabel();
					ProjectPanel.add(UserLabel, new AnchorConstraint(81, 231,
							160, 116, AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					UserLabel.setText("User :");
					UserLabel.setPreferredSize(new java.awt.Dimension(63, 28));
					UserLabel
							.setToolTipText(amTooltips.getSetting("UserLabel"));
				}
			}
			{
				ALPanel = new JPanel();
				Projekt.addTab("AL settings", null, ALPanel, null);
				ALPanel.setLayout(null);
				ALPanel.setBounds(-63, -28, 547, 351);
				ALPanel.setPreferredSize(new java.awt.Dimension(658, 441));
				{
					ALTagsFileLabel = new JLabel();
					ALPanel.add(ALTagsFileLabel);
					ALTagsFileLabel.setText("labels:");
					ALTagsFileLabel.setPreferredSize(new java.awt.Dimension(84,
							28));
					ALTagsFileLabel.setBounds(42, 231, 84, 28);
				}
				{
					ALTagsFileField = new JTextField();
					ALPanel.add(ALTagsFileField);
					ALTagsFileField.setPreferredSize(new java.awt.Dimension(
							203, 28));
					ALTagsFileField.setEditable(false);
					ALTagsFileField.setBounds(132, 231, 204, 28);
				}
				{
					CorpusTokField = new JTextField();
					ALPanel.add(CorpusTokField);
					CorpusTokField.setPreferredSize(new java.awt.Dimension(203,
							28));
					CorpusTokField.setEditable(false);
					CorpusTokField.setBounds(132, 161, 204, 28);
				}
				{
					CorpusPosLabel = new JLabel();
					ALPanel.add(CorpusPosLabel);
					CorpusPosLabel.setText("Corpus POS:");
					CorpusPosLabel.setPreferredSize(new java.awt.Dimension(84,
							28));
					CorpusPosLabel.setBounds(42, 196, 84, 28);
				}
				{
					CorpusTokLabel = new JLabel();
					ALPanel.add(CorpusTokLabel);
					CorpusTokLabel.setText("Corpus TOK:");
					CorpusTokLabel.setPreferredSize(new java.awt.Dimension(84,
							28));
					CorpusTokLabel.setBounds(42, 161, 84, 28);
				}
				{
					PreviousButtonAL = new JButton();
					ALPanel.add(PreviousButtonAL);
					PreviousButtonAL.setText("Previous");
					PreviousButtonAL.setPreferredSize(new java.awt.Dimension(
							112, 35));
					PreviousButtonAL.setBounds(380, 406, 120, 39);
					PreviousButtonAL.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							Projekt.setEnabledAt(0, true);
							Projekt.setSelectedComponent(ProjectPanel);
							Projekt.setEnabledAt(1, false);
						}
					});
				}
				{
					NextButtonAL = new JButton();
					ALPanel.add(NextButtonAL);
					NextButtonAL.setText("Next");
					NextButtonAL.setPreferredSize(new java.awt.Dimension(133,
							35));
					NextButtonAL.setBounds(508, 406, 142, 39);
					NextButtonAL.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							try {
								if (BatchSizeField.getText().equals("")
										|| FractionField.getText().equals("")
										|| BINField.getText().equals("")
										|| CorpusTokField.getText().equals("")
										|| CPosFileField.getText().equals("")
										|| ALTagsFileField.getText().equals("")
										|| TrainingProportionField.getText()
												.equals("")
										|| CommitteeField.getText().equals("")) {
									GUIMessages.warnMessage("Missing data!");
								} else {
									// ---------THE COLLECTOR
									// RETURNS-----------------
									batchSize = BatchSizeField.getText();
									fraction = FractionField.getText();
									trainingProp = (new Double(
											TrainingProportionField.getText()))
											.doubleValue();
									committee = CommitteeField.getText();
									// File objects are collected on invocation
									// of
									// Browse-Events, see above
									// ---------ONLY TO LEAVE ONCE
									// AGAIN--------------
									Projekt.setEnabledAt(2, true);
									Projekt.setSelectedIndex(2);
									Projekt.setEnabledAt(1, false);
								}
							} catch (RuntimeException e) {
								GUIMessages.exceptionMessage(e);
								logger.log(Level.SEVERE, "", e);
							}

						}
					});
				}
				{
					FractionField = new JTextField();
					ALPanel.add(FractionField);
					FractionField.setPreferredSize(new java.awt.Dimension(196,
							28));
					FractionField.setBounds(130, 44, 204, 30);
					FractionField.setInputVerifier(new InputVerifier() {
						public boolean verify(JComponent aComponent) {
							// 0.0 < textfield.input <= 1.0
							return (java.util.regex.Pattern.matches(
									"((0\\.(\\d){0,9})[1-9])|(1\\.0{1,10})",
									FractionField.getText()));
						}

						public boolean shouldYieldFocus(JComponent aComponent) {
							boolean isValid = super
									.shouldYieldFocus(aComponent);
							if (!isValid) {
								FractionField.setText("");
							}
							return true;
						}
					});
				}
				{
					BatchSizeField = new JTextField();
					ALPanel.add(BatchSizeField);
					BatchSizeField.setPreferredSize(new java.awt.Dimension(196,
							28));
					BatchSizeField.setBounds(130, 15, 204, 29);
					BatchSizeField.setInputVerifier(new InputVerifier() {
						public boolean verify(JComponent aComponent) {
							return (java.util.regex.Pattern.matches(
									"(\\d){1,9}", BatchSizeField.getText()));
						}

						public boolean shouldYieldFocus(JComponent aComponent) {
							boolean isValid = super
									.shouldYieldFocus(aComponent);
							if (!isValid) {
								BatchSizeField.setText("");
							}
							return true;
						}
					});
				}
				{
					BrowseButtonCorpusBIN = new JButton();
					ALPanel.add(BrowseButtonCorpusBIN);
					BrowseButtonCorpusBIN.setText("Browse");
					BrowseButtonCorpusBIN
							.setPreferredSize(new java.awt.Dimension(119, 28));
					BrowseButtonCorpusBIN.setBounds(371, 126, 119, 28);
					BrowseButtonCorpusBIN
							.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									try {
										CorpusBinFileChooser
												.setCurrentDirectory(currentDirectory);
										int returnVal = CorpusBinFileChooser
												.showOpenDialog(CreateProject.this);

										if (returnVal == JFileChooser.APPROVE_OPTION) {
											File fileCBin = CorpusBinFileChooser
													.getSelectedFile();
											BINField
													.setText(fileCBin.getName());
											corpus_BIN = fileCBin;
											currentDirectory = fileCBin
													.getParentFile();
										} else {
											BINField.setText("");
											corpus_BIN = null;
										}
									} catch (RuntimeException e) {
										GUIMessages.exceptionMessage(e);
										logger.log(Level.SEVERE, "", e);
									}

								}

							});
				}
				{
					BINField = new JTextField();
					ALPanel.add(BINField);
					BINField.setPreferredSize(new java.awt.Dimension(196, 28));
					BINField.setEditable(false);
					BINField.setBounds(130, 125, 204, 30);
				}
				{
					FractionLabel = new JLabel();
					ALPanel.add(FractionLabel);
					FractionLabel.setText("Fraction :");
					FractionLabel.setPreferredSize(new java.awt.Dimension(77,
							35));
					FractionLabel.setBounds(43, 44, 80, 37);
				}
				{
					BatchSizeLabe = new JLabel();
					ALPanel.add(BatchSizeLabe);
					BatchSizeLabe.setText("Batch Size :");
					BatchSizeLabe.setPreferredSize(new java.awt.Dimension(77,
							35));
					BatchSizeLabe.setBounds(43, 15, 80, 37);
				}
				{
					CorpusBinLabel = new JLabel();
					ALPanel.add(CorpusBinLabel);
					CorpusBinLabel.setText("Corpus BIN:");
					CorpusBinLabel.setPreferredSize(new java.awt.Dimension(112,
							35));
					CorpusBinLabel.setBounds(43, 125, 117, 37);
				}
				{
					BrowseButtonCorpusTOK = new JButton();
					ALPanel.add(BrowseButtonCorpusTOK);
					BrowseButtonCorpusTOK.setText("Browse");
					BrowseButtonCorpusTOK
							.setPreferredSize(new java.awt.Dimension(119, 28));
					BrowseButtonCorpusTOK.setBounds(371, 161, 119, 28);
					BrowseButtonCorpusTOK
							.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									try {
										CorpusTokFileChooser
												.setCurrentDirectory(currentDirectory);
										int returnVal = CorpusTokFileChooser
												.showOpenDialog(CreateProject.this);
										if (returnVal == JFileChooser.APPROVE_OPTION) {
											File fileCTok = CorpusTokFileChooser
													.getSelectedFile();
											CorpusTokField.setText(fileCTok
													.getName());
											corpus_TOK = fileCTok;
											currentDirectory = fileCTok
													.getParentFile();
										} else {
											CorpusTokField.setText("");
											corpus_TOK = null;
										}
									} catch (RuntimeException e) {
										GUIMessages.exceptionMessage(e);
										logger.log(Level.SEVERE, "", e);
									}

								}
							});
				}
				{
					BrowseButtonALTagsFile = new JButton();
					ALPanel.add(BrowseButtonALTagsFile);
					BrowseButtonALTagsFile.setText("Browse");
					BrowseButtonALTagsFile
							.setPreferredSize(new java.awt.Dimension(119, 28));
					BrowseButtonALTagsFile.setBounds(371, 231, 119, 28);
					BrowseButtonALTagsFile
							.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									try {
										TagsFileChooser
												.setCurrentDirectory(currentDirectory);
										int returnVal = TagsFileChooser
												.showOpenDialog(CreateProject.this);
										if (returnVal == JFileChooser.APPROVE_OPTION) {
											File fileTags = TagsFileChooser
													.getSelectedFile();
											ALTagsFileField.setText(fileTags
													.getName());
											tags_File = fileTags;
											currentDirectory = fileTags
													.getParentFile();
										} else {
											ALTagsFileField.setText("");
											tags_File = null;
										}
									} catch (RuntimeException e) {
										GUIMessages.exceptionMessage(e);
										logger.log(Level.SEVERE, "", e);
									}

								}
							});
				}
				{
					CPosFileField = new JTextField();
					ALPanel.add(CPosFileField);
					CPosFileField.setEditable(false);
					CPosFileField.setPreferredSize(new java.awt.Dimension(203,
							28));
					CPosFileField.setBounds(132, 196, 204, 28);
				}
				{
					BrowseButtonCorpusPOS = new JButton();
					ALPanel.add(BrowseButtonCorpusPOS);
					BrowseButtonCorpusPOS.setText("Browse");
					BrowseButtonCorpusPOS
							.setPreferredSize(new java.awt.Dimension(119, 28));
					BrowseButtonCorpusPOS.setBounds(371, 196, 119, 28);
					BrowseButtonCorpusPOS
							.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									try {
										CorpusPosFileChooser
												.setCurrentDirectory(currentDirectory);
										int returnVal = CorpusPosFileChooser
												.showOpenDialog(CreateProject.this);
										if (returnVal == JFileChooser.APPROVE_OPTION) {
											File fileCPos = CorpusPosFileChooser
													.getSelectedFile();
											CPosFileField.setText(fileCPos
													.getName());
											corpus_POS = fileCPos;
											currentDirectory = fileCPos
													.getParentFile();
										} else {
											CPosFileField.setText("");
											corpus_POS = null;
										}
									} catch (RuntimeException e) {
										GUIMessages.exceptionMessage(e);
										logger.log(Level.SEVERE, "", e);
									}

								}
							});
				}
				{
					CommitteeLabel = new JLabel();
					ALPanel.add(CommitteeLabel);
					CommitteeLabel.setText("committee:");
					CommitteeLabel.setBounds(35, 322, 119, 28);
				}
				{
					TrainingProportionLabel = new JLabel();
					ALPanel.add(TrainingProportionLabel);
					TrainingProportionLabel.setText("training proportion:");
					TrainingProportionLabel.setBounds(35, 357, 133, 28);
				}
				{
					CommitteeField = new JTextField();
					ALPanel.add(CommitteeField);
					CommitteeField.setBounds(168, 322, 203, 28);
					CommitteeField.setText("ME,ME,ME");
					CommitteeField.setInputVerifier(new InputVerifier() {
						public boolean verify(JComponent aComponent) {
							boolean valid = false;
							try {
								valid = ALChecks
										.isValidCommittee(CommitteeField
												.getText());
							} catch (RuntimeException e) {
								GUIMessages.exceptionMessage(e);
								logger.log(Level.SEVERE, "", e);
							}
							return valid;
						}

						public boolean shouldYieldFocus(JComponent aComponent) {
							boolean isValid = super
									.shouldYieldFocus(aComponent);
							if (!isValid) {
								CommitteeField.setText("");
							}
							return true;
						}
					});
				}
				{
					TrainingProportionField = new JTextField();
					TrainingProportionField.setText("0.65");
					ALPanel.add(TrainingProportionField);
					TrainingProportionField.setBounds(168, 357, 203, 28);
					TrainingProportionField
							.setInputVerifier(new InputVerifier() {
								public boolean verify(JComponent aComponent) {
									if (TrainingProportionField.getText()
											.equals("")) {
										return false;
									}
									if (!java.util.regex.Pattern
											.matches(
													"((0\\.(\\d){0,8})[1-9])|(1\\.0{1,10})",
													TrainingProportionField
															.getText())) {
										return false;
									}

									return (ALChecks.isValidTrainProportion(
											CommitteeField.getText(),
											new Double(TrainingProportionField
													.getText()).doubleValue()));
								}

								public boolean shouldYieldFocus(
										JComponent aComponent) {
									boolean isValid = super
											.shouldYieldFocus(aComponent);
									if (!isValid) {
										TrainingProportionField.setText("");
									}
									return true;
								}
							});
				}
			}
			{
				SchemataPanel = new JPanel();
				Projekt
						.addTab("annotation schemata", null, SchemataPanel,
								null);
				SchemataPanel.setLayout(null);
				SchemataPanel
						.setPreferredSize(new java.awt.Dimension(658, 448));
				{
					jScrollPane1 = new JScrollPane();
					SchemataPanel.add(jScrollPane1);
					jScrollPane1.setPreferredSize(new java.awt.Dimension(224,
							301));
					jScrollPane1.setBounds(29, 37, 233, 318);
					{
						ListModel LevelNameListModel = new DefaultComboBoxModel();
						LevelNameList = new JList();
						jScrollPane1.setViewportView(LevelNameList);
						LevelNameList.setModel(LevelNameListModel);
					}
				}
				{
					PreviousButtonSchemata = new JButton();
					SchemataPanel.add(PreviousButtonSchemata);
					PreviousButtonSchemata.setText("Previous");
					PreviousButtonSchemata
							.setPreferredSize(new java.awt.Dimension(105, 35));
					PreviousButtonSchemata.setBounds(271, 368, 132, 47);
					PreviousButtonSchemata
							.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									if (isAL) {
										Projekt.setEnabledAt(1, true);
										Projekt.setSelectedComponent(ALPanel);
										Projekt.setEnabledAt(2, false);
									} else {
										Projekt.setEnabledAt(0, true);
										Projekt
												.setSelectedComponent(ProjectPanel);
										Projekt.setEnabledAt(2, false);
									}
								}
							});
				}
				{
					NextButtonSchemata = new JButton();
					SchemataPanel.add(NextButtonSchemata);
					NextButtonSchemata.setText("Next");
					NextButtonSchemata.setPreferredSize(new java.awt.Dimension(
							133, 42));
					NextButtonSchemata.setBounds(429, 369, 138, 45);
					NextButtonSchemata.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							if (!mainLvlIsAlreadySet
									|| LevelNameList.getModel().getSize() == 0)
								JOptionPane.showMessageDialog(null,
										"Missing data!", "alert!",
										JOptionPane.ERROR_MESSAGE);
							else {
								// COLLECTING DATA HERE!!!!!!!!!!!!!!!!!!!!!!!!
								// COLLECTOR
								Projekt.setEnabledAt(3, true);
								Projekt.setSelectedIndex(3);
								Projekt.setEnabledAt(2, false);
								UriList.setListData(baseDataVector.toArray());
								AddMultipleButton.setEnabled(!isAL);
							}
						}
					});
				}
				{
					RemoveButtonSchemata = new JButton();
					SchemataPanel.add(RemoveButtonSchemata);
					RemoveButtonSchemata.setText("Remove");
					RemoveButtonSchemata
							.setPreferredSize(new java.awt.Dimension(77, 28));
					RemoveButtonSchemata.setBounds(324, 113, 97, 38);
					RemoveButtonSchemata
							.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									if (LevelNameList.isSelectionEmpty()) {
									} else {
										Object md = LevelNameList
												.getSelectedValue();
										schemataVector.remove(md);
										if (mainLvlIsAlreadySet
												&& ((MmaxData) md)
														.isMainLevel())
											mainLvlIsAlreadySet = false;
										LevelNameList
												.setListData(schemataVector);
										LevelNameList.repaint();
										AddButtonSchemata.setEnabled(true);
										baseDataVector.clear();
										baseDataVector.trimToSize();
										markableVector.removeAllElements();
										AddButtonUriList.setEnabled(true);
									}
								}
							});
				}
				{
					AddButtonSchemata = new JButton();
					SchemataPanel.add(AddButtonSchemata);
					AddButtonSchemata.setText("Add");
					AddButtonSchemata.setPreferredSize(new java.awt.Dimension(
							77, 28));
					AddButtonSchemata.setBounds(324, 47, 97, 38);
					AddButtonSchemata.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							try {
								Vector<String> lvlNamesVector = new Vector<String>();
								for (MmaxData m : schemataVector) {
									lvlNamesVector.add(m.getLevelName());
								}
								MmaxData m = AddSchemataDialog.getValue(
										mainLvlIsAlreadySet, lvlNamesVector,
										currentDirectory, sqlDatabaseM);
								currentDirectory = AddSchemataDialog
										.getCurrentPath();
								if (m != null) {
									schemataVector.add(m);
									mainLvlIsAlreadySet |= m.isMainLevel();
									LevelNameList.setListData(schemataVector
											.toArray());
									LevelNameList.repaint();
									baseDataVector.clear();
									baseDataVector.trimToSize();
									markableVector.removeAllElements();
								}
							} catch (RuntimeException e) {
								GUIMessages.exceptionMessage(e);
								logger.log(Level.SEVERE, "", e);
							}
						}
					});
				}
			}
			{
				BaseDataPanel = new JPanel();
				Projekt.addTab("documents", null, BaseDataPanel, null);
				BaseDataPanel.setLayout(null);
				BaseDataPanel.setVisible(false);
				{
					AddMultipleButton = new JButton();
					BaseDataPanel.add(AddMultipleButton);
					AddMultipleButton.setText("Add Multiple");
					AddMultipleButton.setPreferredSize(new java.awt.Dimension(
							105, 35));
					AddMultipleButton.setBounds(434, 42, 105, 35);
					AddMultipleButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							try {
								AddMultipleBaseDataDialog dialog = new AddMultipleBaseDataDialog(
										schemataVector, sqlDatabaseM);
								dialog.setCurrentPath(currentDirectory);
								dialog.setModal(true);
								dialog.setVisible(true);
								if (!dialog.getBaseDataVector().isEmpty()) {
									baseDataVector.addAll(dialog
											.getBaseDataVector());
									markableVector.addAll(dialog
											.getMarkableVector());
									UriList.setListData(baseDataVector
											.toArray());
									UriList.repaint();
									currentDirectory = dialog.getCurrentPath();
								}
							} catch (RuntimeException e) {
								GUIMessages.exceptionMessage(e);
								logger.log(Level.SEVERE, "", e);
							}
						}
					});
				}
				{
					jScrollPane2 = new JScrollPane();
					BaseDataPanel.add(jScrollPane2);
					jScrollPane2.setPreferredSize(new java.awt.Dimension(224,
							357));
					jScrollPane2.setBounds(58, 37, 233, 377);
					{

						ListModel UriListModel = new DefaultComboBoxModel();
						UriList = new JList();
						jScrollPane2.setViewportView(UriList);
						UriList.setModel(UriListModel);
					}
				}
				{
					SubmitButton = new JButton();
					BaseDataPanel.add(SubmitButton);
					SubmitButton.setText("Submit");
					SubmitButton.setPreferredSize(new java.awt.Dimension(119,
							35));
					SubmitButton.setBounds(516, 384, 124, 37);
					SubmitButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							try {
								if (UriList.getModel().getSize() == 0) {
									JOptionPane.showMessageDialog(null,
											"empty list!", "alert!",
											JOptionPane.ERROR_MESSAGE);
								} else {
									if (isAL) {
										createALProject(projectName,
												((User) userCollection[0])
														.getUserID(),
												description, new SQLFunctions(
														sdm));

									} else {
										createProject(projectName,
												((User) userCollection[0])
														.getUserID(),
												description, new SQLFunctions(
														sdm));
									}
									JOptionPane.showMessageDialog(null,
											"Project submission successful!",
											"Info", JOptionPane.PLAIN_MESSAGE);
									CreateProject.this.dispose();
								}
							} catch (RuntimeException e) {
								GUIMessages.exceptionMessage(e);
								logger.log(Level.SEVERE, "", e);
							}
						}
					});
				}
				{
					PreviousButtonBaseData = new JButton();
					BaseDataPanel.add(PreviousButtonBaseData);
					PreviousButtonBaseData.setText("Previous");
					PreviousButtonBaseData
							.setPreferredSize(new java.awt.Dimension(119, 35));
					PreviousButtonBaseData.setBounds(378, 384, 124, 37);
					PreviousButtonBaseData
							.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									Projekt.setEnabledAt(2, true);
									Projekt.setSelectedComponent(SchemataPanel);
									Projekt.setEnabledAt(3, false);
								}
							});
				}
				{
					RemoveButtonUriList = new JButton();
					BaseDataPanel.add(RemoveButtonUriList);
					RemoveButtonUriList.setText("Remove");
					RemoveButtonUriList
							.setPreferredSize(new java.awt.Dimension(105, 35));
					RemoveButtonUriList.setBounds(329, 112, 105, 35);
					RemoveButtonUriList.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							if (UriList.isSelectionEmpty()) {
							} else {
								BaseDataFull bd = (BaseDataFull) UriList
										.getSelectedValue();
								baseDataVector.remove(bd);
								markableVector.removeAll(bd.getMarkables());
								UriList.setListData(baseDataVector.toArray());
								UriList.repaint();
								if (isAL && (baseDataVector.size() == 0))
									AddButtonUriList.setEnabled(true);
							}
						}
					});
				}
				{
					AddButtonUriList = new JButton();
					BaseDataPanel.add(AddButtonUriList);
					AddButtonUriList.setText("Add");
					AddButtonUriList.setPreferredSize(new java.awt.Dimension(
							105, 35));
					AddButtonUriList.setBounds(329, 42, 105, 35);
					AddButtonUriList.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							AddBaseDataDialog dialog = new AddBaseDataDialog(
									schemataVector, isAL, sqlDatabaseM);
							dialog.setCurrentPath(currentDirectory);
							dialog.setModal(true);
							dialog.setVisible(true);
							BaseDataFull O = dialog.getBaseData();
							markableVector.addAll(dialog.getMarkableVector());
							currentDirectory = dialog.getCurrentPath();
							if (O != null) {
								baseDataVector.add(O);
								UriList.setListData(baseDataVector.toArray());
								UriList.repaint();
								if (isAL && (baseDataVector.size() >= 1)) {
									AddButtonUriList.setEnabled(false);
								}
							}
						}
					});
				}
			}

		}

		Projekt.setEnabledAt(1, false);
		Projekt.setEnabledAt(2, false);
		Projekt.setEnabledAt(3, false);
		// amSettings.setDefaultSettings(this);
		amTooltips.readTooltips(this);
	}

	private void createProject(String name, int id, String desc, SQLFunctions sf) {
		try {
			AnnoFull project = new AnnoFull();
			project.setName(name);
			project.setUserID(id);
			project.setPriolistFile(prio_List.getAbsoluteFile());
			schemataVector.trimToSize();
			baseDataVector.trimToSize();

			for (BaseDataFull bd : baseDataVector) {
				bd.setStatus("raw");
			}

			for (Markable m : markableVector) {
				m.setCreationDate(new Timestamp(System.currentTimeMillis()));
			}

			project.setMmaxdata(new ArrayList<MmaxData>(schemataVector));
			project
					.setBaseDataFull(new ArrayList<BaseDataFull>(baseDataVector));
			project.setDescription(desc);
			project.setCreationDate(new Date(System.currentTimeMillis()));
			project.setMode(AnnoCore.DEFAULTPROJECT);
			project.write(sf);
		} catch (RuntimeException e) {
			GUIMessages.exceptionMessage(e);
			logger.log(Level.SEVERE, "", e);
		}
	}

	private void createALProject(String name, int id, String desc,
			SQLFunctions sf) {
		try {
			AnnoFull project = new AnnoFull();
			project.setName(name);
			project.setUserID(id);
			project.setDescription(desc);
			project.setMode(AnnoCore.ALPROJECT);
			project.setCreationDate(new Date(System.currentTimeMillis()));
			project.setStatus("unlocked");
			project.setAlBatchsize((new Integer(batchSize)).intValue());
			project.setAlCorpusFraction((new Float(fraction)).floatValue());
			project.setAlIterations(0);

			// ----
			// eingef??gt: KT, 29.1.2007, muss irgendwann in
			// createproject frame eingebaut und dann von dort ausgelesen
			// werden!
			project.setAlCommittee(committee);
			project.setAlTrainprop(trainingProp);
			// ----

			project.setAlCorpusBin(corpus_BIN.getAbsolutePath());
			project.setAlCorpusPos(corpus_POS.getAbsolutePath());
			project.setAlCorpusTok(corpus_TOK.getAbsolutePath());
			project.setPriolistFile(prio_List.getAbsoluteFile());
			project.setTagsFile(tags_File.getAbsoluteFile());
			// auskommentiert von KT (19.07.2006), damit t2_file nicht
			// geschrieben wird
			// project.set_t2_file(t2_File.getAbsoluteFile());

			for (BaseDataFull bd : baseDataVector) {
				bd.setStatus("raw");
			}

			for (Markable m : markableVector) {
				m.setCreationDate(new Timestamp(System.currentTimeMillis()));
			}

			project.setMmaxdata(new ArrayList<MmaxData>(schemataVector));
			project
					.setBaseDataFull(new ArrayList<BaseDataFull>(baseDataVector));
			project.write(sf);
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
			CreateProject inst = null;
			inst = new CreateProject(new SQLDatabaseManager());
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
