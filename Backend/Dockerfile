FROM openjdk:18.0-oracle
MAINTAINER joacibb
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} notes-challenge-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/notes-challenge-0.0.1-SNAPSHOT.jar"]