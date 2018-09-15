package repositories;

import java.util.Optional;

public interface CRUD<T> {
    Optional<T> read(Long id);
    void create(T model);
    void delete(Long id);
    void update(Long id, T model);

}
