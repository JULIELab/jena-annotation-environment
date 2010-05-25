/** 
 * NewAnnoClientMasterFrame.java
 * 
 * Copyright (c) 2007, JULIE Lab. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0 
 *
 * Author: Klaue
 * 
 * Current version: 2.0
 * Since version:   0.9
 *
 * Creation date: June, 2007 
 * 

 **/

package de.julielab.annoenv.ui.annoclient;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import org.jdesktop.swingworker.SwingWorker.StateValue;

import de.julielab.annoenv.db.annodata.AnnoCore;
import de.julielab.annoenv.db.annodata.AnnoLight;
import de.julielab.annoenv.db.annodata.BaseDataLight;
import de.julielab.annoenv.db.annodata.User;
import de.julielab.annoenv.db.sql.SQLDatabaseManager;
import de.julielab.annoenv.db.sql.SQLFunctions;
import de.julielab.annoenv.ui.GUIMessages;
import de.julielab.annoenv.ui.annoclient.MMAX.MMAXExecuter;
import de.julielab.annoenv.ui.annoclient.MMAX.MMAXSession;
import de.julielab.annoenv.ui.annoclient.MMAX.MMAXWorker;
import de.julielab.annoenv.ui.annomaster.HelpFrame;

import de.julielab.annoenv.utils.AnnoEnvLogger;
import de.julielab.annoenv.utils.Constants;
import de.julielab.annoenv.utils.settings.AnnoClientSettings;

// TODO code refactoring und v.a. Zerlegen in Teilmethoden

/**
 * This is the AnnoClient GUI.
 * 
 * It is a GUI for managing annotation via Mmax or starting Active Learning for
 * certain documents. A frame is shown with two inner frames each one
 * representing different type of projects: ALProjects and DefaultProjects.
 */
public class AnnoClientMasterFrame extends javax.swing.JFrame {
	private static Logger logger = AnnoEnvLogger
			.getLogger("de.julielab.annoenv.ui.annoclient.AnnoClientMasterFrame");

	private static final String FRAME_TITLE = "JANE Annotation GUI, User: ";

	private JList ALProjectsList;

	private JScrollPane ALProjectsScrollPane;

	private JScrollPane DefaultProjectsScrollPane;

	private JInternalFrame ALProjectsInternalFrame;

	private JList DefaultProjectsList;

	private JInternalFrame DefaultProjectsInternalFrame;

	private DocumentComment DescFrame;

	private JMenu ALWindowMenu;

	private JMenu HelpMenu;

	private JCheckBoxMenuItem InProgress2MenuItem;

	private JCheckBoxMenuItem Done2MenuItem;

	private JCheckBoxMenuItem Raw2MenuItem;

	private JPopupMenu ALPopupMenu;

	private JCheckBoxMenuItem InProgressMenuItem;

	private JCheckBoxMenuItem DoneMenuItem;

	private JCheckBoxMenuItem RawMenuItem;

	private JPopupMenu DefaultPopupMenu;

	private JButton ALButton;

	private JList ALBaseDataList;

	private JPanel ALProjectsPanel;

	private JPanel DefaultProjectPanel;

	private JList DefaultBaseDataList;

	private JScrollPane DefaultBaseDataScrollPane;

	private JDesktopPane AnnoClientDesktopPane;

	private JMenuBar jMenuBar1;

	private JMenuItem AboutMenuItem;

	private JMenuItem DocumentationMenuItem;

	private ArrayList<AnnoCore> coreProjects;

	private ArrayList<AnnoCore> alProjects;

	private ArrayList<AnnoCore> defaultProjects;

	private ArrayList<BaseDataLight> alBaseData;

	private ArrayList<BaseDataLight> defaultBaseData;

	private SQLDatabaseManager sdm;

	private SQLFunctions sdf;

	private User user;

	public static final String RAW = "raw";

	public static final String DONE = "done";

	public static final String INPROGRESS = "progress";

	private AnnoCore currentALProject;

	private AnnoCore currentDefaultProject;

	private MmaxMouseAdapter mMouseAdapter;

	private WindowExitListener mWindowAdapter;

	public boolean UIisEnabled;

	private Vector<ALWorker> workers;

	private Vector<AnnoClientLogFrame> outputFrames;

	private BaseDataLight bd = null;

	MMAXWorker mmax;

	AnnoClientLogFrame outputFrame;

	private AnnoClientSettings annoclientSettings;

