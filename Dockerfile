# Use the official Maven image to build the application
FROM maven:3.8.5-openjdk-17-slim AS build
WORKDIR /DocConnect-main

# Copy the project files
COPY pom.xml .
COPY src ./src

# Build the project
RUN mvn clean package -DskipTests

# Use the official OpenJDK image for the runtime environment
FROM openjdk:17
WORKDIR /DocConnect-main

# Copy the jar file from the build stage
COPY --from=build /DocConnect-main/target/DocConnectApp-2.0.jar /DocConnect-main/DocConnectApp-2.0.jar

# Expose the port that the application runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/DocConnect-main/DocConnectApp-2.0.jar"]
