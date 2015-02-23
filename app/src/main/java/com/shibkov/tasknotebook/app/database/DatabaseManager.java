package com.shibkov.tasknotebook.app.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by alexxxshib
 */
public class DatabaseManager {

    private static DatabaseManager cInstance;
    private static DatabaseHelper mDatabaseHelper;

    private DatabaseManager() {
    }

    public static synchronized void initializeInstance(Context context) {
        if (cInstance == null) {
            cInstance = new DatabaseManager();
            mDatabaseHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
        }
    }

    public static synchronized DatabaseManager getInstance() {
        if (cInstance == null) {
            throw new IllegalStateException(DatabaseManager.class.getSimpleName() +
                    " is not initialized, call initializeInstance(..) method first.");
        }
        return cInstance;
    }

    public synchronized DatabaseHelper getHelper() {
        return mDatabaseHelper;
    }

    public synchronized void closeDatabase() {
        OpenHelperManager.releaseHelper();
        mDatabaseHelper = null;
    }
}
