package com.sharang.vibrometercompanionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button graph_analys_button =(Button)findViewById(R.id.button1);
        //Button BT_button = (Button) findViewById(R.id.BTButton1);

        graph_analys_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ga_page = new Intent(MainActivity.this,graphical_analysis.class);
                startActivity(ga_page);
            }
        });


    }
}
