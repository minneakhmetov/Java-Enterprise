package repositories;

import models.User;

public interface LikeRepository {
    void like(User user);
    void deleteLike(User user);
}
