# to run ALServer as a daemon start it with screen!

cd ..
ANNOENV="AnnoEnv.jar"
CP="lib/*:."
java -Xmx256m -cp $CP de.julielab.annoenv.al.ALServer $1

