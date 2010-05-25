/** 
 * ShowLogsFrame.java
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

import de.julielab.annoenv.db.sql.LogEntry;
import de.julielab.annoenv.db.sql.LogEntryReader;
import de.julielab.annoenv.db.sql.SQLDatabaseManager;
import de.julielab.annoenv.ui.GUIMessages;
import de.julielab.annoenv.utils.AnnoEnvLogger;

import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;



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
 * GUI managing log files. A table is used to provide information about them.
 * 
 */
public class ShowLogsFrame extends InternalAnnoMasterFrame {

	private static final String FRAME_TITLE = "Logs";

	private static Logger logger = AnnoEnvLogger
			.getLogger("de.julielab.annoenv.ui.annomaster.ShowLogsFrame");

	private JPanel FilterPanel;

	private JTable LogTable;

	private JTextField ToField;

	private JTextField LimitField;

	private JLabel LimitLabel;

	private JLabel ToLabel;

	private JTextField FromField;

	private JLabel FromLabel;

	private JLabel DateLabel;

	private JButton ApplyButton;

	private JComboBox SortTypeBox;

	private JLabel SortLabel;

	private JTextField SourceMethodField;

	private JTextField SourceClassField;

	private JPanel TablePanel;

	private JTextField MessageField;

	private JTextField LevelField;

	private JLabel SourceMethodLabel;

	private JLabel SourceClassLabel;

	private JLabel MessageLabel;

	private JLabel LevelLabel;

	private JLabel FilterLabel;

	private JScrollPane TableScrollPanel;

	private JPanel DatePanel;

	private JPanel SortPanel;

	private ArrayList<LogEntry> logEntries;

	private Vector<String> columnIdentifiers;

	private Vector data;

	private LogEntryReader reader;

	private DefaultTableModel dm;



