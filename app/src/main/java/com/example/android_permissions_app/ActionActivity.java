package com.example.android_permissions_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ActionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}