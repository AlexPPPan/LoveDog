package com.homework.lovedog.http;

import android.app.Application;


import com.rxjava.BuildConfig;

import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import rxhttp.HttpSender;
import rxhttp.wrapper.param.DeleteRequest;
import rxhttp.wrapper.param.GetRequest;
import rxhttp.wrapper.param.Param;
import rxhttp.wrapper.param.PostRequest;
import rxhttp.wrapper.param.PutRequest;

public class AppHolder extends Application {

    private static AppHolder instance;

    public static AppHolder getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        httpSenderInit();
    }


    private void httpSenderInit() {
//        HttpSender.init(new OkHttpClient());

        RxJavaPlugins.setErrorHandler(throwable -> {
        });
        HttpSender.setDebug(BuildConfig.DEBUG);
        HttpSender.setOnParamAssembly(new Function<Param, Param>() {
            @Override
            public Param apply(Param p) {

                if (p instanceof GetRequest) {

                } else if (p instanceof PostRequest) {

                } else if (p instanceof PutRequest) {

                } else if (p instanceof DeleteRequest) {

                }
                //p.setUrl("");
                return p.add("versionName", "1.0.0")
                        .addHeader("deviceType", "android");
            }
        });
    }
}
