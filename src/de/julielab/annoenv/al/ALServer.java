/** 
 * ALServer.java
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
 **/

package de.julielab.annoenv.al;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.julielab.annoenv.utils.AnnoEnvLogger;
import de.julielab.annoenv.utils.settings.ActiveLearningSettings;

/**
 * The AL server that waits for AL requests. It listens on a TCP port, whenever
 * a AL request comes in (a request includes the user id and the project id) the
 * server starts an AL thread and sends back an "OK".
 * 
 * Thus, remote usage of AL can be yielded, i.e. the server might run on an
 * other machine which remotely accessible, even though the real AL process
 * server is not.
 * 
 * 
 * To run the server as a daemon one might start it with screen.!
 */
public class ALServer extends Thread {

	private static Logger logger = AnnoEnvLogger
			.getLogger("de.julielab.annoenv.al.ALServer");

	public static void main(String[] args) {

		try {
			runServer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void runServer() throws Exception {

		ServerSocket sock = null;
		Socket clientSock = null;

		int clientsServed = 0;

		ActiveLearningSettings alSettings = new ActiveLearningSettings();
		sock = new ServerSocket(alSettings.ALSERVER_PORT);
		alSettings.showSettings();
		logger.info("AL Server started on: " + alSettings.ALSERVER_HOST + ":"
				+ alSettings.ALSERVER_PORT);

		/* wait for a connection */
		try {
			while (true) {
				clientSock = sock.accept();
				/* create a thread to do the communication */
				clientsServed++;
				new ALHandler(clientSock).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.SEVERE,"error accepting client",e);
		}

	}
}
