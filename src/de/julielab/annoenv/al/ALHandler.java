/** 
 * ALHandler.java
 * 
 * Copyright (c) 2007, JULIE Lab. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0 
 *
 * Author: tomanek, kampe
 * 
 * Current version: 2.0
 * Since version:   0.7
 *
 * Creation date: Aug 01, 2006 
 */

package de.julielab.annoenv.al;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

import de.julielab.annoenv.conversions.Tidsid;
import de.julielab.annoenv.db.annodata.AnnoLight;
import de.julielab.annoenv.db.annodata.BaseDataFull;
import de.julielab.annoenv.db.annodata.Markable;
import de.julielab.annoenv.db.annodata.MmaxData;
import de.julielab.annoenv.db.sql.SQLDatabaseManager;
import de.julielab.annoenv.db.sql.SQLFunctions;

import de.julielab.annoenv.utils.AnnoEnvLogger;
import de.julielab.annoenv.utils.Constants;
import de.julielab.annoenv.utils.DeleteDir;

import de.julielab.annoenv.utils.InputStreamHandler;
import de.julielab.annoenv.utils.settings.ActiveLearningSettings;

/**
 * This class defines the AL programm that is executed on behalf of the
 * ALserver. It will first download all data needed from the DB, than convert
 * the data (IOB, pipedFormat), then start AL itself (in single or multithread
 * mode). Finally, it converts the AL output (list of tid and sid) into MMax
 * data which is uploaded to the RepDB.
 */
public class ALHandler extends Thread {

	private static Logger logger = AnnoEnvLogger
			.getLogger("de.julielab.annoenv.al.ALHandler");

	Socket sock;

	ActiveLearningSettings alSettings;

	SQLDatabaseManager sdm;

	SQLFunctions sf;

	int projectID = 120; // value set for testing with ALTester.java

	AnnoLight project;

	File tmpDir;

	File settingsFile;

	File atFile;

	File t2File;

	File corpusBin;

	int batchsize;

	File tagsFile;

	float samplingFraction;

	File selList;

	String committee;

	String trainprop;

	/**
	 * the standard constructor!
	 * 
	 * @throws AnnoPlatformException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ParserConfigurationException
	 */
	public ALHandler(Socket sock) {
		this.sock = sock;
		System.out.println("new ALHandler instance started...");
		sdm = new SQLDatabaseManager();
		sdm.setConnection();
		sf = new SQLFunctions(sdm);

		alSettings = new ActiveLearningSettings();
		tmpDir = alSettings.ALSERVER_TMPDIR;
	}

	/**
	 * this method is automatically run when this thread is created
	 */
	public void run() {
		try {
			PrintStream os = new PrintStream(sock.getOutputStream(), true);
			BufferedReader is = new BufferedReader(new InputStreamReader(sock
					.getInputStream()));

			String line = "";
			boolean listen = true;
			while (listen && (line = is.readLine()) != null) {
				logger.info(projectID + "#socket received line: " + line);

				String[] values = line.split("#");
				if (values.length == 2 && values[0].equals("AL")) {
					projectID = (new Integer(values[1])).intValue();
					logger.info(projectID + "#Trying to start AL selection...");

					/* get data for active learning */
					boolean ok = false;
					RuntimeException getDataRuntimeException = null;

					try {
						ok = getData();
					} catch (RuntimeException e) {
						getDataRuntimeException = e;
					}

					if (ok) {
						os.print("AL=1\r\n"); // return ok to client
						os.flush();
					} else {
						os.print("AL=-1\r\n"); // return failure to client
						os.flush();
						sock.close();
						if (getDataRuntimeException!=null)
							throw getDataRuntimeException;
						else 
							throw new RuntimeException("AL process could not be started. Getting data failed.");
					}
					listen = false;
				} else 
					throw new RuntimeException("unexpected command received from ALClient: " + line);
				
				os.close();
				is.close();
			}

			/* now close the connection to AL Client */
			sock.close();

			/* write settings file */
			writeSettings();

			/* run active learning process */
			runAL();

			/* upload active learning data */
			uploadData();

		} catch (Exception e) {
			project.setAlStatus(Constants.AL_PROJECT_STATUS_ERROR);
			project.updateProject(sf);
			logger.log(Level.SEVERE, projectID + "#", e);
			throw new RuntimeException("error running AL selection", e);
		}

		try {
			/* clean up tmp dir */
			cleanUp();

			/* finished */
			logger.info(projectID + "#AL finished successfully!");
		} catch (Exception e) {
			logger.log(Level.WARNING, projectID + "#", e);
			throw new RuntimeException("error running AL", e);
		}

	}

