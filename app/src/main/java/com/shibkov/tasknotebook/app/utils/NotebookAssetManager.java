package com.shibkov.tasknotebook.app.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Alexey on 28.05.2015.
 */
public class NotebookAssetManager {

    public static final String ICONS_PATH = "icons";

    public static List<String> getIconsList(Context context) {
        AssetManager assetManager = context.getAssets();
        try {
            String[] fileList = assetManager.list(ICONS_PATH);
            return Arrays.asList(fileList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static Drawable getDrawableBy(Context context, String name) {
        try {
            InputStream ims = context.getAssets().open(ICONS_PATH + "/" + name);
            return Drawable.createFromStream(ims, null);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
