package com.shibkov.tasknotebook.app.managers;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by alexxxshib
 */
public interface IDataManager<T> {

    public void add(T object);

    public void remove(T object);

    public T findById(long id);

    public List<T> getAll();
}
