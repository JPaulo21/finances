FROM eclipse-temurin:21-alpine as builder
WORKDIR /builder
COPY . .
RUN ./gradlew clean build -x test

FROM eclipse-temurin:21-alpine
WORKDIR /app
COPY --from=builder /builder/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
