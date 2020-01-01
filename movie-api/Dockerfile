FROM openjdk:8

COPY target/movie-api-0.0.1-SNAPSHOT.jar /usr/src/movie-api.jar

WORKDIR /usr/src

CMD ["java", "-jar", "movie-api.jar"]