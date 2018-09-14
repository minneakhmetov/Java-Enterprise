package repositories;

import models.User;

public interface MessageRepository {
    void write(User user, String text);

}
