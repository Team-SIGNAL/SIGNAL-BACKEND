FROM openjdk:17-jdk-slim
EXPOSE 8080
ARG JAR_FILE=/build/libs/Web-Team-2-Backend-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","/app.jar"]

FROM eclipse-temurin:17-jre-focal

EXPOSE 8080

ADD /build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "-Duser.timezone=Asia/Seoul", "/app.jar"]