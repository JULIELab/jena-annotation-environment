/** 
 * AddMultipleBaseDataDialog.java
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
 * Creation date: September, 2006 
 * 
 
 **/
package de.julielab.annoenv.ui.annomaster;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;
import de.julielab.annoenv.db.annodata.BaseDataFull;
import de.julielab.annoenv.db.annodata.Markable;
import de.julielab.annoenv.db.annodata.MmaxData;
import de.julielab.annoenv.db.sql.SQLDatabaseManager;
import de.julielab.annoenv.ui.GUIMessages;
import de.julielab.annoenv.utils.AnnoEnvLogger;
import de.julielab.annoenv.utils.XMLFileFilter;
import de.julielab.annoenv.utils.settings.AnnoMasterTooltips;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
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
 * This dialog makes it possible to select a directory of BaseData files, thus
 * adding multiple documents to a project.
 * 
 * @see AddBaseDataDialog
 */
public class AddMultipleBaseDataDialog extends javax.swing.JDialog implements
		ActionListener, DirectoryAccess {

	private static final String FRAME_TITLE = "MultipleBaseData";

	private static Logger logger = AnnoEnvLogger
			.getLogger("de.julielab.annoenv.ui.annomaster.AddMultipleBaseDataDialog");

	private JPanel jPanel1;

	private JLabel BaseDataLabel;

	private JLabel StyleFileLabel;

	private JButton BrowseButtonMarkables;

	private JButton BrowseButtonBaseD;

	private JButton BrowseButtonStyleF;

	private JTextField BaseDataField;

	private JTextField MarkableField;

	private JTextField StyleFileField;

	private JButton CancelButton;

	private JButton OKButton;

	private JLabel MarkableLabel;

	private JTextArea jTextArea1;

	private JScrollPane jScrollPane1;

	private JFileChooser SFileChooser;

	private JFileChooser BFileChooser;

	private JFileChooser MFileChooser;

	private File sFile = null;

	private File bFile = null;

	private File mFile = null;

	private Vector<MmaxData> mmaxDataVector;

	private Vector<BaseDataFull> BaseDataVector;

	private Vector<Markable> MarkableVector;

	private SQLDatabaseManager sdm;

	private File currentPath;

	public AddMultipleBaseDataDialog(Vector<MmaxData> mmaxDataVector,
			SQLDatabaseManager sdm) {
		this.mmaxDataVector = mmaxDataVector;
		this.sdm = sdm;
		this.MarkableVector = new Vector<Markable>();
		this.BaseDataVector = new Vector<BaseDataFull>();
		initGUI();
	}

	private void initGUI() {
		SFileChooser = new JFileChooser();
		BFileChooser = new JFileChooser();
		MFileChooser = new JFileChooser();
		BFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		MFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		SFileChooser.setFileFilter(new XMLFileFilter());
		try {
			{
				this.setTitle(FRAME_TITLE);
				this.setResizable(false);
				{
					jPanel1 = new JPanel();
					getContentPane().add(jPanel1, BorderLayout.CENTER);
					AnchorLayout jPanel1Layout = new AnchorLayout();
					jPanel1.setLayout(jPanel1Layout);
					jPanel1.setPreferredSize(new java.awt.Dimension(413, 273));
					{
						BrowseButtonMarkables = new JButton();
						jPanel1.add(BrowseButtonMarkables,
								new AnchorConstraint(329, 956, 429, 748,
										AnchorConstraint.ANCHOR_REL,
										AnchorConstraint.ANCHOR_REL,
										AnchorConstraint.ANCHOR_REL,
										AnchorConstraint.ANCHOR_REL));
						BrowseButtonMarkables.setText("Browse");
						BrowseButtonMarkables
								.setPreferredSize(new java.awt.Dimension(84, 28));
						BrowseButtonMarkables.addActionListener(this);
					}
					{
						BrowseButtonBaseD = new JButton();
						jPanel1.add(BrowseButtonBaseD, new AnchorConstraint(
								203, 956, 303, 748,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
						BrowseButtonBaseD.setText("Browse");
						BrowseButtonBaseD
								.setPreferredSize(new java.awt.Dimension(84, 28));
						BrowseButtonBaseD.addActionListener(this);
					}
					{
						BrowseButtonStyleF = new JButton();
						jPanel1.add(BrowseButtonStyleF, new AnchorConstraint(
								77, 956, 178, 748, AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
						BrowseButtonStyleF.setText("Browse");
						BrowseButtonStyleF
								.setPreferredSize(new java.awt.Dimension(84, 28));
						BrowseButtonStyleF.addActionListener(this);
					}
					{
						MarkableLabel = new JLabel();
						jPanel1.add(MarkableLabel, new AnchorConstraint(329,
								244, 429, 18, AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
						MarkableLabel.setText("Markables:");
						MarkableLabel.setPreferredSize(new java.awt.Dimension(
								91, 28));
					}
					{
						BaseDataLabel = new JLabel();
						jPanel1.add(BaseDataLabel, new AnchorConstraint(203,
								244, 303, 18, AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
						BaseDataLabel.setText("BaseDatas:");
						BaseDataLabel.setPreferredSize(new java.awt.Dimension(
								91, 28));
					}
					{
						StyleFileLabel = new JLabel();
						jPanel1.add(StyleFileLabel, new AnchorConstraint(77,
								244, 178, 18, AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
						StyleFileLabel.setText("Style File:");
						StyleFileLabel.setPreferredSize(new java.awt.Dimension(
								91, 28));
					}
					{
						MarkableField = new JTextField();
						jPanel1.add(MarkableField, new AnchorConstraint(329,
								730, 429, 244, AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
						MarkableField.setPreferredSize(new java.awt.Dimension(
								196, 28));
						MarkableField.setEditable(false);
					}
					{
						StyleFileField = new JTextField();
						jPanel1.add(StyleFileField, new AnchorConstraint(77,
								730, 178, 244, AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
						StyleFileField.setPreferredSize(new java.awt.Dimension(
								196, 28));
						StyleFileField.setEditable(false);
					}
					{
						CancelButton = new JButton();
						jPanel1.add(CancelButton, new AnchorConstraint(530,
								956, 631, 748, AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
						CancelButton.setText("Cancel");
						CancelButton.setPreferredSize(new java.awt.Dimension(
								84, 28));
						CancelButton.addActionListener(this);
					}
					{
						OKButton = new JButton();
						jPanel1.add(OKButton, new AnchorConstraint(530, 730,
								631, 522, AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
						OKButton.setText("OK");
						OKButton
								.setPreferredSize(new java.awt.Dimension(84, 28));
						OKButton.addActionListener(this);
					}
					{
						jScrollPane1 = new JScrollPane();
						jPanel1.add(jScrollPane1, new AnchorConstraint(681,
								973, 958, 35, AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
						jScrollPane1.setPreferredSize(new java.awt.Dimension(
								378, 77));
						jScrollPane1
								.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
						jTextArea1 = new JTextArea();
						jScrollPane1.setViewportView(jTextArea1);
						jTextArea1.setLineWrap(true);
						jTextArea1.setEditable(false);
					}
					{
						BaseDataField = new JTextField();
						jPanel1.add(BaseDataField, new AnchorConstraint(203,
								730, 303, 244, AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
						BaseDataField.setPreferredSize(new java.awt.Dimension(
								196, 28));
						BaseDataField.setEditable(false);
					}
					jPanel1.setFocusCycleRoot(true);
				}
			}
			this.setSize(409, 307);

			AnnoMasterTooltips amTooltips = AnnoMasterTooltips.instantiateAnnoMasterTooltips();
			amTooltips.readTooltips(this);
		} catch (Exception e) {
			GUIMessages.exceptionMessage(e);
			logger.log(Level.SEVERE, "", e);
		}
	}

	public void actionPerformed(ActionEvent evt) {
		Object source = evt.getSource();

		if (source.equals(BrowseButtonStyleF)) {
			SFileChooser.setCurrentDirectory(currentPath);
			int returnVal = SFileChooser
					.showOpenDialog(AddMultipleBaseDataDialog.this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				sFile = SFileChooser.getSelectedFile();
				StyleFileField.setText(sFile.getName());
				currentPath = sFile.getParentFile();
			} else {
				StyleFileField.setText("");
			}
		} else if (source.equals(BrowseButtonBaseD)) {
			BFileChooser.setCurrentDirectory(currentPath);
			int returnVal = BFileChooser
					.showOpenDialog(AddMultipleBaseDataDialog.this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				bFile = BFileChooser.getSelectedFile();
				BaseDataField.setText(bFile.getName());
				infoUpdate(bFile);
				currentPath = bFile.getParentFile();
				if (!isXMLOnly(bFile.listFiles())) {
					GUIMessages
							.warnMessage("Files in directory are to be *.xml only!");
					BaseDataField.setText("");
				}
			} else {
				BaseDataField.setText("");
			}

		} else if (source.equals(BrowseButtonMarkables)) {
			MFileChooser.setCurrentDirectory(currentPath);
			int returnVal = MFileChooser
					.showOpenDialog(AddMultipleBaseDataDialog.this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				mFile = MFileChooser.getSelectedFile();
				MarkableField.setText(mFile.getName());
				infoUpdate(mFile);
				File[] allFiles = mFile.listFiles();
				currentPath = mFile.getParentFile();
				if (!(allFiles.length == mmaxDataVector.size() && isXMLDirectoryOnly(allFiles))) {
					GUIMessages
							.warnMessage("Files in directories are to be *.xml only and number of directories must be equal to schemata files!");
					MarkableField.setText("");
				}
			} else {
				MarkableField.setText("");
			}
		} else if (source.equals(OKButton)) {
			if (StyleFileField.getText().equals("") || BaseDataField.equals("")
					|| MarkableField.equals("")) {
				GUIMessages.warnMessage("missing data!");
			} else if (sumAllFiles() != mmaxDataVector.size()
					* bFile.listFiles().length) {
				GUIMessages
						.warnMessage("Incorrect number of markable files!");
			} else {
				try {
					File[] directories = mFile.listFiles();
					File[] allBFiles = bFile.listFiles();
					Arrays.sort(allBFiles, new Comp());
					for (int i = 0; i < directories.length; i++) {
						File[] mList = directories[i].listFiles();
						Arrays.sort(mList, new Comp());
						for (int j = 0; j < mList.length; j++) {
							Markable M = new Markable();
							M.setLevelName(directories[i].getName());
							M.setMarkableFile(mList[j]);
							MarkableVector.add(M);
						}
					}
					int bLength = allBFiles.length;
					for (int k = 0; k < bLength; k++) {
						ArrayList<Markable> marks = new ArrayList<Markable>();
						for (int l = 0; l < directories.length; l++) {
							marks.add(MarkableVector.get(k + bLength * l));
						}
						BaseDataFull B = new BaseDataFull();
						File f = allBFiles[k];
						B.setMarkables(marks);
						B.setBasedataFile(f);
						String fName = f.toString();
						B.setUri(fName.substring(fName.lastIndexOf('/') + 1,
								fName.indexOf(".xml")));
						B.setStyleFile(sFile);
						BaseDataVector.add(B);
					}

				} catch (Exception e) {
					GUIMessages.exceptionMessage(e);
					logger.log(Level.SEVERE, "", e);
				}
				AddMultipleBaseDataDialog.this.dispose();
			}
		} else if (source.equals(CancelButton)) {
			AddMultipleBaseDataDialog.this.dispose();
		}
	}

	private void infoUpdate(final File xFile) {
		StringBuilder output = new StringBuilder("");
		String[] directoryCollection = xFile.list();
		if (directoryCollection != null) {
			output.append("----------------------------------\n"
					+ xFile.getAbsolutePath() + ": \n");
			for (int i = 0; i < directoryCollection.length; i++) {
				output.append(directoryCollection[i]);
				output.append("\n");
			}
		}
		output.append("\n");
		jTextArea1.append(output.toString());
	}

	public Vector<Markable> getMarkableVector() {
		return MarkableVector;
	}

	public Vector<BaseDataFull> getBaseDataVector() {
		return BaseDataVector;
	}

	public File getCurrentPath() {
		return this.currentPath;
	}

	public void setCurrentPath(final File currentPath) {
		this.currentPath = currentPath;
	}

	private boolean isXMLDirectoryOnly(File[] allFiles) {
		boolean b = (allFiles != null);
		if (b) {
			for (int i = 0; i < allFiles.length; i++) {
				b &= allFiles[i].isDirectory();
				if (b) {
					b &= isXMLOnly(allFiles[i].listFiles());
				}
			}
		}
		return b;
	}

	private boolean isXMLOnly(File[] allFiles) {
		boolean b = true;
		for (int i = 0; i < allFiles.length; i++) {
			b = b
					& ((allFiles[i].toString().endsWith(".xml")) && (allFiles[i]
							.isFile()));
		}
		return b;
	}

	private int sumAllFiles() {
		int sum = 0;
		File[] directories = mFile.listFiles();
		for (int i = 0; i < directories.length; i++) {
			sum += directories[i].listFiles().length;
		}
		return sum;
	}

	static private class Comp implements Comparator {
		public int compare(Object f1, Object f2) {
			return (((File) f1).getName().compareTo(((File) f2).getName()));
		}
	}

	/**
	 * main method for debugging
	 */
	public static void main(String[] args) {
		AddMultipleBaseDataDialog inst = null;
		try {
			inst = new AddMultipleBaseDataDialog(new Vector(),
					new SQLDatabaseManager());
		} catch (Exception e) {
			e.printStackTrace();
		}
		inst.setVisible(true);
	}

}
