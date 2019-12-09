package ch.heigvd.amt.bidirhandshake.authapi.controller.dto;
import java.util.List;

import ch.heigvd.amt.bidirhandshake.authapi.model.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByUsername(String username);

    User findById(long id);

    List<User> findByRole(User.Role role);
}