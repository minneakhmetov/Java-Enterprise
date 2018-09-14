package repositories;
import models.User;

import java.util.List;

public interface UserRepository extends CRUD<User> {
    List<User> findAllByFirstName(String firstName);
    List<User> findAll();
}
