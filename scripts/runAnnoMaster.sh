# now run the gui
ANNOENV="AnnoEnv.jar"
cd ..
#CP="lib/$ANNOENV:lib/mysql-connector-java-3.1.12-bin.jar:lib/EntityAL-Libs-2.0.jar:lib/jama.jar:lib/mallet-deps.jar:lib/mallet.jar:lib/maxent-2.4.0.jar:lib/opennlp-tools-1.3.0.jar:lib/trove.jar:lib/UEAstemmer.jar:." 
CP="lib/*:."
java -cp $CP -Djava.util.logging.config.file=../settings/logging.properties de.julielab.annoenv.ui.annomaster.AnnoMasterLoginDialog
