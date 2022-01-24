package com.homework.lovedog.activity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/*
 * databaseOperationClass
 * */
public class MyDbService {

    private final MyDbHelper myDbHelper;

    public MyDbService(Context context) {
        myDbHelper = new MyDbHelper(context);
    }

    public void insertPerson(ContentValues value) {
        SQLiteDatabase writableDatabase = getDb();
        writableDatabase.insertOrThrow("User", null, value);
    }

    @SuppressLint("Range")
    public String login(String name) {
        SQLiteDatabase writableDatabase = getDb();

        String res = null;
        Cursor cursor = writableDatabase.rawQuery("select * from User where username = ? ", new String[]{name}, null);
        if (cursor.moveToFirst()) {
            res = cursor.getString(cursor.getColumnIndex("password"));
        }
        cursor.close();
        return res;
    }

    public SQLiteDatabase getDb() {
        return myDbHelper.getWritableDatabase();
    }

}
