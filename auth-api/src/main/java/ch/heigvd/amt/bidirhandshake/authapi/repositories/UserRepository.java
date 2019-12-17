package ch.heigvd.amt.bidirhandshake.authapi.repositories;
import java.util.List;

import ch.heigvd.amt.bidirhandshake.authapi.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

    User findByEmail(String email);

    User findById(Integer id);

    List<User> findByRole(User.Role role);

    User save(User user);
}