
package com.homework.lovedog.utils.dbutils.common.util;

import android.text.TextUtils;
import android.util.Log;

import com.homework.lovedog.utils.dbutils.x;


public class LogUtil {

    public static String customTagPrefix = "x_log";

    private LogUtil() {
    }

    private static String generateTag() {
        StackTraceElement caller = new Throwable().getStackTrace()[2];
        String tag = "%s.%s(L:%d)";
        String callerClazzName = caller.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        tag = String.format(tag, callerClazzName, caller.getMethodName(), caller.getLineNumber());
        tag = TextUtils.isEmpty(customTagPrefix) ? tag : customTagPrefix + ":" + tag;
        return tag;
    }

    public static void d(String content) {
        if (!x.isDebug()) return;
        String tag = generateTag();

        Log.d(tag, content);
    }

    public static void d(String content, Throwable tr) {
        if (!x.isDebug()) return;
        String tag = generateTag();

        Log.d(tag, content, tr);
    }

    public static void e(String content) {
        if (!x.isDebug()) return;
        String tag = generateTag();

        Log.e(tag, content);
    }

    public static void e(String content, Throwable tr) {
        if (!x.isDebug()) return;
        String tag = generateTag();

        Log.e(tag, content, tr);
    }

    public static void i(String content) {
        if (!x.isDebug()) return;
        String tag = generateTag();

        Log.i(tag, content);
    }

    public static void i(String content, Throwable tr) {
        if (!x.isDebug()) return;
        String tag = generateTag();

        Log.i(tag, content, tr);
    }

    public static void v(String content) {
        if (!x.isDebug()) return;
        String tag = generateTag();

        Log.v(tag, content);
    }

    public static void v(String content, Throwable tr) {
        if (!x.isDebug()) return;
        String tag = generateTag();

        Log.v(tag, content, tr);
    }

    public static void w(String content) {
        if (!x.isDebug()) return;
        String tag = generateTag();

        Log.w(tag, content);
    }

    public static void w(String content, Throwable tr) {
        if (!x.isDebug()) return;
        String tag = generateTag();

        Log.w(tag, content, tr);
    }

    public static void w(Throwable tr) {
        if (!x.isDebug()) return;
        String tag = generateTag();

        Log.w(tag, tr);
    }


    public static void wtf(String content) {
        if (!x.isDebug()) return;
        String tag = generateTag();

        Log.wtf(tag, content);
    }

    public static void wtf(String content, Throwable tr) {
        if (!x.isDebug()) return;
        String tag = generateTag();

        Log.wtf(tag, content, tr);
    }

    public static void wtf(Throwable tr) {
        if (!x.isDebug()) return;
        String tag = generateTag();

        Log.wtf(tag, tr);
    }

    public static void dd(String tag, String content) {
        if (!x.isDebug()) return;
        if (content.length() > 4000) {
            for (int i = 0; i < content.length(); i += 4000) {
                if (i + 4000 < content.length())
                    Log.d(tag + "-" + i, content.substring(i, i + 4000));
                else
                    Log.d(tag + "-" + i, content.substring(i, content.length()));
            }
        } else {
            Log.d(tag, content);
        }
    }

    public static void de(String tag, String content) {
        if (!x.isDebug()) return;
        if (content.length() > 4000) {
            for (int i = 0; i < content.length(); i += 4000) {
                if (i + 4000 < content.length())
                    Log.e(tag + "-" + i, content.substring(i, i + 4000));
                else
                    Log.e(tag + "-" + i, content.substring(i, content.length()));
            }
        } else {
            Log.e(tag, content);
        }
    }

    public static void di(String tag, String content) {
        if (!x.isDebug()) return;
        if (content.length() > 4000) {
            for (int i = 0; i < content.length(); i += 4000) {
                if (i + 4000 < content.length())
                    Log.i(tag + "-" + i, content.substring(i, i + 4000));
                else
                    Log.i(tag + "-" + i, content.substring(i, content.length()));
            }
        } else {
            Log.i(tag, content);
        }
    }

    public static void dv(String tag, String content) {
        if (!x.isDebug()) return;
        if (content.length() > 4000) {
            for (int i = 0; i < content.length(); i += 4000) {
                if (i + 4000 < content.length())
                    Log.v(tag + "-" + i, content.substring(i, i + 4000));
                else
                    Log.v(tag + "-" + i, content.substring(i, content.length()));
            }
        } else {
            Log.v(tag, content);
        }
    }

    public static void dw(String tag, String content) {
        if (!x.isDebug()) return;
        if (content.length() > 4000) {
            for (int i = 0; i < content.length(); i += 4000) {
                if (i + 4000 < content.length())
                    Log.w(tag + "-" + i, content.substring(i, i + 4000));
                else
                    Log.w(tag + "-" + i, content.substring(i, content.length()));
            }
        } else {
            Log.w(tag, content);
        }
    }
}
