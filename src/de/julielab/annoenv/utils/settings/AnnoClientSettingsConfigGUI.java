package de.julielab.annoenv.utils.settings;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;

import de.julielab.annoenv.ui.GUIMessages;
import de.julielab.annoenv.utils.AnnoEnvLogger;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;

/**
 * small GUI to configure anno client-
 *
 */
public class AnnoClientSettingsConfigGUI extends javax.swing.JFrame {

	private static final String FRAME_TITLE = "Configure Settings";

	private static Logger logger = AnnoEnvLogger
			.getLogger("de.julielab.annoenv.utils.settings.AnnoClientSettingsConfigGUI");

	private JPanel jPanel1;

	private JButton DoneButton;

	private JButton ExitButton;

	private JButton SessionDirButton;

	private JButton MmaxDirButton;

	private JButton BaseDirButton;

	private JLabel SessionDirLabel;

	private JLabel MmaxDirLabel;

	private JLabel BaseDirLabel;

	private JTextField SessionDirTextField;

	private JTextField MmaxDirTextField;

	private JTextField BaseDirTextField;

	private JFileChooser chooser;

	private File sessionDir, baseDir, mmaxDir;

	/**
	 * main method for debugging
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				AnnoClientSettingsConfigGUI inst = new AnnoClientSettingsConfigGUI();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public AnnoClientSettingsConfigGUI() {
		super();
		chooser = new JFileChooser();
		initGUI();
	}

	private void initGUI() {

		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setTitle(FRAME_TITLE);
			{
				jPanel1 = new JPanel();
				getContentPane().add(jPanel1, BorderLayout.CENTER);
				AnchorLayout jPanel1Layout = new AnchorLayout();
				jPanel1.setLayout(jPanel1Layout);
				{
					DoneButton = new JButton();
					jPanel1.add(DoneButton, new AnchorConstraint(857, 959, 958,
							705, AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					DoneButton.setText("Done");
					DoneButton.setPreferredSize(new java.awt.Dimension(91, 28));
					DoneButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
								if (!((baseDir == null) || (sessionDir == null) || (mmaxDir == null))) {
									// write settings file

									AnnoClientSettings settings = new AnnoClientSettings(
											0);
									settings.BASE_DIR = baseDir
											.getAbsolutePath()
											+ File.separatorChar;
									settings.MMAX_LIBS = settings
											.getMMAXLibs(mmaxDir);
									settings.SESSION_DIR = sessionDir;

									// store new values
									Properties newProps = new Properties();
									newProps.setProperty("base_dir",
											settings.BASE_DIR);
									newProps.setProperty("mmax.jars",
											settings.MMAX_LIBS);
									newProps
											.setProperty(
													"mmax.mem",
													AnnoClientSettings.MMAX_MEM_DEFAULT);
									newProps.setProperty("ac.session_dir",
											settings.SESSION_DIR.toString());
									newProps.setProperty("ac.refresh_interval",
											settings.AL_REFRESH_INTERVAL + "");
									newProps.setProperty("autosave.interval",
											settings.AUTOSAVE_INTERVAL + "");

									File propsURL = new File(
											AnnoClientSettings.PROPERTIES_FILE);

									OutputStream out = new FileOutputStream(
											propsURL);
									newProps.store(out, "");
									out.close();

									AnnoClientSettingsConfigGUI.this.dispose();

								} else {
									GUIMessages
											.warnMessage("missing data!");
								}

							} catch (Exception e1) {
								GUIMessages
										.exceptionMessage(e1);
								logger.log(Level.SEVERE, "", e1);
							}
						}
					});
				}
				{
					ExitButton = new JButton();
					jPanel1.add(ExitButton, new AnchorConstraint(857, 275, 958,
							20, AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					ExitButton.setText("Exit");
					ExitButton.setPreferredSize(new java.awt.Dimension(91, 28));
					ExitButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							AnnoClientSettingsConfigGUI.this.dispose();
						}
					});
				}
				{
					BaseDirButton = new JButton();
					jPanel1.add(BaseDirButton, new AnchorConstraint(127, 529,
							228, 294, AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					BaseDirButton.setText("browse...");
					BaseDirButton.setPreferredSize(new java.awt.Dimension(84,
							28));
					BaseDirButton.addActionListener(new BrowseActionListener());
				}
				{
					SessionDirLabel = new JLabel();
					jPanel1.add(SessionDirLabel, new AnchorConstraint(580, 275,
							681, 20, AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					SessionDirLabel.setText("tmp dir:");
					SessionDirLabel.setPreferredSize(new java.awt.Dimension(91,
							28));
				}
				{
					MmaxDirLabel = new JLabel();
					jPanel1.add(MmaxDirLabel, new AnchorConstraint(303, 275,
							404, 20, AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					MmaxDirLabel.setText("mmaxlib dir:");
					MmaxDirLabel
							.setPreferredSize(new java.awt.Dimension(91, 28));
				}
				{
					BaseDirLabel = new JLabel();
					jPanel1.add(BaseDirLabel, new AnchorConstraint(26, 275,
							127, 20, AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					BaseDirLabel.setText("JANE base dir:");
					BaseDirLabel
							.setPreferredSize(new java.awt.Dimension(91, 28));
				}
				{
					SessionDirTextField = new JTextField();
					jPanel1.add(SessionDirTextField, new AnchorConstraint(580,
							979, 681, 294, AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					SessionDirTextField
							.setPreferredSize(new java.awt.Dimension(245, 28));
					SessionDirTextField.setEditable(false);
				}
				{
					MmaxDirTextField = new JTextField();
					jPanel1.add(MmaxDirTextField, new AnchorConstraint(303,
							979, 404, 294, AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					MmaxDirTextField.setPreferredSize(new java.awt.Dimension(
							245, 28));
					MmaxDirTextField.setEditable(false);
				}
				{
					BaseDirTextField = new JTextField();
					jPanel1.add(BaseDirTextField, new AnchorConstraint(26, 979,
							127, 294, AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					BaseDirTextField.setPreferredSize(new java.awt.Dimension(
							245, 28));
					BaseDirTextField.setEditable(false);
				}
				{
					MmaxDirButton = new JButton();
					jPanel1.add(MmaxDirButton, new AnchorConstraint(404, 529,
							505, 294, AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					MmaxDirButton.setText("browse...");
					MmaxDirButton.setPreferredSize(new java.awt.Dimension(84,
							28));
					MmaxDirButton.addActionListener(new BrowseActionListener());
				}
				{
					SessionDirButton = new JButton();
					jPanel1.add(SessionDirButton, new AnchorConstraint(681,
							529, 782, 294, AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
					SessionDirButton.setText("browse...");
					SessionDirButton.setPreferredSize(new java.awt.Dimension(
							84, 28));
					SessionDirButton
							.addActionListener(new BrowseActionListener());
				}
			}
			pack();
			this.setSize(364, 310);
		} catch (Exception e) {
			GUIMessages.exceptionMessage(e);
			logger.log(Level.SEVERE, "", e);
		}
	}

	private class BrowseActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			try {
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = chooser
						.showOpenDialog(AnnoClientSettingsConfigGUI.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					if (arg0.getSource().equals(BaseDirButton)) {
						baseDir = chooser.getSelectedFile();
						BaseDirTextField.setText(baseDir.getPath());
					} else if (arg0.getSource().equals(MmaxDirButton)) {
						mmaxDir = chooser.getSelectedFile();
						MmaxDirTextField.setText(mmaxDir.getPath());
					} else if (arg0.getSource().equals(SessionDirButton)) {
						sessionDir = chooser.getSelectedFile();
						SessionDirTextField.setText(sessionDir.getPath());
					}
				}
			} catch (Exception e) {
				GUIMessages.exceptionMessage(e);
				logger.log(Level.SEVERE, "", e);
			}
		}
	}
}
