package ch.heigvd.amt.bidirhandshake.authapi.api.endpoints;

import ch.heigvd.amt.bidirhandshake.authapi.api.exceptions.ApiError;
import ch.heigvd.amt.bidirhandshake.authapi.api.utils.UserDTOHelper;
import ch.heigvd.amt.bidirhandshake.authapi.dto.UserDTO;
import ch.heigvd.amt.bidirhandshake.authapi.entities.User;
import ch.heigvd.amt.bidirhandshake.authapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

@Controller
public class AdminAPIController implements AdminApi {
    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<UserDTO> createUser(String authorization, @Valid UserDTO userDTO) throws Exception {
        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) throw new ApiError(HttpStatus.BAD_REQUEST, "Malformed request body : The confirm password is different");

        if (userRepository.existsByEmail(userDTO.getEmail())) throw new ApiError(HttpStatus.FORBIDDEN, "Bad request : Email already used");

        User user = userRepository.save(UserDTOHelper.toEntity(userDTO));

        return ResponseEntity.ok().body(UserDTOHelper.fromEntity(user));
    }
}
