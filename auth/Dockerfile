FROM openjdk:17-ea

VOLUME /tmp
WORKDIR /opt/app
COPY build/libs/auth-ws-0.0.1-SNAPSHOT.jar AuthWs.jar

ENTRYPOINT ["java", "-jar", "AuthWs.jar"]