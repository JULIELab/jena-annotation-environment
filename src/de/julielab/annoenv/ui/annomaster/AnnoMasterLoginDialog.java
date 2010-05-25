/** 
 * AnnoMasterLoginDialog.java
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
 * Creation date: March, 2007 
 * 

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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.KeyStroke;

/**
 * Access to AnnoMaster is granted by this login dialog.
 */
public class AnnoMasterLoginDialog extends javax.swing.JDialog {

	private static final String FRAME_TITLE = "JANE Administration GUI -- Login";

	private static Logger logger = AnnoEnvLogger
			.getLogger("de.julielab.annoenv.ui.annomaster.AnnoMasterLoginDialog");

	private JPanel jPanel1;

	private JLabel PassswordLabel;

	private JLabel AccountLabel;

	private JButton OKButton;

	private JComboBox UserBox;

	private JPasswordField PasswordField;

	private ArrayList<User> userList;

	private SQLDatabaseManager sdm;

	private InputMap map;

	{
		// Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager
					.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			logger.log(Level.SEVERE, "error setting look and feel", e);
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		AnnoMasterLoginDialog inst = new AnnoMasterLoginDialog(frame);
		inst.setVisible(true);
		frame.dispose();
	}

	public AnnoMasterLoginDialog(JFrame frame) {
		super(frame);
		try {
			sdm = new SQLDatabaseManager();
			userList = (new SQLFunctions(sdm)).retrieveAllUsers();
			if (userList.isEmpty()) {
				throw new RuntimeException(
						"Empty user list -- error occurred loading users from db!");
			}
			// new Arraylist only consisting of supervisors
			ArrayList<User> l = new ArrayList<User>();
			for (User u : userList) {
				if (u.isSupervisor()) {
					l.add(u);
				}
			}
			userList = l;

			if (userList.isEmpty())
				GUIMessages
						.warnMessage("No supervisors found!");

		} catch (Exception e) {
			GUIMessages.exceptionMessage(e);
			logger.log(Level.SEVERE, "", e);
		}
		initGUI();
	}

	private void initGUI() {
		try {
			{
				this.setTitle(FRAME_TITLE);
				this.setModal(true);
				map = new InputMap();
				{
					jPanel1 = new JPanel();
					getContentPane().add(jPanel1, BorderLayout.CENTER);
					AnchorLayout jPanel1Layout = new AnchorLayout();
					jPanel1.setLayout(jPanel1Layout);
					jPanel1.setPreferredSize(new java.awt.Dimension(273, 133));
					{
						PassswordLabel = new JLabel();
						jPanel1.add(PassswordLabel, new AnchorConstraint(397,
								272, 595, 43, AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
						PassswordLabel.setText("password:");
						PassswordLabel.setPreferredSize(new java.awt.Dimension(
								77, 28));
					}
					{
						AccountLabel = new JLabel();
						jPanel1.add(AccountLabel, new AnchorConstraint(102,
								272, 299, 43, AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
						AccountLabel.setText("super user: ");
						AccountLabel.setPreferredSize(new java.awt.Dimension(
								77, 28));
					}
					{
						OKButton = new JButton();
						jPanel1.add(OKButton, new AnchorConstraint(742, 563,
								940, 293, AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
						OKButton.setText("Login");
						OKButton
								.setPreferredSize(new java.awt.Dimension(91, 28));
						OKEventListener listener = new OKEventListener();
						OKButton.addActionListener(listener);
						OKButton.registerKeyboardAction(listener, KeyStroke
								.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
								JComponent.WHEN_IN_FOCUSED_WINDOW);
					}
					{
						UserBox = new JComboBox(userList.toArray());
						jPanel1.add(UserBox, new AnchorConstraint(102, 938,
								299, 293, AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
						UserBox
								.setPreferredSize(new java.awt.Dimension(217,
										28));
						UserBox.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								if (UserBox.getSelectedItem() != null) {
									PasswordField.setEditable(true);
								}
							}
						});
					}
					{
						PasswordField = new JPasswordField();
						jPanel1.add(PasswordField, new AnchorConstraint(397,
								938, 595, 293, AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
						PasswordField.setPreferredSize(new java.awt.Dimension(
								217, 28));
						PasswordField
								.setEditable(UserBox.getSelectedItem() != null);
					}
					jPanel1.setInputMap(JComponent.WHEN_FOCUSED, map);
					UserBox.setInputMap(JComboBox.WHEN_FOCUSED, map);
				}
			}
			this.setSize(343, 179);
		} catch (RuntimeException e) {
			GUIMessages.exceptionMessage(e);
			logger.log(Level.SEVERE, "", e);
		}
	}

	private class OKEventListener implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			try {
				if ((UserBox.getSelectedItem() != null)
						&& (!PasswordField.getPassword().equals(""))) {
					User user = (User) UserBox.getSelectedItem();
					String name = user.getLoginName();
					String pwd = new String(PasswordField.getPassword());
					int value = -1;

					value = new SQLFunctions(sdm).login(name, pwd);
					if (value != -1) {
						(new AnnoMasterFrame(user, sdm)).setVisible(true);
						dispose();
					}
				}
			} catch (RuntimeException e) {
				GUIMessages.exceptionMessage(e);
				logger.log(Level.SEVERE, "", e);
			}

		}
	}
}