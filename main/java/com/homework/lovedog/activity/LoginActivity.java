package com.homework.lovedog.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.homework.lovedog.MainActivity;
import com.homework.lovedog.R;

public class LoginActivity extends AppCompatActivity {

    private TextView input1;
    private TextView input2;
    private CheckBox auto;

    public MyDbService dbService;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbService = new MyDbService(this);
        sharedPreferences = getSharedPreferences("lovedog", Context.MODE_PRIVATE);

        input1 = findViewById(R.id.input_1);
        input2 = findViewById(R.id.input_2);
        auto = findViewById(R.id.auto);

    }

    //logIn
    public void loginClick(View view) {

        String name = input1.getText().toString();
        String pwd = input2.getText().toString();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(LoginActivity.this, "please enter your account", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(LoginActivity.this, "please enter your password", Toast.LENGTH_SHORT).show();
            return;
        }
        String login = dbService.login(name);
        String md5Str = Util.md5(name + "" + pwd, Util.SLAT);
        if (md5Str.equals(login)) {
            Toast.makeText(LoginActivity.this, "login successfully", Toast.LENGTH_SHORT).show();
            if (auto.isChecked()) {
                setLoginUser(name);
            }
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        } else {
            Toast.makeText(LoginActivity.this, "wrong password", Toast.LENGTH_SHORT).show();
        }
    }

    //rememberUserLoginStatus
    public void setLoginUser(String loginUser) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("loginUser", loginUser);
        edit.apply();
    }

    //register
    public void regClick(View view) {
        startActivity(new Intent(LoginActivity.this, RegActivity.class));
    }

}