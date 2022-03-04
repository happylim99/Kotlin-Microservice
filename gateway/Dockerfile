FROM openjdk:17-ea

VOLUME /tmp
WORKDIR /opt/app
COPY build/libs/gateway-0.0.1-SNAPSHOT.jar SpringGateway.jar

ENTRYPOINT ["java", "-jar", "SpringGateway.jar"]