# Etapa 1: Compilar o projeto com Maven
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Criar a imagem final só com o JAR
FROM eclipse-temurin:17
WORKDIR /app
COPY --from=builder /app/target/gateway-service-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