	File makeTmpDir(File tmpDir) {

		if (!tmpDir.isDirectory()) {
			throw new RuntimeException(
					"temporary directory for AL does not exist ("
							+ tmpDir.getAbsolutePath() + ")");
		}

		// create session directory
		long timestamp = System.currentTimeMillis();
		File new_tmpDir = new File(tmpDir.getAbsolutePath() + File.separator
				+ "ALSERVER_" + timestamp);
		boolean success = new_tmpDir.mkdir();
		if (!success) {
			throw new RuntimeException("could not create new AL tmp subdir");
		}

		return new_tmpDir;
	}

	public boolean writeSettings() {
		try {
			settingsFile = new File(tmpDir.getAbsolutePath() + File.separator
					+ "al.conf");
			FileWriter fw = new FileWriter(settingsFile);

			fw.write("#AL configuration\n");
			fw.write("minsentlen = " + alSettings.AL_MINSENTLEN + "\n");
			fw.write("samplingfrac = " + samplingFraction + "\n");
			fw.write("simmodels = " + committee + "\n");
			fw.write("trainprop = " + trainprop + "\n");
			fw.write("batchsize = " + batchsize + "\n");
			fw.write("atfile = " + atFile + "\n");
			fw.write("acfile = " + corpusBin + "\n");
			fw.write("tagsfile = " + tagsFile + "\n");
			fw.write("selfile = " + selList + "\n");
			fw.write("project_id = " + projectID + "\n");
			fw.close();
		} catch (IOException e) {
			throw new RuntimeException("Could not create AL settings file. ", e);
		}

		return true;

	}

