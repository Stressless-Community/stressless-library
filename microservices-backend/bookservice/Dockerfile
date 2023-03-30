FROM openjdk:17-jdk-alpine

# Setting up work directory
WORKDIR /app

MAINTAINER stressless.com

COPY ./target/bookservice.jar.jar /app

ENTRYPOINT ["java","-jar","bookservice.jar"]

# Starting the application
CMD ["java", "-jar", "bookservice.jar"]

EXPOSE 8082