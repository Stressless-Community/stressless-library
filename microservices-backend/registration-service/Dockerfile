FROM openjdk:17-jdk-alpine

# Setting up work directory
WORKDIR /app

MAINTAINER stressless.com

COPY ./target/eurekaserviceregistry.jar /app

ENTRYPOINT ["java","-jar","eurekaserviceregistry.jar"]

# Starting the application
CMD ["java", "-jar", "eurekaserviceregistry.jar"]

EXPOSE 9093