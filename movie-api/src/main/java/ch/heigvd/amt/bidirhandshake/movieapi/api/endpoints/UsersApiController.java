package ch.heigvd.amt.bidirhandshake.movieapi.api.endpoints;

import ch.heigvd.amt.bidirhandshake.movieapi.api.exceptions.ApiError;
import ch.heigvd.amt.bidirhandshake.movieapi.api.utils.ToWatchDTOHelper;
import ch.heigvd.amt.bidirhandshake.movieapi.api.utils.WatchedDTOHelper;
import ch.heigvd.amt.bidirhandshake.movieapi.dto.ToWatchDTO;
import ch.heigvd.amt.bidirhandshake.movieapi.dto.WatchedDTO;
import ch.heigvd.amt.bidirhandshake.movieapi.entities.ToWatchMediaUser;
import ch.heigvd.amt.bidirhandshake.movieapi.entities.User;
import ch.heigvd.amt.bidirhandshake.movieapi.entities.WatchedMediaUser;
import ch.heigvd.amt.bidirhandshake.movieapi.entities.keys.MediaUserKey;
import ch.heigvd.amt.bidirhandshake.movieapi.repositories.MediaRepository;
import ch.heigvd.amt.bidirhandshake.movieapi.repositories.ToWatchMediaUserRepository;
import ch.heigvd.amt.bidirhandshake.movieapi.repositories.UserRepository;
import ch.heigvd.amt.bidirhandshake.movieapi.repositories.WatchedMediaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Controller
public class UsersApiController implements UsersApi {

    @Autowired
    private ToWatchMediaUserRepository toWatchMediaUserRepository;

    @Autowired
    private WatchedMediaUserRepository watchedMediaUserRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private HttpServletRequest context;

    @Override
    public ResponseEntity<ToWatchDTO> addToWatch(String authorization, Integer userId, @Valid ToWatchDTO toWatchDTO) throws Exception {
        if (!context.getAttribute("userId").equals(userId)) throw new ApiError(HttpStatus.UNAUTHORIZED, "Can modifiy media_user of a user");
        existOrCreate(userId);

        toWatchDTO.setUserId(userId);
        ToWatchMediaUser toWatchMediaUser = ToWatchDTOHelper.toEntity(toWatchDTO, mediaRepository, userRepository);

        toWatchMediaUser = toWatchMediaUserRepository.save(toWatchMediaUser);

        return ResponseEntity.ok().body(ToWatchDTOHelper.fromEntity(toWatchMediaUser));
    }

    @Override
    public ResponseEntity<WatchedDTO> addWatched(String authorization, Integer userId, @Valid WatchedDTO watchedDTO) throws Exception {
        if (!context.getAttribute("userId").equals(userId)) throw new ApiError(HttpStatus.UNAUTHORIZED, "Can modifiy media_user of a user");
        existOrCreate(userId);

        watchedDTO.setUserId(userId);
        WatchedMediaUser watchedMediaUser = WatchedDTOHelper.toEntity(watchedDTO, mediaRepository, userRepository);

        watchedMediaUser = watchedMediaUserRepository.save(watchedMediaUser);

        return ResponseEntity.ok().body(WatchedDTOHelper.fromEntity(watchedMediaUser));
    }

    @Override
    public ResponseEntity<WatchedDTO> updateToWatched(String authorization, Integer userId, Integer mediaId, @Valid WatchedDTO watchedDTO) throws Exception {
        if (!context.getAttribute("userId").equals(userId)) throw new ApiError(HttpStatus.UNAUTHORIZED, "Can modifiy media_user of a user");
        existOrCreate(userId);

        if (toWatchMediaUserRepository.existsById(new MediaUserKey(userId, mediaId))) throw new ApiError(HttpStatus.NOT_FOUND, "ToWatch not found, you must create to update it to a watched media");

        watchedDTO.setUserId(userId);
        WatchedMediaUser watchedMediaUser = WatchedDTOHelper.toEntity(watchedDTO, mediaRepository, userRepository);

        watchedMediaUser = watchedMediaUserRepository.save(watchedMediaUser);

        return ResponseEntity.ok().body(WatchedDTOHelper.fromEntity(watchedMediaUser));
    }

    @Override
    public ResponseEntity<Void> deleteToWatch(String authorization, Integer userId, Integer mediaId) throws Exception {
        if (!context.getAttribute("userId").equals(userId)) throw new ApiError(HttpStatus.UNAUTHORIZED, "Can modifiy media_user of a user");
        existOrCreate(userId);

        try {
            toWatchMediaUserRepository.deleteById(new MediaUserKey(userId, mediaId));
        } catch (InvalidDataAccessApiUsageException e) {
            throw new ApiError(HttpStatus.NOT_FOUND, "towatch entity not found");
        }

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deleteWatched(String authorization, Integer userId, Integer mediaId) throws Exception {
        if (!context.getAttribute("userId").equals(userId)) throw new ApiError(HttpStatus.UNAUTHORIZED, "Can modifiy media_user of a user");
        existOrCreate(userId);

        try {
            watchedMediaUserRepository.deleteById(new MediaUserKey(userId, mediaId));
        } catch (InvalidDataAccessApiUsageException e) {
            throw new ApiError(HttpStatus.NOT_FOUND, "watched entity not found");
        }

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<ToWatchDTO>> getToWatch(String authorization, Integer userId, @Min(1) @Valid Integer pageNumber, @Min(1) @Valid Integer pageSize) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(() -> new ApiError(HttpStatus.NOT_FOUND, "User not found"));

        Page<ToWatchMediaUser> toWatchMediaUser = toWatchMediaUserRepository.findAllByUser(PageRequest.of(pageNumber, pageSize), user);

        double count = toWatchMediaUserRepository.countByUser(user);

        return ResponseEntity.ok()
                .header("PageInfo", "NbPages=" + (int)Math.ceil(count / (double)pageSize) + ";Total=" + count)
                .body(ToWatchDTOHelper.fromEntity(toWatchMediaUser.toList()));
    }

    @Override
    public ResponseEntity<List<WatchedDTO>> getWatched(String authorization, Integer userId, @Min(1) @Valid Integer pageNumber, @Min(1) @Valid Integer pageSize) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(() -> new ApiError(HttpStatus.NOT_FOUND, "User not found"));

        Page<WatchedMediaUser> watchedsMediaUsers = watchedMediaUserRepository.findAllByUser(PageRequest.of(pageNumber, pageSize), user);

        double count = watchedMediaUserRepository.countByUser(user);

        return ResponseEntity.ok()
                .header("PageInfo", "NbPages=" + (int)Math.ceil(count / (double)pageSize) + ";Total=" + count)
                .body(WatchedDTOHelper.fromEntity(watchedsMediaUsers.toList()));
    }

    private void existOrCreate(Integer id) {
        if (!userRepository.existsById(id)) {
            User newUser = new User();
            newUser.setId(id);

            userRepository.save(newUser);
        }
    }
}
