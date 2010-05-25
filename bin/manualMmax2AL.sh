# usage: 
# ARG1: alsession_txt_file
# ARG2: scheme_file
# ARG3: priolist_txt_file
# ARG4: posdirpath
# ARG5: basedata_file
# ARG6: markables_level_file
# ARG7: output for t2_file
# ARG8: -b = Print B-/I- before nonempty MMAX2-tags [optional]


ANNOENV="AnnoEnv.jar"
CP="../lib/$ANNOENV:../settings:../lib/mysql-connector-java-3.1.12-bin.jar:../lib/EntityAL-Libs-2.0.jar:../lib/jama.jar:../lib/mallet-deps.jar:../lib/mallet.jar:../lib/maxent-2.4.0.jar:../lib/opennlp-tools-1.3.0.jar:../lib/trove.jar:../lib/UEAstemmer.jar:."

# now run the program
java -cp $CP de.julielab.annoenv.scripts.Mmax2AL2 $1 $2 $3 $4 $5 $6 $7 $8
