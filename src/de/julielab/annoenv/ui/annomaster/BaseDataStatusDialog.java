/** 
 * BaseDataStatusDialog.java
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

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;

import de.julielab.annoenv.db.annodata.BaseDataLight;
import de.julielab.annoenv.db.sql.SQLDatabaseManager;
import de.julielab.annoenv.db.sql.SQLFunctions;
import de.julielab.annoenv.ui.GUIMessages;
import de.julielab.annoenv.utils.AnnoEnvLogger;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenuItem;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * small dialog window that shows the status of the basedata entries of a
 * project
 */
public class BaseDataStatusDialog extends javax.swing.JDialog {
	private static final String FRAME_TITLE = "Change BaseData Status";

	private static Logger logger = AnnoEnvLogger
			.getLogger("de.julielab.annoenv.ui.annomaster.BaseDataStatusDialog");

	private static final long serialVersionUID = 1L;

	private JPanel jPanel1;

	private JMenuItem ProgressMenuItem;

	private JMenuItem DoneMenuItem;

	private JMenuItem RawMenuItem;

	private JPopupMenu jPopupMenu1;

	private JButton CancelButton;

	private JButton OKButton;

	private JList BaseDataList;

	private JScrollPane jScrollPane1;

	private ArrayList<BaseDataLight> bdList;

	private ArrayList<BaseDataLight> changeList;

	private int exitStatus;

	public static int OK = 1;

	public static int CANCEL = 0;

	public BaseDataStatusDialog(JFrame frame, ArrayList<BaseDataLight> bdList,
			SQLDatabaseManager sdm) {
		super(frame);
		this.bdList = bdList;
		this.changeList = new ArrayList<BaseDataLight>();
		this.exitStatus = BaseDataStatusDialog.CANCEL;
		initGUI(sdm);
	}

	private void initGUI(final SQLDatabaseManager sdm) {
		try {

			this.setModal(true);
			this.setResizable(false);
			this.setTitle(FRAME_TITLE);
			{
				jPanel1 = new JPanel();
				getContentPane().add(jPanel1, BorderLayout.CENTER);
				AnchorLayout jPanel1Layout = new AnchorLayout();
				jPanel1.setLayout(jPanel1Layout);
				jPanel1.setPreferredSize(new java.awt.Dimension(303, 251));
				{
					CancelButton = new JButton();
					jPanel1.add(CancelButton, new AnchorConstraint(814, 913,
							931, 646, AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					CancelButton.setText("Cancel");
					CancelButton
							.setPreferredSize(new java.awt.Dimension(82, 28));
					CancelButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							exitStatus = BaseDataStatusDialog.CANCEL;
							BaseDataStatusDialog.this.dispose();
						}
					});
				}
				{
					OKButton = new JButton();
					jPanel1.add(OKButton, new AnchorConstraint(814, 627, 931,
							366, AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					OKButton.setText("OK");
					OKButton.setPreferredSize(new java.awt.Dimension(80, 28));
					OKButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							exitStatus = BaseDataStatusDialog.OK;
							SwingUtilities.invokeLater(new Runnable() {
								public void run() {
									for (int i = 0; i < changeList.size(); i++) {
										try {
											changeList.get(i).updateBaseData(
													sdm);
										} catch (RuntimeException e) {
											GUIMessages.exceptionMessage(e);
											logger.log(Level.SEVERE, "", e);
										}
									}
								}
							});
							BaseDataStatusDialog.this.dispose();
						}
					});
				}
				{
					jScrollPane1 = new JScrollPane();
					jPanel1.add(jScrollPane1, new AnchorConstraint(47, 962,
							764, 37, AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					jScrollPane1.setPreferredSize(new java.awt.Dimension(284,
							172));
					{
						ListModel BaseDataListModel = new DefaultComboBoxModel(
								bdList.toArray());
						BaseDataList = new JList();
						jScrollPane1.setViewportView(BaseDataList);
						BaseDataList.setModel(BaseDataListModel);
						BaseDataList
								.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
						{
							jPopupMenu1 = new JPopupMenu();
							setComponentPopupMenu(BaseDataList, jPopupMenu1);
							{
								RawMenuItem = new JMenuItem();
								jPopupMenu1.add(RawMenuItem);
								RawMenuItem.setText("raw");
								RawMenuItem
										.addActionListener(new PopupItemListener());
							}
							{
								DoneMenuItem = new JMenuItem();
								jPopupMenu1.add(DoneMenuItem);
								DoneMenuItem.setText("done");
								DoneMenuItem
										.addActionListener(new PopupItemListener());
							}
							{
								ProgressMenuItem = new JMenuItem();
								jPopupMenu1.add(ProgressMenuItem);
								ProgressMenuItem.setText("progress");
								ProgressMenuItem
										.addActionListener(new PopupItemListener());
							}
						}
						BaseDataList
								.addListSelectionListener(new ListSelectionListener() {
									public void valueChanged(
											ListSelectionEvent evt) {

									}
								});
					}
				}
			}

			this.setSize(317, 270);
		} catch (RuntimeException e) {
			GUIMessages.exceptionMessage(e);
			logger.log(Level.SEVERE, "", e);
		}
	}

	/**
	 * Auto-generated method for setting the popup menu for a component
	 */
	private void setComponentPopupMenu(final java.awt.Component parent,
			final javax.swing.JPopupMenu menu) {
		parent.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent e) {
				if (e.isPopupTrigger()) {
					menu.show(parent, e.getX(), e.getY());
				}
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
				if (e.isPopupTrigger()) {
					menu.show(parent, e.getX(), e.getY());
				}
			}
		});
	}

	public int getExitStatus() {
		return exitStatus;
	}

	private class PopupItemListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String status = "";
			if (e.getSource().equals(RawMenuItem)) {
				status = BaseDataLight.STATUS_RAW;
			} else if (e.getSource().equals(DoneMenuItem)) {
				status = BaseDataLight.STATUS_DONE;
			} else if (e.getSource().equals(ProgressMenuItem)) {
				status = BaseDataLight.STATUS_PROGRESS;
			}

			if (!BaseDataList.isSelectionEmpty()) {
				Object[] selectedValues = BaseDataList.getSelectedValues();
				for (Object value : selectedValues) {
					BaseDataLight b = ((BaseDataLight) value);
					b.setStatus(status);
					changeList.add(b);
				}
			}

			BaseDataList.repaint();
		}
	}

	/**
	 * main method for debugging
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				BaseDataStatusDialog inst = null;
				try {
					SQLDatabaseManager sdm = new SQLDatabaseManager();
					SQLFunctions sf = new SQLFunctions(sdm);
					inst = new BaseDataStatusDialog(frame, sf
							.getAllBaseDataLight(138), sdm);
				} catch (Exception e) {

					e.printStackTrace();
				}
				inst.setVisible(true);
			}
		});
	}
}