	public boolean getData() {

		logger.info(projectID
				+ "#AL preprocessing: getting data for AL from DB...");

		try {
			// get a new AL tmp dir
			tmpDir = makeTmpDir(tmpDir);
			logger.info(projectID
					+ "#AL preprocessing: storing data in tmp directory: "
					+ tmpDir);

			// make sure you get a project of type AL
			project = sf.getLightProject(projectID);

			if (!project.getMode().equals("AL")) {
				// make sure project is a AL project
				return false;
			}

			committee = project.getAlCommittee();

			if (committee == null || committee.equals("")) {
				logger
						.severe(projectID
								+ "#AL preprocessing: AL committee not specified. No AL possible!");
			}

			double trainprop_value = project.getAlTrainprop();

			if (trainprop_value < 0.1) {
				logger
						.severe(projectID
								+ "#AL preprocessing: Training proportion for AL not correctly specified. No AL possible.");
			}

			trainprop = trainprop_value + "," + (1 - trainprop_value);

			if (project.getAlStatus().equals(Constants.AL_PROJECT_STATUS_ERROR)) {
				throw new IllegalStateException(
						"AL project has status 'error'. Restart MMax and AL, if that doesn't help ask administrator!");
			}

			// set lock on project
			project.setAlStatus(Constants.AL_PROJECT_STATUS_RUNNING);
			project.updateProject(sf);

			// 1) get files from DB
			logger.fine(projectID
					+ "#AL preprocessing: getting needed files... ");
			int bdID = project.getCurrentBaseDataLight().getBasedataID();

			BaseDataFull latestBD = sf.getBaseDataFull(bdID);
			List<Tidsid> TIDSIDFile = latestBD.getTidsidList(sdm);

			File corpusPosDir = project.getAlCorpusPos();
			logger.fine(projectID + "#AL preprocessing: CORPUS: "
					+ corpusPosDir.toString());

			List<String> priolist = project.getPriolist(sf
					.getSQLDatabaseManager());

			t2File = project.writeT2File(tmpDir.getAbsolutePath()
					+ File.separator + "aT.t2", sf.getSQLDatabaseManager());

			Document basedataDoc = latestBD.getBasedataDoc(sdm);

			ArrayList<MmaxData> mmaxDataList = sf.getMmaxDataList(projectID);

			Document markableDoc = latestBD.getMainLevelMarkable()
					.getMarkableDoc(sdm);

			// 2) convert data: create aT in PipedFormat
			atFile = ALConversions.makeALFile(t2File, basedataDoc, markableDoc,
					priolist, TIDSIDFile, corpusPosDir, tmpDir, projectID,
					latestBD.getUri(), sf);

			logger.fine(projectID + "#AL preprocessing: created aTFile: "
					+ atFile.toString());

			// 3) initialize other object variables nedded
			logger
					.fine(projectID
							+ "#AL preprocessing: other variable initialization... --- ");

			selList = new File(tmpDir.getAbsolutePath() + File.separator
					+ "list.sel");
			corpusBin = project.getAlCorpusBin();
			tagsFile = project.writeTagsFile(tmpDir.getAbsolutePath()
					+ File.separator + "tags.def", sf.getSQLDatabaseManager());
			batchsize = project.getAlBatchsize();
			samplingFraction = project.getAlCorpusFraction();

			logger.info(projectID + "#AL preprocessing: finished.");

		} catch (RuntimeException e) {
			throw new RuntimeException("AL preprocessing failed", e);
		}

		// if everything went fine, return positive status via socket
		return true;
	}

	/**
	 * runs the AL, i.e. starts a AL process on a remote machine and writes the
	 * list.sel file to the specified destination
	 * 
	 * @return the list.sel File object
	 * @throws InterruptedException
	 * @throws ALException
	 * @throws IOException
	 */
	public void runAL() {

		String[] cmd = null;
		try {

			logger.info(projectID + "#AL process to be initiated with configuration: "
					+ settingsFile.getAbsolutePath());

			// start process
			cmd = getSSHCommand();
			Process proc = Runtime.getRuntime().exec(cmd);
			System.out.println("running ssh command:\n" + showSSHCommand(cmd));
			logger.info(projectID +"#AL process is now running...");
			// read stderr and stdin and ignore output
			handleSTDInAndOut(proc);

			// wait for process and handle exit status
			if (proc.waitFor() != 0)
				throw new RuntimeException("AL process ended abnormally!");
			if (proc.waitFor() != 0)
				throw new RuntimeException("AL process ended abnormally!");

			// read in the data that was written
			if (!selList.isFile())
				throw new RuntimeException(
						"Selection file (list.sel) missing/not found.");

			// ArrayList<String> sel = IOUtils.readFile(selList);
			// System.out.println(sel.toString());

		} catch (InterruptedException e) {
			throw new RuntimeException("AL Process was interrupted!", e);
		} catch (IOException e) {
			String sshCommand = showSSHCommand(cmd);
			throw new RuntimeException(
					"#Error executing AL process ssh command '" + sshCommand, e);
		} catch (RuntimeException e) {
			throw new RuntimeException("Error running AL process", e);
		}
	}

	private String showSSHCommand(String[] cmd) {
		String sshCommand = "";
		for (int i = 0; i < cmd.length; i++)
			sshCommand += cmd[i] + " ";
		return sshCommand;
	}

	private void handleSTDInAndOut(Process proc) {
		StringBuffer inBuffer = new StringBuffer();
		InputStream inStream = proc.getInputStream();
		@SuppressWarnings("unused")
		InputStreamHandler stdin = new InputStreamHandler(inBuffer, inStream);
		StringBuffer errBuffer = new StringBuffer();
		InputStream errStream = proc.getErrorStream();
		@SuppressWarnings("unused")
		InputStreamHandler stderr = new InputStreamHandler(errBuffer, errStream);
	}

