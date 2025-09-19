# Use an official OpenJDK runtime as a parent image
FROM amazoncorretto:21-alpine-jdk

# Set working directory inside the container
WORKDIR /app

# Copy the Gradle wrapper and build files first (for caching)
COPY gradlew .
COPY gradle gradle
COPY build.gradle.kts .
COPY settings.gradle.kts .

# Copy source code
COPY src src

# Mount secret file in production
RUN --mount=type=secret,id=.env,dst=/etc/secrets/.env

# Build the application inside the container
RUN ./gradlew clean bootJar --no-daemon

# Expose the port your app runs on
EXPOSE 8080

# Run the jar file
CMD ["java", "-jar", "build/libs/auth-0.0.1-SNAPSHOT.jar"]
