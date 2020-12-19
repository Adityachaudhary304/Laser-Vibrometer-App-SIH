package com.sharang.vibrometercompanionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ExtraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.extra_analysis);

        Button back_button = (Button)findViewById(R.id.button);
        Button vib_monitor = (Button)findViewById(R.id.button3);
        Button calib_pg = (Button)findViewById(R.id.button2);

        vib_monitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent oneD_page = new Intent(ExtraActivity.this,graphical_analysis.class);
                startActivity(oneD_page);
            }
        });

        calib_pg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent twoD_page = new Intent(ExtraActivity.this,ComparisonActivity.class);
                startActivity(twoD_page);
            }
        });

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}