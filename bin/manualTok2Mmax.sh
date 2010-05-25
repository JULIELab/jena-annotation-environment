
# usage: 
# ARG1: <FileDir> = tokenized input text files (one sentence per line)
# ARG2: <BasedataDir> = output directory for basedata files
# ARG3: <MarkableLevelDir/> = output directory for sentence- and entity-level markable files (e.g. for sentence_level, organism_level, etc.)
# ARG4: level_name = name of annotation level (e.g. cell_component, organism etc.)
# ARG5: entity_markable_file = e.g cell_component_level.xml

ANNOENV="AnnoEnv.jar"
CP="../lib/$ANNOENV:../settings:../lib/mysql-connector-java-3.1.12-bin.jar:../lib/EntityAL-Libs-2.0.jar:../lib/jama.jar:../lib/mallet-deps.jar:../lib/mallet.jar:../lib/maxent-2.4.0.jar:../lib/opennlp-tools-1.3.0.jar:../lib/trove.jar:../lib/UEAstemmer.jar:."

# now run the program
java -cp $CP de.julielab.annoenv.scripts.Tok2mmax1 $1 $2 $3 $4 $5
