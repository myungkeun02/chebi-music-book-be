# Build stage
FROM gradle:8.5-jdk17 AS build
WORKDIR /app

# Copy only the files needed for dependency resolution
COPY build.gradle settings.gradle ./
COPY gradle ./gradle

# Download dependencies
RUN gradle dependencies --no-daemon

# Copy source code
COPY src ./src

# Build the application without tests
RUN gradle build -x test --no-daemon

# Run stage
FROM openjdk:17-slim
WORKDIR /app

# Copy only the built jar from build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Add healthcheck
HEALTHCHECK --interval=30s --timeout=3s \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

EXPOSE 8080

# Use non-root user for security
USER nobody

ENTRYPOINT ["java", "-jar", "app.jar"] 