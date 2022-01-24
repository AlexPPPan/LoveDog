package com.homework.lovedog.activity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/*
 * databaseHelperClass
 * */
public class MyDbHelper extends SQLiteOpenHelper {


    private static final String name = "data";
    private static final String CREATE_PERSON = "create table User(_id integer primary key autoincrement,username text unique,password text,age integer)";

    public MyDbHelper(@Nullable Context context) {
        super(context, name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_PERSON);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
