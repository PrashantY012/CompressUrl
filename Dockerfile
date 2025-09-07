# Use an official OpenJDK image
FROM openjdk:21-jdk-slim

LABEL authors="dbatm"

# Expose the port your Spring Boot app runs on
EXPOSE 8080

# Copy the built JAR into the image
COPY target/compress_url_backend.jar compress_url_backend.jar

# Run the application
ENTRYPOINT ["java", "-jar", "/compress_url_backend.jar"]
