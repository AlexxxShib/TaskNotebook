package com.shibkov.tasknotebook.app.database;

import android.provider.BaseColumns;

/**
 * Created by alexxxshib
 */
public class Contract {

    public static class CTaskNote implements BaseColumns {

        public static final String TABLE_NAME  = "task_notes_table";

        public static final String CATEGORY    = "category";
        public static final String IS_DONE     = "is_done";

        public static final String JSON_OBJECT = "json_object";

        public static final String SQL_CREATE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                CATEGORY + " INTEGER," +
                IS_DONE  + " INTEGER," +
                JSON_OBJECT + " TEXT )";

        public static final String SQL_DROP =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
