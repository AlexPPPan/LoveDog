package com.homework.lovedog.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.homework.lovedog.MainActivity;
import com.homework.lovedog.base.BaseActivity;
import com.homework.lovedog.databinding.ActivitySplashLayoutBinding;

public class SplashActivity extends BaseActivity {

    private SharedPreferences sharedPreferences;

    @Override
    protected int fragmentLayoutId() {
        return 0;
    }

    @Override
    public void onClick(View view) {

    }

    //getLoggedInUser
    public String getLoginUser() {
        return sharedPreferences.getString("loginUser", "");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySplashLayoutBinding inflate =
                ActivitySplashLayoutBinding.inflate(getLayoutInflater());
        setContentView(inflate.getRoot());
        sharedPreferences = getSharedPreferences("lovedog", Context.MODE_PRIVATE);

        new Handler().postDelayed(() -> runOnUiThread(() -> {

            //loginUserIsEmpty
            if ("".equals(getLoginUser())) {
                //jump to the login
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            } else {
                //already logged in jump to the main
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

        }), 1000);

    }
}
