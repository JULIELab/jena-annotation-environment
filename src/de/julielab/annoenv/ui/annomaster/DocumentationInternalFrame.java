/** 
 * DocumentationInternalFrame.java
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
 * @deprecated DocumentationInternalFrame is replaced by annomaster.HelpFrame 
 * @see annomaster.HelpFrame
 **/

package de.julielab.annoenv.ui.annomaster;

import java.awt.BorderLayout;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

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
public class DocumentationInternalFrame extends javax.swing.JInternalFrame {

	private static final String FRAME_TITLE = "Documentation";

	private static Logger logger = AnnoEnvLogger
			.getLogger("de.julielab.annoenv.ui.annomaster.DocumentationInternalFrame");

	private JTextArea jTextArea1;

	private JScrollPane jScrollPane1;

	private String type;

	public static final String ANNOMASTER = "AnnoMaster";

	public static final String ANNOCLIENT = "AnnoClient";

	static final String DOCTEXTCLIENT = "\n\t\t\tAnnoMaster documentation\n https://watchtower.coling.uni-jena.de/index.php/AnnoEnv_User_How-To#AnnoClient";

	static final String DOCTEXTMASTER = "\n\t\t\tAnnoMaster documentation\n https://watchtower.coling.uni-jena.de/index.php/AnnoEnv_User_How-To#AnnoMaster";

	public DocumentationInternalFrame(String type) {
		super();
		this.type = type;
		initGUI();
	}

	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(700, 150));
			this.setBounds(0, 0, 700, 150);
			setVisible(true);
			this.setTitle(FRAME_TITLE);
			this.setClosable(true);
			{
				jScrollPane1 = new JScrollPane();
				getContentPane().add(jScrollPane1, BorderLayout.NORTH);
				jScrollPane1
						.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				jScrollPane1.setPreferredSize(new java.awt.Dimension(364, 276));
				{
					jTextArea1 = new JTextArea();
					jScrollPane1.setViewportView(jTextArea1);
					jTextArea1.setEditable(false);
					jTextArea1.setText(getText());
					jTextArea1.setLineWrap(true);
					jTextArea1
							.setPreferredSize(new java.awt.Dimension(365, 273));
				}
			}
		} catch (RuntimeException e) {
			GUIMessages.exceptionMessage(e);
			logger.log(Level.SEVERE, "", e);
		}
	}

	private String getText() {
		if (type.equals(DocumentationInternalFrame.ANNOMASTER)) {
			return DOCTEXTMASTER;
		}

		return DOCTEXTCLIENT;
	}

	/**
	 * main method for debugging
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		DocumentationInternalFrame inst = new DocumentationInternalFrame(
				DocumentationInternalFrame.ANNOCLIENT);
		JDesktopPane jdp = new JDesktopPane();
		jdp.add(inst);
		jdp.setPreferredSize(inst.getPreferredSize());
		frame.setContentPane(jdp);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
