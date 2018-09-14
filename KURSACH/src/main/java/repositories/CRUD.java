package repositories;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CRUD<T> {
    Optional<T> read(Long id);
    void create(T model);
    void delete(Long id);
    void update(Long id, T model);

}
