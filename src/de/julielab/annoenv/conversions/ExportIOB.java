/** 
 * ExportIOB.java
 * 
 * Copyright (c) 2007, JULIE Lab. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0 
 *
 * Author: tomanek
 * 
 * Current version: 2.0
 * Since version:   0.7
 *
 * Creation date: Jan 29, 2007 
 * 
 * 
 */

package de.julielab.annoenv.conversions;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.w3c.dom.Document;

import de.julielab.annoenv.db.annodata.AnnoFull;
import de.julielab.annoenv.db.annodata.BaseDataFull;
import de.julielab.annoenv.db.annodata.Markable;
import de.julielab.annoenv.db.sql.SQLDatabaseManager;
import de.julielab.annoenv.db.sql.SQLFunctions;
import de.julielab.annoenv.utils.AnnoEnvLogger;
import de.julielab.annoenv.utils.DeleteDir;

/**
 * Class to export a project's basedata annotations into files in IOB format.
 * Uses Mmaxconverter.
 */
public class ExportIOB {

	private static Logger logger = AnnoEnvLogger
			.getLogger("de.julielab.annoenv.conversions.ExportIOB");

	private AnnoFull project;

	final private File outDir;

	private File tmpDir = null;

	private List<String> priolist = null;

	/**
	 * the constructor
	 */
	public ExportIOB(final AnnoFull project, final File outDir) {

		this.project = project;
		this.outDir = outDir;
	}

	public void run(SQLFunctions sf) {
		// make directories and get needed data
		init(sf.getSQLDatabaseManager());

		// get the basedata and convert it
		export(sf.getSQLDatabaseManager());

		// now cleanup
		cleanUp(tmpDir);
	}

	private void init(SQLDatabaseManager sdm) {
		if (!outDir.isDirectory()) {
			throw new RuntimeException("directory does not exist.");
		}
		if (!outDir.canWrite()) {
			throw new RuntimeException("directory not writeable.");
		}
		tmpDir = new File(outDir.toString() + File.separator + "tmp");
		if (!tmpDir.isDirectory()) {
			final boolean success = tmpDir.mkdir();
			if (!success) {
				throw new RuntimeException(
						"Error: could not create tmp dir. "
								+ "Probably this directory already exists, so please delete it!");
			}
		}

		// get some data from the project
		priolist = project.getPriolist(sdm);
	}

	/**
	 * this deletes all files and directories that have been downloaded and/or
	 * created for the session...
	 */
	void cleanUp(final File path) {
		final boolean success = DeleteDir.deleteDirectory(path);
		if (!success) {
			// log that cleanup did not work
			logger
					.warning("could not delete tmp directory: "
							+ path.toString());
		}
	}

	/**
	 * exports all basedata that has been set to "done" status
	 */
	private void export(SQLDatabaseManager sdm) {

		// now loop overall all basedata and make iob files

		ArrayList<BaseDataFull> basedata = null;
		basedata = project.getBaseDataFull();

		for (BaseDataFull bd : basedata) {

			if (!bd.getStatus().equals("done")) {
				// do not export
				continue;
			}

			Document basedataDoc = bd.getBasedataDoc(sdm);

			ArrayList<Markable> markables = bd.getMarkables();
			Document mainmarkableDoc = null;
			Document sentmarkableDoc = null;

			// FIXME: There can be more than 2 Markables,
			// i.e. in project Final_Genregulation!
			for (Markable m : markables) {
				if (m.isMainLevel()) {
					mainmarkableDoc = m.getMarkableDoc(sdm);
				} else {
					sentmarkableDoc = m.getMarkableDoc(sdm);
				}
			}

			// convert it to IOB
			final File iobFile = new File(outDir.toString() + File.separator
					+ bd.getUri() + ".iob");

			MmaxDataConverter converter = new MmaxDataConverter();
			// switch here if we want to use I and B tags for IOB export as well
			boolean addIOBLabels = false;

			converter.convertToIOB(iobFile, mainmarkableDoc, basedataDoc,
					priolist, sentmarkableDoc, addIOBLabels, bd.getUri());
		}
	}

	/**
	 * for testing and debugging use the main function... otherwise employ the
	 * ExportIOB object with its run() function
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SQLDatabaseManager sdm = new SQLDatabaseManager();
			sdm.setConnection();
			SQLFunctions sf = new SQLFunctions(sdm);
			AnnoFull project = sf.getFullProject(63);
			ExportIOB E = new ExportIOB(project, new File("/home/tomanek/tmp"));
			E.run(sf);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}