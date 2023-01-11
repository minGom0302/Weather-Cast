package com.example.weathercast.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.weathercast.R;

import java.util.Objects;

public class Activity_Intro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        Objects.requireNonNull(getSupportActionBar()).hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Activity_Intro.this, Activity_Main.class);
                startActivity(intent);
                finish();
            }
        }, 1000);

    }
}