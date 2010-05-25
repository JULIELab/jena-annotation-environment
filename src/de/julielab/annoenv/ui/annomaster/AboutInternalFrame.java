/** 
 * AboutInternalFrame.java
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
import java.awt.BorderLayout;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import de.julielab.annoenv.ui.GUIMessages;
import de.julielab.annoenv.utils.AnnoEnvLogger;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/

/**
  * @deprecated HelpFrame is providing information about this software now.
 * @see annomaster.HelpFrame
 */
public class AboutInternalFrame extends javax.swing.JInternalFrame {
	
	private static Logger logger = AnnoEnvLogger
	.getLogger("de.julielab.annoenv.ui.annomaster.AboutInternalFrame");
	
	private JTextArea jTextArea1;
	public static final String ANNOMASTER = "AnnoMaster";
	public static final String ANNOCLIENT = "AnnoClient";
	private String type;

	static final String ABOUTTEXTMASTER = "\n              AnnoMaster\n            " +
			"Version 0.9\n    " +
			"Release date: 23.05.2007\n\n        " +
			"(c) 2007 JULIE Lab";
	static final String ABOUTTEXTCLIENT = "\n              AnnoClient\n            " +
			"Version 0.9\n    " +
			"Release date: 23.05.2007\n\n        " +
			"(c) 2007 JULIE Lab";
	
	/**
	* Auto-generated main method to display this 
	* JInternalFrame inside a new JFrame.
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		AboutInternalFrame inst = new AboutInternalFrame(AboutInternalFrame.ANNOCLIENT);
		JDesktopPane jdp = new JDesktopPane();
		jdp.add(inst);
		jdp.setPreferredSize(inst.getPreferredSize());
		frame.setContentPane(jdp);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public AboutInternalFrame(String type) {
		super();
		this.type = type;
		try {
			initGUI();
		} catch (Exception e) {
			GUIMessages.exceptionMessage(e);
			logger.log(Level.SEVERE, "", e);
		}
	}
	
	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(200, 150));
			this.setBounds(25, 25, 200, 150);
			setVisible(true);
			this.setTitle("About " + type);
			this.setClosable(true);
			this.setLocation(new java.awt.Point(0, 100));
			{
				jTextArea1 = new JTextArea();
				getContentPane().add(jTextArea1, BorderLayout.CENTER);
				jTextArea1.setEditable(false);

				jTextArea1.setText(getText());
				jTextArea1.setLineWrap(true);
			}
		} catch (Exception e) {
			GUIMessages.exceptionMessage(e);
			logger.log(Level.SEVERE, "", e);
		}
	}
	
	private String getText(){
		if(type.equals(AboutInternalFrame.ANNOMASTER)) {
			return ABOUTTEXTMASTER;
		} else {
			return ABOUTTEXTCLIENT;
		}
	}
}
