FROM openjdk:8

COPY target/auth-api-0.0.1-SNAPSHOT.jar /usr/src/auth-api.jar

WORKDIR /usr/src

CMD ["java", "-jar", "auth-api.jar"]