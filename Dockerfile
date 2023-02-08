FROM eclipse-temurin:17-jdk-alpine
COPY "./target/blackjack-account-and-security-service-0.0.1-SNAPSHOT.jar" "app.jar"
EXPOSE 8083
ENTRYPOINT ["java","-jar","app.jar"]