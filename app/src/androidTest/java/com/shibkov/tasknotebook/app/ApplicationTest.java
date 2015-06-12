package com.shibkov.tasknotebook.app;

import android.app.Application;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Debug;
import android.test.ActivityInstrumentationTestCase2;
import android.test.AndroidTestCase;
import android.test.ApplicationTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.util.Log;

import com.shibkov.tasknotebook.app.activities.NotebookMenuActivity;
import com.shibkov.tasknotebook.app.database.DatabaseHelper;
import com.shibkov.tasknotebook.app.database.DatabaseManager;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ActivityInstrumentationTestCase2<NotebookMenuActivity> {

    public ApplicationTest() {
        super(NotebookMenuActivity.class);
    }

    @MediumTest
    public void databaseTest0() {
        /*DatabaseManager.initializeInstance(new DatabaseHelper(getContext()));
        SQLiteDatabase database = DatabaseManager.getInstance().openWritableDB();

        ContentValues values = new ContentValues();
        values.put("_id", 0);
        values.put("value", "some text");

        database.insert("meta", null, values);

        DatabaseManager.getInstance().closeDatabase();*/
        Log.d("TEST","TEST");
        assertEquals(true, false);
    }

}