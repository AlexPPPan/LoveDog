package com.homework.lovedog.http;

import android.content.Context;
import android.util.Log;


import java.util.concurrent.TimeUnit;

import io.reactivex.plugins.RxJavaPlugins;
import okhttp3.OkHttpClient;
import rxhttp.HttpSender;

public class RxHttpRequest {
    private static final long DEFAULT_TIMEOUT_SECONDS = 60;

    private static RxHttpRequest mRxHttpRequest;


    public static RxHttpRequest getInstance(Context context) {
        if (mRxHttpRequest == null) {
            synchronized (RxHttpRequest.class) {
                if (mRxHttpRequest == null) {
                    mRxHttpRequest = new RxHttpRequest(context);
                }
            }
        }
        return mRxHttpRequest;
    }

    private RxHttpRequest(Context context) {

        httpSenderInit(context);

    }

    private void httpSenderInit(Context context) {
        RxJavaPlugins.setErrorHandler(throwable -> {
            Log.e("LJX", "setErrorHandler=" + throwable);
        });
        initClient(context);
    }

    private void initClient(Context context) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.readTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        builder.writeTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        builder.connectTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS);

//		 HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
//		 loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//		 builder.addInterceptor(loggingInterceptor);

        HttpSender.init(builder.build());
    }
}
