/** 
 * ALClient.java
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
 * Creation date: Aug 01, 2006 
 * 

 **/

package de.julielab.annoenv.al;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Logger;

import de.julielab.annoenv.utils.AnnoEnvLogger;
import de.julielab.annoenv.utils.settings.ActiveLearningSettings;

/**
 * A client to sent an AL request to the AL server.
 */

// TODO handle exceptions appropriately (catch runtime stuff and write to DB)
public class ALClient {

	private static Logger logger = AnnoEnvLogger
			.getLogger("de.julielab.annoenv.al.ALClient");

	//TODO is this for testing only ?
	public static void main(String[] args) {
		//if (args.length == 1) {
			//int projectID = (new Integer(args[0]).intValue());
		int projectID = 162;
			System.out.println("project id: " + projectID);
			sendALRequest(projectID);
		//} else {
		//	System.err.println("usage: ALClient <projectID>");
		//}
	}

	/**
	 * send a request to the AL server to start an AL process for the specified
	 * project ID. This function does also open a tcp socket to the server. If
	 * the server is down, it throws an exception.
	 * 
	 * @param projectID
	 * @return true, if AL process could be started
	 */
	public static boolean sendALRequest(int projectID) {
		ActiveLearningSettings alSettings;
		logger.info("[AL client] sending AL request for project with PID="+projectID);
		alSettings = new ActiveLearningSettings();
		try {
			Socket sock = new Socket(alSettings.ALSERVER_HOST,
					alSettings.ALSERVER_PORT);

			DataInputStream is = new DataInputStream(sock.getInputStream());
			PrintStream os = new PrintStream(sock.getOutputStream(), true);

			// first send the AL start request
			os.print("AL#" + projectID + "\r\n");
			os.flush();
			// then wait for confirmation or time out
			String line=null;
			while ((line = is.readLine()) != null) {
				if (line.equals("AL=1")) {
					// success
					logger.info("AL successfully started...");
					return true;
				} else if (line.equals("AL=-1")) {
					throw new RuntimeException(
							"AL could not be started. Check AL log frame!");
				} else {
					throw new RuntimeException(
							"Received unknown command from AL server, this should not happen, ask administrator.");
				}
			}
			
		} catch (UnknownHostException e1) {
			throw new RuntimeException("Connection to AL server failed: Host '"
					+ alSettings.ALSERVER_HOST + "' unknown", e1);
		} catch (IOException e2) {
			throw new RuntimeException(
					"Connection to AL server failed with error '"
							+ e2.getMessage()
							+ "'.\nProbably AL server not started or listening on other host or port.",
					e2);
		}
		return false;
	}

}
