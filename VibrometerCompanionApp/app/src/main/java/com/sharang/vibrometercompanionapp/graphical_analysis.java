package com.sharang.vibrometercompanionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class graphical_analysis extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.grahical_analysis);
        Button back_button = (Button)findViewById(R.id.button);
        Button oneDime_button = (Button)findViewById(R.id.button3);
        Button twoDime_button = (Button)findViewById(R.id.button2);

        oneDime_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent oneD_page = new Intent(graphical_analysis.this,oneDimension.class);
                startActivity(oneD_page);
            }
        });

        twoDime_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent twoD_page = new Intent(graphical_analysis.this,twoDimension.class);
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
