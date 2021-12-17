package com.homework.lovedog;

import android.app.Application;

import com.getkeepsafe.relinker.ReLinker;
import com.homework.lovedog.http.RxHttpRequest;
import com.homework.lovedog.utils.AdaptiveUtils;
import com.tencent.mmkv.MMKV;



public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AdaptiveUtils.setDensity(this, AdaptiveUtils.DESIGN_WIDTH);
        RxHttpRequest.getInstance(this);
        MMKV.initialize(this, libName -> ReLinker.loadLibrary(MyApp.this, libName));
    }
}
