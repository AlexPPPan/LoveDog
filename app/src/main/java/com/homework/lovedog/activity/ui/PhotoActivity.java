package com.homework.lovedog.activity.ui;


import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.homework.lovedog.R;

public class PhotoActivity extends AppCompatActivity {

    private PhotoView photoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        photoView = findViewById(R.id.photoView);
        Glide.with(this).load(getIntent().getStringExtra("url")).into(photoView);
    }
}