	private String[] getSSHCommand() {
		String usernameHost = alSettings.ALSERVER_USER + "@"
				+ alSettings.AL_PROCESS_SERVER;
		String cmd = "cd " + alSettings.AL_PATH + ";" + alSettings.AL_RAWCMD
				+ " " + settingsFile.getAbsolutePath();
		String[] c = new String[] { "ssh", usernameHost, cmd };
		return c;
	}

	public void uploadData() {
		// reconvert and write to DB

		logger.info(projectID + "#AL postprocessing: uploading data to DB.");
		try {
			// 1) get data needed
			File corpusTokDir = project.getAlCorpusTok();
			ArrayList<MmaxData> mmaxDataList = sf.getMmaxDataList(projectID);
			String levelName = getMainLevelMmaxData(mmaxDataList)
					.getLevelName();

			File new_styleFile = new File(tmpDir + File.separator
					+ "newstyle.xsl");
			File new_basedataFile = new File(tmpDir + File.separator
					+ "newbasedata.xml");
			File new_sentmarkableFile = new File(tmpDir + File.separator
					+ "newsentmarkable.xml");
			File new_mainmarkableFile = makeEmptyMainMarkable(levelName);
			File new_TIDSIDFile = new File(tmpDir + File.separator
					+ "newalsession.txt");

			// 2) create the new files
			logger
					.fine(projectID
							+ "#AL postprocessing: creating mmaxfiles...");
			ALConversions.makeMmaxFiles(corpusTokDir, selList, levelName,
					new_styleFile, new_basedataFile, new_sentmarkableFile,
					new_TIDSIDFile, projectID);

			// 3) upload to DB
			logger.info(projectID + "#AL postprocessing: now uploading");
			int sentMmaxdataID = getSentenceLevelMmaxData(mmaxDataList)
					.getMmaxdataID();
			int mainMmaxdataID = getMainLevelMmaxData(mmaxDataList)
					.getMmaxdataID();

			int its = project.getAlIterations();
			its++;

			// new basedata with markables
			Timestamp t = new Timestamp(System.currentTimeMillis());

			Markable sentM = new Markable();
			sentM.setMmaxdataID(sentMmaxdataID);
			sentM.setMarkableFile(new_sentmarkableFile);
			sentM.setCreationDate(t);
			sentM.setLevelName("");

			Markable mainM = new Markable();
			mainM.setMmaxdataID(mainMmaxdataID);
			mainM.setMarkableFile(new_mainmarkableFile);
			mainM.setCreationDate(t);
			mainM.setLevelName("");

			ArrayList<Markable> markables = new ArrayList<Markable>();
			markables.add(sentM);
			markables.add(mainM);

			BaseDataFull newBD = new BaseDataFull();
			String newUri = "IT_" + its;
			newBD.setUri(newUri);
			newBD.setProjectID(projectID);
			newBD.setBasedataFile(new_basedataFile);
			newBD.setStyleFile(new_styleFile);
			newBD.setTidsidFile(new_TIDSIDFile);
			newBD.setStatus("raw");
			newBD.setMarkables(markables);
			newBD.write(sdm);

			logger.fine(projectID + "#AL postprocessing: New basedata: \""
					+ newUri + " with ID \" " + newBD.getBasedataID()
					+ " created.");

			// 3) update AL_t2_file and AL iterations and remove project lock
			project.setAlIterations(its);
			project.setT2File(t2File);
			project.setAlStatus(Constants.AL_PROJECT_STATUS_IDLE);

			project.updateProject(sf);
			logger
					.info(projectID
							+ "#AL postprocessing: updated AL project: t2 file and AL iterations");

		} catch (RuntimeException e) {
			throw new RuntimeException("Error in AL post-processing", e);
		}
	}