	private static final long serialVersionUID = 0;

	private static final String ALBUTTONTEXT = "Run AL!";

	public AnnoClientMasterFrame(User user, SQLDatabaseManager sdm) {
		super();
		this.user = user;
		this.sdm = sdm;
		try {
			sdf = new SQLFunctions(sdm);
		} catch (RuntimeException e) {
			GUIMessages.exceptionMessage(e);
			logger.log(Level.SEVERE, "", e);
			System.exit(-1);
		}

		initGUI();
	}

	/**
	 * attention: spaghetti code (due to automatic code creation with Jiggloo)
	 * 
	 */
	private void initGUI() {

		// if we can't load the settings, kill the programm
		try {
			annoclientSettings = new AnnoClientSettings();
		} catch (RuntimeException e) {
			GUIMessages.exceptionMessage(e);
			logger.log(Level.SEVERE, "", e);
			System.exit(-1);
		}

		try {

			// init Frame in general
			outputFrame = new AnnoClientLogFrame("");
			mMouseAdapter = new MmaxMouseAdapter();
			mWindowAdapter = new WindowExitListener();
			UIisEnabled = true;
			this.setTitle(FRAME_TITLE + user.getFullName());
			this.addWindowListener(mWindowAdapter);

			// load projects and initialize AL-workers
			coreProjects = sdf.getCoreProjects(user.getUserID());
			alProjects = extractProjects(true, coreProjects);
			workers = new Vector<ALWorker>();
			outputFrames = new Vector<AnnoClientLogFrame>();
			this.initVectors(alProjects);
			defaultProjects = extractProjects(false, coreProjects);

			// init window components
			AnnoClientDesktopPane = new JDesktopPane();
			getContentPane().add(AnnoClientDesktopPane, BorderLayout.CENTER);
			getContentPane().add(AnnoClientDesktopPane, BorderLayout.CENTER);
			AnnoClientDesktopPane.setPreferredSize(new java.awt.Dimension(742,
					518));

			DefaultProjectsInternalFrame = new JInternalFrame();
			BoxLayout DefaultProjectsInternalFrameLayout = new BoxLayout(
					DefaultProjectsInternalFrame.getContentPane(),
					javax.swing.BoxLayout.X_AXIS);
			DefaultProjectsInternalFrame.getContentPane().setLayout(
					DefaultProjectsInternalFrameLayout);
			AnnoClientDesktopPane.add(DefaultProjectsInternalFrame);
			DefaultProjectsInternalFrame.setBounds(42, 49, 609, 161);
			DefaultProjectsInternalFrame.setTitle("Default Projects");

			DefaultProjectsScrollPane = new JScrollPane();
			DefaultProjectsInternalFrame.getContentPane().add(
					DefaultProjectsScrollPane);
			DefaultProjectsScrollPane.setPreferredSize(new java.awt.Dimension(
					260, 135));

			ListModel DefaultProjectsListModel = new DefaultComboBoxModel(
					defaultProjects.toArray());
			DefaultProjectsList = new JList();
			DefaultProjectsScrollPane.setViewportView(DefaultProjectsList);
			DefaultProjectsList.setModel(DefaultProjectsListModel);
			DefaultProjectsList
					.addListSelectionListener(new ListSelectionListener() {
						public void valueChanged(ListSelectionEvent evt) {
							try {
								if (!DefaultProjectsList.isSelectionEmpty()) {
									int p_ID = ((AnnoCore) DefaultProjectsList
											.getSelectedValue()).getProjectID();

									DefaultProjectsList.setToolTipText(sdf
											.getCoreProject(p_ID)
											.getDescription());
								}
							} catch (RuntimeException e) {
								GUIMessages.exceptionMessage(e);
								logger.log(Level.SEVERE, "", e);
							}
						}
					});

			DefaultProjectsList.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					// check for double-clicks
					try {
						if (!DefaultProjectsList.isSelectionEmpty()) {
							currentDefaultProject = (AnnoCore) DefaultProjectsList
									.getSelectedValue();
						}
						if (e.getClickCount() == 2) {
							defaultBaseData = sdf
									.getAllBaseDataLight(((AnnoCore) DefaultProjectsList
											.getSelectedValue()).getProjectID());
							DefaultBaseDataList.setListData(defaultBaseData
									.toArray());
						}
					} catch (RuntimeException e1) {
						GUIMessages.exceptionMessage(e1);
						logger.log(Level.SEVERE, "", e1);
					}

				}
			});

			DefaultProjectPanel = new JPanel();
			DefaultProjectsInternalFrame.getContentPane().add(
					DefaultProjectPanel);
			DefaultProjectPanel
					.setPreferredSize(new java.awt.Dimension(90, 135));

			DefaultBaseDataScrollPane = new JScrollPane();
			DefaultProjectsInternalFrame.getContentPane().add(
					DefaultBaseDataScrollPane);
			DefaultBaseDataScrollPane.setPreferredSize(new java.awt.Dimension(
					257, 135));
			{
				ListModel DefaultBaseDataListModel = new DefaultComboBoxModel();
				DefaultBaseDataList = new JList();
				DefaultBaseDataScrollPane.setViewportView(DefaultBaseDataList);
				DefaultBaseDataList.setModel(DefaultBaseDataListModel);
				DefaultBaseDataList.addMouseListener(mMouseAdapter);
				DefaultBaseDataList
						.addListSelectionListener(new SelectionChangeListener(
								DefaultBaseDataList));
				{
					DefaultPopupMenu = new JPopupMenu();
					setComponentPopupMenu(DefaultBaseDataList, DefaultPopupMenu);
					DefaultPopupMenu
							.addPopupMenuListener(new ListPopupMenuListener());
					{
						RawMenuItem = new JCheckBoxMenuItem();
						DefaultPopupMenu.add(RawMenuItem);
						RawMenuItem.setText(AnnoClientMasterFrame.RAW);
						RawMenuItem.addActionListener(new PopupItemListener());
					}
					{
						DoneMenuItem = new JCheckBoxMenuItem();
						DefaultPopupMenu.add(DoneMenuItem);
						DoneMenuItem.setText(AnnoClientMasterFrame.DONE);
						DoneMenuItem.addActionListener(new PopupItemListener());
					}
					{
						InProgressMenuItem = new JCheckBoxMenuItem();
						DefaultPopupMenu.add(InProgressMenuItem);
						InProgressMenuItem
								.setText(AnnoClientMasterFrame.INPROGRESS);
						InProgressMenuItem
								.addActionListener(new PopupItemListener());
					}
				}
			}

			DefaultProjectsInternalFrame.setVisible(true);

			{
				ALProjectsInternalFrame = new JInternalFrame();
				BoxLayout ALProjectsInternalFrameLayout = new BoxLayout(
						ALProjectsInternalFrame.getContentPane(),
						javax.swing.BoxLayout.X_AXIS);
				ALProjectsInternalFrame.getContentPane().setLayout(
						ALProjectsInternalFrameLayout);
				AnnoClientDesktopPane.add(ALProjectsInternalFrame);
				ALProjectsInternalFrame.setBounds(42, 224, 609, 161);
				ALProjectsInternalFrame.setTitle("AL Projects");
				ALProjectsInternalFrame.setVisible(true);
				{
					ALProjectsScrollPane = new JScrollPane();
					ALProjectsInternalFrame.getContentPane().add(
							ALProjectsScrollPane);
					ALProjectsScrollPane
							.setPreferredSize(new java.awt.Dimension(259, 135));
					{
						ListModel ALProjectsListModel = new DefaultComboBoxModel(
								alProjects.toArray());
						ALProjectsList = new JList();
						ALProjectsScrollPane.setViewportView(ALProjectsList);
						ALProjectsList.setModel(ALProjectsListModel);
						ALProjectsList
								.addListSelectionListener(new ListSelectionListener() {
									public void valueChanged(
											ListSelectionEvent evt) {
										try {
											if (!ALProjectsList
													.isSelectionEmpty()) {
												int p_ID = ((AnnoCore) ALProjectsList
														.getSelectedValue())
														.getProjectID();

												ALProjectsList
														.setToolTipText(sdf
																.getCoreProject(
																		p_ID)
																.getDescription());

											}
										} catch (RuntimeException e) {
											GUIMessages.exceptionMessage(e);
											logger.log(Level.SEVERE, "", e);
										}
									}
								});
						ALProjectsList.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								try {
									if (!ALProjectsList.isSelectionEmpty()) {
										currentALProject = (AnnoCore) (ALProjectsList
												.getSelectedValue());
									}
									if (e.getClickCount() == 2) {

										int p_ID = ((AnnoCore) ALProjectsList
												.getSelectedValue())
												.getProjectID();
										AnnoLight proj = sdf
												.getLightProject(p_ID);
										alBaseData = proj.getBaseDataLight();
										ALBaseDataList.setEnabled(true);
										ALBaseDataList.setListData(alBaseData
												.toArray());
										if (!sdf
												.getProjectALStatus(p_ID)
												.equals(
														Constants.AL_PROJECT_STATUS_ERROR)) {
											if (sdf
													.getProjectALStatus(p_ID)
													.equals(
															Constants.AL_PROJECT_STATUS_RUNNING)) {
												ALBaseDataList
														.setEnabled(false);
												ALButton.setEnabled(false);
												ALWorker w = getWorker(p_ID);
												if (!w.getState().equals(
														StateValue.STARTED)) {
													w.setSQLDBaseManager(sdm);
													w
															.addRunnable(new DbaseTimeRunnable(
																	ALButton,
																	sdf,
																	w
																			.getProject()));
													w.getOutputFrame()
															.setVisible(true);
													w.setIsRunning(true);
													System.err
															.println("already running!!!!");// TODO
													// catch
													// ?
													w.execute();
													ALButton.setEnabled(false);
												}
											} else {
												ALButton
														.setEnabled(((BaseDataLight) alBaseData
																.get(0))
																.getStatus()
																.equals(
																		AnnoClientMasterFrame.DONE));
												ALButton
														.setText(AnnoClientMasterFrame.ALBUTTONTEXT);
											}
										} else {
											ALButton.setEnabled(false);
											GUIMessages
													.warnMessage("This project contains an error in the current annotation. Revise the annotation, please.");
										}

									}

								} catch (RuntimeException e1) {
									GUIMessages.exceptionMessage(e1);
									logger.log(Level.SEVERE, "", e1);
								}
							}
						});
					}
				}
				{
					ALProjectsPanel = new JPanel();
					ALProjectsPanel.setLayout(null);
					ALProjectsInternalFrame.getContentPane().add(
							ALProjectsPanel);
					ALProjectsPanel.setPreferredSize(new java.awt.Dimension(
							343, 135));
					{
						ListModel ALBaseDataListModel = new DefaultComboBoxModel();
						ALBaseDataList = new JList();
						ALProjectsPanel.add(ALBaseDataList);
						ALBaseDataList.setModel(ALBaseDataListModel);
						ALBaseDataList.setBounds(84, 14, 217, 28);
						ALBaseDataList.setFixedCellHeight(28);
						ALBaseDataList.addMouseListener(mMouseAdapter);
						ALBaseDataList
								.addListSelectionListener(new SelectionChangeListener(
										ALBaseDataList));
						{
							ALPopupMenu = new JPopupMenu();
							setComponentPopupMenu(ALBaseDataList, ALPopupMenu);
							ALPopupMenu
									.addPopupMenuListener(new ListPopupMenuListener());
							{
								Raw2MenuItem = new JCheckBoxMenuItem();
								ALPopupMenu.add(Raw2MenuItem);
								Raw2MenuItem.setText(AnnoClientMasterFrame.RAW);
								Raw2MenuItem
										.addActionListener(new PopupItemListener());

							}
							{
								Done2MenuItem = new JCheckBoxMenuItem();
								ALPopupMenu.add(Done2MenuItem);
								Done2MenuItem
										.setText(AnnoClientMasterFrame.DONE);
								Done2MenuItem
										.addActionListener(new PopupItemListener());
							}
							{
								InProgress2MenuItem = new JCheckBoxMenuItem();
								ALPopupMenu.add(InProgress2MenuItem);
								InProgress2MenuItem
										.setText(AnnoClientMasterFrame.INPROGRESS);
								InProgress2MenuItem
										.addActionListener(new PopupItemListener());
							}
						}
					}
					{
						ALButton = new JButton();
						ALProjectsPanel.add(ALButton);
						ALButton.setText(AnnoClientMasterFrame.ALBUTTONTEXT);
						ALButton.setBounds(84, 77, 119, 28);
						ALButton.setEnabled(false);
						ALButton.addActionListener(new ALButtonListener());
					}
				}
			}

			this.setSize(818, 591);

			// description frame + its event handling
			{
				DescFrame = new DocumentComment();
				DescFrame.setLocation(180, 400);
				EnterEventListener listener = new EnterEventListener();
				DescFrame.getButton().addActionListener(listener);
				DescFrame.addWindowListener(new DescFrameExitListener());
			}

			{
				jMenuBar1 = new JMenuBar();
				setJMenuBar(jMenuBar1);
				{
					ALWindowMenu = new JMenu();
					jMenuBar1.add(ALWindowMenu);
					ALWindowMenu.setText("AL log frame");
					// slow for large projectlists
					for (int i = 0; i < workers.size(); i++) {
						ALWorker w = workers.get(i);
						AnnoCore currPr = w.getProject();
						JMenuItem item = new JMenuItem(currPr.getName());
						ALWindowMenu.add(item);
						item.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								try {
									AnnoClientLogFrame f = null;
									for (Enumeration<ALWorker> e = workers
											.elements(); e.hasMoreElements();) {
										ALWorker work = e.nextElement();
										f = work.getOutputFrame();
										if (((JMenuItem) (evt.getSource()))
												.getText().equals(
														work.getProject()
																.getName())) {
											break;
										}
									}
									f.setVisible(true);
								} catch (RuntimeException e) {
									GUIMessages.exceptionMessage(e);
									logger.log(Level.SEVERE, "", e);
								}
							}
						});
					}

					HelpMenu = new JMenu();
					jMenuBar1.add(HelpMenu);
					HelpMenu.setText("Help");
					AboutMenuItem = new JMenuItem("About");
					DocumentationMenuItem = new JMenuItem("Documentation");
					HelpMenu.add(AboutMenuItem);
					HelpMenu.add(DocumentationMenuItem);
					AboutMenuItem.addActionListener(new HelpActionListener());
					DocumentationMenuItem
							.addActionListener(new HelpActionListener());
				}
			}
		} catch (Exception e) {
			GUIMessages.exceptionMessage(e);
			logger.log(Level.SEVERE, "", e);
		}
	}

	// extract default(false) or AL(true) projects form a list of projects
	private ArrayList<AnnoCore> extractProjects(boolean isAL,
			ArrayList<AnnoCore> coreProjects2) {
		ArrayList<AnnoCore> list = new ArrayList<AnnoCore>();

		for (int i = 0; i < coreProjects2.size(); i++) {
			AnnoCore p = coreProjects2.get(i);
			if (isAL == p.isAl()) {
				list.add(p);
			}
		}
		return list;
	}

	/**
	 * Auto-generated method for setting the popup menu for a component
	 */
	private void setComponentPopupMenu(final java.awt.Component parent,
			final javax.swing.JPopupMenu menu) {
		parent.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent e) {
				if (e.isPopupTrigger()) {
					menu.show(parent, e.getX(), e.getY());
				}
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
				if (e.isPopupTrigger()) {
					menu.show(parent, e.getX(), e.getY());
				}
			}
		});
	}

	// process popup menu selection
	private class PopupItemListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				if (e.getSource() instanceof JCheckBoxMenuItem) {
					JList list = null;
					if (((JCheckBoxMenuItem) e.getSource()).getParent().equals(
							ALPopupMenu)) {
						list = ALBaseDataList;
					} else if (((JCheckBoxMenuItem) e.getSource()).getParent()
							.equals(DefaultPopupMenu)) {
						list = DefaultBaseDataList;
					}
					if (list != null) {
						BaseDataLight bd = ((BaseDataLight) list
								.getSelectedValue());
						bd.setStatus(((JCheckBoxMenuItem) e.getSource())
								.getText());

						bd.updateBaseData(sdm);
						list.repaint();
						// check whether ALRunButton should trigger or not
						if (((JCheckBoxMenuItem) e.getSource()).getParent()
								.equals(ALPopupMenu)) {
							ALButton
									.setEnabled((bd.getStatus()
											.equals(AnnoClientMasterFrame.DONE))
											&& (!sdf
													.getProjectALStatus(
															bd.getProjectID())
													.equals(
															Constants.AL_PROJECT_STATUS_ERROR)));
						}

					}
				}
			} catch (Exception e1) {
				GUIMessages.exceptionMessage(e1);
				logger.log(Level.SEVERE, "", e1);
			}
		}
	}

	// handling popup menu events
	private class ListPopupMenuListener implements PopupMenuListener {
		public void popupMenuCanceled(PopupMenuEvent e) {
		}

		public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
		}

		public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
			if (e.getSource().equals(ALPopupMenu)) {
				setPopupStatus(((BaseDataLight) ALBaseDataList
						.getSelectedValue()).getStatus());
			} else if (e.getSource().equals(DefaultPopupMenu)) {
				setPopupStatus(((BaseDataLight) DefaultBaseDataList
						.getSelectedValue()).getStatus());
			}
		}
	}

	// start mmax session on mouse double-click
	private class MmaxMouseAdapter extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			try {
				if (e.getClickCount() == 2) {
					MMAXExecuter mex = null;
					MMAXSession session;
					File tmpDir = annoclientSettings.SESSION_DIR;
					File runFile;
					AnnoClientSettings acSettings;
					// AnnoLight project=null;
					// Core project is enough here, KT 07.10.2008
					AnnoCore project = null;

					// fetch basedata and create MMaxSession

					acSettings = new AnnoClientSettings();
					if (e.getSource().equals(ALBaseDataList)) {
						// System.out.println("going for AL");
						bd = (BaseDataLight) ALBaseDataList.getSelectedValue();
						session = new MMAXSession(sdm, tmpDir, currentALProject
								.getUserID(), bd.getBasedataID());
						// project =
						// sdf.getLightProject(currentALProject.getProjectID());
						project = sdf.getCoreProject(currentALProject
								.getProjectID());
					} else {
						// System.out.println("going for Default");
						bd = (BaseDataLight) DefaultBaseDataList
								.getSelectedValue();
						session = new MMAXSession(sdm, tmpDir,
								currentDefaultProject.getUserID(), bd
										.getBasedataID());
						project = sdf.getCoreProject(currentDefaultProject
								.getProjectID());
						// project =
						// sdf.getLightProject(currentDefaultProject.getProjectID());
					}

					sdf.setProjectALStatus(bd.getProjectID(),
							Constants.AL_PROJECT_STATUS_IDLE);

					outputFrame.setVisible(true);
					outputFrame.setTitle("MMAX output for document: "
							+ bd.getUri());
					outputFrame
							.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

					// disable user input
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							setUIEnabled(false);
						}
					});
					// start Mmax
					mmax = new MMAXWorker(sdm, project.getUserID(), project
							.getProjectID(), bd.getBasedataID(), outputFrame,
							project.isAl());
					mmax.addPropertyChangeListener(new MmaxWorkerListener());
					mmax.execute();
				}
			} catch (Exception ex) {
				GUIMessages.exceptionMessage(ex);
				logger.log(Level.SEVERE, "", ex);
			}
		}
	}

	// disables/enables user input
	private void setUIEnabled(boolean enabled) {
		UIisEnabled = enabled;
		this.getGlassPane().setVisible(!enabled);
		this.setEnabled(enabled);
		ALProjectsList.setEnabled(enabled);
		ALProjectsScrollPane.setEnabled(enabled);
		DefaultProjectsScrollPane.setEnabled(enabled);
		ALProjectsInternalFrame.setEnabled(enabled);
		DefaultProjectsList.setEnabled(enabled);
		DefaultProjectsInternalFrame.setEnabled(enabled);
		ALPopupMenu.setEnabled(enabled);
		DefaultPopupMenu.setEnabled(enabled);
		ALBaseDataList.setEnabled(enabled);
		ALProjectsPanel.setEnabled(enabled);
		DefaultProjectPanel.setEnabled(enabled);
		DefaultBaseDataList.setEnabled(enabled);
		DefaultBaseDataScrollPane.setEnabled(enabled);
		AnnoClientDesktopPane.setEnabled(enabled);

		if (!enabled) {
			DefaultBaseDataList.removeMouseListener(mMouseAdapter);
			ALBaseDataList.removeMouseListener(mMouseAdapter);
			this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
			this.removeWindowListener(mWindowAdapter);
		} else {
			DefaultBaseDataList.addMouseListener(mMouseAdapter);
			ALBaseDataList.addMouseListener(mMouseAdapter);
			this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.addWindowListener(mWindowAdapter);
		}

	}

	// preselects the proper menu item of the popup menu,
	// according to the basedata status the popup menu belongs to
	private void setPopupStatus(String status) {
		InProgressMenuItem.setSelected(InProgressMenuItem.getText().equals(
				status));
		DoneMenuItem.setSelected(DoneMenuItem.getText().equals(status));
		RawMenuItem.setSelected(RawMenuItem.getText().equals(status));
		InProgress2MenuItem.setSelected(InProgress2MenuItem.getText().equals(
				status));
		Done2MenuItem.setSelected(Done2MenuItem.getText().equals(status));
		Raw2MenuItem.setSelected(Raw2MenuItem.getText().equals(status));
	}

	// initialization of all AL workers for all AL projects
	private void initVectors(ArrayList<AnnoCore> projectList) {
		try {
			for (int i = 0; i < projectList.size(); i++) {
				ALWorker w;
				AnnoClientLogFrame frame = new AnnoClientLogFrame(
						"AL logs for project: " + projectList.get(i).toString());
				workers.add(w = new ALWorker(frame));
				outputFrames.add(frame);
				w.setProject(projectList.get(i));
				w.addPropertyChangeListener(new ALWorkerListener());
			}
		} catch (RuntimeException e) {
			GUIMessages.exceptionMessage(e);
			logger.log(Level.SEVERE, "", e);
		}
	}

	// extracts the AL worker belonging to a certain
	// AL project form the worker collection: (Vector) this.workers
	private ALWorker getWorker(int projectID) {
		ALWorker w = null;
		for (Enumeration<ALWorker> e = workers.elements(); e.hasMoreElements();) {
			w = e.nextElement();
			if (w.getID() == projectID)
				break;
		}
		return w;
	}

	private class MmaxWorkerListener implements PropertyChangeListener {
		public void propertyChange(PropertyChangeEvent evt) {
			try {
				if (mmax.getState().equals(StateValue.DONE)) {
					outputFrame.appendText("MMAX finished!");

					// TODO only one single BaseData item needs to be updated...
					// not
					// the whole list, to reduce traffic

					if (mmax.getIsAl()) {
						BaseDataLight newBD = sdf.getBaseDataLight(mmax
								.getBaseDataID());
						BaseDataLight oldBD = (BaseDataLight) ALBaseDataList
								.getSelectedValue();
						int index = ALBaseDataList.getSelectedIndex();
						oldBD = newBD;
						ALButton.setEnabled(((BaseDataLight) ALBaseDataList
								.getModel().getElementAt(0)).getStatus()
								.equals(AnnoClientMasterFrame.DONE));
						ALBaseDataList.repaint();
						ALBaseDataList.setSelectedIndex(index);
						ALBaseDataList.ensureIndexIsVisible(index);
					} else {
						// bDataList =
						// sdf.getAllBaseDataLight(mmax.getProjectID());
						BaseDataLight newBD = sdf.getBaseDataLight(mmax
								.getBaseDataID());
						BaseDataLight oldBD = (BaseDataLight) DefaultBaseDataList
								.getSelectedValue();
						int index = DefaultBaseDataList.getSelectedIndex();
						oldBD = newBD;
						DefaultBaseDataList.repaint();
						DefaultBaseDataList.setSelectedIndex(index);
						DefaultBaseDataList.ensureIndexIsVisible(index);
					}

					// enable user input
					setUIEnabled(true);
					outputFrame
							.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				}
			} catch (Exception e) {
				GUIMessages.exceptionMessage(e);
				logger.log(Level.SEVERE, "", e);
			}
		}
	}

	private class ALWorkerListener implements PropertyChangeListener {

		public void propertyChange(PropertyChangeEvent evt) {
			try {
				ALWorker w = (ALWorker) evt.getSource();
				if (w.getState().equals(StateValue.DONE)) {
					AnnoLight ap = sdf.getLightProject(w.getID());
					ArrayList<BaseDataLight> list = new ArrayList<BaseDataLight>();
					list.add(ap.getCurrentBaseDataLight());
					// KT 23.01.2008
					// list.add(sdf.getBaseDataLightForAL(ap.get_project_id()));
					ALBaseDataList.setListData(list.toArray());
					ALProjectsList.setSelectedValue(w.getProject(), true);
					// replace old worker with new one
					// critical
					ALWorker newW = new ALWorker(w.getOutputFrame());
					newW.setIsRunning(false);
					newW.setProject(w.getProject());
					newW.addPropertyChangeListener(new ALWorkerListener());
					workers.removeElement(w);
					workers.add(newW);
					ALBaseDataList.setEnabled(true);
					ALButton.setText("Run AL!");
					ALButton.setEnabled(list.get(0).getStatus().equals(
							AnnoClientMasterFrame.DONE));
				} else if (w.getState().equals(StateValue.STARTED)) {
					// System.err.println("WORKER STARTED!");
					ALButton.setEnabled(false);
				}
			} catch (Exception e) {
				GUIMessages.exceptionMessage(e);
				logger.log(Level.SEVERE, "", e);
			}
		}
	}

	private class ALButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent evt) {

			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					ALBaseDataList.setEnabled(false);
				}
			});

			try {
				ALWorker w = getWorker(((BaseDataLight) ALBaseDataList
						.getModel().getElementAt(0)).getProjectID());
				w.addRunnable(new DbaseTimeRunnable(ALButton, sdf, w
						.getProject()));
				w.setIsRunning(false);
				w.setSQLDBaseManager(sdm);
				w.getOutputFrame().setVisible(true);
				w.execute();
			} catch (RuntimeException e) {
				GUIMessages.exceptionMessage(e);
				logger.log(Level.SEVERE, "", e);
			}
		}
	}

	private class WindowExitListener extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			try {
				for (AnnoClientLogFrame en : outputFrames)
					en.setVisible(false);
				outputFrame.setVisible(false);
				new SQLFunctions(sdm).logout(user.getUserID());
			} catch (RuntimeException ex) {
				GUIMessages.exceptionMessage(ex);
				logger.log(Level.SEVERE, "", ex);
			} finally {
				System.exit(0);
			}
		}
	}

	private class HelpActionListener implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			try {
				Object source = evt.getSource();
				JInternalFrame f = null;
				if (source.equals(AboutMenuItem)) {
					f = new HelpFrame(annoclientSettings.ANNOCLIENT_ABOUT_HTML);
				} else if (source.equals(DocumentationMenuItem)) {
					f = new HelpFrame(annoclientSettings.ANNOCLIENT_DOCU_HTML);
				}
				AnnoClientDesktopPane.add(f);
				f.setLocation(AnnoClientDesktopPane.getWidth() / 4
						- f.getWidth() / 4,
						AnnoClientDesktopPane.getSize().height / 4
								- f.getHeight() / 4);
				f.pack();
				f.setVisible(true);
				f.moveToFront();
				f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			} catch (RuntimeException ex) {
				GUIMessages.exceptionMessage(ex);
				logger.log(Level.SEVERE, "", ex);
			}
		}
	}

	private class SelectionChangeListener implements ListSelectionListener {
		JList list = null;

		SelectionChangeListener(JList list) {
			this.list = list;
		}

		public void valueChanged(ListSelectionEvent lse) {
			// check whether it exists a selection and and a correct number of
			// selected documents
			try {
				if (lse.getValueIsAdjusting() || list.isSelectionEmpty()) {
					DescFrame.setVisible(false);
				} else {
					Object d = list.getSelectedValue();
					String desc = "";
					BaseDataLight b = (BaseDataLight) d;
					desc = b.getDescription();
					if (desc != null) {
						DescFrame.setDescription(desc, b);
						DescFrame.setVisible(true);
					}
				}
			} catch (RuntimeException ex) {
				GUIMessages.exceptionMessage(ex);
				logger.log(Level.SEVERE, "", ex);
			}
		}
	}

	private class EnterEventListener implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			try {
				BaseDataLight b = DescFrame.getBaseData();
				if (b != null) {
					b.setDescription(DescFrame.getDescription());
					b.updateBaseData(sdm);
				}

			} catch (Exception e) {
				GUIMessages.exceptionMessage(e);
				logger.log(Level.SEVERE, "", e);
			}
		}
	}

	// when mmax closes, the description frame also closes since selection of
	// the basedata
	// is lost due to reloading the documents belonging to a project
	// thus saving the description seems appropriate =]

	private class DescFrameExitListener extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			try {
				BaseDataLight b = DescFrame.getBaseData();
				int result = JOptionPane.showConfirmDialog(null,
						"Do You want to save the description?", "choose one",
						JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					if (b != null) {
						b.setDescription(DescFrame.getDescription());
						b.updateBaseData(sdm);
					}
				}
			} catch (Exception ex) {
				GUIMessages.exceptionMessage(ex);
				logger.log(Level.SEVERE, "", ex);
			}

		}
	}

	/**
	 * main method for debugging (should not be started directly but instead via
	 * LoginDialog TODO remove one day
	 */
	public static void main(String[] args) {
		try {
			SQLDatabaseManager dm = new SQLDatabaseManager();
			User u = new User(dm);
			u.setUserId(7);
			AnnoClientMasterFrame inst = new AnnoClientMasterFrame(u, dm);
			inst.setVisible(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
