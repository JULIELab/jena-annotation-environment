/** 
 * EditProjectFrame.java
 * 
 * Copyright (c) 2007, JULIE Lab. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0 
 *
 * Author: Klaue
 * 
 * Current version: 2.0
 * Since version:   1.4
 *
 * Creation date: April, 2008 
 * 
 
 **/

package de.julielab.annoenv.ui.annomaster;
import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;

import de.julielab.annoenv.db.annodata.AnnoFull;
import de.julielab.annoenv.db.annodata.AnnoLight;
import de.julielab.annoenv.db.annodata.BaseDataFull;
import de.julielab.annoenv.db.annodata.MmaxData;
import de.julielab.annoenv.db.sql.SQLDatabaseManager;
import de.julielab.annoenv.db.sql.SQLFunctions;



import de.julielab.annoenv.ui.GUIMessages;
import de.julielab.annoenv.utils.AnnoEnvLogger;
import de.julielab.annoenv.utils.settings.AnnoMasterSettings;

import java.awt.BorderLayout;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;




import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;


/**
 * Modify project specific settings including name, description, schemata... 
 */
public class EditProjectFrame extends javax.swing.JInternalFrame {
	
	private static final String FRAME_TITLE = "Edit Project";

	private static Logger logger = AnnoEnvLogger
	.getLogger("de.julielab.annoenv.ui.annomaster.EditProjectFrame");
	
	private JPanel jPanel1;
	private JTextField NameField;
	private JButton BrowsePriolistButton;
	private JButton CancelButton;
	private JButton ApplyChangesButton;
	private JButton SchemaButton;
	private JButton CustomButton;
	private JTextField CustomizationFileField;
	private JTextField SchemaFileField;
	private JLabel CustomizationFileLabel;
	private JLabel SchemaFileLabel;
	private JLabel MainLvlLabel;
	private JTextArea DescriptionArea;
	private JButton StyleButton;
	private JTextField StyleFileField;
	private JLabel StyleFileLabel;
	private JComboBox LevelBox;
	private JScrollPane jScrollPane1;
	private JLabel DescriptionLabel;
	private JTextField PrioListField;
	private JLabel PrioListLabel;
	private JLabel NameLabel;
	protected JFileChooser PrioFileChooser;
	protected JFileChooser StyleFileChooser;
	
	private AnnoLight editableProject;
	private String name;
	private File prioFile;
	private String description;
	private File schemaFile;
	private File customFile;
	private File styleFile;
	private ArrayList<MmaxData> mmaxList;
	private MmaxData mmax;	
	private SQLDatabaseManager sdm;
	
	protected JFileChooser CFileChooser;
	protected JFileChooser SFileChooser;


	
	public EditProjectFrame(SQLDatabaseManager sdm)  {
		super();
		this.sdm = sdm;
		try {
			initGUI(sdm);
		} catch (RuntimeException e) {
			GUIMessages.exceptionMessage(e);
			logger.log(Level.SEVERE, "", e);
		}
	}
	
	public EditProjectFrame(AnnoLight project, SQLDatabaseManager sdm){
		super();
		this.sdm = sdm;
		try {
			initGUI(sdm);
		} catch (RuntimeException e) {
			GUIMessages.exceptionMessage(e);
			logger.log(Level.SEVERE, "", e);
		}
		editableProject = project;
		addContent(project);
	}

