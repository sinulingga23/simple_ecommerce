# Build Jar File
FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /app
COPy pom.xml .
COPY src ./src
RUN mvn clean package

# Create Imafe
FROM amazoncorretto:17-alpine-jdk
MAINTAINER Denny Rezky Sinulingga
WORKDIR /app

COPY wait-for-it.sh wait-for-it.sh
CMD ["sh", "-c", "./wait-for-it.sh db:5432"]

COPY --from=build /app/target/productsservice-0.0.1-SNAPSHOT.jar ./productsservice-0.0.1.jar
CMD ["java", "-jar", "productsservice-0.0.1.jar"] 