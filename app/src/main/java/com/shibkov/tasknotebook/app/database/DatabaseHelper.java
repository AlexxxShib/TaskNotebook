package com.shibkov.tasknotebook.app.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.shibkov.tasknotebook.app.models.Category;
import com.shibkov.tasknotebook.app.models.TaskNote;
import com.shibkov.tasknotebook.app.utils.Logger;

import java.sql.SQLException;

/**
 * Created by alexxshib
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "TaskNotebookDatabaseORM.db";

    private static class DaoHolder {

        Dao<TaskNote, Long> taskNoteDao;
        Dao<Category, Long> categoryDao;

        public void release() {
            taskNoteDao = null;
            categoryDao = null;
        }
    }

    private final DaoHolder mDaoHolder = new DaoHolder();

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, TaskNote.class);
            TableUtils.createTableIfNotExists(connectionSource, Category.class);
        } catch (SQLException e) {
            Logger.error(e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, TaskNote.class, true);
            TableUtils.dropTable(connectionSource, Category.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            Logger.error(e.getMessage());
        }
    }

    public Dao<TaskNote, Long> getTaskNoteDao() {
        if (mDaoHolder.taskNoteDao == null) {
            try {
                mDaoHolder.taskNoteDao = getDao(TaskNote.class);
            } catch (SQLException e) {
                Logger.error(e.getMessage());
            }
        }
        return mDaoHolder.taskNoteDao;
    }

    public Dao<Category, Long> getCategoryDao() {
        if (mDaoHolder.categoryDao == null) {
            try {
                mDaoHolder.categoryDao = getDao(Category.class);
            } catch (SQLException e) {
                Logger.error(e.getMessage());
            }
        }
        return mDaoHolder.categoryDao;
    }

    @Override
    public void close() {
        super.close();
        mDaoHolder.release();
    }
}
