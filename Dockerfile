FROM maven:3.8.1-openjdk-17-slim AS build
WORKDIR /app
COPY ./pom.xml /app/pom.xml
COPY ./src /app/src
RUN mvn clean package

FROM eclipse-temurin:17-jdk-alpine
COPY --from=build /app/target/blackjack-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "/app/app.jar"]