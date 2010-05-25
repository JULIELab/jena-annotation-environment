package de.julielab.annoenv.test;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.ArrayList;


import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;


import de.julielab.annoenv.db.annodata.AnnoCore;
import de.julielab.annoenv.db.annodata.BaseDataLight;
import de.julielab.annoenv.db.sql.SQLDatabaseManager;
import de.julielab.annoenv.db.sql.SQLFunctions;


import org.eml.MMAX2.core.*;

public class MmaxTestFrame extends javax.swing.JFrame {
	private JPanel jPanel1;

	private JScrollPane jScrollPane1;

	private JScrollPane jScrollPane2;

	private JButton LaunchButton;

	private JList BaseDataList;

	private JList ProjectList;

	private SQLDatabaseManager sdm;

	private SQLFunctions sqf;

	int currentprojectID;

	int userID = 0;

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MmaxTestFrame inst = new MmaxTestFrame();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public MmaxTestFrame() {
		super();

		
			sdm = new SQLDatabaseManager();
		

		sqf = new SQLFunctions(sdm);

		sqf.login("tomanek", "tomanek");

		initGUI();
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.addWindowListener(new WindowExitListener());
			this.setTitle("test launching of mmax");
			userID = sqf.retrieveUser("tomanek").getUserID();
			{
				jPanel1 = new JPanel();
				getContentPane().add(jPanel1, BorderLayout.CENTER);
				{
					jScrollPane1 = new JScrollPane();
					jPanel1.add(jScrollPane1);
					jScrollPane1.setPreferredSize(new java.awt.Dimension(247,
							107));
					{
						ListModel jList1Model = new DefaultComboBoxModel(sqf
								.getCoreProjects(userID).toArray());
						ProjectList = new JList();
						jScrollPane1.setViewportView(ProjectList);
						ProjectList.setModel(jList1Model);
						ProjectList.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								// check for double-clicks
								ArrayList<BaseDataLight> defaultBaseData;
								if (e.getClickCount() == 2) {
									try {
										currentprojectID = ((AnnoCore) ProjectList
												.getSelectedValue())
												.getProjectID();
										defaultBaseData = sqf
												.getAllBaseDataLight(currentprojectID);
										BaseDataList
												.setListData(defaultBaseData
														.toArray());
									} catch (Exception e1) {
										JOptionPane.showMessageDialog(null, e1
												.getStackTrace(), "alert!",
												JOptionPane.ERROR_MESSAGE);
									}
								}
							}
						});
					}
				}
				{
					jScrollPane2 = new JScrollPane();
					jPanel1.add(jScrollPane2);
					jScrollPane2.setPreferredSize(new java.awt.Dimension(250,
							95));
					{
						ListModel BaseDataListModel = new DefaultComboBoxModel();
						BaseDataList = new JList();
						jScrollPane2.setViewportView(BaseDataList);
						BaseDataList.setModel(BaseDataListModel);
					}
				}
				{
					LaunchButton = new JButton();
					jPanel1.add(LaunchButton);
					LaunchButton.setText("LAUNCH MMAX 3...2...1...");
					LaunchButton.setPreferredSize(new java.awt.Dimension(166,
							32));
					LaunchButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							System.out
									.println("LaunchButton.actionPerformed, event="
											+ evt);
							if (!BaseDataList.isSelectionEmpty()) {
								;
								/** Create new MMAX2 instance */

								final MMAX2 mmax2 = new MMAX2();
								String toLoad = "/home/klaue/temporary/ProjectsForMMax/mmax_1206974777282/project.mmax";

								mmax2.setTitle("MMAX2 "
										+ mmax2.getVersionString());

								mmax2.createMenu();
								// mmax2.disableComponentForInput(mmax2.getJMenuBar().getMenu(0));
								mmax2.initGlobalStyles();

								int initialWidth = 1200; // 800
								int initialHeight = 750; // 500

								mmax2.setSize(initialWidth, initialHeight);
								mmax2.setLocation((mmax2.getScreenWidth() / 2)
										- initialWidth / 2, (mmax2
										.getScreenHeight() / 2)
										- initialHeight / 2);

								if (toLoad.equals("") == false) {
									mmax2.loadMMAXFile(toLoad);
								}

								mmax2.setVisible(true);
								mmax2.toFront();
							}
						}
					});
				}
			}
			pack();
			setSize(400, 300);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private class WindowExitListener extends WindowAdapter {
		public void windowClosing(final WindowEvent e) {

			try {
				new SQLFunctions(sdm).logout(userID);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			System.exit(0);
		}
	}

}
