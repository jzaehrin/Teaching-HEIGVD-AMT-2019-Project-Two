package ch.heigvd.amt.bidirhandshake.movieapi.api.endpoints;

import ch.heigvd.amt.bidirhandshake.movieapi.UsersApi;
import ch.heigvd.amt.bidirhandshake.movieapi.api.exceptions.ApiError;
import ch.heigvd.amt.bidirhandshake.movieapi.api.utils.MediaDTOHelper;
import ch.heigvd.amt.bidirhandshake.movieapi.api.utils.ToWatchDTOHelper;
import ch.heigvd.amt.bidirhandshake.movieapi.dto.ToWatchDTO;
import ch.heigvd.amt.bidirhandshake.movieapi.dto.WatchedDTO;
import ch.heigvd.amt.bidirhandshake.movieapi.entities.Media;
import ch.heigvd.amt.bidirhandshake.movieapi.entities.ToWatchMediaUser;
import ch.heigvd.amt.bidirhandshake.movieapi.entities.User;
import ch.heigvd.amt.bidirhandshake.movieapi.repositories.ToWatchMediaUserRepository;
import ch.heigvd.amt.bidirhandshake.movieapi.repositories.UserRepository;
import ch.heigvd.amt.bidirhandshake.movieapi.repositories.WatchedMediaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

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


    @Override
    public ResponseEntity<ToWatchDTO> addToWatch(String authorization, Integer userId, @Valid ToWatchDTO toWatchDTO) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<WatchedDTO> addWatched(String authorization, Integer userId, @Valid WatchedDTO watchedDTO) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteToWatch(String authorization, Integer userId, Integer towatchId, @Min(1) @Valid Integer pageNumber, @Min(1) @Valid Integer pageSize) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteWatched(String authorization, Integer userId, Integer watchedId) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<List<ToWatchDTO>> getToWatch(String authorization, Integer userId, @Min(1) @Valid Integer pageNumber, @Min(1) @Valid Integer pageSize) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(() -> new ApiError(HttpStatus.NOT_FOUND, "User not found"));

        Page<ToWatchMediaUser> toWatchMediaUser = toWatchMediaUserRepository.findAllByUser(PageRequest.of(pageNumber, pageSize), user);

        return ResponseEntity.ok().body(ToWatchDTOHelper.fromEntity(toWatchMediaUser.toList()));
    }

    @Override
    public ResponseEntity<List<WatchedDTO>> getWatched(String authorization, Integer userId, @Min(1) @Valid Integer pageNumber, @Min(1) @Valid Integer pageSize) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(() -> new ApiError(HttpStatus.NOT_FOUND, "User not found"));

        return null;
    }
}
