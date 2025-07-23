FROM openjdk:17-jdk-slim AS builder

WORKDIR /app

COPY pom.xml .

COPY src ./src

RUN mvn clean install -DskipTests

FROM openjdk:17-jre-slim # Можно использовать jre-slim, если вам не нужен JDK в рантайме

WORKDIR /app

COPY --from=builder /app/target/ryu-no-kinu-back-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8888

ENTRYPOINT ["java", "-jar", "/app.jar"]