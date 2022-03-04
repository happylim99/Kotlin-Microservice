FROM openjdk:17-ea

VOLUME /tmp
WORKDIR /opt/app
COPY build/libs/discovery-0.0.1.jar DiscoveryServer.jar

ENTRYPOINT ["java", "-jar", "DiscoveryServer.jar"]