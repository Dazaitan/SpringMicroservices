FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/microservice-config-0.0.1-SNAPSHOT.jar msvc-config.jar
ENTRYPOINT ["java","-jar","/msvc-config.jar"]
