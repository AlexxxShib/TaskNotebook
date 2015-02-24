package com.shibkov.tasknotebook.app.managers;

import com.j256.ormlite.dao.Dao;
import com.shibkov.tasknotebook.app.database.DatabaseHelper;
import com.shibkov.tasknotebook.app.models.Category;
import com.shibkov.tasknotebook.app.utils.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexxxshib
 */
public class CategoryManager implements IDataManager<Category> {

    private final Dao<Category, Long> mCategoryeDao;

    public CategoryManager(DatabaseHelper helper) {
        mCategoryeDao = helper.getCategoryDao();
        if (mCategoryeDao == null) {
            throw new IllegalStateException("Dao Category not initialized!");
        }
    }

    @Override
    public void add(Category object) {
        try {
            mCategoryeDao.createOrUpdate(object);
        } catch (SQLException e) {
            Logger.error(e.getMessage());
        }
    }

    @Override
    public void remove(Category object) {
        try {
            mCategoryeDao.delete(object);
        } catch (SQLException e) {
            Logger.error(e.getMessage());
        }
    }

    @Override
    public Category findById(long id) {
        try {
            mCategoryeDao.queryForId(id);
        } catch (SQLException e) {
            Logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Category> getAll() {
        try {
            mCategoryeDao.queryForAll();
        } catch (SQLException e) {
            Logger.error(e.getMessage());
        }
        return new ArrayList<Category>();
    }
}
