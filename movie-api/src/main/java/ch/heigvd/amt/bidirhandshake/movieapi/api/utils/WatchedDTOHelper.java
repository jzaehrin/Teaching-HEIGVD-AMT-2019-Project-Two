package ch.heigvd.amt.bidirhandshake.movieapi.api.utils;

import ch.heigvd.amt.bidirhandshake.movieapi.dto.WatchedDTO;
import ch.heigvd.amt.bidirhandshake.movieapi.entities.WatchedMediaUser;
import ch.heigvd.amt.bidirhandshake.movieapi.entities.keys.MediaUserKey;
import ch.heigvd.amt.bidirhandshake.movieapi.repositories.MediaRepository;
import ch.heigvd.amt.bidirhandshake.movieapi.repositories.UserRepository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

public class WatchedDTOHelper {

    public static WatchedDTO fromEntity(WatchedMediaUser watchedMediaUser) {
        System.out.println(watchedMediaUser.getRating());
        System.out.println(watchedMediaUser.getWatched());

        return new WatchedDTO().mediaId(watchedMediaUser.getId().getMediaId())
                                .userId(watchedMediaUser.getId().getUserId())
                                .rating(watchedMediaUser.getRating());
                                //.watched(watchedMediaUser.getWatched().toInstant().getNano() / 1000000);
    }

    public static List<WatchedDTO> fromEntity(List<WatchedMediaUser> watchedsMediaUser) {
        return watchedsMediaUser.stream().map(v -> fromEntity(v)).collect(Collectors.toList());
    }

    public static WatchedMediaUser toEntity(WatchedDTO watchedDTO, MediaRepository mediaRepository, UserRepository userRepository) {
        WatchedMediaUser watchedMediaUser = new WatchedMediaUser();

        watchedMediaUser.setId(new MediaUserKey(watchedDTO.getUserId(), watchedDTO.getMediaId()));
        watchedMediaUser.setMedia(mediaRepository.findById(watchedDTO.getMediaId()).orElse(null));
        watchedMediaUser.setUser(userRepository.findById(watchedDTO.getUserId()).orElse(null));
        watchedMediaUser.setRating(watchedDTO.getRating());
        watchedMediaUser.setWatched(Timestamp.from(Instant.ofEpochSecond(watchedDTO.getWatched())));

        return watchedMediaUser;
    }

    public static List<WatchedMediaUser> toEntity(List<WatchedDTO> watchedsDTO, MediaRepository mediaRepository, UserRepository userRepository) {
        return  watchedsDTO.stream().map(watchedDTO -> toEntity(watchedDTO, mediaRepository, userRepository)).collect(Collectors.toList());
    }
}
