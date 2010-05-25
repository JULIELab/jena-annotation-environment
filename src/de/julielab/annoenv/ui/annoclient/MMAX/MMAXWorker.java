/** 
 * MmaxWorker.java
 * 
 * Copyright (c) 2007, JULIE Lab. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0 
 *
 * Author: Klaue
 * 
 * Current version: 2.0
 * Since version:   0.9
 *
 * Creation date: June, 2007 
 * 
 **/

package de.julielab.annoenv.ui.annoclient.MMAX;

import java.io.File;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jdesktop.swingworker.SwingWorker;

import de.julielab.annoenv.db.sql.SQLDatabaseManager;
import de.julielab.annoenv.ui.GUIMessages;
import de.julielab.annoenv.ui.annoclient.AnnoClientLogFrame;
import de.julielab.annoenv.utils.AnnoEnvLogger;
import de.julielab.annoenv.utils.settings.AnnoClientSettings;

/**
 * Mmax is executed synchronously to the Event Dispatch Thread. This worker
 * autosaves the Mmax session. Using as interval:
 */
public class MMAXWorker extends SwingWorker<Long, String> {

	private static Logger logger = AnnoEnvLogger
			.getLogger("de.julielab.annoenv.ui.annoclient.MMAXWorker");

	final private AnnoClientLogFrame outputFrame;

	final private SQLDatabaseManager sdm;

	final private int projectID;

	final private int baseDataID;

	private MMAXExecuter mex = null;

	private MMAXSession session;

	private AnnoClientSettings acSettings;

	final private boolean isAl;

	// complete annotation time needed, i.e. time mmax was opened during session
	private long overallAnnotationTime;

	// time since last autosave, needed to update DB
	private long annotationTime;

	private long initiallyStartedAt;

	private long startedAt;

	private long finishedAt;

	private boolean hasStarted = false;

	public static int sleeping = 1000;

	public MMAXWorker(SQLDatabaseManager sdm, int userID, int projectID,
			int baseDataID, AnnoClientLogFrame outputFrame, boolean isAl) {

		this.outputFrame = outputFrame;
		this.sdm = sdm;
		this.projectID = projectID;
		this.baseDataID = baseDataID;
		this.isAl = isAl;
		try {
			acSettings = new AnnoClientSettings();
			session = new MMAXSession(sdm, acSettings.SESSION_DIR, userID,
					baseDataID);
			final File runFile = session.downloadSession();
			mex = new MMAXExecuter(runFile, acSettings);
		} catch (RuntimeException e) {
			// catch runtime exceptions to show them in the GUI
			GUIMessages.exceptionMessage(e);
			logger.log(Level.SEVERE, "", e);
		}

	}

	/**
	 * @return annotation_time in seconds since last autosave
	 */
	public Long doInBackground() {
		try {
			// start mmax
			final Thread t = new Thread() {
				public void run() {
					try {
						initiallyStartedAt = System.currentTimeMillis();
						startedAt = initiallyStartedAt;
						int timer = 0;

						while (!hasStarted || mex.isAlive()) {
							if (!hasStarted) {
								mex.start();
								hasStarted = true;
							}
							// message process
							ArrayList<String> err = mex.getMMaxErrors();
							if (!err.isEmpty()) {
								for (String error : err) {
									publish(error);
								}
							}
							timer++;
							// autosave process
							int interval = acSettings.AUTOSAVE_INTERVAL;
							// interval ought to be > 1, sleeping is set to
							// 1000milli on default sleeping/1000 == 1
							if ((((timer * sleeping) / 1000) % interval) == 0) {
								publish("Autosaving now...");
								long autoStopped = System.currentTimeMillis();
								int currentAnnotationTime = (int) ((autoStopped - startedAt) / (1000)); // in
								// seconds
								startedAt = autoStopped; // KT
								// publish(" -> annotation time: " +
								// currentAnnotationTime + " secs ");
								session
										.autoUploadSession(currentAnnotationTime);
							}
							// System.out.println("system time: " +
							// (System.currentTimeMillis() % (interval*1000)));
							try {
								Thread.sleep(MMAXWorker.sleeping);
							} catch (InterruptedException e) {
								throw new RuntimeException(
										"MMAXWorker thread caused problems.", e);
							}
						}
					} catch (RuntimeException e) {
						// this catch block is to make sure that exceptions are thrown to
						// GUI when thrown by the Worker Thread
						publish(e.toString());
						GUIMessages.exceptionMessage(e);
						logger.log(Level.SEVERE, "", e);
					}
					finishedAt = System.currentTimeMillis();
				}
			};
			t.start();
			t.join();
		} catch (Exception e) {
			// this catch block is to make sure that exceptions are thrown to
			// GUI
			GUIMessages.exceptionMessage(e);
			logger.log(Level.SEVERE, "", e);
		}
		// set the overall annotation time
		overallAnnotationTime = (finishedAt - initiallyStartedAt) / 1000;
		
		// annotation time since last autosave
		long timeSinceLastAutosave = annotationTime = (finishedAt - startedAt) / 1000;
		
		
		return timeSinceLastAutosave;
	}

	protected void done() {
		try {
			publish("\n\n=> Overall annotation time: "
					+ (int) (overallAnnotationTime) + " seconds");

			// upload the data
			session.uploadSession(true, (int) annotationTime);

			// remove autosave markables
			session.removeAutosaveMarkables();
		} catch (Exception e) {
			GUIMessages.exceptionMessage(e);
			logger.log(Level.SEVERE, "", e);
		}
	}

	public int getProjectID() {
		return this.projectID;
	}

	public int getBaseDataID() {
		return this.baseDataID;
	}

	protected void process(List<String> chunks) {
		for (String s : chunks) {
			outputFrame.appendText(s + "\n");
		}
	}

	public boolean getIsAl() {
		return this.isAl;
	}
}
