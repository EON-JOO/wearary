package com.professsionalandroid.apps.wearary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Activity3_addmemo extends AppCompatActivity {
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity3_addmemo);

        editText=findViewById(R.id.edtMemo);

        findViewById(R.id.completeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = editText.getText().toString();

                if(str.length()>0){
                    Date date = new Date();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

                    String substr = simpleDateFormat.format(date);

                    Toast.makeText(Activity3_addmemo.this,str+","+substr,Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent();
                    intent.putExtra("main",str);
                    intent.putExtra("sub",substr);
                    setResult(0,intent);

                    finish();
                }
            }
        });
    }
}