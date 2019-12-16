package ch.heigvd.amt.bidirhandshake.movieapi.repositories;

import ch.heigvd.amt.bidirhandshake.movieapi.entities.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);

    User findById(Integer id);
}