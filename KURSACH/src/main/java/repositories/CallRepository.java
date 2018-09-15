package repositories;

import models.Call;
import models.User;

public interface CallRepository extends CRUD<Call> {
    void call(Long user_to, Long user_from);
}