	private void initGUI(final SQLDatabaseManager sdm) {
		PrioFileChooser = new JFileChooser();
		CFileChooser = new JFileChooser();
		SFileChooser = new JFileChooser();
		StyleFileChooser = new JFileChooser();
		try {
			this.setPreferredSize(new java.awt.Dimension(431, 443));
			this.setBounds(0, 0, 431, 443);
			setVisible(true);
			this.setTitle(FRAME_TITLE);
			this.setClosable(true);
			{
				jPanel1 = new JPanel();
				getContentPane().add(jPanel1, BorderLayout.CENTER);
				AnchorLayout jPanel1Layout = new AnchorLayout();
				jPanel1.setLayout(jPanel1Layout);
				jPanel1.setPreferredSize(new java.awt.Dimension(430, 414));
				{
					StyleFileField = new JTextField();
					jPanel1.add(StyleFileField, new AnchorConstraint(776, 758, 848, 329, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					StyleFileField.setPreferredSize(new java.awt.Dimension(184, 30));
					StyleFileField.setEditable(false);
				}
				{
					StyleFileLabel = new JLabel();
					jPanel1.add(StyleFileLabel, new AnchorConstraint(769, 311, 850, 29, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					StyleFileLabel.setText("Style File:");
					StyleFileLabel.setPreferredSize(new java.awt.Dimension(121, 34));
				}
				{
					LevelBox = new JComboBox();
					jPanel1.add(LevelBox, new AnchorConstraint(488, 758, 562, 175, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					LevelBox.setPreferredSize(new java.awt.Dimension(250, 31));
					LevelBox.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							if(LevelBox.getSelectedIndex() != -1){
								mmax = (MmaxData) LevelBox.getSelectedItem();
								try{
									customFile = mmax.writeCustomFile( (new AnnoMasterSettings()).TMP_DIR + File.separator + mmax.toString() +"_customFile", sdm);
									schemaFile = mmax.writeSchemaFile((new AnnoMasterSettings()).TMP_DIR + File.separator + mmax.toString() +"_schemaFile", sdm);		
									prioFile = editableProject.writePriolistFile((new AnnoMasterSettings()).TMP_DIR + File.separator + mmax.toString() +"_prioList", sdm);
									
									PrioListField.setText(prioFile.getPath());
									SchemaFileField.setText(schemaFile.getPath());
									CustomizationFileField.setText(customFile.getPath());
								}
								catch(RuntimeException e){
									GUIMessages.exceptionMessage(e);
									logger.log(Level.SEVERE, "", e);
								}
							}
						}
					});
				}
				{
					CancelButton = new JButton();
					jPanel1.add(CancelButton, new AnchorConstraint(900, 810, 976, 545, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					CancelButton.setText("Cancel");
					CancelButton.setPreferredSize(new java.awt.Dimension(112, 28));
					CancelButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							EditProjectFrame.this.dispose();
						}
					});
				}
				{
					ApplyChangesButton = new JButton();
					jPanel1.add(ApplyChangesButton, new AnchorConstraint(900, 512, 976, 149, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					ApplyChangesButton.setText("Apply Changes");
					ApplyChangesButton.setPreferredSize(new java.awt.Dimension(154, 28));
					ApplyChangesButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							name = NameField.getText();
							description = DescriptionArea.getText();
							
							try{
								mmax.setSchemaFile(schemaFile);
								mmax.setCustomFile(customFile);
								mmax.updateMmaxData(sdm);
																
								editableProject.setName(name);
								editableProject.setPriolistFile(prioFile);
								editableProject.setDescription(description);
								editableProject.updateProject(new SQLFunctions(sdm));
								
								//this might take a while.... needs responsiveness
								if(styleFile != null)
									updateStyleFiles(editableProject.getProjectID(), styleFile);
							}catch(RuntimeException e){
								GUIMessages.exceptionMessage(e);
								logger.log(Level.SEVERE, "", e);
							}
							EditProjectFrame.this.dispose();
						}
					});
				}
				{
					SchemaButton = new JButton();
					jPanel1.add(SchemaButton, new AnchorConstraint(590, 989, 667, 775, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					SchemaButton.setText("Browse");
					SchemaButton.setPreferredSize(new java.awt.Dimension(92, 32));
					SchemaButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							int returnVal = SFileChooser.showOpenDialog(EditProjectFrame.this);
							
							if (returnVal == JFileChooser.APPROVE_OPTION) {
								File SFile = SFileChooser
									.getSelectedFile();
								SchemaFileField.setText(SFile.getName());
								schemaFile = SFile;
							} else
								SchemaFileField.setText("");
						}
					});
				}
				{
					CustomButton = new JButton();
					jPanel1.add(CustomButton, new AnchorConstraint(681, 987, 757, 772, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					CustomButton.setText("Browse");
					CustomButton.setPreferredSize(new java.awt.Dimension(92, 32));
					CustomButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							int returnVal = CFileChooser.showOpenDialog(EditProjectFrame.this);
							
							if (returnVal == JFileChooser.APPROVE_OPTION) {
								File CFile = CFileChooser
									.getSelectedFile();
								CustomizationFileField.setText(CFile.getName());
								customFile = CFile;
							} else
								CustomizationFileField.setText("");

						}
					});
				}
				{
					CustomizationFileField = new JTextField();
					jPanel1.add(CustomizationFileField, new AnchorConstraint(681, 758, 757, 329, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					CustomizationFileField.setPreferredSize(new java.awt.Dimension(184, 32));
					CustomizationFileField.setEditable(false);
				}
				{
					SchemaFileField = new JTextField();
					jPanel1.add(SchemaFileField, new AnchorConstraint(590, 761, 667, 332, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					SchemaFileField.setPreferredSize(new java.awt.Dimension(184, 32));
					SchemaFileField.setEditable(false);
				}
				{
					CustomizationFileLabel = new JLabel();
					jPanel1.add(CustomizationFileLabel, new AnchorConstraint(678, 332, 755, 19, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					CustomizationFileLabel.setText("Customization File:");
					CustomizationFileLabel.setPreferredSize(new java.awt.Dimension(134, 32));
				}
				{
					SchemaFileLabel = new JLabel();
					jPanel1.add(SchemaFileLabel, new AnchorConstraint(597, 332, 674, 19, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					SchemaFileLabel.setText("Schema File: ");
					SchemaFileLabel.setPreferredSize(new java.awt.Dimension(134, 32));
				}
				{
					MainLvlLabel = new JLabel();
					jPanel1.add(MainLvlLabel, new AnchorConstraint(485, 148, 562, 17, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					MainLvlLabel.setText("Level:");
					MainLvlLabel.setPreferredSize(new java.awt.Dimension(56, 32));
				}
				{
					jScrollPane1 = new JScrollPane();
					jPanel1.add(jScrollPane1, new AnchorConstraint(301, 579, 459, 182, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jScrollPane1.setPreferredSize(new java.awt.Dimension(170, 66));
					{
						DescriptionArea = new JTextArea();
						jScrollPane1.setViewportView(DescriptionArea);
						DescriptionArea.setLineWrap(true);
					}
				}
				{
					DescriptionLabel = new JLabel();
					jPanel1.add(DescriptionLabel, new AnchorConstraint(307, 248, 383, 17, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					DescriptionLabel.setText("Description: ");
					DescriptionLabel.setPreferredSize(new java.awt.Dimension(98, 28));
				}
				{
					BrowsePriolistButton = new JButton();
					jPanel1.add(BrowsePriolistButton, new AnchorConstraint(192, 859, 269, 628, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					BrowsePriolistButton.setText("Browse");
					BrowsePriolistButton.setPreferredSize(new java.awt.Dimension(98, 28));
					BrowsePriolistButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							int returnVal = PrioFileChooser.showOpenDialog(EditProjectFrame.this);
							if (returnVal == JFileChooser.APPROVE_OPTION) {
								File filePrio = PrioFileChooser.getSelectedFile();
								PrioListField.setText(filePrio.getName());
								prioFile = filePrio;
							} else {
								PrioListField.setText("");
								prioFile = null;
							}
						}
					});
				}
				{
					PrioListField = new JTextField();
					jPanel1.add(PrioListField, new AnchorConstraint(192, 579, 269, 182, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					PrioListField.setPreferredSize(new java.awt.Dimension(168, 28));
					PrioListField.setEditable(false);
				}
				{
					NameField = new JTextField();
					jPanel1.add(NameField, new AnchorConstraint(77, 579, 154, 182, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					NameField.setPreferredSize(new java.awt.Dimension(168, 28));
				}
				{
					PrioListLabel = new JLabel();
					jPanel1.add(PrioListLabel, new AnchorConstraint(192, 199, 288, 17, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					PrioListLabel.setText("Priolist: ");
					PrioListLabel.setPreferredSize(new java.awt.Dimension(77, 35));
				}
				{
					NameLabel = new JLabel();
					jPanel1.add(NameLabel, new AnchorConstraint(77, 199, 173, 17, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					NameLabel.setText("Name: ");
					NameLabel.setPreferredSize(new java.awt.Dimension(77, 35));
				}
				{
					StyleButton = new JButton();
					jPanel1.add(StyleButton, new AnchorConstraint(774, 987, 850, 772, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					StyleButton.setText("Browse");
					StyleButton.setPreferredSize(new java.awt.Dimension(92, 32));
					StyleButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							int returnVal = StyleFileChooser.showOpenDialog(EditProjectFrame.this);
							
							if (returnVal == JFileChooser.APPROVE_OPTION) {
								File SFile = StyleFileChooser
								.getSelectedFile();
								StyleFileField.setText(SFile.getName());
								styleFile = SFile;
							} else
								StyleFileField.setText("");
							
						}
					});
				}
			}
		} catch (RuntimeException e) {
			GUIMessages.exceptionMessage(e);
			logger.log(Level.SEVERE, "", e);
		}
	}	
	
	private void addContent(AnnoLight project){
		this.name = project.getName();
		this.description = project.getDescription();
		this.mmaxList = null;
		try{
			this.mmaxList = new SQLFunctions(sdm).getMmaxDataList(project.getProjectID());
			this.mmax = mmaxList.get(0);
			
			customFile = mmax.writeCustomFile((new AnnoMasterSettings()).TMP_DIR + File.separator + mmax.toString() +"_customFile", sdm);
			schemaFile = mmax.writeSchemaFile((new AnnoMasterSettings()).TMP_DIR + File.separator + mmax.toString()+"_schemaFile", sdm);		
			prioFile = editableProject.writePriolistFile((new AnnoMasterSettings()).TMP_DIR + File.separator + mmax.toString()+"_prioList", sdm);
			//SQLFunctions sf = new SQLFunctions(sdm);
			//BaseDataFull currentRepresentationForAllBDs = sf.getBaseDataFull(editableProject.getCurrentBaseDataLight().getBasedataID());
			//styleFile = currentRepresentationForAllBDs.
			PrioListField.setText(prioFile.getPath());
			SchemaFileField.setText(schemaFile.getPath());
			CustomizationFileField.setText(customFile.getPath());
			
		} catch (RuntimeException e) {
			GUIMessages.exceptionMessage(e);
			logger.log(Level.SEVERE, "", e);
		}
		
		
		ComboBoxModel LevelBoxModel = 
			new DefaultComboBoxModel(mmaxList.toArray());
		LevelBox.setModel(LevelBoxModel);
		NameField.setText(name);		
		DescriptionArea.setText(description);
		this.setTitle(this.getTitle() + ": " + project.getName());
	}
	
	private void updateStyleFiles(int projectID, File sFile) {
		SQLFunctions sf = new SQLFunctions(sdm);
		AnnoFull project = sf.getFullProject(projectID);
		ArrayList<BaseDataFull> bdList = project.getBaseDataFull();
		for (BaseDataFull bd : bdList) {
			bd.setStyleFile(sFile);
			bd.updateBaseData(sdm);
		}
	}
	
	
	/**
	* main method for debugging
	*/
	public static void main(String[] args) throws Exception {
		JFrame frame = new JFrame();
		SQLDatabaseManager sdm = new SQLDatabaseManager();
		EditProjectFrame inst = new EditProjectFrame(sdm);
		JDesktopPane jdp = new JDesktopPane();
		jdp.add(inst);
		jdp.setPreferredSize(inst.getPreferredSize());
		frame.setContentPane(jdp);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
}