	public ShowLogsFrame(SQLDatabaseManager sdm) {
		super(sdm);
		// 
		logEntries = null;
		try {
			reader = new LogEntryReader(sdm);
			logEntries = reader.getLogEntries();
			data = Utility.convertToVectorOfVectors(logEntries);
		} catch (RuntimeException e) {
			GUIMessages.exceptionMessage(e);
			logger.log(Level.SEVERE, "", e);
		}

		//
		columnIdentifiers = new Vector<String>();
		columnIdentifiers.add("level");
		columnIdentifiers.add("message");
		columnIdentifiers.add("sourceClass");
		columnIdentifiers.add("sourceMethod");
		columnIdentifiers.add("timeEntered");
		columnIdentifiers.add("sequence");
		initGUI();
	}

	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(591, 409));
			this.setBounds(25, 25, 591, 409);
			setVisible(true);
			this.setTitle(FRAME_TITLE);
			this.setClosable(true);
			BoxLayout thisLayout = new BoxLayout(getContentPane(),
					javax.swing.BoxLayout.Y_AXIS);
			getContentPane().setLayout(thisLayout);
			this.setResizable(true);
			{
				FilterPanel = new JPanel();
				getContentPane().add(FilterPanel);
				FilterPanel.setPreferredSize(new java.awt.Dimension(586, 79));
				FilterPanel.setLayout(null);
				{
					FilterLabel = new JLabel();
					FilterPanel.add(FilterLabel);
					FilterLabel.setText("Filter");
					FilterLabel.setBounds(7, 7, 63, 28);
				}
				{
					LevelLabel = new JLabel();
					FilterPanel.add(LevelLabel);
					LevelLabel.setText("level:");
					LevelLabel.setBounds(63, 7, 63, 28);
				}
				{
					MessageLabel = new JLabel();
					FilterPanel.add(MessageLabel);
					MessageLabel.setText("message:");
					MessageLabel.setBounds(63, 42, 63, 28);
				}
				{
					SourceClassLabel = new JLabel();
					FilterPanel.add(SourceClassLabel);
					SourceClassLabel.setText("source class:");
					SourceClassLabel.setBounds(294, 7, 105, 28);
				}
				{
					SourceMethodLabel = new JLabel();
					FilterPanel.add(SourceMethodLabel);
					SourceMethodLabel.setText("source method:");
					SourceMethodLabel.setBounds(294, 42, 105, 28);
				}
				{
					LevelField = new JTextField();
					FilterPanel.add(LevelField);
					LevelField.setBounds(133, 7, 133, 28);
				}
				{
					MessageField = new JTextField();
					FilterPanel.add(MessageField);
					MessageField.setBounds(133, 42, 133, 28);
				}
				{
					SourceClassField = new JTextField();
					FilterPanel.add(SourceClassField);
					SourceClassField.setBounds(406, 7, 133, 28);
				}
				{
					SourceMethodField = new JTextField();
					FilterPanel.add(SourceMethodField);
					SourceMethodField.setBounds(406, 42, 133, 28);
				}
			}
			{
				SortPanel = new JPanel();
				SortPanel.setLayout(null);
				getContentPane().add(SortPanel);
				SortPanel.setPreferredSize(new java.awt.Dimension(586, 51));
				{
					SortLabel = new JLabel();
					SortPanel.add(SortLabel);
					SortLabel.setText("Sort");
					SortLabel.setBounds(7, 7, 63, 28);
				}
				{
					ComboBoxModel SortTypeBoxModel = new DefaultComboBoxModel(
							LogEntryReader.sortMapping);
					SortTypeBox = new JComboBox();
					SortPanel.add(SortTypeBox);
					SortTypeBox.setModel(SortTypeBoxModel);
					SortTypeBox.setBounds(84, 14, 252, 28);
				}
				{
					ApplyButton = new JButton();
					SortPanel.add(ApplyButton);
					ApplyButton.setText("apply");
					ApplyButton.setBounds(406, 14, 77, 28);
					ApplyButton.addActionListener(new ApplyButtonListener());
				}
			}
			{
				TablePanel = new JPanel();
				BorderLayout TablePanelLayout = new BorderLayout();
				getContentPane().add(TablePanel);
				TablePanel.setPreferredSize(new java.awt.Dimension(586, 206));
				TablePanel.setLayout(TablePanelLayout);
				{
					TableScrollPanel = new JScrollPane();
					TablePanel.add(TableScrollPanel, BorderLayout.CENTER);
					TableScrollPanel.setBounds(14, 14, 553, 182);
					{
						dm = new DefaultTableModel();
						dm.setColumnIdentifiers(columnIdentifiers);
						dm.setDataVector(data, columnIdentifiers);
						LogTable = new JTable();
						LogTable.setEnabled(false);
						TableScrollPanel.setViewportView(LogTable);
						LogTable.setModel(dm);
					}
				}
			}
			{
				DatePanel = new JPanel();
				getContentPane().add(DatePanel);
				DatePanel.setPreferredSize(new java.awt.Dimension(586, 44));
				DatePanel.setLayout(null);
				{
					DateLabel = new JLabel();
					DatePanel.add(DateLabel);
					DateLabel.setText("Date");
					DateLabel.setBounds(7, 7, 63, 28);
				}
				{
					FromLabel = new JLabel();
					DatePanel.add(FromLabel);
					FromLabel.setText("from:");
					FromLabel.setBounds(77, 7, 63, 28);
				}
				{
					FromField = new JTextField();
					DatePanel.add(FromField);
					FromField.setBounds(119, 7, 112, 28);
					FromField.setInputVerifier(new InputVerifier() {
						public boolean verify(JComponent aComponent) {
							return (java.util.regex.Pattern.matches(
									"(([0-9]{1,2}).([0-9]{1,2}).([0-9]{1,4}))",
									FromField.getText()));
						}

						public boolean shouldYieldFocus(JComponent aComponent) {
							boolean isValid = super
									.shouldYieldFocus(aComponent);
							if (!isValid)
								FromField.setText("");
							return true;
						}
					});
				}
				{
					ToLabel = new JLabel();
					DatePanel.add(ToLabel);
					ToLabel.setText("to:");
					ToLabel.setBounds(266, 7, 35, 28);
				}
				{
					ToField = new JTextField();
					DatePanel.add(ToField);
					ToField.setBounds(294, 7, 112, 28);
					ToField.setInputVerifier(new InputVerifier() {
						public boolean verify(JComponent aComponent) {
							return (java.util.regex.Pattern.matches(
									"(([0-9]{1,2}).([0-9]{1,2}).([0-9]{1,4}))",
									ToField.getText()));
						}

						public boolean shouldYieldFocus(JComponent aComponent) {
							boolean isValid = super
									.shouldYieldFocus(aComponent);
							if (!isValid)
								ToField.setText("");
							return true;
						}
					});
				}
				{
					LimitLabel = new JLabel();
					DatePanel.add(LimitLabel);
					LimitLabel.setText("Limit:");
					LimitLabel.setBounds(441, 7, 56, 28);
				}
				{
					LimitField = new JTextField();
					DatePanel.add(LimitField);
					LimitField.setBounds(497, 7, 56, 28);
					LimitField.setText(new String(new Integer(
							LogEntryReader.DEFAULT_LIMIT).toString()));
				}
			}
		} catch (RuntimeException e) {
			GUIMessages.exceptionMessage(e);
			logger.log(Level.SEVERE, "", e);
		}
	}

	private class ApplyButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			reader.resetOptions();

			String level = LevelField.getText();
			if (!level.equals(""))
				reader.setFilter(LogEntryReader.LEVEL_FIELD, level);

			String message = MessageField.getText();
			if (!message.equals(""))
				reader.setFilter(LogEntryReader.MESSAGE_FIELD, message);

			String sourceClass = SourceClassField.getText();
			if (!sourceClass.equals(""))
				reader.setFilter(LogEntryReader.SOURCECLASS_FIELD, sourceClass);

			String sourceMethod = SourceMethodField.getText();
			if (!sourceMethod.equals(""))
				reader.setFilter(LogEntryReader.SOURCEMETHOD_FIELD,
						sourceMethod);

			Calendar date1;
			String from = FromField.getText();
			if (!from.equals("")) {
				String[] fromar = from.split("\\D");
				date1 = new GregorianCalendar(
						new Integer(fromar[2]).intValue(), new Integer(
								fromar[1]).intValue(), new Integer(fromar[0])
								.intValue());
			} else
				date1 = null;

			Calendar date2;
			String to = ToField.getText();
			if (!to.equals("")) {
				String[] toar = to.split("\\D");
				date2 = new GregorianCalendar(new Integer(toar[2]).intValue(),
						new Integer(toar[1]).intValue(), new Integer(toar[0])
								.intValue());
			} else
				date2 = null;

			reader.setDate(date1, date2);

			String limit = LimitField.getText();
			if (!limit.equals(""))
				reader.setLimit(new Integer(limit).intValue());
			Object sort = SortTypeBox.getSelectedItem();
			if (sort != null)
				reader.setSort(SortTypeBox.getSelectedIndex());

			try {
				data = Utility.convertToVectorOfVectors(reader.getLogEntries());
			} catch (RuntimeException ex) {
				GUIMessages.exceptionMessage(ex);
				logger.log(Level.SEVERE, "", e);
			}
			dm.setDataVector(data, columnIdentifiers);
		}
	}
	
	/**
	 * main method for debugging
	 */
	public static void main(String[] args) {

		try {
			JFrame frame = new JFrame();
			ShowLogsFrame inst = null;
			inst = new ShowLogsFrame(new SQLDatabaseManager());
			JDesktopPane jdp = new JDesktopPane();
			jdp.add(inst);
			jdp.setPreferredSize(inst.getPreferredSize());
			frame.setContentPane(jdp);
			frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			frame.pack();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
