# Stage 1
FROM maven:3.9.6-amazoncorretto-21-al2023 AS mavenBuilder
WORKDIR /application

# Copy only the POM file initially to cache dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the application source code
COPY src/ src/

# Build artifact
RUN mvn clean package

# Stage 2
FROM eclipse-temurin:21-jre AS builder
WORKDIR /application

# Copy artifact from builder
COPY --from=mavenBuilder application/target/*.jar ./app.jar

# Layertools extract builder
RUN java -Djarmode=layertools -jar app.jar extract

# Stage 3
FROM eclipse-temurin:21-jre
WORKDIR /service

# Copy artifact from builder
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./

# Setup environtment
ENV BANK_TF_DB_PASSWORD=SecurePassword

# Expose internal port
EXPOSE 3333/tcp

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS org.springframework.boot.loader.launch.JarLauncher"]