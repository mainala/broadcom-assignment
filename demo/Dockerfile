# Use the official Maven image to build the application
FROM maven:3.9.8-eclipse-temurin-22-alpine AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml and source code
COPY pom.xml .
COPY src ./src

# Package the application
RUN mvn clean package -DskipTests

# Use the official OpenJDK image to run the application
FROM openjdk:22-jdk

# Set the working directory
WORKDIR /app

# Copy the jar file from the build stage
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar app.jar

# Expose the port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
