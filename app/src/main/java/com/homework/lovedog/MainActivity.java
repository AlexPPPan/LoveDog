package com.homework.lovedog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.os.Bundle;

import com.leaf.library.StatusBarUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtil.setColor(this,
            ResourcesCompat.getColor(getResources(),R.color.white,null));
        StatusBarUtil.setDarkMode(this);

    }
}