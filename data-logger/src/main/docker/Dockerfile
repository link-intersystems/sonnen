FROM ubuntu:20.04

RUN apt-get update -y
RUN apt-get -y install -y openjdk-11-jre-headless

ARG JAR_FILE=target/*-spring-boot.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar","/app.jar"]
