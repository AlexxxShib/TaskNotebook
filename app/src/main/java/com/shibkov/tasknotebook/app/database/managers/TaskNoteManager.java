package com.shibkov.tasknotebook.app.database.managers;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedUpdate;
import com.j256.ormlite.stmt.QueryBuilder;
import com.shibkov.tasknotebook.app.database.Contract;
import com.shibkov.tasknotebook.app.database.DatabaseHelper;
import com.shibkov.tasknotebook.app.models.Category;
import com.shibkov.tasknotebook.app.models.TaskNote;
import com.shibkov.tasknotebook.app.utils.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by alexxxshib
 */
public class TaskNoteManager implements IDataManager<TaskNote> {

    private final Dao<TaskNote, Long> mTaskNoteDao;

    public TaskNoteManager(DatabaseHelper helper) {
        mTaskNoteDao = helper.getTaskNoteDao();
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
            Logger.error(e.getMessage());
        }
        return new ArrayList<TaskNote>();
    }

    public void batchUpdate(final List<TaskNote> taskNotes) {
        try {
            mTaskNoteDao.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (TaskNote taskNote : taskNotes) {
                        mTaskNoteDao.update(taskNote);
                    }
                    return null;
                }
            });
        } catch (Exception e) {
            Logger.error(e.getMessage());
        }
    }

    public List<TaskNote> getByCategory(Category category) {
        Calendar endTimeCalendar = Calendar.getInstance();
        endTimeCalendar.setTimeInMillis(System.currentTimeMillis() + category.getInterval());

        Date startTime = new Date();
        Date endTime = endTimeCalendar.getTime();

        QueryBuilder<TaskNote, Long> taskBuilder = mTaskNoteDao.queryBuilder();
        try {
            if (category.getId().equals(CategoryManager.CATEGORY_ARCH)) {
                taskBuilder.where().le(Contract.CTaskNote.DATE, startTime);
            } else if (category.getId().equals(CategoryManager.CATEGORY_DONE)) {
                taskBuilder.where().eq(Contract.CTaskNote.IS_DONE, true);
            } else {
                taskBuilder.where().between(Contract.CTaskNote.DATE, startTime, endTime);
            }
            return mTaskNoteDao.query(taskBuilder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<TaskNote>();
    }

    public void initTestNotes(Context context) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 12);
        add(new TaskNote(-1, calendar.getTime(), false, "Подстричься", "Пора привести себя в порядок"));
        calendar.add(Calendar.DAY_OF_YEAR, 3);
        add(new TaskNote(-1, calendar.getTime(), false, "Убраться", "Убраться в комнате, а то заебало"));
        calendar.add(Calendar.DAY_OF_YEAR, 10);
        add(new TaskNote(-1, calendar.getTime(), false, "Дочитать статью", "Ну там статья на хабре крутая, я помню какая"));
    }
}
