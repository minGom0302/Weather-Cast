package com.example.weathercast.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.weathercast.R;
import com.example.weathercast.etc.BackPressed;

public class Activity_Main extends AppCompatActivity {
    BackPressed backPressed = new BackPressed(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onBackPressed() {
        backPressed.onBackPressed("\"뒤로가기\"를 한번 더 누르면 종료됩니다.");
    }
}