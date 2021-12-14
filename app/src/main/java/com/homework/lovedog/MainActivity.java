package com.homework.lovedog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.LifecycleOwner;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.homework.lovedog.bean.DogList;
import com.homework.lovedog.presenter.MainPresenter;
import com.homework.lovedog.view.IMainView;
import com.leaf.library.StatusBarUtil;

import java.util.List;

public class MainActivity extends AppCompatActivity implements IMainView {

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

    @Override
    protected void onResume() {
        super.onResume();
        mMainPresenter.queryDogList(true);
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

    @Override
    public void showDogList(@NonNull List<DogList> dogList) {

    }
}