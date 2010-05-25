/** 
 * ShowProjectFrame.java
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

 **/

package de.julielab.annoenv.ui.annomaster;

import java.awt.BorderLayout;
import java.awt.Cursor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextPane;

import javax.swing.ListModel;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;

import de.julielab.annoenv.al.ALChecks;
import de.julielab.annoenv.conversions.ConvertIOB2T2;
import de.julielab.annoenv.conversions.DownloadBasedataMarkables;
import de.julielab.annoenv.conversions.DownloadMMaxData;
import de.julielab.annoenv.conversions.DownloadSchemataCustom;
import de.julielab.annoenv.conversions.ExportBaseDataComments;
import de.julielab.annoenv.conversions.ExportIOB;
import de.julielab.annoenv.conversions.ExportMarkables;
import de.julielab.annoenv.db.annodata.AnnoFull;
import de.julielab.annoenv.db.annodata.AnnoLight;
import de.julielab.annoenv.db.annodata.BaseDataLight;
import de.julielab.annoenv.db.annodata.MmaxData;
import de.julielab.annoenv.db.annodata.User;
import de.julielab.annoenv.db.sql.SQLDatabaseManager;
import de.julielab.annoenv.db.sql.SQLFunctions;

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
 * Platform for displaying and managing projects and providing the option to
 * modify some project properties
 */
