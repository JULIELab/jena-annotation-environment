#!/bin/bash

# to run ALServer as a daemon start it with screen!



ANNOENV="AnnoEnv.jar"
CP="../lib/$ANNOENV:../settings:../lib/mysql-connector-java-3.1.12-bin.jar:."

java -cp $CP -Djava.util.logging.config.file=../settings/logging.properties de.julielab.annoenv.al.ALServer $1 
