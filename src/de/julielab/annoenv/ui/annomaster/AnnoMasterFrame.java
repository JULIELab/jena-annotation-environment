/** 
 * AnnoMasterFrame.java
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
 * Creation date: January, 2007 
 * 
 
 **/

package de.julielab.annoenv.ui.annomaster;

import de.julielab.annoenv.db.annodata.User;
import de.julielab.annoenv.db.sql.SQLDatabaseManager;
import de.julielab.annoenv.db.sql.SQLFunctions;
import de.julielab.annoenv.ui.GUIMessages;
import de.julielab.annoenv.utils.AnnoEnvLogger;
import de.julielab.annoenv.utils.DeleteDir;
import de.julielab.annoenv.utils.settings.AnnoMasterSettings;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

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
 * GUI for managing anno projects and anno users. This is the main menu of the
 * supervisor tool: 'AnnoMaster' for indirect maintenance of the database.
 */
public class AnnoMasterFrame extends javax.swing.JFrame implements
		ActionListener {
	private static final String FRAME_TITLE = "JANE Administration GUI";

	private static Logger logger = AnnoEnvLogger
			.getLogger("de.julielab.annoenv.ui.annomaster.AnnoMasterFrame");

	private JMenuBar jMenuBar1;

	private JMenu jMenu1;

	private JMenuItem jMenuItem1;

	private JMenuItem DocumentationMenuItem;

	private JMenuItem InfoMenuItem8;

	private JMenu jMenu4;

	private JDesktopPane jDesktopPane1;

	private JMenuItem jMenuItem7;

	private JMenuItem jMenuItem6;

	private JMenuItem jMenuItem5;

	private JMenuItem jMenuItem4;

	private JMenuItem jMenuItem3;

	private JMenu jMenu3;

	private JMenu jMenu2;

	private JMenuItem jMenuItem2;

	private User user;

	private SQLDatabaseManager sdm;

	private AnnoMasterSettings annomasterSettings;

	public AnnoMasterFrame(User user, SQLDatabaseManager sdm) {
		super();
		this.user = user;
		this.sdm = sdm;

		// read the configuration parameters
		try {
			annomasterSettings = new AnnoMasterSettings();
		} catch (RuntimeException e) {
			GUIMessages.exceptionMessage(e);
			logger.log(Level.SEVERE, "", e);
			System.exit(0);
		}

		if (!annomasterSettings.TMP_DIR.isDirectory()) {
			if (!annomasterSettings.TMP_DIR.mkdir()) {
				GUIMessages.warnMessage("TMP-Directory could not be created: "
						+ annomasterSettings.TMP_DIR);
				System.exit(-1);
			}
		}
		try {
			initGUI();
		} catch (Exception e) {
			GUIMessages.exceptionMessage(e);
			logger.log(Level.SEVERE, "", e);
		}
	}

	private void initGUI() {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			BorderLayout thisLayout = new BorderLayout();
			getContentPane().setLayout(thisLayout);
			this.setTitle(FRAME_TITLE);
			this.addWindowListener(new WindowExitListener());
			this.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent evt) {
					try {
						DeleteDir.deleteContents(annomasterSettings.TMP_DIR);
					} catch (NullPointerException e) {
						GUIMessages.exceptionMessage(
								"error deleting contents from tmp dir", e);
						logger.log(Level.SEVERE,
								"error deleting contents from tmp dir", e);
					}
				}
			});
			{
				jDesktopPane1 = new JDesktopPane();
				getContentPane().add(jDesktopPane1, BorderLayout.CENTER);
			}
			{

				{
				}
			}
			{
				jMenuBar1 = new JMenuBar();
				setJMenuBar(jMenuBar1);
				jMenuBar1.setPreferredSize(new java.awt.Dimension(393, 34));
				{
					// MENU1 ^= show
					jMenu1 = new JMenu();
					jMenuBar1.add(jMenu1);
					jMenu1.setText("Show");
					jMenu1.setPreferredSize(new java.awt.Dimension(56, 7));
					{
						jMenuItem1 = new JMenuItem();
						jMenu1.add(jMenuItem1);
						jMenuItem1.setText("Projects");
						jMenuItem1.addActionListener(this);
					}
					{
						jMenuItem3 = new JMenuItem();
						jMenu1.add(jMenuItem3);
						jMenuItem3.setText("Users");
						jMenuItem3.addActionListener(this);
					}
					{
						jMenuItem2 = new JMenuItem();
						jMenu1.add(jMenuItem2);
						jMenuItem2.setText("Logs");
						jMenuItem2.addActionListener(this);
					}
				}

				// MENU2 ^= create
				{
					jMenu2 = new JMenu();
					jMenuBar1.add(jMenu2);
					jMenu2.setText("Create");
					{
						jMenuItem4 = new JMenuItem();
						jMenu2.add(jMenuItem4);
						jMenuItem4.setText("User");
						jMenuItem4.addActionListener(this);

					}
					{
						jMenuItem5 = new JMenuItem();
						jMenu2.add(jMenuItem5);
						jMenuItem5.setText("Project");
						jMenuItem5.addActionListener(this);
					}
				}
				{
					jMenu3 = new JMenu();
					jMenuBar1.add(jMenu3);
					jMenu3.setText("Analyze");
					{
						jMenuItem6 = new JMenuItem();
						jMenu3.add(jMenuItem6);
						jMenuItem6.setText("AL Plots");
						jMenuItem6.setVisible(false);
						jMenuItem6.addActionListener(this);
					}
					{
						jMenuItem7 = new JMenuItem();
						jMenu3.add(jMenuItem7);
						jMenuItem7.setText("IAA");
						jMenuItem7.addActionListener(this);
					}
				}
				{
					jMenu4 = new JMenu();
					jMenuBar1.add(jMenu4);
					jMenu4.setText("Help");
					{
						InfoMenuItem8 = new JMenuItem();
						jMenu4.add(InfoMenuItem8);
						InfoMenuItem8.setText("About");
						InfoMenuItem8.addActionListener(this);
					}
					{
						DocumentationMenuItem = new JMenuItem();
						jMenu4.add(DocumentationMenuItem);
						DocumentationMenuItem.setText("Documentation");
						DocumentationMenuItem.addActionListener(this);
					}
				}
			}
			pack();
			this.setSize(1069, 880);

	}

	private class WindowExitListener extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			try {
				new SQLFunctions(sdm).logout(user.getUserID());
			} catch (RuntimeException ex) {
				GUIMessages.exceptionMessage(ex);
				logger.log(Level.SEVERE, "", ex);
			}
			System.exit(0);
		}
	}

	public void actionPerformed(ActionEvent evt) {
		Object source = evt.getSource();
		JInternalFrame F = null;
		if (source.equals(jMenuItem5)) {
			F = new CreateProject(sdm);
		} else if (source.equals(jMenuItem4)) {
			F = new CreateUser(sdm);
		} else if (source.equals(jMenuItem3)) {
			F = new ShowUsersFrame(sdm);
		} else if (source.equals(jMenuItem1)) {
			F = new ShowProjectFrame(sdm);
		} else if (source.equals(jMenuItem7)) {
			F = new AnnotatorAgreementInternalFrame(sdm);
		} else if (source.equals(jMenuItem2)) {
			F = new ShowLogsFrame(sdm);
		} else if (source.equals(InfoMenuItem8)) {
			F = new HelpFrame(annomasterSettings.ANNOMASTER_ABOUT_HTML);
		} else if (source.equals(DocumentationMenuItem)) {
			F = new HelpFrame(annomasterSettings.ANNOMASTER_DOCU_HTML);
		}
		jDesktopPane1.add(F);
		jDesktopPane1.setPreferredSize(F.getPreferredSize());
		if (evt.getSource().equals(DocumentationMenuItem)
				|| evt.getSource().equals(InfoMenuItem8)) {
			F.setLocation(jDesktopPane1.getWidth() / 3 - F.getWidth() / 3,
					jDesktopPane1.getSize().height / 3 - F.getHeight() / 3);
		} else {
			F.setBounds(0, 0, 474, 296);
		}
		if (evt.getSource().equals(jMenuItem5)) {
			F.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		} else {
			F.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		}

		F.pack();
		F.setVisible(true);
		F.moveToFront();
	}

	/**
	 * main method for debugging
	 */
	public static void main(String[] args) {
		User u = null;
		SQLDatabaseManager dm = null;
		try {
			dm = new SQLDatabaseManager();
			u = new User(dm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		u.setUserId(36);
		AnnoMasterFrame inst;

		inst = new AnnoMasterFrame(u, dm);
		inst.setVisible(true);
	}
}
