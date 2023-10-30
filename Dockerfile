FROM openjdk:17-jdk-slim

EXPOSE 8080

ADD /build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "-Duser.timezone=Asia/Seoul", "/app.jar"]