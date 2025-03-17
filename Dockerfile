FROM gradle:8.5-jdk21 AS build

# Set work directory
WORKDIR /app

# Copy Gradle files and dependencies
COPY build.gradle settings.gradle ./
COPY src ./src

# Build application
RUN gradle bootJar --no-daemon

# Use a minimal JDK image for runtime
FROM openjdk:21-jdk-slim

# Set work directory
WORKDIR /app

# Copy built JAR from the build stage
COPY --from=build /app/build/libs/f3d-manager-*.jar app.jar

# Expose application port
EXPOSE 8080

# Run application
CMD ["java", "-jar", "app.jar"]