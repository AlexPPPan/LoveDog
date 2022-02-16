package com.homework.lovedog.utils.dbutils;


import android.database.Cursor;
import android.text.TextUtils;


import com.homework.lovedog.utils.FileUtil;
import com.homework.lovedog.utils.FileUtils;

import net.sqlcipher.database.SQLiteDatabase;

import java.io.File;


public class DBUtils {
    public static boolean copyDataBase(DbManager src, DbManager dest) {
        boolean result = false;
        try {
            SQLiteDatabase dbRes = src.getDatabase();
            SQLiteDatabase dbDest = dest.getDatabase();
            Cursor cursor = dbRes.rawQuery("SELECT name FROM sqlite_master where type='table' order by name", null);
            dbDest.execSQL(String.format("attach '%s' as sourceLib key ''", dbRes.getPath()));
            while (cursor != null && cursor.moveToNext()) {
                String table = cursor.getString(0);
                if (table.equals("android_metadata")) {
                    //跳过android_metadata
                    continue;
                }

                dbDest.execSQL(String.format("CREATE TABLE IF NOT EXISTS '%s' AS SELECT * FROM sourceLib.'%s'", table
                    , table));
            }
            if (cursor != null) {
                cursor.close();
            }
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean copyDataBase(String res, String pwdRes, String dest, String pwdDest) {
        boolean result = false;
        try {
            FileUtil.deleteFile(dest);
            DbManager dmRes = getManager(res, pwdRes);
            DbManager dmDest = getManager(dest, pwdDest);
            result = copyDataBase(dmRes, dmDest);
            if (dmRes != null) {
                dmRes.close();
            }
            if (dmDest != null) {
                dmDest.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static DbManager getManager(String path, String passWord) {
        if (!TextUtils.isEmpty(path) && path.lastIndexOf("/") > -1) {
            String dir = path.substring(0, path.lastIndexOf("/"));
            String name = path.substring(path.lastIndexOf("/") + 1, path.length());
            File dirFile = new File(dir);
            try {
                DbManager.DaoConfig config = new DbManager
                    .DaoConfig()
                    .setDbName(name)
                    .setDbVersion(1)
                    .setPassword(passWord)
                    .setDbDir(dirFile);
                return getManager(config);
            } catch (Exception e) {
                e.printStackTrace();
                //处理APP对数据库加密与非加密切换造成数据库不可用的问题
                if (e.getMessage().startsWith("file is not a database")) {
                    FileUtil.deleteFileSafely(new File(path));
                }
            }
            return null;
        } else {
            return null;
        }
    }

    public static DbManager getManager(DbManager.DaoConfig config) {
        return x.getDb(config);
    }

    public static String findDB(String dirPath) {
        return FileUtils.findFileFrist(dirPath, ".*\\.db");
    }

    public boolean moveDB(String res, String dest) {
        try {
            FileUtils.move(res, dest);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isTableExist(SQLiteDatabase database, String table) {
        boolean result = false;
        String sql = String.format("select * from sqlite_master where type = 'table' and name = '%s';", table);
        Cursor cursor = database.rawQuery(sql, null);
        if (cursor != null && cursor.moveToNext()) {
            result = true;
        } else {
            result = false;
        }
        if (cursor != null) {
            cursor.close();
        }
        return result;
    }
}
