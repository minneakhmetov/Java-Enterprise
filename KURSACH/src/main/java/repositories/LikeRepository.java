package repositories;

import models.Like;


public interface LikeRepository extends CRUD<Like> {
    void like(Long user_to, Long user_from);
}
