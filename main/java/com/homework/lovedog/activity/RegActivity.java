package com.homework.lovedog.activity;


import static com.homework.lovedog.activity.Util.*;

import android.content.ContentValues;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.homework.lovedog.R;


public class RegActivity extends AppCompatActivity {
    private TextView input1;
    private TextView input2;
    private TextView input3;
    public MyDbService dbService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        dbService = new MyDbService(this);

        input1 = findViewById(R.id.input_1);
        input2 = findViewById(R.id.input_2);
        input3 = findViewById(R.id.input_3);

    }

    //register
    public void regClick(View view) {
        String name = input1.getText().toString();
        String pwd = input2.getText().toString();
        String r_pwd = input3.getText().toString();


        if (TextUtils.isEmpty(name)) {
            Toast.makeText(RegActivity.this, "please enter your account", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(RegActivity.this, "please enter your password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!r_pwd.equals(pwd)) {
            Toast.makeText(RegActivity.this, "the two passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }


        ContentValues value = new ContentValues();

        String md5Str = md5(name + "" + pwd, SLAT);
        value.put("username", name);
        value.put("password", md5Str);
        try {
            dbService.insertPerson(value);
            Toast.makeText(RegActivity.this, "Registration is successful, please log in~", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(RegActivity.this, "The account already exists, please log in~", Toast.LENGTH_SHORT).show();
        }
        finish();
    }
}