package Repositories;

import models.User;

public interface LikeRepository {
    void like(User user);
    void deleteLike(User user);
}
