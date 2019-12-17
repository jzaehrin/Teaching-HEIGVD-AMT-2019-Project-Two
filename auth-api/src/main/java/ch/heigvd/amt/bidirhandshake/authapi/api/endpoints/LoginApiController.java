package ch.heigvd.amt.bidirhandshake.authapi.api.endpoints;

import ch.heigvd.amt.bidirhandshake.authapi.api.exceptions.ApiError;
import ch.heigvd.amt.bidirhandshake.authapi.api.utils.JWTHelper;
import ch.heigvd.amt.bidirhandshake.authapi.api.utils.PasswordAuthentication;
import ch.heigvd.amt.bidirhandshake.authapi.dto.UserCredential;
import ch.heigvd.amt.bidirhandshake.authapi.entities.User;
import ch.heigvd.amt.bidirhandshake.authapi.repositories.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import java.util.Date;

@Controller
public class LoginApiController implements LoginApi {

    @Autowired
    private UserRepository userRepository;

    private PasswordAuthentication passwordAuthentication = new PasswordAuthentication(5);

    @Override
    public ResponseEntity<Void> login(@Valid UserCredential userCredential) throws Exception {
        User user = userRepository.findByEmail(userCredential.getEmail());

        if (user != null && passwordAuthentication.authenticate(userCredential.getPassword().toCharArray(), user.getPassword())) {

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Authorization", JWTHelper.createFor(user));

            return ResponseEntity.ok().headers(httpHeaders).build();
        }

        throw new ApiError(HttpStatus.FORBIDDEN,"Invalid email or password");
    }
}
