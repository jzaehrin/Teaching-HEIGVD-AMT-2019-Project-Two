package ch.heigvd.amt.bidirhandshake.movieapi.api.utils;

import ch.heigvd.amt.bidirhandshake.movieapi.dto.ToWatchDTO;
import ch.heigvd.amt.bidirhandshake.movieapi.entities.ToWatchMediaUser;
import ch.heigvd.amt.bidirhandshake.movieapi.entities.keys.MediaUserKey;
import ch.heigvd.amt.bidirhandshake.movieapi.repositories.MediaRepository;
import ch.heigvd.amt.bidirhandshake.movieapi.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ToWatchDTOHelper {

    public static ToWatchDTO fromEntity(ToWatchMediaUser toWatchMediaUser) {
        return new ToWatchDTO().mediaId(toWatchMediaUser.getId().getMediaId())
                                .userId(toWatchMediaUser.getId().getUserId());
    }

    public static List<ToWatchDTO> fromEntity(List<ToWatchMediaUser> toWatchMediaUser) {
        return toWatchMediaUser.stream().map(v -> fromEntity(v)).collect(Collectors.toList());
    }

    public static ToWatchMediaUser toEntity(ToWatchDTO toWatchDTO) {
        ToWatchMediaUser toWatchMediaUser = new ToWatchMediaUser();

        toWatchMediaUser.setId(new MediaUserKey(toWatchDTO.getUserId(), toWatchDTO.getMediaId()));

        return toWatchMediaUser;
    }

    public static List<ToWatchMediaUser> toEntity(List<ToWatchDTO> toWatchesDTO) {
        return  toWatchesDTO.stream().map(toWatchDTO -> toEntity(toWatchDTO)).collect(Collectors.toList());
    }
}
