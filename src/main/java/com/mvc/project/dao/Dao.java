package com.mvc.project.dao;

import java.util.List;

/**
 * @author DacaP
 * @version 1.0
 * @create 25/09/2022 10:28 pm
 */
public interface Dao<E> {

    void create(E entity);

    List<E> findAll();

    E findById(long id);

    void update(E entity);

    void remove(long id);
}