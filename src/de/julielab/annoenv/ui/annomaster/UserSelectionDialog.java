/** 
 * UserSelectionDialog.java
 * 
 * Copyright (c) 2007, JULIE Lab. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0 
 *
 * Author: Klaue
 * 
 * Current version: 0.9
 * Since version:   0.9
 *
 * Creation date: April, 2007 
 * 
 * Change the user belonging to a project via this dialog.
 **/

package de.julielab.annoenv.ui.annomaster;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.xml.parsers.ParserConfigurationException;

import de.julielab.annoenv.db.annodata.User;
import de.julielab.annoenv.db.sql.SQLDatabaseManager;
import de.julielab.annoenv.db.sql.SQLFunctions;
import de.julielab.annoenv.ui.GUIMessages;
import de.julielab.annoenv.utils.AnnoEnvLogger;

public class UserSelectionDialog extends javax.swing.JDialog {

	private static final String FRAME_TITLE = "Change User";

	private static Logger logger = AnnoEnvLogger
			.getLogger("de.julielab.annoenv.ui.annomaster.UserSelectionDialog");

	private JComboBox UserBox;

	private String userName;

	private JPanel jPanel1;

	private JButton CancelButton;

	private JButton OKButton;

	private User user;

	private int id;

	private int exitStatus;

	public static int OK = 1;

	public static int CANCEL = 0;


	public UserSelectionDialog(JFrame frame, SQLDatabaseManager sdm, String userName) {
		super(frame);
		this.userName = userName;
		id = -1;
		this.exitStatus = UserSelectionDialog.CANCEL;
		try {
		initGUI(sdm);
		} catch (RuntimeException e) {
			GUIMessages.exceptionMessage(e);
			logger.log(Level.SEVERE, "", e);
		}
	}

	public int getUserID() {
		return id;
	}

	public int getExitStatus() {
		return exitStatus;
	}

	private void initGUI(SQLDatabaseManager sdm) {
		
			{
				AnchorLayout thisLayout = new AnchorLayout();
				getContentPane().setLayout(thisLayout);
				this.setTitle(FRAME_TITLE);
				this.setResizable(false);
				this.setModal(true);
				{
					jPanel1 = new JPanel();
					getContentPane().add(
							jPanel1,
							new AnchorConstraint(5, 1001, 963, 1,
									AnchorConstraint.ANCHOR_REL,
									AnchorConstraint.ANCHOR_REL,
									AnchorConstraint.ANCHOR_REL,
									AnchorConstraint.ANCHOR_REL));
					jPanel1.setPreferredSize(new java.awt.Dimension(284, 91));
					jPanel1.setLayout(null);
					{
						ArrayList Users;
						SQLFunctions AM = new SQLFunctions(sdm);
						user = AM.retrieveUser(userName);
						Users = AM.retrieveAllUsers();
						Object[] data = Users.toArray();
						UserBox = new JComboBox(data);
						jPanel1.add(UserBox);
						UserBox.setSelectedItem(user);
						UserBox.setBounds(14, 7, 259, 28);
					}
					{
						OKButton = new JButton();
						jPanel1.add(OKButton);
						OKButton.setText("OK");
						OKButton.setBounds(14, 49, 84, 28);
						OKButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								User u = (User) UserBox.getSelectedItem();
								exitStatus = UserSelectionDialog.OK;
								if (u != null) {
									id = u.getUserID();
									dispose();
								}
							}
						});
					}
					{
						CancelButton = new JButton();
						jPanel1.add(CancelButton);
						CancelButton.setText("Cancel");
						CancelButton.setBounds(189, 49, 84, 28);
						CancelButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								exitStatus = UserSelectionDialog.CANCEL;
								dispose();
							}
						});
					}
				}
			}
			this.setSize(291, 132);

	}

	/**
	 * main method for debugging purposes
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		UserSelectionDialog inst = new UserSelectionDialog(frame, new SQLDatabaseManager(), "katrin");
		inst.setVisible(true);
	}

}
