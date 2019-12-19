package ch.heigvd.amt.bidirhandshake.movieapi.api.utils;

import ch.heigvd.amt.bidirhandshake.movieapi.dto.ToWatchDTO;
import ch.heigvd.amt.bidirhandshake.movieapi.entities.ToWatchMediaUser;
import ch.heigvd.amt.bidirhandshake.movieapi.repositories.MediaRepository;
import ch.heigvd.amt.bidirhandshake.movieapi.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ToWatchDTOHelper {

    public static ToWatchDTO fromEntity(ToWatchMediaUser toWatchMediaUser) {
        return new ToWatchDTO().id(toWatchMediaUser.getId())
                                .mediaId(toWatchMediaUser.getMedia().getId())
                                .userId(toWatchMediaUser.getUser().getId());
    }

    public static List<ToWatchDTO> fromEntity(List<ToWatchMediaUser> toWatchMediaUser) {
        return toWatchMediaUser.stream().map(v -> fromEntity(v)).collect(Collectors.toList());
    }

    public static ToWatchMediaUser toEntity(ToWatchDTO toWatchDTO, MediaRepository mediaRepository, UserRepository userRepository) {
        ToWatchMediaUser toWatchMediaUser = new ToWatchMediaUser();

        toWatchMediaUser.setId(null);
        toWatchMediaUser.setMedia(mediaRepository.findById(toWatchDTO.getMediaId()).orElse(null));
        toWatchMediaUser.setUser(userRepository.findById(toWatchDTO.getUserId()).orElse(null));

        return toWatchMediaUser;
    }

    public static List<ToWatchMediaUser> toEntity(List<ToWatchDTO> toWatchesDTO, MediaRepository mediaRepository, UserRepository userRepository) {
        return  toWatchesDTO.stream().map(toWatchDTO -> toEntity(toWatchDTO, mediaRepository, userRepository)).collect(Collectors.toList());
    }
}
