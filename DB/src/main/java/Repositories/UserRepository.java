package Repositories;
import models.User;

import java.util.List;

public interface UserRepository {
    User findOne(Long id);
    List<User> findAll();
    void create(User user);
    void delete(User user);

}
