# Dockerfile

# --- Stage 1: Build the application ---
FROM maven:3.9.11-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src

# Build the project, creating the .jar file
RUN mvn clean package -DskipTests

# --- Stage 2: Create the final production image ---
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# Copy ONLY the compiled .jar file from the 'builder' stage
COPY --from=builder /app/target/*.jar app.jar
# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]