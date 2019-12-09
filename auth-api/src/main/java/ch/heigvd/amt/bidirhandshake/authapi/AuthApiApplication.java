package ch.heigvd.amt.bidirhandshake.authapi;

import ch.heigvd.amt.bidirhandshake.authapi.repositories.UserRepository;
import ch.heigvd.amt.bidirhandshake.authapi.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AuthApiApplication {

    private static final Logger log = LoggerFactory.getLogger(AuthApiApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AuthApiApplication.class, args);
    }

    @Bean
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
            List<User> users = repository.findByRole(User.Role.admin);
            log.info("User found with findByRole('Admin'):");
            log.info("--------------------------------");
            for (User user : users) {
                log.info(user.toString());
            }
            log.info("");

            // fetch users by last name
            log.info("User found with findByUsername('pete842'):");
            log.info("--------------------------------------------");
            repository.findByUsername("pete842").forEach(bauer -> {
                log.info(bauer.toString());
            });

            log.info("");
        };
    }

}
