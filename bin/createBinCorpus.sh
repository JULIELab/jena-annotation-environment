# usage: 
# ARG1: the POS-dir
# ARG2: the sentence length
# ARG3: the binary corpus output file
# ARG4: optional: -i

ANNOENV="AnnoEnv.jar"
CP="../lib/$ANNOENV:../settings:../lib/mysql-connector-java-3.1.12-bin.jar:../lib/EntityAL-Libs-2.0.jar:../lib/jama.jar:../lib/mallet-deps.jar:../lib/mallet.jar:../lib/maxent-2.4.0.jar:../lib/opennlp-tools-1.3.0.jar:../lib/trove.jar:../lib/UEAstemmer.jar:."

# now run the gui
java -cp $CP de.julielab.annoenv.scripts.CorpusWriter $1 $2 $3 $4
