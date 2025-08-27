FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY build/libs/payment-service-0.0.1-SNAPSHOT.jar /app/payment-service.jar

EXPOSE 8084

CMD ["java", "-jar", "/app/payment-service.jar"]