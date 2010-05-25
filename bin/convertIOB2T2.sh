# set classpath dir
#source /home/coling/java_libs/classpath.def

ANNOENV="AnnoEnv.jar"
CP="../lib/$ANNOENV:../settings:../lib/mysql-connector-java-3.1.12-bin.jar:../lib/EntityAL-Libs-2.0.jar:../lib/jama.jar:../lib/mallet-deps.jar:../lib/mallet.jar:../lib/maxent-2.4.0.jar:../lib/opennlp-tools-1.3.0.jar:../lib/trove.jar:../lib/UEAstemmer.jar:." 

# now run the gui
java -cp $CP -Djava.util.logging.config.file=settings/logging.properties  de.julielab.annoenv.conversions.ConvertIOB2T2   $1 $2 
