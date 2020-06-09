package com.sharang.vibrometercompanionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class oneDimension extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.one_dimension);

        Button back_button=(Button)findViewById(R.id.button4);

        Spinner spin1d = (Spinner)findViewById(R.id.spinner1);

        ArrayAdapter<CharSequence> adapter1d = ArrayAdapter.createFromResource(this,R.array.spin1d, android.R.layout.simple_spinner_item);


// Specify the layout to use when the list of choices appears
        adapter1d.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spin1d.setAdapter(adapter1d);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
