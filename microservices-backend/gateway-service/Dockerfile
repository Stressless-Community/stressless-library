FROM openjdk:17-jdk-alpine

# Setting up work directory
WORKDIR /app

MAINTAINER stressless.com

COPY ./target/gatewayservice.jar /app

ENTRYPOINT ["java","-jar","gatewayservice.jar"]

# Starting the application
CMD ["java", "-jar", "gatewayservice.jar"]

EXPOSE 9091