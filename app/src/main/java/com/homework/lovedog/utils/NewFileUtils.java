package com.homework.lovedog.utils;


import android.content.Context;
import android.os.Environment;

import java.io.File;

import androidx.core.content.ContextCompat;

public class NewFileUtils {
    private static String DIRECTORY_DATA = "Data";

    public static File getInternalStorageDir(Context context, String dirName) {
        File file = new File(context.getFilesDir(), dirName);

        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static File getExternalStorageDir(Context context, String type, String dirName) {

        File file = new File(context.getExternalFilesDir(type), dirName);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static boolean isExternalStorageWritable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    public static boolean isExternalStorageReadable() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)
                || Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            return true;
        }
        return false;
    }

    public static File getPrimaryExternalStorage(Context applicationContext) {
        File[] externalFilesDirs = ContextCompat.getExternalFilesDirs(applicationContext, null);
        return externalFilesDirs[0];
    }



} 