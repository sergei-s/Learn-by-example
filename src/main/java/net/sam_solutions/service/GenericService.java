package net.sam_solutions.service;

import java.util.List;

/**
 * User: Администратор
 * Date: 05.05.12
 */
public interface GenericService<T> {
    List<T> getAll();
    void create(T t);
    void delete(T t);
    void update(T t);
}
