ARG IMAGE=SET_IMAGE_BUILD_ARG
FROM ${IMAGE}

ARG JAR_FILE=target/*-spring-boot.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar","/app.jar"]
