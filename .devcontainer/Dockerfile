# Dockerfile
FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/my-app.jar app.jar

EXPOSE 8089

ENTRYPOINT ["java", "-jar", "app.jar"]
