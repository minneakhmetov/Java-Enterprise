package Repositories;

import models.User;

public interface MessageRepository {
    void write(User user, String text);
}
