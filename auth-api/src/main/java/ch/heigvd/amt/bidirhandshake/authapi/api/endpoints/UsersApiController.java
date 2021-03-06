package ch.heigvd.amt.bidirhandshake.authapi.api.endpoints;

import ch.heigvd.amt.bidirhandshake.authapi.api.exceptions.ApiError;
import ch.heigvd.amt.bidirhandshake.authapi.api.utils.PasswordAuthentication;
import ch.heigvd.amt.bidirhandshake.authapi.dto.PasswordChanger;
import ch.heigvd.amt.bidirhandshake.authapi.entities.User;
import ch.heigvd.amt.bidirhandshake.authapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class UsersApiController implements UsersApi {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HttpServletRequest context;

    private PasswordAuthentication passwordAuthentication = new PasswordAuthentication(5);

    @Override
    public ResponseEntity<Void> changePassword(String authorization, Integer userId, @Valid PasswordChanger passwordChanger) throws Exception {
        if (context.getAttribute("userId") != userId) throw new ApiError(HttpStatus.UNAUTHORIZED, "Unauthorized : Cannot change password of another user !");

        if (!passwordChanger.getPassword().equals(passwordChanger.getConfirmPassword())) throw new ApiError(HttpStatus.BAD_REQUEST, "Malformed request body : The confirm password is different");

        User user = userRepository.findById(userId);

        if (user == null || ! passwordAuthentication.authenticate(passwordChanger.getCurrentPassword().toCharArray(), user.getPassword()))
            throw new ApiError(HttpStatus.UNAUTHORIZED, "Unauthorized : Current password is different");

        user.setPassword(passwordAuthentication.hash(passwordChanger.getPassword().toCharArray()));

        userRepository.save(user);

        return ResponseEntity.ok().build();
    }
}
