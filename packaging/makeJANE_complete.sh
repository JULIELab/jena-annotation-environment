VERSION="2.0"

cd ..

# copy AnnoEnv to JANE directory
rm -rf JANE-$VERSION
mkdir JANE-$VERSION
cp -a * JANE-$VERSION

# get dummy settings
cd JANE-$VERSION
rm -rf settings
mv packaging/settings_full settings
cd ..

# build AnnoEnv.jar
cd JANE-$VERSION
ant
cd ..

# make docu
cd packaging
./makeDocu.sh
cd ..

# make tgz file
tar -czvf JANE-$VERSION.tgz JANE-$VERSION/bin JANE-$VERSION/doc JANE-$VERSION/lib JANE-$VERSION/resources JANE-$VERSION/scripts JANE-$VERSION/settings --exclude=".svn"

# remove JANE directory
rm -rf JANE-$VERSION
