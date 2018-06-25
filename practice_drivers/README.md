# whereIsMyDriver4

Tech stack 
-----------------------------------------------------------------
Dropwizard - web framework,  Junit for tests, Postman for generating/running single test cases
maven - build-dependency-management-tool
redis = data store - redis has native means to store lat-long geo locations,
    and a native implementation of georadius, which searches and provides the nearest n locations



Setup instructions, automated deployment of the program and dependencies to
------------------------------------------------------------------------------
Build - to compile test and run the application, run :
 - 'mvn clean install' from the unzip-folder to build your application ( this step is long, maven downloads the internet)
 - 'mvn test' to run unit tests
 - 'mvn clean verify -P integration-test'  to run integration-tests. This needs a running application (see next)
 - 'java -jar target/whereIsMyDriver4-1.0-SNAPSHOT.jar server config.yml' 

Start application with
To check that your application is running enter url `http://localhost:8080`
