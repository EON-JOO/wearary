package com.professsionalandroid.apps.wearary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class Activity2 extends AppCompatActivity {

    boolean i = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        Button btn_hot = findViewById(R.id.btn_hot);
        Button btn_cold = findViewById(R.id.btn_cold);
        Button btn_soso = findViewById(R.id.btn_soso);
        Button btn_good = findViewById(R.id.btn_good);

        btn_hot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i) {
                    Intent intent = new Intent(getApplicationContext(), Activity2_codi.class);
                    intent.putExtra("sticker", "hot");
                    startActivity(intent);
                }
            }
        });
        btn_cold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i) {
                    Intent intent = new Intent(getApplicationContext(), Activity2_codi.class);
                    intent.putExtra("sticker", "cold");
                    startActivity(intent);
                }
            }
        });
        btn_soso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i) {
                    Intent intent = new Intent(getApplicationContext(), Activity2_codi.class);
                    intent.putExtra("sticker", "soso");
                    startActivity(intent);
                }
            }
        });
        btn_good.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i) {
                    Intent intent = new Intent(getApplicationContext(), Activity2_codi.class);
                    intent.putExtra("sticker", "good");
                    startActivity(intent);
                }
            }
        });
    }
}
