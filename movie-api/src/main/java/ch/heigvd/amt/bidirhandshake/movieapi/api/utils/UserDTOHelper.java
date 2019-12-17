package ch.heigvd.amt.bidirhandshake.movieapi.api.utils;

import ch.heigvd.amt.bidirhandshake.authapi.dto.UserDTO;
import ch.heigvd.amt.bidirhandshake.authapi.entities.User;

import java.sql.Timestamp;
import java.time.Instant;

public class UserDTOHelper {

    private static final PasswordAuthentication passwordAuthentication = new PasswordAuthentication(5);


    public static UserDTO fromEntity(User user) {
        return  new UserDTO().email(user.getEmail()).username(user.getUsername()).role(user.getRole().name());
    }

    public static User toEntity(UserDTO userDTO) {
        User user = new User();

        user.setId(null);
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordAuthentication.hash(userDTO.getPassword().toCharArray()));
        user.setRole(ch.heigvd.amt.bidirhandshake.authapi.entities.User.Role.valueOf(userDTO.getRole()));
        user.setMemberSince(Timestamp.from(Instant.now()));

        return user;
    }
}
