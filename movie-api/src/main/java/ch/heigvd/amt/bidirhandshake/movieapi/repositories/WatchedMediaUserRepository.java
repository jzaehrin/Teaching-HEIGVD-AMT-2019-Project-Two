package ch.heigvd.amt.bidirhandshake.movieapi.repositories;

import ch.heigvd.amt.bidirhandshake.movieapi.entities.*;
import ch.heigvd.amt.bidirhandshake.movieapi.entities.keys.MediaUserKey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;


public interface WatchedMediaUserRepository extends CrudRepository<WatchedMediaUser, MediaUserKey> {
    Page<WatchedMediaUser> findAllByUser(Pageable pageable, User user);
    long countByUser(User user);
}