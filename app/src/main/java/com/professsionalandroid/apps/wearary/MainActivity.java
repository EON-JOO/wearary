package com.professsionalandroid.apps.wearary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btn1(View v){
        Intent intent001 = new Intent(this, activity1.class);
        startActivity(intent001);
    }
    public void btn2(View v){
        Intent intent002 = new Intent(this, activity2.class);
        startActivity(intent002);
    }
    public void btn3(View v){
        Intent intent003 = new Intent(this, activity3.class);
        startActivity(intent003);
    }
    public void btn4(View v){
        Intent intent004 = new Intent(this, activity4.class);
        startActivity(intent004);
    }
}