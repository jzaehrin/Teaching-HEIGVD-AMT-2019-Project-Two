package ch.heigvd.amt.bidirhandshake.movieapi.repositories;

import ch.heigvd.amt.bidirhandshake.movieapi.entities.Media;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;


public interface MediaRepository extends CrudRepository<Media, Integer> {
    Media findByTitle(String title);

    Page<Media> findAll(Pageable pageable);

    Media save(Media media);
}