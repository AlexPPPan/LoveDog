package com.homework.lovedog.http;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class ExceptionHelper {

    //处理网络异常
    public static <T> String handleNetworkException(T throwable) {
        String stringMsg = "";
        if (throwable instanceof UnknownHostException) {
            if (!isNetworkConnected(AppHolder.getInstance())) {
//                stringId = R.string.network_error;
                stringMsg = "No network";
            } else {
                stringMsg = "Net Error";
            }
        } else if (throwable instanceof SocketTimeoutException) {
            stringMsg = "Time out";
        } else if (throwable instanceof ConnectException) {
            stringMsg = "Net Error";
        }
        return TextUtils.isEmpty(stringMsg) ? null : stringMsg;
    }

    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }

        return false;
    }
}
