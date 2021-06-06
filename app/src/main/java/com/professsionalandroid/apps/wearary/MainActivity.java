package com.professsionalandroid.apps.wearary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    long mNow;
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.textView);
        SimpleDateFormat dateFormat = new SimpleDateFormat("오늘은 yyyy년 MM월 dd일 이고 \n현재 시간은 a hh시 mm분 ss초 입니다.");
        mTextView.setText(dateFormat.format(new Date()));
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