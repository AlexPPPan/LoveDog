package com.homework.lovedog.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

import com.homework.lovedog.R;
import com.homework.lovedog.base.BaseActivity;
import com.leaf.library.StatusBarUtil;

import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

class SplashActivity extends BaseActivity {
    @Override
    protected int fragmentLayoutId() {
        return 0;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setColor(this,
            ResourcesCompat.getColor(getResources(), R.color.white,null));
        StatusBarUtil.setDarkMode(this);

    }
}
