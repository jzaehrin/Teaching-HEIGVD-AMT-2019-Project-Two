package ch.heigvd.amt.bidirhandshake.movieapi.api.utils;

import ch.heigvd.amt.bidirhandshake.movieapi.dto.MediaDTO;
import ch.heigvd.amt.bidirhandshake.movieapi.entities.Media;

import java.sql.Timestamp;
import java.time.Instant;

public class MediaDTOHelper {

    public static MediaDTO fromEntity(Media media) {
        return  new MediaDTO().title(media.getTitle())
                                .release(media.getRelease().getNanos() / 1000000)
                                .duration(media.getDuration())
                                .mainGenre(media.getMainGenre())
                                .rating(media.getRating());
    }

    public static Media toEntity(MediaDTO mediaDTO) {
        Media media = new Media();

        media.setId(null);
        media.setTitle(mediaDTO.getTitle());
        media.setDuration(mediaDTO.getDuration());
        media.setMainGenre(mediaDTO.getMainGenre());
        media.setRating(mediaDTO.getRating());
        media.setRelease(Timestamp.from(Instant.ofEpochSecond(mediaDTO.getRelease())));

        return media;
    }
}
