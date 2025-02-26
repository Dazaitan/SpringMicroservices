FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/microservice-gateway-0.0.1-SNAPSHOT.jar msvc-gateway.jar
RUN apt-get update && apt-get install -y curl
ENTRYPOINT ["java","-jar","/msvc-gateway.jar"]