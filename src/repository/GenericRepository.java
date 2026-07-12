package repository;

import model.BaseModel;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<T extends BaseModel<ID> ,ID > {
    void save(T t);

    Optional<T> findById(ID id);

    List<T> findAll();

    void update(T t);

    void delete(ID id);
}
