package com.shibkov.tasknotebook.app.utils;

import android.util.Log;

/**
 * Created by alexxxshib
 */
public class Logger {

    private static final String LOGGER_TAG = "TaskNotebook_logger";

    public static void error(String msg) {
        Log.e(LOGGER_TAG, msg);
    }

    public static void info(String msg) {
        Log.i(LOGGER_TAG, msg);
    }
}
