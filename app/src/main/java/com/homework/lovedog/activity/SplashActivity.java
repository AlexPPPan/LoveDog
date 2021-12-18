package com.homework.lovedog.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.View;

import com.homework.lovedog.MainActivity;
import com.homework.lovedog.R;
import com.homework.lovedog.base.BaseActivity;
import com.homework.lovedog.databinding.ActivitySplashLayoutBinding;
import com.leaf.library.StatusBarUtil;

import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

public class SplashActivity extends BaseActivity {
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
        ActivitySplashLayoutBinding inflate =
            ActivitySplashLayoutBinding.inflate(getLayoutInflater());
        setContentView(inflate.getRoot());
        new Handler().postDelayed(() -> runOnUiThread(() -> {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
        }),1000);

    }
}
