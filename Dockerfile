# Dockerfile
FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/Aziz-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8089

ENTRYPOINT ["java", "-jar", "app.jar"]