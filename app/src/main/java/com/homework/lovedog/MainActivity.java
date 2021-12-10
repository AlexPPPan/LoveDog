package com.homework.lovedog;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.LifecycleOwner;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.homework.lovedog.base.IBaseView;
import com.homework.lovedog.presenter.MainPresenter;
import com.leaf.library.StatusBarUtil;

public class MainActivity extends AppCompatActivity implements IBaseView {

    private MainPresenter mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtil.setColor(this,
            ResourcesCompat.getColor(getResources(),R.color.white,null));
        StatusBarUtil.setDarkMode(this);
        mMainPresenter = new MainPresenter(this);
    }

    @Nullable
    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void serverErr(@Nullable String message) {

    }

    @Nullable
    @Override
    public LifecycleOwner getViewLifecycleOwner() {
        return null;
    }

    @Nullable
    @Override
    public Context getContext() {
        return null;
    }
}