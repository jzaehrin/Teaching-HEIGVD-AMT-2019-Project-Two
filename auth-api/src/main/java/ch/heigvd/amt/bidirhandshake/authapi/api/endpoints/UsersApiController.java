package ch.heigvd.amt.bidirhandshake.authapi.api.endpoints;

import ch.heigvd.amt.bidirhandshake.authapi.api.utils.PasswordAuthentication;
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

    PasswordAuthentication passwordAuthentication = new PasswordAuthentication(5);

    public ResponseEntity<Void> login(@Valid UserCredential userCredential) {
        User user = userRepository.findByEmail(userCredential.getEmail());

        if (user == null) return ResponseEntity.status(404).build();

        if (passwordAuthentication.authenticate(user.getPassword().toCharArray(), userCredential.getPassword())) return ResponseEntity.ok().build();

        return ResponseEntity.badRequest().build();
    }
}
