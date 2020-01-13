FROM openjdk:8

COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh

COPY target/movie-api-0.0.1-SNAPSHOT.jar /usr/src/movie-api.jar

WORKDIR /usr/src

CMD ["/wait-for-it.sh" , "db:3306" , "--strict" , "--timeout=0" , "--" , "java", "-jar", "movie-api.jar"]
