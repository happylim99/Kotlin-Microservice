FROM openjdk:17-ea
#FROM openjdk:11-ea-9-jdk-slim
#FROM openjdk:17-alpine
#FROM openjdk:17-ea-jdk-oracle
#FROM openjdk:11-jre-slim-buster

VOLUME /tmp

RUN mkdir -p /opt/app
#WORKDIR /opt/app
#COPY encryptionKey.jks encryption.jks
COPY configfile /opt/app/config
COPY build/libs/ConfigServer-0.0.1-SNAPSHOT.jar ConfigServer.jar

#RUN ["apt-get", "update"]
#RUN ["apt-get", "install", "-y", "vim"]

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/urandom", "-jar", "ConfigServer.jar"]

CMD /bin/bash


