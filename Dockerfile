FROM openjdk:15-jdk-alpine
EXPOSE 9090
RUN apk update && apk upgrade && apk add bash
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
