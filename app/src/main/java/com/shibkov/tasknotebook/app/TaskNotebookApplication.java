package com.shibkov.tasknotebook.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by Alexey on 28.05.2015.
 */
public class TaskNotebookApplication extends Application {

    private static TaskNotebookApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static Context getAppContext() {
        return instance;
    }
}
