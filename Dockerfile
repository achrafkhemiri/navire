# Multi-stage Dockerfile: build with Maven wrapper and run on Temurin 17

# Builder stage
FROM eclipse-temurin:21-jdk as builder
WORKDIR /workspace

# Copy maven wrapper and pom
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Copy source
COPY src src

# Ensure mvnw is executable
RUN chmod +x mvnw || true

# Build the application (skip tests for faster builds)
RUN ./mvnw -B clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy jar from builder
COPY --from=builder /workspace/target/*.jar app.jar

EXPOSE 8086

ENTRYPOINT ["java","-jar","/app/app.jar"]
