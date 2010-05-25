# now run the gui
cd ..
ANNOENV="AnnoEnv.jar"
CP="lib/*:."
java -Xmx256m -cp $CP de.julielab.annoenv.utils.settings.AnnoClientSettingsConfigGUI
