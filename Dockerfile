FROM maven:3.9.6-openjdk-17 AS builder

WORKDIR /app

COPY pom.xml .

COPY src ./src

RUN mvn clean install -DskipTests 

FROM openjdk:17-jre-slim-bullseye

WORKDIR /app

COPY --from=builder /app/target/ryu-no-kinu-back-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8888 

ENTRYPOINT ["java", "-jar", "/app.jar"]