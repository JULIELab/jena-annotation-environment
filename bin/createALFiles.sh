# usage: 
# ARG1: tokdirpath = Path of dir with tokenized input files, should not end in '/'
# ARG2: list_sel_path = Path of 'list.sel' file
# ARG3: levelname = "Annotation level" name
# ARG4: WHAT = 'S' for Styles output
#        'B' for Basedata output
#        'M' for Markables/sentence_level output
#        'D' for alsession.txt output
# ARG5: the output file for WHAT [optional]

# now run the program

ANNOENV="AnnoEnv.jar"
CP="../lib/$ANNOENV:../settings:../lib/mysql-connector-java-3.1.12-bin.jar:../lib/EntityAL-Libs-2.0.jar:../lib/jama.jar:../lib/mallet-deps.jar:../lib/mallet.jar:../lib/maxent-2.4.0.jar:../lib/opennlp-tools-1.3.0.jar:../lib/trove.jar:../lib/UEAstemmer.jar:." 

if [ -z "$5" ]
then
       java -cp $CP de.julielab.annoenv.scripts.MkALStyle1 $1 $2 $3 $4
       exit 0
fi


java -cp $CP de.julielab.annoenv.scripts.MkALStyle1 $1 $2 $3 $4 > $5