	/**
	 * this deletes all files and directories that have been downloaded and/or
	 * created for the AL process...
	 */
	void cleanUp() {
		try {
			boolean success = DeleteDir.deleteDirectory(tmpDir);
			if (!success) {
				logger
						.warning(projectID
								+ "#AL postprocessing: Cleaning up AL session directory failed."
								+ tmpDir.toString());
			} else {
				logger
						.info(projectID
								+ "#AL postprocessing: Cleaning up AL session directory...");
			}
		} catch (RuntimeException e) {
			throw new RuntimeException("Error cleaning up after AL run", e);
		}
	}

	/**
	 * get the MmaxData of the main level i.g. if there is a sentence and a
	 * organism level, then organism should be the main level. This level is
	 * also marked in the DB!
	 * 
	 * @return
	 */
	private MmaxData getMainLevelMmaxData(ArrayList<MmaxData> mmaxDataList) {
		MmaxData mmaxData = null;
		for (MmaxData m : mmaxDataList) {
			if (m.isMainLevel()) {
				mmaxData = m;
			}
		}
		if (mmaxData == null) {
			logger
					.severe("MmaxData error: no main level found for this project");
		}
		return mmaxData;
	}

	/**
	 * get the MmaxData of the sentences level. This assumes that there is
	 * exactly one level which is not the main level... TODO this might have to
	 * be modified when we have more than one non-main level
	 */
	private MmaxData getSentenceLevelMmaxData(ArrayList<MmaxData> mmaxDataList) {
		MmaxData mmaxData = null;
		int count = 0;
		for (int i = 0; i < mmaxDataList.size(); i++) {
			MmaxData m = mmaxDataList.get(i);
			if (!m.isMainLevel()) {
				mmaxData = m;
				count++;
			}
		}
		if (mmaxData == null) {
			logger
					.severe("MmaxData error: no main level found for this project");
		}
		if (count > 1) {
			logger
					.severe("MmaxData error: more than one non-main level found! Taking last one!");
		}
		return mmaxData;
	}

	/**
	 * creates an empty main markable file... we might want to put this function
	 * to a more central position later... TODO: move this code ?!
	 */
	private File makeEmptyMainMarkable(String levelname) {
		String content = "<?xml version=\"1.0\"?>\n"
				+ "<!DOCTYPE markables SYSTEM \"markables.dtd\">\n"
				+ "<markables xmlns=\"www.eml.org/NameSpaces/" + levelname
				+ "\">\n" + "</markables>";
		File outFile = new File(tmpDir.getAbsoluteFile() + File.separator
				+ "newmainmarkable.xml");
		try {
			FileWriter fw = new FileWriter(outFile);
			fw.write(content);
			fw.close();
		} catch (IOException e) {
			throw new RuntimeException("Error writing empty markable.", e);
		}
		return outFile;
	}

	/*
	 * for testing...
	 */

	/**
	 * a constructur to be only used for testing purposes!
	 */
	public ALHandler(File tmpDir, int PID) throws InstantiationException,
			IllegalAccessException, SQLException, ClassNotFoundException,
			ParserConfigurationException {
		System.out.println("new ALHandler instance started...");
		sdm = new SQLDatabaseManager();
		sdm.setConnection();
		sf = new SQLFunctions(sdm);
		alSettings = new ActiveLearningSettings();
		this.tmpDir = tmpDir;
		this.projectID = PID;
		System.out.println("testing project with id " + PID);
	}

	/**
	 * this method is just used for testing. However, it runs all AL steps!
	 */
	public void testRun() {
		try {
			getData();
			writeSettings();
			runAL();
			uploadData();
		} catch (RuntimeException e) {
			project.setAlStatus(Constants.AL_PROJECT_STATUS_ERROR);
			project.updateProject(sf);
			logger.log(Level.SEVERE, projectID + "#", e);
			System.exit(-1);
		}

		try {
			cleanUp();
		} catch (RuntimeException e) {
			logger.log(Level.SEVERE, projectID + "#", e);
		}

	}
}
