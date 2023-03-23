FROM openjdk:17-jdk-alpine
MAINTAINER stressless.com
COPY target/gatewayservice.jar /opt/lib/
ENTRYPOINT ["java","-jar","/gatewayservice.jar"]
CMD ["-jar", "/opt/lib/gatewayservice.jar"]
EXPOSE 9091