FROM ubuntu:20.04

RUN apt-get update -y
RUN apt-get -y install -y openjdk-11-jre-headless

ARG ENTRYPOINT=src/main/docker/docker-entrypoint.sh
COPY ${ENTRYPOINT} /docker-entrypoint.sh
RUN chmod +x /docker-entrypoint.sh

STOPSIGNAL SIGQUIT
ENTRYPOINT ["/docker-entrypoint.sh"]

ARG JAR_FILE=target/*-spring-boot.jar
COPY ${JAR_FILE} /app.jar