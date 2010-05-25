/** 
 * ToggleSelectionModel.java
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
 * TODO: comment
 **/

package de.julielab.annoenv.ui.annomaster;

import javax.swing.DefaultListSelectionModel;


class ToggleSelectionModel extends DefaultListSelectionModel
{
    boolean gestureStarted = false;
    
    public void setSelectionInterval(int index0, int index1) {
        if (isSelectedIndex(index0) && !gestureStarted) {
            super.removeSelectionInterval(index0, index1);
        }
        else {
            super.setSelectionInterval(index0, index1);
        }
        gestureStarted = true;
    }

    public void setValueIsAdjusting(boolean isAdjusting) {
        if (isAdjusting == false) {
            gestureStarted = false;
        }
    }
}