package respositories;

import java.util.List;

public interface BaseCrudServices <T, ID> {
    List<T> findAll();
    T findById(ID id);
    T save(T t);
    void delete(ID id);
    void update(ID id, T t);
}
