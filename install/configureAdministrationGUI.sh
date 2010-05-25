ANNOENV="AnnoEnv.jar"
CP="../lib/$ANNOENV:../settings:../lib/mysql-connector-java-3.1.12-bin.jar:."

java -cp $CP -Djava.util.logging.config.file=settings/logging.properties de.julielab.annoenv.utils.settings.AnnoMasterSettings
