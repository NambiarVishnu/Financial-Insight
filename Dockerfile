FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/transaction-0.0.1-SNAPSHOT.jar /app/app.jar
# Install necessary fonts for AWT
RUN apk add --no-cache ttf-dejavu fontconfig

EXPOSE 8080
ENTRYPOINT ["java", "-Djava.awt.headless=true","-jar","/app/app.jar"]