public class ShowProjectFrame extends InternalAnnoMasterFrame implements
		ActionListener {

	private static Logger logger = AnnoEnvLogger
			.getLogger("de.julielab.annoenv.ui.annomaster.ShowProjectFrame");

	private JPanel jPanel1;

	private JMenu StatsMenu;

	private JMenu DeploymentMenu;

	private JMenu EditMenu;

	private JMenuItem OverallDisagreementStatsItem;

	private JMenuItem AnnoTimeItem;

	private JMenuItem DeleteMenuItem;

	private JMenuItem ExportBDCommentsMenuItem;

	private JMenuItem DownloadBDMarkables;

	private JMenuItem DownloadSchemataCustomizationItem;

	private JMenuItem ChangeBaseDataStatusMenuItem;

	private JMenuItem DownloadMmaxItem;

	private JButton EditButton;

	private JSeparator jSeparator2;

	private JSeparator jSeparator1;

	private JMenuItem UserMenuItem;

	private JMenuItem CommitteeItem;

	private JMenuItem TrainingProportionItem;

	private JMenuItem SelectiveDisagreementItem;

	private JMenu DisagreementMenu;

	private JMenuItem ShowSelectionTimeMenutItem;

	private JMenuItem CopyProjectMenuItem;

	private JMenu CopyProjectMenu;

	private JMenuItem ExportMarkablesItem;

	private JMenuItem ExportAnnotationItem;

	private JMenuItem ShowCumulatedEntitiesItem;

	private JScrollPane jScrollPane2;

	private JMenuItem FractionItem;

	private JMenuItem BatchSizeItem;

	private JMenuItem ExportItem;

	private JMenuItem DownloadItem;

	private JMenuItem ResetMenuItem;

	private JPopupMenu jPopupMenu1;

	private JScrollPane jScrollPane1;

	private JList ProjectList;

	final private JTextPane InfoPanel = new JTextPane();

	private JComboBox UserBox;

	final private String SELECTUSERSELECTIONLABEL = "-- select user --";

	public ShowProjectFrame(SQLDatabaseManager sdm) {
		super(sdm);
		initGUI(sdm);
	}

	private void initGUI(final SQLDatabaseManager sdm) {
		try {
			this.setPreferredSize(new java.awt.Dimension(704, 470));
			this.setBounds(25, 25, 704, 470);
			setVisible(true);
			this.setClosable(true);
			this.setTitle("Projects");
			{
				jPanel1 = new JPanel();
				final AnchorLayout jPanel1Layout = new AnchorLayout();
				jPanel1.setLayout(jPanel1Layout);
				getContentPane().add(jPanel1, BorderLayout.CENTER);
				jPanel1.setPreferredSize(new java.awt.Dimension(490, 336));
				{
					EditButton = new JButton();
					jPanel1.add(EditButton, new AnchorConstraint(890, 381, 953,
							121, AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					EditButton.setText("modify project");
					EditButton
							.setPreferredSize(new java.awt.Dimension(182, 28));
					EditButton.setEnabled(false);
					EditButton.addActionListener(new ActionListener() {
						public void actionPerformed(final ActionEvent evt) {
							EditProjectFrame modFrame = null;
							final Object o = ProjectList.getSelectedValue();
							if ((o != null) && (o instanceof AnnoLight)) {
								modFrame = new EditProjectFrame((AnnoLight) o,
										ShowProjectFrame.this.sqlDatabaseM);

								final JDesktopPane jdp = ShowProjectFrame.this
										.getDesktopPane();
								jdp.add(modFrame);
								modFrame.setVisible(true);
								modFrame.moveToFront();
							}
						}
					});
				}
				{
					jScrollPane2 = new JScrollPane();
					jPanel1.add(jScrollPane2, new AnchorConstraint(190, 902,
							884, 491, AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					jScrollPane2.setPreferredSize(new java.awt.Dimension(287,
							308));
					jScrollPane2.setViewportView(InfoPanel);
				}
				{
					jScrollPane1 = new JScrollPane();
					jPanel1.add(jScrollPane1, new AnchorConstraint(174, 431,
							884, 70, AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					jScrollPane1.setPreferredSize(new java.awt.Dimension(252,
							315));
					{
						// ArrayList Projects;
						// SQLFunctions AM = new SQLFunctions(sqlDatabaseM);
						// Projects = AM.getProjects();
						// Object[] data = Projects.toArray();
						ListModel jList1Model = new DefaultComboBoxModel(/* data */);
						ProjectList = new JList();
						jScrollPane1.setViewportView(ProjectList);
						ProjectList.setModel(jList1Model);
						{
							jPopupMenu1 = new JPopupMenu();
							{
								CopyProjectMenu = new JMenu();
								jPopupMenu1.add(CopyProjectMenu);
								CopyProjectMenu.setText("Copy Project");
								{
									CopyProjectMenuItem = new JMenuItem();
									CopyProjectMenu.add(CopyProjectMenuItem);
									CopyProjectMenuItem
											.setText("copy selected project");
									CopyProjectMenuItem.addActionListener(this);
								}
							}
							setComponentPopupMenu(ProjectList, jPopupMenu1);
							{
								StatsMenu = new JMenu();
								jPopupMenu1.add(StatsMenu);
								StatsMenu.setText("Statistics");
								{
									AnnoTimeItem = new JMenuItem();
									StatsMenu.add(AnnoTimeItem);
									AnnoTimeItem.setText("annotation times");
									AnnoTimeItem.addActionListener(this);
								}
								{
									ShowSelectionTimeMenutItem = new JMenuItem();
									StatsMenu.add(ShowSelectionTimeMenutItem);
									ShowSelectionTimeMenutItem
											.setText("AL selection time");
									ShowSelectionTimeMenutItem
											.addActionListener(this);
								}
								{
									ShowCumulatedEntitiesItem = new JMenuItem();
									StatsMenu.add(ShowCumulatedEntitiesItem);
									ShowCumulatedEntitiesItem
											.setText("cumulated entities");
									ShowCumulatedEntitiesItem
											.addActionListener(this);
								}
								{
									DisagreementMenu = new JMenu();
									StatsMenu.add(DisagreementMenu);
									DisagreementMenu.setText("disagreement");
									{
										OverallDisagreementStatsItem = new JMenuItem();
										DisagreementMenu
												.add(OverallDisagreementStatsItem);
										OverallDisagreementStatsItem
												.setText("overall disagreement");
										OverallDisagreementStatsItem
												.addActionListener(this);
									}
									{
										SelectiveDisagreementItem = new JMenuItem();
										DisagreementMenu
												.add(SelectiveDisagreementItem);
										SelectiveDisagreementItem
												.setText("disagreement within selected sentences");
										SelectiveDisagreementItem
												.addActionListener(this);
									}
								}
							}
							{
								EditMenu = new JMenu();
								jPopupMenu1.add(EditMenu);
								EditMenu.setText("Edit");
								{
									UserMenuItem = new JMenuItem();
									EditMenu.add(UserMenuItem);
									UserMenuItem.setText("change user");
									UserMenuItem.addActionListener(this);
								}
								{
									jSeparator2 = new JSeparator();
									EditMenu.add(jSeparator2);
								}
								{
									ChangeBaseDataStatusMenuItem = new JMenuItem();
									EditMenu.add(ChangeBaseDataStatusMenuItem);
									ChangeBaseDataStatusMenuItem
											.setText("change basedata status");
									ChangeBaseDataStatusMenuItem
											.addActionListener(this);
								}
								{
									BatchSizeItem = new JMenuItem();
									EditMenu.add(BatchSizeItem);
									BatchSizeItem.setText("change batch size");
									BatchSizeItem.addActionListener(this);
								}
								{
									CommitteeItem = new JMenuItem();
									EditMenu.add(CommitteeItem);
									CommitteeItem.setText("change committee");
									CommitteeItem.addActionListener(this);
								}
								{
									TrainingProportionItem = new JMenuItem();
									EditMenu.add(TrainingProportionItem);
									TrainingProportionItem
											.setText("change training proportion");
									TrainingProportionItem
											.addActionListener(this);
								}
								{
									FractionItem = new JMenuItem();
									EditMenu.add(FractionItem);
									FractionItem
											.setText("change Corpus fraction");
									FractionItem.addActionListener(this);
								}
								{
									jSeparator1 = new JSeparator();
									EditMenu.add(jSeparator1);
								}
								{
									DeleteMenuItem = new JMenuItem();
									EditMenu.add(DeleteMenuItem);
									DeleteMenuItem.setText("delete project");
									DeleteMenuItem.setContentAreaFilled(false);
									DeleteMenuItem.addActionListener(this);
								}
								{
									ResetMenuItem = new JMenuItem();
									EditMenu.add(ResetMenuItem);
									ResetMenuItem.setText("reset project");
									ResetMenuItem.addActionListener(this);
								}
							}
							{
								DeploymentMenu = new JMenu();
								jPopupMenu1.add(DeploymentMenu);
								DeploymentMenu.setText("Deployment");
								{
									DownloadItem = new JMenuItem();
									DeploymentMenu.add(DownloadItem);
									DownloadItem
											.setText("export annotated documents to t2 format");
									DownloadItem.addActionListener(this);
								}
								{
									ExportAnnotationItem = new JMenuItem();
									DeploymentMenu.add(ExportAnnotationItem);
									ExportAnnotationItem
											.setText("convert and export annotations to t2 format");
									ExportAnnotationItem
											.addActionListener(this);
								}
								{
									ExportItem = new JMenuItem();
									DeploymentMenu.add(ExportItem);
									ExportItem
											.setText("convert and export annotated documents to IOB format");
									ExportItem.addActionListener(this);
								}
								{
									ExportMarkablesItem = new JMenuItem();
									DeploymentMenu.add(ExportMarkablesItem);
									ExportMarkablesItem
											.setText("export annotated (main) markables");
									ExportMarkablesItem.addActionListener(this);
								}
								{
									DownloadMmaxItem = new JMenuItem();
									DeploymentMenu.add(DownloadMmaxItem);
									DownloadMmaxItem
											.setText("download MMax Data");
									DownloadMmaxItem.addActionListener(this);
								}
								{
									DownloadBDMarkables = new JMenuItem();
									DeploymentMenu.add(DownloadBDMarkables);
									DownloadBDMarkables
											.setText("download project's basedata and markables");
									DownloadBDMarkables.addActionListener(this);
								}
								{
									DownloadSchemataCustomizationItem = new JMenuItem();
									DeploymentMenu
											.add(DownloadSchemataCustomizationItem);
									DownloadSchemataCustomizationItem
											.setText("download project's schemata and customization files");
									DownloadSchemataCustomizationItem
											.addActionListener(this);
								}
								{
									ExportBDCommentsMenuItem = new JMenuItem();
									DeploymentMenu
											.add(ExportBDCommentsMenuItem);
									ExportBDCommentsMenuItem
											.setText("export basedata descriptions");
									ExportBDCommentsMenuItem
											.addActionListener(this);
								}
							}
						}

						ProjectList
								.addListSelectionListener(new ListSelectionListener() {
									public void valueChanged(
											final ListSelectionEvent evt) {
										try {

											InfoPanel.setText("");
											EditButton.setEnabled(!ProjectList
													.isSelectionEmpty());
											String s = "";

											if (!ProjectList.isSelectionEmpty()) {

												EditButton.setEnabled(true);
												// disable popupmenu
												setMenuForALProject(((AnnoLight) ProjectList
														.getSelectedValue())
														.isAl());
												//

												AnnoLight AP = (AnnoLight) (ProjectList
														.getSelectedValue());

												StringBuffer mdInfo = new StringBuffer();
												ArrayList<MmaxData> allMD = new SQLFunctions(
														ShowProjectFrame.this.sqlDatabaseM)
														.getMmaxDataList(AP
																.getProjectID());
												for (int i = 0; i < allMD
														.size(); i++) {
													mdInfo.append("    * "
															+ allMD.get(i)
																	.toString()
															+ "\n");
												}

												s = "* General project properties: \n"
														+ "-- project ID: "
														+ AP.getProjectID()
														+ "\n"
														+ "-- project name: "
														+ AP.getName()
														+ "\n"
														+ "-- description: "
														+ AP.getDescription()
														+ "\n"
														+ "-- mode: "
														+ AP.getMode()
														+ "\n"
														+ "-- creation date: "
														+ AP.getCreationDate()
																.toString()
														+ "\n"
														+ "-- mmaxdata: \n"
														+ mdInfo.toString();

												if (AP.getMode().equals("AL")) {
													DecimalFormat df = new DecimalFormat(
															"0.00");
													// for AL projects show AL
													// properties
													s += "\n\n* AL properties\n";
													s = s
															+ "-- batch size: "
															+ AP
																	.getAlBatchsize()
															+ "\n";
													s = s
															+ "-- corpus fraction: "
															+ AP
																	.getAlCorpusFraction()
															+ "\n";
													s = s
															+ "-- committee: "
															+ AP
																	.getAlCommittee()
															+ "\n";
													s = s
															+ "-- training proportion: "
															+ df
																	.format(AP
																			.getAlTrainprop())
															+ "\n";
													s = s
															+ "-- iterations done: "
															+ AP
																	.getAlIterations()
															+ "\n";
													s = s + "-- AL status: "
															+ AP.getAlStatus()
															+ "\n";

												} else {
													// for default projects show
													// basedata
													StringBuffer bdInfo = new StringBuffer();
													ArrayList<BaseDataLight> allBD = AP
															.getBaseDataLight();
													for (int i = 0; i < allBD
															.size(); i++) {
														bdInfo
																.append("    * "
																		+ allBD
																				.get(
																						i)
																				.toString()
																		+ "\n");
													}
													s = s + "\n-- basedata: \n"
															+ bdInfo.toString();
												}

											}
											InfoPanel.setText(s);
										} catch (RuntimeException e) {
											GUIMessages.exceptionMessage(e);
											logger.log(Level.SEVERE, "", e);
										}
									}
								});
					}
				}
				{

					InfoPanel.setText("<- Select project.");
					InfoPanel
							.setPreferredSize(new java.awt.Dimension(287, 315));
				}

				// user selection box
				{
					ArrayList<User> Users;
					SQLFunctions AM = new SQLFunctions(sqlDatabaseM);
					Users = AM.retrieveAllUsers();
					Object[] data = Users.toArray();
					Arrays.sort(data);
					UserBox = new JComboBox(data);
					// String allUser = new String(ALLUSERSELECTIONLABEL);
					// UserBox.addItem(allUser);
					String selectUser = SELECTUSERSELECTIONLABEL;
					UserBox.addItem(selectUser);
					UserBox.getModel();
					UserBox.setSelectedItem(selectUser);
					// UserBox.setSelectedItem(allUser);
					jPanel1.add(UserBox, new AnchorConstraint(79, 431, 143, 70,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					UserBox.setPreferredSize(new java.awt.Dimension(252, 28));
					UserBox.addItemListener(new ItemListener() {
						public void itemStateChanged(ItemEvent evt) {
							UserBox.setToolTipText(UserBox.getSelectedItem()
									.toString());
						}
					});
					UserBox.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							try {
								SQLFunctions SF = new SQLFunctions(sdm);
								if (UserBox.getSelectedItem().toString()
										.equals(SELECTUSERSELECTIONLABEL)) {
									String[] sth = { "no selection" };
									ProjectList.setListData(sth);
								} else {
									long time1 = System.currentTimeMillis();
									ProjectList.setListData(SF
											.getLightProjects(
													((User) UserBox
															.getSelectedItem())
															.getUserID())
											.toArray());
									long time2 = System.currentTimeMillis();
									System.out
											.println((time2 - time1) / 1000.0);
								}
							} catch (RuntimeException e) {
								GUIMessages.exceptionMessage(e);
								logger.log(Level.SEVERE, "", e);
							}
						}
					});
				}
			}
		} catch (RuntimeException e) {
			GUIMessages.exceptionMessage(e);
			logger.log(Level.SEVERE, "", e);
		}
	}

	/**
	 * Auto-generated method for setting the popup menu for a component
	 */
	private void setComponentPopupMenu(final java.awt.Component parent,
			final javax.swing.JPopupMenu menu) {
		parent.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent e) {
				if (e.isPopupTrigger())
					menu.show(parent, e.getX(), e.getY());
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
				if (e.isPopupTrigger())
					menu.show(parent, e.getX(), e.getY());
			}
		});
	}

	/**
	 * 
	 * @param b
	 */
	private void setMenuForALProject(boolean b) {
		DisagreementMenu.setEnabled(b);
		BatchSizeItem.setEnabled(b);
		FractionItem.setEnabled(b);
		DownloadItem.setEnabled(b);
		TrainingProportionItem.setEnabled(b);
		CommitteeItem.setEnabled(b);
		ShowSelectionTimeMenutItem.setEnabled(b);
	}

	public void actionPerformed(ActionEvent evt) {
		Object source = evt.getSource();
		String message = "default";
		SQLFunctions sf = new SQLFunctions(sqlDatabaseM);

		if (!ProjectList.isSelectionEmpty()) {
			AnnoLight project = ((AnnoLight) ProjectList.getSelectedValue());
			int projectID = project.getProjectID();

			// menu edit project**********************************
			if (source.equals(DeleteMenuItem) || source.equals(ResetMenuItem)
					|| source.equals(FractionItem)
					|| source.equals(BatchSizeItem)
					|| source.equals(TrainingProportionItem)
					|| source.equals(CommitteeItem)
					|| source.equals(UserMenuItem)
					|| source.equals(ChangeBaseDataStatusMenuItem)) {
				int option = JOptionPane.showConfirmDialog(null,
						"Do you really want to edit this project?",
						"Confirmation", JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.YES_OPTION) {
					try {
						if (source.equals(ResetMenuItem)) {
							sf.resetProject(projectID);
							message = "project has been reseted";
						} else if (source.equals(DeleteMenuItem)) {
							sf.deleteProject(projectID);
							message = "project has been deleted";
						} else {
							String regex = "";
							if (source.equals(BatchSizeItem)) {
								regex = "(\\d){1,3}";
								AnnoLight alproject = ((AnnoLight) ProjectList
										.getSelectedValue());
								int result = (new Integer(
										StringInputDialog
												.showStringInputDialog(
														null,
														"Enter new batchsize!",
														Integer
																.valueOf(
																		alproject
																				.getAlBatchsize())
																.toString(),
														regex))).intValue();
								if (result != -1) {
									// now write the new batch size to DB
									// KT, 14.9.2006

									if (result < 1)
										message = "ERR: batch size must at least be 1. Nothing changed.";
									else if (result > 100)
										message = "ERR: batch size cannot be greater then 100. Nothing changed.";
									else {
										alproject.setAlBatchsize(result);
										alproject.updateProject(sf);
										message = "Batch size set to " + result
												+ ".";
										alproject.setAlBatchsize(result);
									}
								}
							} else if (source.equals(FractionItem)) {
								// some changes made, KT 15.9.06, changed result
								// to type float
								regex = "((0\\.(\\d){0,9})[1-9])|(1\\.0{1,10})";
								AnnoLight alproject = ((AnnoLight) ProjectList
										.getSelectedValue());
								float result = (new Float(StringInputDialog
										.showStringInputDialog(null,
												"Enter new corpus fraction",
												new Float(alproject
														.getAlCorpusFraction())
														.toString(), regex)))
										.floatValue();
								if (result != -1) {
									// now write the new fraction size to DB
									// KT, 14.9.2006
									alproject.setAlCorpusFraction(result);
									alproject.updateProject(sf);
									message = "Corpus fraction set to: "
											+ result;

									if (result > 0.1) {
										message += "\n entered fraction seems very large - maybe you should change it to a smaller value!";
										message += "\n(corpus fraction * corpus size should not be larger than 100000 sentences)";
									}
								} else {
									message = "Error! Corpus fraction not changed.";
								}
							} else if (source.equals(CommitteeItem)) {
								regex = "[^,]+,[^,]+,[^,]+";
								AnnoLight alproject = ((AnnoLight) ProjectList
										.getSelectedValue());
								String result = StringInputDialog
										.showStringInputDialog(null,
												"Enter new committee!",
												alproject.getAlCommittee(),
												regex);
								if (!result.equals("-1")) {
									if (ALChecks.isValidCommittee(result)) {
										alproject.setAlCommittee(result);
										alproject.updateProject(sf);
										message = "New committee: " + result;
									} else
										message = "Error: invalid committee!";
								}
							} else if (source.equals(TrainingProportionItem)) {
								regex = "((0\\.(\\d){0,9})[1-9])|(1\\.0{1,10})";
								AnnoLight alproject = ((AnnoLight) ProjectList
										.getSelectedValue());
								double result = (Double
										.valueOf(StringInputDialog
												.showStringInputDialog(
														null,
														"Enter new training proportion!",
														Double
																.valueOf(
																		alproject
																				.getAlTrainprop())
																.toString(),
														regex))).doubleValue();
								// FIXME: Ist nie genau -1.0!
								if (result != -1.0) {
									if (ALChecks.isValidTrainProportion(
											alproject.getAlCommittee(), result)) {
										alproject.setAlTrainprop(result);
										alproject.updateProject(sf);
										message = "New training proportion: "
												+ result;
									} else
										message = "Error: invalid training proportion.";
								}
							} else if (source.equals(UserMenuItem)) {
								UserSelectionDialog dialog = new UserSelectionDialog(
										new JFrame(), this.sqlDatabaseM, project.getUserName(sf));
								dialog.setModal(true);
								dialog.setVisible(true);
								if (dialog.getExitStatus() == UserSelectionDialog.OK) {
									int u = dialog.getUserID();
									project.setUserID(u);
									((AnnoLight) ProjectList.getSelectedValue())
											.updateProject(sf);
									message = "The user has been changed! ";
								} else
									message = "User has not been changed!";
							} else if (source
									.equals(ChangeBaseDataStatusMenuItem)) {
								BaseDataStatusDialog dialog = new BaseDataStatusDialog(
										new JFrame(), project
												.getBaseDataLight(), sf
												.getSQLDatabaseManager());
								dialog.setVisible(true);
								if (dialog.getExitStatus() == BaseDataStatusDialog.OK)
									message = "Basedata status changed! ";
								else
									message = "Editing BaseData status has been canceled!";
							}
						}
					} catch (Exception e) {
						GUIMessages.exceptionMessage(e);
						logger.log(Level.SEVERE, "", e);
					}
					GUIMessages.infoMessage(message);
				}

				// menu statistics*******************************************
			} else if (source.equals(OverallDisagreementStatsItem)
					|| source.equals(AnnoTimeItem)
					|| source.equals(ShowSelectionTimeMenutItem)
					|| source.equals(SelectiveDisagreementItem)
					|| source.equals(ShowCumulatedEntitiesItem)) {

				JInternalFrame f = null;
				if (source.equals(AnnoTimeItem))
					f = new TimeInternalFrame(projectID,
							TimeInternalFrame.ANNOTATIONTIME, sqlDatabaseM);
				else if (source.equals(ShowSelectionTimeMenutItem))
					f = new TimeInternalFrame(projectID,
							TimeInternalFrame.SELECTIONTIME, sqlDatabaseM);
				else if (source.equals(OverallDisagreementStatsItem))
					f = new DisagreementStatisticsInternalFrame(projectID,
							DisagreementStatistics.ALLDIS_TYPE, sqlDatabaseM,
							"Overall Disagreement");
				else if (source.equals(SelectiveDisagreementItem))
					f = new DisagreementStatisticsInternalFrame(projectID,
							DisagreementStatistics.SELDIS_TYPE, sqlDatabaseM,
							"Selection Disagreement");
				else if (source.equals(ShowCumulatedEntitiesItem))
					f = new CumulatedEntitiesInternalFrame(projectID,
							sqlDatabaseM);

				JFrame Master = Utility.getJFrame(this);
				JDesktopPane j = (JDesktopPane) Master.getContentPane()
						.getComponent(0);
				j.add(f);
				f.pack();
				f.moveToFront();

				// menu deployment *******************************************
			} else if (source.equals(DownloadItem) || source.equals(ExportItem)
					|| source.equals(ExportMarkablesItem)
					|| source.equals(ExportAnnotationItem)
					|| source.equals(DownloadMmaxItem)
					|| source.equals(DownloadBDMarkables)
					|| source.equals(DownloadSchemataCustomizationItem)
					|| source.equals(ExportBDCommentsMenuItem)) {
				// download t2 file: should only be allowed for AL projects
				if (source.equals(DownloadItem)) {
					if (((AnnoLight) ProjectList.getSelectedValue()).isAl()) {
						JFileChooser C = new JFileChooser();
						int returnVal = C.showOpenDialog(ShowProjectFrame.this);
						if (returnVal == JFileChooser.APPROVE_OPTION) {
							File f = C.getSelectedFile();
							try {
								(new SQLFunctions(sqlDatabaseM))
										.downloadT2File(projectID, f);
								JOptionPane.showMessageDialog(null,
										"T2 file successfully downloaded!",
										"Download successful!",
										JOptionPane.INFORMATION_MESSAGE);
							} catch (RuntimeException e) {
								GUIMessages.exceptionMessage(e);
								logger.log(Level.SEVERE, "", e);
							}
						}
					} else
						JOptionPane.showMessageDialog(null,
								"Only works with AL projects!", "alert",
								JOptionPane.ERROR_MESSAGE);

				} else if (source.equals(ExportItem)) {
					JFileChooser C = new JFileChooser();
					C.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					int returnVal = C.showOpenDialog(ShowProjectFrame.this);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File f = C.getSelectedFile();
						try {
							Cursor cursor = super.getCursor();
							super.setCursor(new Cursor(Cursor.WAIT_CURSOR));
							AnnoFull p = sf
									.getFullProject(((AnnoLight) ProjectList
											.getSelectedValue()).getProjectID());
							ExportIOB E = new ExportIOB(p, f);
							// long time1 = System.currentTimeMillis();
							E.run(new SQLFunctions(sqlDatabaseM));
							// long time2 = System.currentTimeMillis();
							super.setCursor(cursor);
							JOptionPane.showMessageDialog(null,
									"IOB data successfully exported!",
									"Download successful!",
									JOptionPane.INFORMATION_MESSAGE);
							// System.out.println("Elapsed time: " + (time2 -
							// time1) / 1000.0);
						} catch (RuntimeException e) {
							GUIMessages.exceptionMessage(e);
							logger.log(Level.SEVERE, "", e);
						}
					}
				} else if (source.equals(ExportAnnotationItem)) {
					JFileChooser C = new JFileChooser();
					C.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					int returnVal = C.showOpenDialog(ShowProjectFrame.this);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File f = C.getSelectedFile();
						try {
							Cursor cursor = super.getCursor();
							super.setCursor(new Cursor(Cursor.WAIT_CURSOR));
							AnnoFull p = sf
									.getFullProject(((AnnoLight) ProjectList
											.getSelectedValue()).getProjectID());
							ConvertIOB2T2 E = new ConvertIOB2T2(p, f);
							super.setCursor(cursor);
							long time1 = System.currentTimeMillis();
							E.run(new SQLFunctions(sqlDatabaseM));
							long time2 = System.currentTimeMillis();
							System.out.println("Elapsed time: "
									+ (time2 - time1) / 1000.0);
							GUIMessages
									.infoMessage("T2 data successfully exported!");
						} catch (RuntimeException e) {
							GUIMessages.exceptionMessage(e);
							logger.log(Level.SEVERE, "", e);
						}
					}
				} else if (source.equals(ExportMarkablesItem)) {
					JFileChooser C = new JFileChooser();
					C.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					int returnVal = C.showOpenDialog(ShowProjectFrame.this);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File f = C.getSelectedFile();
						try {
							AnnoFull p = sf
									.getFullProject(((AnnoLight) ProjectList
											.getSelectedValue()).getProjectID());
							ExportMarkables E = new ExportMarkables(p, f);
							E.run(sqlDatabaseM);
							JOptionPane.showMessageDialog(null,
									"Markables successfully exported!",
									"Export successfull!",
									JOptionPane.INFORMATION_MESSAGE);
						} catch (RuntimeException e) {
							GUIMessages.exceptionMessage(e);
							logger.log(Level.SEVERE, "", e);
						}
					}
				} else if (source.equals(ExportBDCommentsMenuItem)) {
					JFileChooser C = new JFileChooser();
					int returnVal = C.showSaveDialog(ShowProjectFrame.this);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File f = C.getSelectedFile();
						try {
							if (!f.createNewFile()) {
								throw new RuntimeException("File '"
										+ f.toString()
										+ "' could not be created!");
							}
							new ExportBaseDataComments(sqlDatabaseM)
									.exportComments(project.getProjectID(), f);
							GUIMessages
									.infoMessage("Comments have been successfully downloaded!");
						} catch (Exception e) {
							GUIMessages.exceptionMessage(e);
							logger.log(Level.SEVERE, "", e);
						}
					}
				} else if (source.equals(DownloadMmaxItem)) {
					JFileChooser c = new JFileChooser();
					c.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					int returnVal = c.showOpenDialog(ShowProjectFrame.this);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File f = c.getSelectedFile();
						try {
							DownloadMMaxData D = new DownloadMMaxData();
							Cursor cursor = this.getCursor();
							this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
							D.downloadData(this.sqlDatabaseM, f, project);
							super.setCursor(cursor);
							GUIMessages
									.infoMessage("MMax data download successful!");
						} catch (RuntimeException e) {
							GUIMessages.exceptionMessage(e);
							logger.log(Level.SEVERE, "", e);
						}
					}
				} else if (source.equals(DownloadSchemataCustomizationItem)) {
					JFileChooser c = new JFileChooser();

					c.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					int returnVal = c.showSaveDialog(ShowProjectFrame.this);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File f = c.getSelectedFile();
						try {
							DownloadSchemataCustom D = new DownloadSchemataCustom(
									this.sqlDatabaseM, f);
							boolean existence = D.checkForExistingFiles();
							boolean overwrite = false;
							int returnVal02 = JOptionPane.YES_OPTION;
							if (existence) {
								returnVal02 = JOptionPane
										.showConfirmDialog(
												ShowProjectFrame.this,
												"Subdirectory 'MmaxData' already exists. Do you want to overwrite existing directory?",
												"Warning",
												JOptionPane.YES_NO_OPTION);
								overwrite = existence;
							}
							if (returnVal02 == JOptionPane.YES_OPTION) {
								D.doIt(project.getProjectID(), overwrite);
								GUIMessages
										.infoMessage("Mmaxdata successfully downloaded!");
							}
						} catch (RuntimeException e) {
							GUIMessages.exceptionMessage(e);
							logger.log(Level.SEVERE, "", e);
						}
					}
				} else if (source.equals(DownloadBDMarkables)) {
					JFileChooser c = new JFileChooser();
					c.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					int returnVal = c.showOpenDialog(ShowProjectFrame.this);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File f = c.getSelectedFile();

						try {
							DownloadBasedataMarkables D = new DownloadBasedataMarkables(
									this.sqlDatabaseM, f);
							boolean existence = D.checkForExistingFiles();
							boolean overwrite = false;
							int returnVal02 = JOptionPane.YES_OPTION;
							if (existence) {
								returnVal02 = JOptionPane
										.showConfirmDialog(
												ShowProjectFrame.this,
												"Subdirectories 'Basedata' and 'Markables' already exist. Do you want to overwrite existing directories?",
												"Warning",
												JOptionPane.YES_NO_OPTION);
								overwrite = existence;
							}
							if (returnVal02 == JOptionPane.YES_OPTION) {
								D.doIt(project.getProjectID(), overwrite);
								GUIMessages
										.infoMessage("Basedata and Markables successfully downloaded!");
							}
						} catch (RuntimeException e) {
							GUIMessages.exceptionMessage(e);
							logger.log(Level.SEVERE, "", e);
						}
					}
				}
			}
			// copy project*****************************
			else if (source.equals(CopyProjectMenuItem)) {
				(new CopyProjectDialog(this.sqlDatabaseM,project)).setVisible(true);
			}
		}

		try {
			Cursor cursor = super.getCursor();
			super.setCursor(new Cursor(Cursor.WAIT_CURSOR));
			ProjectList.setListData(sf.getLightProjects(
					((User) UserBox.getSelectedItem()).getUserID()).toArray());
			super.setCursor(cursor);
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
			final JFrame frame = new JFrame();
			ShowProjectFrame inst = null;
			inst = new ShowProjectFrame(new SQLDatabaseManager());
			frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			frame.pack();
			frame.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
