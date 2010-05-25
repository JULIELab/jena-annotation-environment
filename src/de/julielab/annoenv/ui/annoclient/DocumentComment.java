/** 
 * DocumentComment.java
 * 
 * Copyright (c) 2007, JULIE Lab. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0 
 *
 * Author: Klaue
 * 
 * Current version: 2.0
 * Since version:   1.1
 *
 * Creation date: June, 2007 
 * 

 **/

package de.julielab.annoenv.ui.annoclient;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;
import java.awt.BorderLayout;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import de.julielab.annoenv.db.annodata.BaseDataLight;

import de.julielab.annoenv.ui.GUIMessages;
import de.julielab.annoenv.utils.AnnoEnvLogger;

/**
 * In this frame, comments/description of the basedata are shown.
 */
public class DocumentComment extends javax.swing.JFrame {

	private static final String FRAME_TITLE = "Comment for document.";

	private static Logger logger = AnnoEnvLogger
			.getLogger("de.julielab.annoenv.ui.annoclient.DescriptionFrame");

	private JScrollPane jScrollPane1;

	private JPanel jPanel1;

	private JButton EnterButton;

	private JPanel jPanel2;

	private JTextArea jTextArea1;

	private BaseDataLight basedata;

	/**
	 * Auto-generated main method to display this JInternalFrame inside a new
	 * JFrame.
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		DocumentComment inst = new DocumentComment();
		JDesktopPane jdp = new JDesktopPane();
		jdp.add(inst);
		jdp.setPreferredSize(inst.getPreferredSize());
		frame.setContentPane(jdp);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public DocumentComment() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(380, 116));
			this.setBounds(0, 0, 380, 116);
			this.setResizable(true);
			BorderLayout thisLayout = new BorderLayout();
			getContentPane().setLayout(thisLayout);
			this.setTitle(FRAME_TITLE);

			{
				jPanel1 = new JPanel();
				BoxLayout jPanel1Layout = new BoxLayout(jPanel1,
						javax.swing.BoxLayout.Y_AXIS);
				jPanel1.setLayout(jPanel1Layout);
				getContentPane().add(jPanel1, BorderLayout.CENTER);
				jPanel1.setPreferredSize(new java.awt.Dimension(332, 86));
				{
					jScrollPane1 = new JScrollPane();
					jPanel1.add(jScrollPane1);
					jScrollPane1.setPreferredSize(new java.awt.Dimension(426,
							71));
					{
						jTextArea1 = new JTextArea();
						jScrollPane1.setViewportView(jTextArea1);
						jTextArea1.setText("jTextArea1");
						;
						jTextArea1.setLineWrap(true);
					}
				}
				{
					jPanel2 = new JPanel();
					AnchorLayout jPanel2Layout = new AnchorLayout();
					jPanel2.setLayout(jPanel2Layout);
					jPanel1.add(jPanel2);
					jPanel2.setPreferredSize(new java.awt.Dimension(357, 21));
					{
						EnterButton = new JButton();
						jPanel2.add(EnterButton, new AnchorConstraint(23, 979,
								1023, 470, AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
						EnterButton.setText("Save Comment");
						EnterButton.setPreferredSize(new java.awt.Dimension(
								182, 21));
					}
				}
			}
		} catch (RuntimeException e) {
			GUIMessages.exceptionMessage(e);
			logger.log(Level.SEVERE, "", e);
		}
	}

	public void setDescription(String s, BaseDataLight basedata) {
		jTextArea1.setText(s);
		this.basedata = basedata;
		this.setTitle("Comment for document " + " " + basedata.getUri());
	}

	public BaseDataLight getBaseData() {
		return this.basedata;
	}

	public String getDescription() {
		return jTextArea1.getText();
	}

	public JTextArea getTextArea() {
		return this.jTextArea1;
	}

	public JButton getButton() {
		return this.EnterButton;
	}
}
