package ch.heigvd.amt.bidirhandshake.authapi.api.endpoints;

import ch.heigvd.amt.bidirhandshake.authapi.dto.UserCredential;
import ch.heigvd.amt.bidirhandshake.authapi.entities.User;
import ch.heigvd.amt.bidirhandshake.authapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

@Controller
public class UsersApiController implements UsersApi {
    @Autowired
    UserRepository userRepository;

    public ResponseEntity<Void> login(@Valid UserCredential userCredential) {
        User user = userRepository.findByEmail(userCredential.getEmail());

        if (user == null) return ResponseEntity.status(404).build();

        if (user.getPassword() != userCredential.getPassword()) return ResponseEntity.ok().build();

        return ResponseEntity.badRequest().build();
    }
}
