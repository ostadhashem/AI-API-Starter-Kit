# Use an official Java runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the project jar file into the container
COPY build/libs/ai-api-starter-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port (default is 8080 for Spring Boot apps)
EXPOSE 8080

# Define the command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
