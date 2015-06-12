package com.shibkov.tasknotebook.app.managers;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.shibkov.tasknotebook.app.R;
import com.shibkov.tasknotebook.app.database.Contract;
import com.shibkov.tasknotebook.app.database.DatabaseHelper;
import com.shibkov.tasknotebook.app.models.Category;
import com.shibkov.tasknotebook.app.models.TaskNote;
import com.shibkov.tasknotebook.app.utils.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by alexxxshib
 */
public class TaskNoteManager implements IDataManager<TaskNote> {

    private final Dao<TaskNote, Long> mTaskNoteDao;
    private final Dao<Category, Long> mCategoryDao;

    private final CategoryManager mCategoryManager;

    public TaskNoteManager(DatabaseHelper helper) {
        mTaskNoteDao = helper.getTaskNoteDao();
        mCategoryDao = helper.getCategoryDao();

        mCategoryManager = new CategoryManager(helper);
    }

    @Override
    public void add(TaskNote taskNote) {
        try {
            mTaskNoteDao.createOrUpdate(taskNote);
        } catch (SQLException e) {
            Logger.error(e.getMessage());
        }
    }

    @Override
    public void remove(TaskNote taskNote) {
        try {
            mTaskNoteDao.delete(taskNote);
        } catch (SQLException e) {
            Logger.error(e.getMessage());
        }
    }

    @Override
    public TaskNote findById(long id) {
        try {
            return mTaskNoteDao.queryForId(id);
        } catch (SQLException e) {
            Logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public List<TaskNote> getAll() {
        try {
            return mTaskNoteDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<TaskNote>();
    }

    public List<TaskNote> getByCategory(Category category) {
        QueryBuilder<TaskNote, Long> taskBuilder = mTaskNoteDao.queryBuilder();
        QueryBuilder<Category, Long> categoryBuilder = mCategoryDao.queryBuilder();
        try {
            categoryBuilder.where().eq(Contract.CCategory._ID, category.getId());
            taskBuilder.join(categoryBuilder);
            return mTaskNoteDao.query(taskBuilder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<TaskNote>();
    }

    public void initTestNotes(Context context) {
        add(new TaskNote(-1, new Date(), false, "Подстричься", "Пора привести себя в порядок"));
        add(new TaskNote(-1, new Date(), false, "Убраться", "Убраться в комнате, а то заебало"));
        add(new TaskNote(-1, new Date(), false, "Дочитать статью", "Ну там статья на хабре крутая, я помню какая"));
    }
}
