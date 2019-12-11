package ch.heigvd.amt.bidirhandshake.authapi.api.endpoints;

import ch.heigvd.amt.bidirhandshake.authapi.api.utils.PasswordAuthentication;
import ch.heigvd.amt.bidirhandshake.authapi.dto.UserCredential;
import ch.heigvd.amt.bidirhandshake.authapi.entities.User;
import ch.heigvd.amt.bidirhandshake.authapi.repositories.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import java.util.Date;

@Controller
public class UsersApiController implements UsersApi {

    @Autowired
    UserRepository userRepository;

    PasswordAuthentication passwordAuthentication = new PasswordAuthentication(5);

    final static String secret = "J9Z9crFXwy5tGJWfKvqGtP7nm71p4CHlT5aCkQLNqI2cHNncdmu36S3QeSPi1IJB";

    public ResponseEntity<Void> login(@Valid UserCredential userCredential) {
        User user = userRepository.findByEmail(userCredential.getEmail());

        if (user == null) return ResponseEntity.status(404).build();

        if (passwordAuthentication.authenticate(userCredential.getPassword().toCharArray(), user.getPassword())) {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("auth_api")
                    .withExpiresAt(new Date(new Date().getTime() + (1000 * 60 * 60 * 24)))
                    .withNotBefore(new Date())
                    .withClaim("user_id", user.getId())
                    .sign(algorithm);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Authorization", token);

            return ResponseEntity.ok().headers(httpHeaders).build();
        }

        return ResponseEntity.badRequest().build();
    }
}
