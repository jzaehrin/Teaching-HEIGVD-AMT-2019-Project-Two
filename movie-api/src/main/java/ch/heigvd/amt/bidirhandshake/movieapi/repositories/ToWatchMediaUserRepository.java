package ch.heigvd.amt.bidirhandshake.movieapi.repositories;

import ch.heigvd.amt.bidirhandshake.movieapi.entities.ToWatchMediaUser;
import ch.heigvd.amt.bidirhandshake.movieapi.entities.User;
import ch.heigvd.amt.bidirhandshake.movieapi.entities.keys.MediaUserKey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ToWatchMediaUserRepository extends CrudRepository<ToWatchMediaUser, MediaUserKey> {
    Page<ToWatchMediaUser> findAllByUser(Pageable pageable, User user);
}