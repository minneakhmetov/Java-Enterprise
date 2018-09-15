package repositories;

import models.Message;
import models.User;

public interface MessageRepository extends CRUD<Message> {
    void write(Long user_to, Long user_from, String text);
}
