# ---------- Build Stage ----------
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copy pom and source
COPY pom.xml .
COPY src ./src

# Build the application (skip tests for faster builds)
RUN mvn clean package -DskipTests

# ---------- Runtime Stage ----------
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copy built jar from previous stage
COPY --from=build /app/target/*.jar app.jar

# Set environment variable for config location if needed
ENV SPRING_CONFIG_LOCATION=classpath:/,file:/app/config/

# Expose port (Render injects $PORT)
EXPOSE 8080

# Run the jar with dynamic port (Render provides $PORT)
CMD ["sh", "-c", "java -jar app.jar --server.port=$PORT"]
