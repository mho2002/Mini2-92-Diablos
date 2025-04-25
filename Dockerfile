# Use the specific OpenJDK 25 image you requested
FROM openjdk:25-ea-4-jdk-oraclelinux9

# Set the working directory inside the container
WORKDIR /app

# Copy only the JAR file to the container
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080 (Spring Boot default)
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
