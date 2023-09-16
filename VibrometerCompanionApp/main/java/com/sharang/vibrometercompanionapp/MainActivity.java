package com.sharang.vibrometercompanionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private final int REQUEST_ENABLE_BT = 0;
    BluetoothDevice mmDevice;
    //final BluetoothServerSocket mmServerSocket;
    BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button graph_analys_button =(Button)findViewById(R.id.button1);
        //Button device_calib_button =(Button)findViewById(R.id.button2);
        ImageButton BT_button = (ImageButton) findViewById(R.id.BTButton1);


        BT_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBT();
            }
        });

/*
        device_calib_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent calib_page = new Intent(MainActivity.this,ComparisonActivity.class);
                startActivity(calib_page);
            }
        });
*/

        graph_analys_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ga_page = new Intent(MainActivity.this,ExtraActivity.class);
                startActivity(ga_page);
            }
        });

    }

    public void openBT() {

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
                    Toast.makeText(this, "Device Not Found !" , Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

}
