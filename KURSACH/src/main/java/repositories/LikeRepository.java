package repositories;

import models.User;

public interface LikeRepository {
    void like(Long user_to, Long user_from);

}
