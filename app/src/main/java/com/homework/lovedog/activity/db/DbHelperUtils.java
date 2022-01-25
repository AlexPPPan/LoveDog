package com.homework.lovedog.activity.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.homework.lovedog.bean.User;

import java.util.ArrayList;
import java.util.List;


public class DbHelperUtils extends SQLiteOpenHelper {

    static private final String DB_NAME="user.db";

    public DbHelperUtils(Context context){
        super(context,DB_NAME,null,1);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        createUserTb(sqLiteDatabase);

    }


    private void createUserTb(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("CREATE TABLE userInfotb(userid integer primary key autoincrement,username text not null,password text not null)");
    }




    public User userQueryUsername(String username) {
        Cursor cursor = this.getReadableDatabase().rawQuery("select * from userInfotb  where username =?", new String[]{username});
        List<User> list = new ArrayList<>();
        if(cursor.getCount()>0) {
            while (cursor.moveToNext()){
                if (cursor.getString(cursor.getColumnIndex("username")).equals(username)){
                    User user = new User();
                    user.setUserid(cursor.getInt(cursor.getColumnIndex("userid")));
                    user.setUsername(cursor.getString(cursor.getColumnIndex("username")));
                    user.setPassword(cursor.getString(cursor.getColumnIndex("password")));
                    list.add(user);
                }
            }
        }
        return list.size() == 0?null:list.get(0);
    }

    public void insertUser(String username,String password) {
        this.getWritableDatabase().execSQL("insert into userInfotb(username,password) values (?,?)",
                new Object[]{username,password});
    }

}
