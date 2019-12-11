package ch.heigvd.amt.bidirhandshake.movieapi;

import ch.heigvd.amt.bidirhandshake.movieapi.entities.User;
import ch.heigvd.amt.bidirhandshake.movieapi.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class MovieApiApplication {

    private static final Logger log = LoggerFactory.getLogger(MovieApiApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MovieApiApplication.class, args);
    }


    @Bean
    @Transactional
    public CommandLineRunner demo(UserRepository repository) {
        return (args) -> {
            // save a user
            // repository.save(new User(...));

            // fetch all users
            log.info("Users found with findAll():");
            log.info("-------------------------------");
            for (User user : repository.findAll()) {
                log.info(user.toString());
            }
            log.info("");

            // fetch an individual user by role
            User user = repository.findByUsername("pete842");

            log.info("User 'pete842' to watch medias: #" + user.getToWatchMediaUser().size());
            /*log.info("--------------------------------");
            for (ToWatchMediaUser toWatch : user.getToWatchMediaUser()) {
                log.info(toWatch.toString());
            }
            log.info("");*/

            log.info("User 'pete842' watched medias: #" + user.getWatchedMediaUser().size());
            /*log.info("--------------------------------");
            for (WatchedMediaUser watched : user.getWatchedMediaUser()) {
                log.info(watched.toString());
            }*/
            log.info("");
        };
    }
}
