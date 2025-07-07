# Use Java 17 base image
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy project files into the container
COPY . .

# Build the application
RUN sh mvnw clean package -DskipTests

# Expose port 8080 (Spring Boot default)
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "target/flightmanagement-0.0.1-SNAPSHOT.jar"]
