package ch.heigvd.amt.bidirhandshake.movieapi.repositories;

import ch.heigvd.amt.bidirhandshake.movieapi.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByGlobalId(Integer globalId);
}