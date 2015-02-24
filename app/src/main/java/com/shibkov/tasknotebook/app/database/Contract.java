package com.shibkov.tasknotebook.app.database;

import android.provider.BaseColumns;

/**
 * Created by alexxxshib
 */
public class Contract {

    public static class CTaskNote implements BaseColumns {

        public static final String TABLE_NAME  = "task_notes_table";

        public static final String DATE        = "date";
        public static final String CATEGORY    = "category";
        public static final String IS_DONE     = "is_done";
        public static final String HEADER      = "header";
        public static final String BODY        = "body";
    }

    public static class CCategory implements BaseColumns {

        public static final String TABLE_NAME  = "categories_table";

        public static final String VALUE       = "value";
        public static final String DESCRIPTION = "description";
        public static final String TIMESTAMP   = "timestamp";
    }
}
