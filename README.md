# infinite-scroll

### Prerequisites
1. [java 8] (http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
2. [apache maven 3.2+] (https://maven.apache.org/download.cgi?Preferred=ftp://mirror.reverse.net/pub/apache/)
3. Installed and running [mongoDB 3.x] (https://www.mongodb.org/downloads).  Mongo db should be running on port localhost:27017 

### Steps to build and run
1. Clone the git repo
2. Go the directory that contains the pom.xml file
3. Run `mvn assembly:assembly`
4. This should create a file target/exercise-1.0-SNAPSHOT-jar-with-dependencies.jar
5. Run `java -jar target/exercise-1.0-SNAPSHOT-jar-with-dependencies.jar`
6. This command will start a http server running on port 4567.  Point a browser to http://localhost:4567/fashions.html
