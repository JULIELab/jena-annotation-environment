/** 
 * CreateUser.java
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

import de.julielab.annoenv.db.annodata.User;
import de.julielab.annoenv.db.sql.SQLDatabaseManager;
import de.julielab.annoenv.ui.GUIMessages;
import de.julielab.annoenv.utils.AnnoEnvLogger;

import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;


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
 * GUI for user creation.
 */
public class CreateUser extends InternalAnnoMasterFrame implements
		FocusListener, CaretListener {

	private static final String FRAME_TITLE = "Create new user";

	private static Logger logger = AnnoEnvLogger
			.getLogger("de.julielab.annoenv.ui.annomaster.CreateUser");

	private JPanel jPanel1;

	private JTextField LoginField;

	private JTextField FNameField;

	private JButton CancelButton;

	private JButton SaveButton;

	private JTextField PasswordField;

	private JLabel FullNameLabel;

	private JLabel PWDLabel;

	private JLabel LoginLabel;

	private boolean COMPLETE;

	private boolean COMPLETE1 = false;

	private boolean COMPLETE2 = false;

	private boolean COMPLETE3 = false;


	public CreateUser(SQLDatabaseManager sdm) {
		super(sdm);
		COMPLETE = false;
		try {
			initGUI();
		} catch (RuntimeException e) {
			GUIMessages.exceptionMessage(e);
			logger.log(Level.SEVERE, "", e);
		}
			
	}

	private void clean() {
		this.LoginField.setText("");
		this.PasswordField.setText("");
		this.FNameField.setText("");
		this.SaveButton.setEnabled(false);
	}

	private void initGUI() {
		
		try {
			
			this.setPreferredSize(new java.awt.Dimension(507, 348));
			this.setBounds(25, 25, 507, 348);
			setVisible(true);
			this.setTitle(FRAME_TITLE);
			this.setClosable(true);
			{
				jPanel1 = new JPanel();
				getContentPane().add(jPanel1, BorderLayout.CENTER);
				jPanel1.setLayout(null);
				jPanel1.setPreferredSize(new java.awt.Dimension(392, 273));
				{
					PasswordField = new JTextField();
					jPanel1.add(PasswordField);
					PasswordField.setBounds(189, 77, 140, 28);
					PasswordField.addCaretListener(this);
					/*
					 * PasswordField.addFocusListener(new FocusAdapter() {
					 * 
					 * });
					 */
					PasswordField.addFocusListener(this);
					PasswordField.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							COMPLETE2 = (!PasswordField.getText().toString()
									.equals(""));
							COMPLETE = COMPLETE1 && (COMPLETE2 && COMPLETE3);
							SaveButton.setEnabled(COMPLETE);
						}
					});
				}
				{
					FNameField = new JTextField();
					jPanel1.add(FNameField);
					FNameField.setBounds(189, 140, 196, 28);
					FNameField.addCaretListener(this);
					FNameField.addFocusListener(this);
					FNameField.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							COMPLETE3 = (!FNameField.getText().equals(""));
							COMPLETE = (COMPLETE1 && COMPLETE2) && COMPLETE3;
							SaveButton.setEnabled(COMPLETE);
						}
					});
					/*
					 * FNameField.addFocusListener(new FocusAdapter() { public
					 * void focusLost(FocusEvent evt) { COMPLETE3 =
					 * (!FNameField.getText().equals("")); COMPLETE = COMPLETE1 &&
					 * (COMPLETE2 && COMPLETE3);
					 * SaveButton.setEnabled(COMPLETE); } });
					 */
				}
				{
					LoginField = new JTextField();
					jPanel1.add(LoginField);
					LoginField
							.setPreferredSize(new java.awt.Dimension(140, 28));
					LoginField.setBounds(189, 35, 140, 28);
					LoginField.addCaretListener(this);
					LoginField.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							COMPLETE1 = (!LoginField.getText().equals(""));
							COMPLETE = COMPLETE3 && (COMPLETE2 && COMPLETE1);
							SaveButton.setEnabled(COMPLETE);
						}
					});
					LoginField.addFocusListener(this);
				}
				{
					CancelButton = new JButton();
					jPanel1.add(CancelButton);
					CancelButton.setText("Cancel");
					CancelButton.setPreferredSize(new java.awt.Dimension(105,
							35));
					CancelButton.setBounds(322, 245, 105, 35);
					CancelButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							try {
								setClosed(true);
							} catch (Exception e) {
								GUIMessages.exceptionMessage(e);
								logger.log(Level.SEVERE, "", e);
							}
						}
					});
				}
				{
					SaveButton = new JButton();
					jPanel1.add(SaveButton);
					SaveButton.setText("Save");
					SaveButton.setPreferredSize(new java.awt.Dimension(98, 35));
					SaveButton.setEnabled(false);
					SaveButton.setBounds(98, 245, 98, 35);
					SaveButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							if (FNameField.getText().equals("")
									|| LoginField.getText().equals(""))
								GUIMessages
										.warnMessage("data missing!");
							else {
								try {
									User U = new User(sqlDatabaseM);
									U.setFullName(FNameField.getText());
									U.setLoginName(LoginField.getText());
									U.setPasswd(PasswordField.getText()
											.toString());
									U.write();
									GUIMessages
											.infoMessage("User "
													+ U.getLoginName()
													+ " has been created succesfully!");
								} catch (RuntimeException e) {
									GUIMessages.exceptionMessage("error creating new user",e);
									logger.log(Level.SEVERE, "error creating new user", e);
								}
								clean();
							}
						}
					});
				}
				{
					FullNameLabel = new JLabel();
					jPanel1.add(FullNameLabel);
					FullNameLabel.setText("full name");
					FullNameLabel.setBounds(56, 140, 133, 35);
				}
				{
					PWDLabel = new JLabel();
					jPanel1.add(PWDLabel);
					PWDLabel.setText("password");
					PWDLabel.setBounds(56, 77, 133, 35);
				}
				{
					LoginLabel = new JLabel();
					jPanel1.add(LoginLabel);
					LoginLabel.setText("login name");
					LoginLabel
							.setPreferredSize(new java.awt.Dimension(133, 35));
					LoginLabel.setBounds(56, 35, 133, 35);
				}
			}
		} catch (RuntimeException e) {
			GUIMessages.exceptionMessage(e);
			logger.log(Level.SEVERE, "", e);
		}
	}

	public void focusLost(FocusEvent evt) {
		SaveButton.setEnabled(isComplete());
	}

	public void caretUpdate(CaretEvent evt) {
		SaveButton.setEnabled(isComplete());
	}

	private boolean isComplete() {
		return (!LoginField.getText().equals("") && !PasswordField.getText()
				.equals(""))
				&& !FNameField.getText().equals("");
	}

	public void focusGained(FocusEvent evt) {
	}
	
	
	/**
	 * main method for debugging
	 */
	public static void main(String[] args) {
		try {
			JFrame frame = new JFrame();
			CreateUser inst = null;
			inst = new CreateUser(new SQLDatabaseManager());
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
