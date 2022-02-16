package com.homework.lovedog.utils;

import android.os.Environment;
import android.text.TextUtils;


import com.homework.lovedog.utils.dbutils.common.util.LogUtil;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class FileUtils {


    public static boolean deleteFile(File file) {
        boolean result = false;
        if (file.exists()) {
            if (file.isFile()) {
                result = file.delete();
            } else {
                for (File child : file.listFiles()) {
                    deleteFile(child);
                }
                result = file.delete();
            }
        }
        return result;
    }

    public static boolean deleteFile(String path) {
        boolean result = false;
        if (path != null && path.length() > 0) {
            result = deleteFile(new File(path));
        }
        return result;
    }


    public static void move(String from, String to) throws Exception {
        try {
            File dir = new File(from);
            to += File.separator + dir.getName();
            File moveDir = new File(to);
            if (dir.isDirectory()) {
                if (!moveDir.exists()) {
                    moveDir.mkdirs();
                }
            } else {
                File toFile = new File(to);
                dir.renameTo(toFile);
                return;
            }
            File[] files = dir.listFiles();
            if (files == null)
                return;

            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    move(files[i].getPath(), to);
                    files[i].delete();
                }
                File moveFile = new File(moveDir.getPath() + File.separator + files[i].getName());
                if (moveFile.exists()) {
                    moveFile.delete();
                }
                files[i].renameTo(moveFile);
            }
            dir.delete();
        } catch (Exception e) {
            throw e;
        }
    }


    public static boolean copyFile(String srcPath, String destPath) {
        boolean result = false;
        try {
            int byteSum = 0;
            int byteRead = 0;
            File oldFile = new File(srcPath);
            if (oldFile.exists()) {
                InputStream inStream = new FileInputStream(srcPath);
                FileOutputStream fs = new FileOutputStream(destPath);
                byte[] buffer = new byte[1024];
                while ((byteRead = inStream.read(buffer)) != -1) {
                    byteSum += byteRead;
                    fs.write(buffer, 0, byteRead);
                }
                inStream.close();
                fs.close();
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    public static boolean renameTo(String src, String des) {
        try {
            File fileSrc = new File(src);
            File fileDes = new File(des);
            fileSrc.renameTo(fileDes);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public static void copyFileToDir(String srcPath, String targetDir) {
        try {
            File srcFile = new File(srcPath);
            String targetFile = targetDir + File.separator + srcFile.getName();
            copyFile(srcPath, targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void copyFolder(String oldPath, String newPath) {
        try {
            new File(newPath).mkdirs();
            File dir = new File(oldPath);
            newPath += File.separator + dir.getName();
            File moveDir = new File(newPath);
            if (dir.isDirectory()) {
                if (!moveDir.exists()) {
                    moveDir.mkdirs();
                }
            }
            String[] file = dir.list();
            File temp = null;
            for (int i = 0; i < file.length; i++) {
                if (oldPath.endsWith(File.separator)) {
                    temp = new File(oldPath + file[i]);
                } else {
                    temp = new File(oldPath + File.separator + file[i]);
                }
                if (temp.isFile()) {
                    FileInputStream input = new FileInputStream(temp);
                    FileOutputStream output =
                            new FileOutputStream(newPath + File.separator + (temp.getName())
                                    .toString());
                    byte[] b = new byte[1024 * 5];
                    int len;
                    while ((len = input.read(b)) != -1) {
                        output.write(b, 0, len);
                    }
                    output.flush();
                    output.close();
                    input.close();
                }
                if (temp.isDirectory()) {
                    copyFolder(oldPath + File.separator + file[i], newPath);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void copyFolderFilesToNewFolder(String oldPath, String newPath) {
        try {
            new File(newPath).mkdirs();
            File dir = new File(oldPath);
            File moveDir = new File(newPath);
            if (dir.isDirectory()) {
                if (!moveDir.exists()) {
                    moveDir.mkdirs();
                }
            }
            String[] file = dir.list();
            File temp = null;
            for (int i = 0; i < file.length; i++) {
                if (oldPath.endsWith(File.separator)) {
                    temp = new File(oldPath + file[i]);
                } else {
                    temp = new File(oldPath + File.separator + file[i]);
                }
                if (temp.isFile()) {
                    FileInputStream input = new FileInputStream(temp);
                    FileOutputStream output =
                            new FileOutputStream(newPath + File.separator + (temp.getName())
                                    .toString());
                    byte[] b = new byte[1024 * 5];
                    int len;
                    while ((len = input.read(b)) != -1) {
                        output.write(b, 0, len);
                    }
                    output.flush();
                    output.close();
                    input.close();
                }
                if (temp.isDirectory()) {
                    copyFolder(oldPath + File.separator + file[i], newPath);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<String> findFile(String dirPath, String regex) {
        ArrayList<String> fileList = new ArrayList<>();
        File dir = new File(dirPath);
        if (dir.exists()) {
            File[] files = dir.listFiles();
            for (File temp : files) {
                String name = temp.getName();
                if (name.matches(regex) && temp.isFile()) {
                    fileList.add(temp.getAbsolutePath());
                }
            }
        }
        return fileList;
    }

    public static String findFileFrist(String dirPath, String regex) {
        String path = null;
        File dir = new File(dirPath);
        if (dir.exists()) {
            File[] files = dir.listFiles();
            for (File temp : files) {
                String name = temp.getName();
                if (name.matches(regex) && temp.isFile()) {
                    path = temp.getAbsolutePath();
                    break;
                }
            }
        }
        return path;
    }

    public static byte[] getBytes(File file) {
        byte[] buffer = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) > 0) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    public static byte[] getBytes(String path) {
        return getBytes(new File(path));
    }


    public static boolean checkDirHasFile(String path) {
        File dir = new File(path);
        String[] file = new String[0];
        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (dir.isDirectory()) {
            file = dir.list();
        }
        return file != null && file.length != 0;
    }


}
