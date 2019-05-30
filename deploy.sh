cd $1
FILE=`ls *.jar`
VERSION=`echo $FILE|awk -F '-|.jar' '{print $2}'`

GROUP_ID="com.dectfix.memorylorry"
ARTIFACT_ID="JSON2SQL"
URL="http://nexus.lp.com/nexus/content/repositories/releases"
REPO_ID="releases"

# upload jar
echo "mvn deploy:deploy-file -DgroupId=$GROUP_ID -DartifactId=$ARTIFACT_ID -Dversion=$VERSION -Dpackaging=jar -Dfile=$FILE -Durl=$URL -DrepositoryId=$REPO_ID"

mvn deploy:deploy-file -DgroupId=$GROUP_ID -DartifactId=$ARTIFACT_ID -Dversion=$VERSION -Dpackaging=jar -Dfile=$FILE -Durl=$URL -DrepositoryId=$REPO_ID
