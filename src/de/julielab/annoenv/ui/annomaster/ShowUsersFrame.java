/** 
 * ShowUsersFrame.java
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
 * Creation date: July, 2006 
 * 
 * Lists all users and further information about selected ones.
 **/

package de.julielab.annoenv.ui.annomaster;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;

import de.julielab.annoenv.db.annodata.User;
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
import javax.swing.JList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ListModel;
import javax.swing.WindowConstants;
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

/**
 * Lists all users and further information about selected ones.
 */
public class ShowUsersFrame extends InternalAnnoMasterFrame {

	private static Logger logger = AnnoEnvLogger
			.getLogger("de.julielab.annoenv.ui.annomaster.ShowUsersFrame");

	private JPanel jPanel1;

	private JScrollPane jScrollPane1;

	private JTextPane InfoPanel;

	private JList Userlist;

	/**
	 * main method for debugging
	 */
	public static void main(String[] args) {

		try {
			JFrame frame = new JFrame();
			ShowUsersFrame inst = null;
			inst = new ShowUsersFrame(new SQLDatabaseManager());
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

	public ShowUsersFrame(SQLDatabaseManager sdm) {
		super(sdm);
		initGUI();
	}

	private void initGUI() {
		try {

			this.setPreferredSize(new java.awt.Dimension(381, 271));
			this.setBounds(0, 0, 381, 271);

			this.setClosable(true);
			this.setPreferredSize(new java.awt.Dimension(704, 407));
			this.setBounds(25, 25, 704, 407);
			setVisible(true);
			this.setTitle("Users");

			jPanel1 = new JPanel();
			getContentPane().add(jPanel1, BorderLayout.CENTER);
			AnchorLayout jPanel1Layout = new AnchorLayout();
			jPanel1.setLayout(jPanel1Layout);
			jPanel1.setPreferredSize(new java.awt.Dimension(651, 357));
			{
				jScrollPane1 = new JScrollPane();
				jPanel1.add(jScrollPane1, new AnchorConstraint(56, 461, 809,
						60, AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL));
				jScrollPane1.setPreferredSize(new java.awt.Dimension(280, 287));
				jScrollPane1
						.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				{
					// Auslesen der User
					ArrayList Users;
					SQLFunctions AM = new SQLFunctions(sqlDatabaseM);
					Users = AM.retrieveAllUsers();
					Object[] data = Users.toArray();
					ListModel jLM = new DefaultComboBoxModel(data);
					Userlist = new JList();
					jScrollPane1.setViewportView(Userlist);
					Userlist.setModel(jLM);
					Userlist
							.addListSelectionListener(new ListSelectionListener() {
								public void valueChanged(ListSelectionEvent evt) {
									try {
										InfoPanel.setText("");
										String s = "";
										User U = (User) (Userlist
												.getSelectedValue());
										String uRole = (U.getSupervisor() == true) ? "supervisor"
												: "annotator";
										String uLoggedIn = (U.isSupervisor() == true) ? "User is logged in."
												: "User offline.";
										s = s + "Full Name: " + U.getFullName()
												+ "\n";
										s = s + "Login Name: "
												+ U.getLoginName() + "\n";
										s = s + "User ID: " + U.getUserID()
												+ "\n";
										s = s + "User Role: " + uRole + "\n";
										s = s
												+ "Last Login Date: "
												+ U.getLastLoginDate()
														.toString();
										s += "\n" + uLoggedIn;
										InfoPanel.setText(s);
										Userlist
												.setToolTipText(U.getFullName());
									} catch (RuntimeException e) {
										GUIMessages.exceptionMessage(e);
										logger.log(Level.SEVERE, "", e);
									}
								}
							});
				}
			}
			{
				InfoPanel = new JTextPane();
				jPanel1.add(InfoPanel, new AnchorConstraint(56, 902, 809, 501,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL));
				InfoPanel.setPreferredSize(new java.awt.Dimension(280, 287));
			}

		} catch (RuntimeException e) {
			GUIMessages.exceptionMessage(e);
			logger.log(Level.SEVERE, "", e);
		}
	}
}
