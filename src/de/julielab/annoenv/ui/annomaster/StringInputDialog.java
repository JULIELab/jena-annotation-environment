/** 
 * StringInputDialog.java
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
 * Creation date: February, 2007 
 * 

 **/

package de.julielab.annoenv.ui.annomaster;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;

import de.julielab.annoenv.ui.GUIMessages;
import de.julielab.annoenv.utils.AnnoEnvLogger;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;

import javax.swing.JFrame;

import javax.swing.JPanel;
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
 * Dialog for text input that needs verification. Verification using regular
 * expressions.

 */
public class StringInputDialog extends javax.swing.JDialog {

	private static Logger logger = AnnoEnvLogger
			.getLogger("de.julielab.annoenv.ui.annomaster.StringInputDialog");

	private static final long serialVersionUID = 1L;

	private String title;

	private static String input;

	private JPanel jPanel1;

	private JTextField InputField;

	private JButton CancelButton;

	private JButton OKButton;

	private String regex;

	private String previousEntry;

	public StringInputDialog(JFrame frame, String title, String previousEntry,
			String regex) {
		super(frame);
		this.title = title;
		this.regex = regex;
		this.previousEntry = previousEntry;
		input = "-1";
		initGUI();
	}

	private void initGUI() {
		try {
			this.setTitle(this.title);
			{
				jPanel1 = new JPanel();
				AnchorLayout jPanel1Layout = new AnchorLayout();
				getContentPane().add(jPanel1, BorderLayout.CENTER);
				jPanel1.setLayout(jPanel1Layout);
				jPanel1.setPreferredSize(new java.awt.Dimension(301, 126));
				jPanel1.setLayout(jPanel1Layout);
				{
					OKButton = new JButton();
					jPanel1.add(OKButton, new AnchorConstraint(634, 502, 949,
							44, AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					OKButton.setText("OK");
					OKButton.setPreferredSize(new java.awt.Dimension(77, 28));
					OKButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							if (!java.util.regex.Pattern.matches(regex,
									InputField.getText())) {
								InputField.setText("");
							} else {
								dispose();
								input = InputField.getText();
							}
						}
					});
				}
				{
					CancelButton = new JButton();
					jPanel1.add(CancelButton, new AnchorConstraint(634, 961,
							949, 502, AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					CancelButton.setText("Cancel");
					CancelButton
							.setPreferredSize(new java.awt.Dimension(77, 28));
					CancelButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							dispose();
							input = "-1";
						}
					});
				}
				{
					InputField = new JTextField();
					jPanel1.add(InputField, new AnchorConstraint(162, 961, 477,
							44, AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					InputField
							.setPreferredSize(new java.awt.Dimension(154, 28));
					InputField.setText(previousEntry);

				}
			}
			this.setSize(175, 123);
		} catch (RuntimeException e) {
			GUIMessages.exceptionMessage(e);
			logger.log(Level.SEVERE, "", e);
		}
	}

	public static String showStringInputDialog(JFrame frame, String title,
			String previousEntry, String regex) {
		StringInputDialog dialog = new StringInputDialog(frame, title,
				previousEntry, regex);
		dialog.setModal(true);
		dialog.setVisible(true);
		return input;
	}

	/**
	 * main method for debugging
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		StringInputDialog inst = new StringInputDialog(frame, "Testing...",
				"!ancient artifacts!", "(\\d){1,9}");
		inst.setVisible(true);
	}
}
