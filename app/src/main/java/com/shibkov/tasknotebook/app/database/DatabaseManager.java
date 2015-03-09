package com.shibkov.tasknotebook.app.database;

import android.content.Context;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.shibkov.tasknotebook.app.utils.Logger;

/**
 * Created by alexxxshib
 */
public class DatabaseManager {

    private static DatabaseHelper mDatabaseHelper;

    private DatabaseManager() {
    }

    public static synchronized DatabaseHelper getHelper(Context context) {
        if (mDatabaseHelper == null) {
            Logger.info("Database init!");
            mDatabaseHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
        }
        return mDatabaseHelper;
    }

    public static synchronized void closeDatabase() {
        Logger.info("Database closed!");
        OpenHelperManager.releaseHelper();
        mDatabaseHelper = null;
    }
}
