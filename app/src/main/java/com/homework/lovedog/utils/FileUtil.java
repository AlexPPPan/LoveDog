package com.homework.lovedog.utils;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.os.Environment;
import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

public class FileUtil {
    private static final String TAG = "==FileUtil";

    public static String sdcardPath = Environment.getExternalStorageDirectory().getPath();


    public static boolean saveImageInSdcard(String dir, String filename, Bitmap bitmap) {
        if (null == bitmap) {
            return false;
        }
        if (!haveSdcard()) {
            return false;
        }
        dir = sdcardPath + "/" + dir;

        File homedir = new File(dir);
        if (!homedir.exists()) {
            if (!homedir.mkdirs()) {
                return false;
            }
        }

        File bitmapFile = new File(dir, filename);
        FileOutputStream bitmapWtriter = null;

        return savePic(dir, filename, bitmap);
    }

    public static boolean saveScreenShot(String dir, String filename, Bitmap bitmap) {
        if (null == bitmap) {
            return false;
        }

        File homedir = new File(dir);
        if (!homedir.exists()) {
            if (!homedir.mkdirs()) {
                return false;
            }
        }
        return savePic(dir, filename, bitmap);
    }

    public static boolean saveScreenShot(String dir, String filename, Bitmap bitmap,
                                         Bitmap.CompressFormat imageType) {
        if (null == bitmap) {
            return false;
        }

        File homedir = new File(dir);
        if (!homedir.exists()) {
            if (!homedir.mkdirs()) {
                return false;
            }
        }
        return savePic(dir, filename, bitmap, imageType);
    }

    private static boolean savePic(String dir, String filename, Bitmap bitmap,
                                   Bitmap.CompressFormat imageType) {
        File bitmapFile = new File(dir, filename);
        FileOutputStream bitmapWtriter = null;
        boolean flag = false;

        try {
            bitmapWtriter = new FileOutputStream(bitmapFile);
            if (bitmap.compress(imageType, 100, bitmapWtriter)) {
                bitmapWtriter.flush();
                bitmapWtriter.close();

                flag = true;

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return flag;
    }

    private static boolean savePic(String dir, String filename, Bitmap bitmap) {
        File bitmapFile = new File(dir, filename);
        FileOutputStream bitmapWtriter = null;
        boolean flag = false;

        try {
            bitmapWtriter = new FileOutputStream(bitmapFile);
            if (bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bitmapWtriter)) {
                bitmapWtriter.flush();
                bitmapWtriter.close();

                flag = true;

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return flag;
    }


    public static boolean deleteFile(String path) {

        File file = new File(path);

        boolean flag = false;
        if (file.exists()) {
            flag = file.delete();
            if (flag) {
                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }
    }


    public static boolean haveSdcard() {
        String status = Environment.getExternalStorageState();
        if (status.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }


    public static String formatFileSize(long length) {
        String result = null;
        int sub_string = 0;
        if (length >= 1073741824) {
            sub_string = String.valueOf((float) length / 1073741824).indexOf(".");
            result = ((float) length / 1073741824 + "000").substring(0, sub_string + 3) + "GB";
        } else if (length >= 1048576) {
            sub_string = String.valueOf((float) length / 1048576).indexOf(".");
            result = ((float) length / 1048576 + "000").substring(0, sub_string + 3) + "MB";
        } else if (length >= 1024) {
            sub_string = String.valueOf((float) length / 1024).indexOf(".");
            result = ((float) length / 1024 + "000").substring(0, sub_string + 3) + "KB";
        } else if (length < 1024) result = Long.toString(length) + "B";
        return result;
    }

    public static String formatFileSize(double size) {
        if (size < 0) {
            return "0K";
        }
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return size + "B";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "K";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "M";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "G";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "T";
    }

    public static long getFileSize(File f) throws Exception {
        long size = 0;
        File flist[] = f.listFiles();
        for (int i = 0; i < flist.length; i++) {
            if (flist[i].isDirectory()) {
                size = size + getFileSize(flist[i]);
            } else {
                size = size + flist[i].length();
            }
        }
        return size;
    }

    public static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath);
            String filePath = folderPath;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            myFilePath.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean delAllFile(String path) {
        boolean flag = false;
        if (TextUtils.isEmpty(path)) {
            return false;
        }
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);
                delFolder(path + "/" + tempList[i]);
            }
        }
        return flag;
    }


    public static void assetToFile(Context context, String name, File soFile) throws IOException,
        NameNotFoundException {

        InputStream is = new FileInputStream(soFile);

        FileOutputStream fos = context.openFileOutput(name, Context.MODE_PRIVATE);


        byte[] buffer = new byte[8192];
        int count = 0;
        while ((count = is.read(buffer)) > 0) {
            fos.write(buffer, 0, count);
        }
        fos.close();
        is.close();
    }

    public static boolean deleteFileSafely(File file) {
        if (file != null) {
            String tmpPath = file.getParent() + File.separator + System.currentTimeMillis();
            File tmp = new File(tmpPath);
            file.renameTo(tmp);
            return tmp.delete();
        }
        return false;
    }


}
