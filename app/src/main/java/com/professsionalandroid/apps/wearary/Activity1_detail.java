package com.professsionalandroid.apps.wearary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Activity1_detail extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1_detail);

        textView = (TextView)findViewById(R.id.text);

        String str = getIntent().getStringExtra("str");
        textView.setText(str);

    }
}
