FROM openjdk:21-jdk-slim

WORKDIR /APP

COPY build/libs/DesafioItau-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["JAVA", "-jar", "app.jar"]