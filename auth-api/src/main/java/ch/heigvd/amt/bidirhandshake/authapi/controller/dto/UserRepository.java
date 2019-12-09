package ch.heigvd.amt.bidirhandshake.authapi.controller.dto;
import java.util.List;

import ch.heigvd.amt.bidirhandshake.authapi.model.entities.Users;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Users, Long> {

    List<Users> findByUsername(String username);

    Users findById(long id);

    List<Users> findByRole(Users.Role role);
}