package com.shibkov.tasknotebook.app.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Alexey on 21.02.2015.
 */
public class DatabaseManager {

    private static DatabaseManager cInstance;
    private static SQLiteOpenHelper mDatabaseHelper;

    private AtomicInteger mOpenCounter = new AtomicInteger();
    private SQLiteDatabase mDatabase;

    public static synchronized void initializeInstance(SQLiteOpenHelper helper) {
        if (cInstance == null) {
            cInstance = new DatabaseManager();
            mDatabaseHelper = helper;
        }
    }

    public static synchronized DatabaseManager getInstance() {
        if (cInstance == null) {
            throw new IllegalStateException(DatabaseManager.class.getSimpleName() +
                    " is not initialized, call initialize(..) method first.");
        }
        return cInstance;
    }

    public synchronized SQLiteDatabase openWritableDB() {
        if (mOpenCounter.incrementAndGet() == 1) {
            mDatabase = mDatabaseHelper.getWritableDatabase();
        }
        return mDatabase;
    }

    public synchronized void closeDatabase() {
        if (mOpenCounter.decrementAndGet() == 0) {
            mDatabase.close();
        }
    }
}
