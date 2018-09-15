package repositories;
import models.Post;
//import models.User;

public interface PostRepository extends CRUD<Post>{
    void post(String text, Long id_user);
}
