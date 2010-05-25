/** 
 * ImageViewer.java
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
 * Creation date: December, 2006 
 * 
 **/

package de.julielab.annoenv.ui.annomaster;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.JComponent;

/**
 * Component for showing images.
 * 
 */
public class ImageViewer extends JComponent {
	private Image image;

	public void setImage(File file) {
		image = Toolkit.getDefaultToolkit().getImage(file.getAbsolutePath());
		if (image != null)
			repaint();
	}

	protected void paintComponent(Graphics g) {
		if (image != null)
			g.drawImage(image, 0, 0, this);
	}
}
