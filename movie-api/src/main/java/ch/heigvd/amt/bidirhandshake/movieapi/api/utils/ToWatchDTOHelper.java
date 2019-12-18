package ch.heigvd.amt.bidirhandshake.movieapi.api.utils;

import ch.heigvd.amt.bidirhandshake.movieapi.dto.ToWatchDTO;
import ch.heigvd.amt.bidirhandshake.movieapi.entities.ToWatchMediaUser;
import ch.heigvd.amt.bidirhandshake.movieapi.entities.keys.MediaUserKey;
import ch.heigvd.amt.bidirhandshake.movieapi.repositories.MediaRepository;
import ch.heigvd.amt.bidirhandshake.movieapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class ToWatchDTOHelper {

    @Autowired
    private static UserRepository userRepository;

    @Autowired
    private static MediaRepository mediaRepository;

    public static ToWatchDTO fromEntity(ToWatchMediaUser toWatchMediaUser) {
        return new ToWatchDTO().id(toWatchMediaUser.getMediaUserKey().getId())
                                .mediaId(toWatchMediaUser.getMedia().getId())
                                .userId(toWatchMediaUser.getUser().getId());
    }

    public static List<ToWatchDTO> fromEntity(List<ToWatchMediaUser> toWatchMediaUser) {
        return toWatchMediaUser.stream().map(v -> fromEntity(v)).collect(Collectors.toList());
    }

    public static ToWatchMediaUser toEntity(ToWatchDTO toWatchDTO) {
        ToWatchMediaUser toWatchMediaUser = new ToWatchMediaUser();

        toWatchMediaUser.setMediaUserKey(new MediaUserKey());
        toWatchMediaUser.setUser(userRepository.findById(toWatchDTO.getUserId()).orElse(null));
        toWatchMediaUser.setMedia(mediaRepository.findById(toWatchDTO.getMediaId()).orElse(null));

        return toWatchMediaUser;
    }

    public static List<ToWatchMediaUser> toEntity(List<ToWatchDTO> toWatchesDTO) {
        return  toWatchesDTO.stream().map(toWatchDTO -> toEntity(toWatchDTO)).collect(Collectors.toList());
    }
}
