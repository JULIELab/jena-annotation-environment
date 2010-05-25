/** 
 * AnnoClientLogFrame.java
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
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BoundedRangeModel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import de.julielab.annoenv.ui.GUIMessages;
import de.julielab.annoenv.utils.AnnoEnvLogger;

/**
 * a generic log frame which is used to show MMAX messages and the log entries during an AL run
 */
public class AnnoClientLogFrame extends javax.swing.JFrame {

	private static Logger logger = AnnoEnvLogger
			.getLogger("de.julielab.annoenv.ui.annoclient.AnnoClientLogFrame");

	private static final long serialVersionUID = 1L;

	private JTextArea OutputArea;

	private String text;

	private JScrollPane jScrollPane1;

	private int maxScroll;

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		AnnoClientLogFrame inst = new AnnoClientLogFrame(
				"some lil some somethin'");
		inst.setVisible(true);
	}

	public AnnoClientLogFrame(String text) {
		super();
		this.text = text;
		try {
			initGUI();
		} catch (RuntimeException e) {
			GUIMessages.exceptionMessage(e);
			logger.log(Level.SEVERE, "", e);
		}

	}

	private void initGUI() {

		this.setLocation(100, 700);
		BorderLayout thisLayout = new BorderLayout();
		getContentPane().setLayout(thisLayout);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setResizable(true);
		this.setTitle(text);

		jScrollPane1 = new JScrollPane();
		getContentPane().add(jScrollPane1, BorderLayout.CENTER);
		jScrollPane1.setPreferredSize(new java.awt.Dimension(549, 17));
		maxScroll = jScrollPane1.getVerticalScrollBar().getModel().getMaximum();
		jScrollPane1.getVerticalScrollBar().getModel().addChangeListener(
				new ChangeListener() {
					public void stateChanged(ChangeEvent e) {
						if (((BoundedRangeModel) e.getSource()).getMaximum() > maxScroll) {
							jScrollPane1.getVerticalScrollBar().setValue(
									jScrollPane1.getVerticalScrollBar()
											.getMaximum());
							maxScroll = jScrollPane1.getVerticalScrollBar()
									.getMaximum();
						}
					}

				});

		OutputArea = new JTextArea(text);
		jScrollPane1.setViewportView(OutputArea);
		OutputArea.setLineWrap(true);
		OutputArea.setWrapStyleWord(true);
		OutputArea.setBackground(new java.awt.Color(26, 26, 26));
		OutputArea.setForeground(new java.awt.Color(230, 230, 250));

		pack();
		this.setSize(580, 209);

	}

	public String getText() {
		return OutputArea.getText();
	}

	/**
	 * @param addOn
	 *            String that is to be added to displayed text
	 */
	public void appendText(String addOn) {
		OutputArea.append(addOn);
		jScrollPane1.getVerticalScrollBar().setValue(
				jScrollPane1.getVerticalScrollBar().getMaximum());
	}

	/**
	 * @param text
	 *            String replacing the currently desplayed text
	 */
	public void setText(String text) {
		OutputArea.setText(text);
		jScrollPane1.getVerticalScrollBar().setValue(
				jScrollPane1.getVerticalScrollBar().getMinimum());
	}
}
