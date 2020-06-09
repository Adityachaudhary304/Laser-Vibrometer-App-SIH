package com.sharang.vibrometercompanionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Set;

public class twoDimension extends AppCompatActivity {

    private static final int REQUEST_ENABLE_BT =0 ;
    BluetoothDevice mmDevice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.two_dimension);

        Button back_button = (Button)findViewById(R.id.button5);
        Spinner spin2d = (Spinner)findViewById(R.id.spinner2);

        ArrayAdapter<CharSequence> adapter2d = ArrayAdapter.createFromResource(this,R.array.spin2d, android.R.layout.simple_spinner_item);


// Specify the layout to use when the list of choices appears
        adapter2d.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spin2d.setAdapter(adapter2d);

        spin2d.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView adapter2d, View spin1d,int pos, long id) {
                // An item was selected. You can retrieve the selected item using
                Log.d("LOGTAG", "" + adapter2d.getItemAtPosition(pos));
                adapter2d.getItemAtPosition(pos);
            }

            @Override
            public void onNothingSelected(AdapterView adapter2d) {
                Log.d("TAG", "Nothing selected");
            }
        });



        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void openBT() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            Log.d( "Vibro","Device doesn't support Bluetooth");

        }
        else {
            Log.d( "Vibro","Bluetooth works !!!!");
        }

        if (!bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }

        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();

        if(pairedDevices.size() > 0) {
            for(BluetoothDevice device : pairedDevices) {
                if(device.getName().equals("HC-05")) {
                    mmDevice = device;
                    break;
                }
                else {
                    Toast.makeText(this, "Device Not Found in page 2d!" , Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
