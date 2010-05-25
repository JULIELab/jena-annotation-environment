# usage: 

# ARG1: trainPosDir
# ARG2: trainTokDir
# ARG3: goldPosDir
# ARG4: goldTokDir
# ARG5: goldSize (the size of the Goldstandard)

ANNOENV="AnnoEnv.jar"
CP="../lib/$ANNOENV:../settings:../lib/mysql-connector-java-3.1.12-bin.jar:../lib/EntityAL-Libs-2.0.jar:../lib/jama.jar:../lib/mallet-deps.jar:../lib/mallet.jar:../lib/maxent-2.4.0.jar:../lib/opennlp-tools-1.3.0.jar:../lib/trove.jar:../lib/UEAstemmer.jar:."

# now run the gui
java -cp $CP de.julielab.annoenv.scripts.SeparateGoldStandard $1 $2 $3 $4 $5
