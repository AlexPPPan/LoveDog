package com.homework.lovedog;

import android.app.Application;

import com.getkeepsafe.relinker.ReLinker;
import com.homework.lovedog.activity.db.DbHelperUtils;
import com.homework.lovedog.http.RxHttpRequest;
import com.homework.lovedog.utils.AdaptiveUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.tencent.mmkv.MMKV;



public class MyApp extends Application {
    private DbHelperUtils helper;
    private static MyApp myApp;

    static {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
            layout.setPrimaryColorsId(R.color.text_grey, android.R.color.white);
            return new ClassicsHeader(context);
        });
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> {
            return new ClassicsFooter(context).setDrawableSize(20);
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AdaptiveUtils.setDensity(this, AdaptiveUtils.DESIGN_WIDTH);
        RxHttpRequest.getInstance(this);
        MMKV.initialize(this, libName -> ReLinker.loadLibrary(MyApp.this, libName));
        myApp = this;
        helper = new DbHelperUtils(this);
    }
    public static MyApp getApplication(){

        return myApp;
    }
    public DbHelperUtils getHelper(){
        if (helper == null )
            helper = new DbHelperUtils(this);
        return helper;
    }
}
