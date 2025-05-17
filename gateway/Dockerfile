# gateway/Dockerfile

# Etapa 1: build com Maven
FROM maven:3.9.5-eclipse-temurin-17-alpine as builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests && ls -l target


# # Etapa 2: runtime com JDK leve
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY gateway\target\gateway-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]