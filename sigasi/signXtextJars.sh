#!/bin/bash
echo sign jars
#sign jars
STOREPASSWD=$(grep sigasi.keystore.store.password ~/.m2/settings.xml | sed -e 's/.*>\(.*\)<.*/\1/')
KEYPASSWD=$(grep sigasi.keystore.key.password ~/.m2/settings.xml | sed -e 's/.*>\(.*\)<.*/\1/')

for i in */*vSigasi-*.jar
do
  echo sign $i
  jarsigner -keystore ~/.m2/sigasiKeyStore -storepass ${STOREPASSWD} -keypass ${KEYPASSWD} $i sigasi
done

#fix repo (md5 sums)
echo "fix md5 sums in repo"

rm -rf artifacts.xml
unzip artifacts.jar
sed -i -e '/download.md5/d' artifacts.xml || exit -1
rm artifacts.jar
jar cvf artifacts.jar artifacts.xml || exit -1
rm artifacts.xml
