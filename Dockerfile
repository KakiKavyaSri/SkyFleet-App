FROM openjdk:17
ADD target/flightmanagement-0.0.1-SNAPSHOT.war flightmanagement-0.0.1-SNAPSHOT.war 
ENTRYPOINT ["java", "-jar", "/flightmanagement-0.0.1-SNAPSHOT.war"]
