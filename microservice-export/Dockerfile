FROM maven:3.8.1-jdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean install -DskipTests

FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/microservice-export-0.0.1-SNAPSHOT.jar msvc-export.jar
RUN apt-get update && apt-get install -y curl
ENTRYPOINT ["java","-jar","/msvc-export.jar"]
