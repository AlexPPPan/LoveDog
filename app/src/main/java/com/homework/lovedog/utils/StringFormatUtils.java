package com.homework.lovedog.utils;


import android.text.TextUtils;



public class StringFormatUtils {

    public static String defaultValueFormat(String originStr, String defaultValue, String unit,
                                            boolean showDefaultUnit) {
        return TextUtils.isEmpty(originStr)
            ? (showDefaultUnit ? String.format("%s%s", defaultValue, unit) : defaultValue)
            : String.format("%s%s", originStr, unit);
    }

    public static String defaultValueFormat(String originStr, String defaultValue, String unit) {
        return defaultValueFormat(originStr, defaultValue, unit, true);
    }

    public static String defaultValueFormat(String originStr, String defaultValue) {
        return defaultValueFormat(originStr, defaultValue, "");
    }
    public static String defaultNumValueFormat(String originStr) {
        return defaultValueFormat(originStr, "0");
    }
    public static String defaultIntegerValueFormat(String originStr) {
        if(!TextUtils.isEmpty(originStr)&&originStr.indexOf(".")>0) {
            String integerStr = originStr.substring(0,originStr.indexOf("."));
            return defaultValueFormat(integerStr, "0");
        }
        return defaultValueFormat(originStr, "0");
    }

}
