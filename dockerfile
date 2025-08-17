# ========== Build Stage ==========
FROM maven:3.9.9-eclipse-temurin-17 AS builder
WORKDIR /app

COPY pom.xml .
RUN mvn -B dependency:go-offline

COPY src ./src
RUN mvn -B clean package -DskipTests

# ========== Run Stage ==========
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app

COPY --from=builder /app/target/archive-service-0.0.1-SNAPSHOT.jar archive-service.jar

EXPOSE 8081

# ========== run service ==========
ENTRYPOINT ["java","-jar","archive-service.jar"]
