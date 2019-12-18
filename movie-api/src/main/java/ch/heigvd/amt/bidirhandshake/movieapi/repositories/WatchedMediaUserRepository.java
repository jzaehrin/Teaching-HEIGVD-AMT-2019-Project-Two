package ch.heigvd.amt.bidirhandshake.movieapi.repositories;

import ch.heigvd.amt.bidirhandshake.movieapi.entities.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;


public interface WatchedMediaUserRepository extends CrudRepository<WatchedMediaUser, Integer> {
    Page<WatchedMediaUser> findAllByUser(Pageable pageable, User user);
}