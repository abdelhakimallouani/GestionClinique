package ma.youcode.clinique.dao;

public interface DAO<T> {
    void create(T obj);
    T read(int id);
    void update(T obj);
    void delete(int id);
}
