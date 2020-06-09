package com.sharang.vibrometercompanionapp;

import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class oneDimension extends AppCompatActivity {

    private static final int REQUEST_ENABLE_BT = 0;
    BluetoothDevice mmDevice;
    BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    private BluetoothServerSocket mmServerSocket;
    final String BT_NAME = bluetoothAdapter.getName();
    final UUID MY_UUID = UUID.fromString("f546a3fa-9c10-11ea-bb37-0242ac130002");
    LineChart mpLineChart1;
    LineChart mpLineChart2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.one_dimension);

//        // Get the screen's density scale
//        final float scale = getResources().getDisplayMetrics().density;
//        final float wid = getResources().getDisplayMetrics().widthPixels;
//        double siz= wid*0.5;

        Button back_button=(Button)findViewById(R.id.button4);
        Spinner spin1d = (Spinner)findViewById(R.id.spinner1);
        mpLineChart1 = (LineChart) findViewById(R.id.chart1);
        LineDataSet lineDataset1 =new LineDataSet(dataValues1(),"Data Set 1");
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataset1);
        LineData data = new LineData(dataSets);
        mpLineChart1.setData(data);
        lineDataset1.setLineWidth(3f);
        lineDataset1.setDrawCircles(false);
        lineDataset1.setDrawCircleHole(false);
        //LimitLine xLimit = new LimitLine(150f);
        Description description = new Description();
        description.setText("After FFT of data");
        description.setTextSize(15);
        description.setTextColor(Color.BLUE);
        mpLineChart1.setDescription(description);
        mpLineChart1.setDrawBorders(true);
        mpLineChart1.setBorderWidth(2);
        mpLineChart1.setBorderColor(Color.BLACK);
        lineDataset1.setColor(Color.RED);
        mpLineChart1.getAxisRight().setEnabled(false);

        mpLineChart2 = (LineChart) findViewById(R.id.chart2);
        LineDataSet lineDataset2 =new LineDataSet(dataValues2(),"Data Set 2");
        ArrayList<ILineDataSet> dataSets2 = new ArrayList<>();
        dataSets2.add(lineDataset2);
        LineData data2 = new LineData(dataSets2);
        mpLineChart2.setData(data2);
        lineDataset2.setLineWidth(3f);
        lineDataset2.setColor(Color.YELLOW);
        mpLineChart2.setDrawBorders(true);
        mpLineChart2.setBorderWidth(2);
        mpLineChart2.setBorderColor(Color.BLACK);
        Description description2 = new Description();
        description2.setText("Actual Data");
        description2.setTextSize(15);
        description2.setTextColor(Color.BLUE);
        mpLineChart2.setDescription(description2);




        ArrayAdapter<CharSequence> adapter1d = ArrayAdapter.createFromResource(this,R.array.spin1d, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter1d.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spin1d.setAdapter(adapter1d);

        openBT();

        spin1d.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView adapter1d, View spin1d,int pos, long id) {
                // An item was selected. You can retrieve the selected item using
                Log.d("LOGTAG", "hhhhh" + adapter1d.getItemAtPosition(pos));
                adapter1d.getItemAtPosition(pos);
                mpLineChart1.invalidate();
            }

            @Override
            public void onNothingSelected(AdapterView adapter1d) {
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

    private ArrayList<Entry> dataValues2(){
        double x = 0.0;
        int l[] = {0,1,0,-1,0,1,0,-1,0,1,0,-1,0,1,0,-1,0,1,0,-1,0,1,0,-1,0,1,0,-1,0,1,0,-1,0,1,0,-1,0,1,0,-1,0,1,0,-1,0,1,0,-1,0,1,0,-1,0,1,0,-1,0,1,0,-1,0,1,0,-1,0,1,0,-1,0,1,0,-1,0,1,0,-1,0,1,0,-1,0,1,0,-1,0,1,0,-1,0,1,0,-1,0,1,0,-1,0,1,0,-1,0,1,0,-1,0,1,0,-1,0,1,0,-1,0,1,0,-1,0,1,0,-1,0,1,0,-1,0,1,0,-1,0,1,0,-1,0,1,0,-1,0,1,0,-1,0,1,0,-1,0,1,0,-1,0,1,0,-1,0,1,0,-1,0,1,0,-1,0,1,0,-1,0,1,0,-1,0,1};
        ArrayList<Entry> dataVals = new ArrayList<Entry>();
        for (int i=0; i<100 ;i++){
            x = x + 0.0067;
            dataVals.add(new Entry((float)x,(float)l[i]));
        }

        return dataVals;
    }


    private ArrayList<Entry> dataValues1()
    {
        float o[] = {0.00f ,0.25f ,0.50f ,0.75f ,1.00f ,1.25f ,1.50f ,1.75f ,2.00f ,2.25f ,2.50f ,2.75f ,3.00f ,3.25f ,3.50f ,3.75f ,4.00f ,4.25f ,4.50f ,4.75f ,5.00f ,5.25f ,5.50f ,5.75f ,6.00f ,6.25f ,6.50f ,6.75f ,7.00f ,7.25f ,7.50f ,7.75f ,8.00f ,8.25f ,8.50f ,8.75f ,9.00f ,9.25f ,9.50f ,9.75f ,10.00f ,10.25f ,10.50f ,10.75f ,11.00f ,11.25f ,11.50f ,11.75f ,12.00f ,12.25f ,12.50f ,12.75f ,13.00f ,13.25f ,13.50f ,13.75f ,14.00f ,14.25f ,14.50f ,14.75f ,15.00f ,15.25f ,15.50f ,15.75f ,16.00f ,16.25f ,16.50f ,16.75f ,17.00f ,17.25f ,17.50f ,17.75f ,18.00f ,18.25f ,18.50f ,18.75f ,19.00f ,19.25f ,19.50f ,19.75f ,20.00f ,20.25f ,20.50f ,20.75f ,21.00f ,21.25f ,21.50f ,21.75f ,22.00f ,22.25f ,22.50f ,22.75f ,23.00f ,23.25f ,23.50f ,23.75f ,24.00f ,24.25f ,24.50f ,24.75f ,25.00f ,25.25f ,25.50f ,25.75f ,26.00f ,26.25f ,26.50f ,26.75f ,27.00f ,27.25f ,27.50f ,27.75f ,28.00f ,28.25f ,28.50f ,28.75f ,29.00f ,29.25f ,29.50f ,29.75f ,30.00f ,30.25f ,30.50f ,30.75f ,31.00f ,31.25f ,31.50f ,31.75f ,32.00f ,32.25f ,32.50f ,32.75f ,33.00f ,33.25f ,33.50f ,33.75f ,34.00f ,34.25f ,34.50f ,34.75f ,35.00f ,35.25f ,35.50f ,35.75f ,36.00f ,36.25f ,36.50f ,36.75f ,37.00f ,37.25f ,37.50f ,37.75f ,38.00f ,38.25f ,38.50f ,38.75f ,39.00f ,39.25f ,39.50f ,39.75f ,40.00f ,40.25f ,40.50f ,40.75f ,41.00f ,41.25f ,41.50f ,41.75f ,42.00f ,42.25f ,42.50f ,42.75f ,43.00f ,43.25f ,43.50f ,43.75f ,44.00f ,44.25f ,44.50f ,44.75f ,45.00f ,45.25f ,45.50f ,45.75f ,46.00f ,46.25f ,46.50f ,46.75f ,47.00f ,47.25f ,47.50f ,47.75f ,48.00f ,48.25f ,48.50f ,48.75f ,49.00f ,49.25f ,49.50f ,49.75f ,50.00f ,50.25f ,50.50f ,50.75f ,51.00f ,51.25f ,51.50f ,51.75f ,52.00f ,52.25f ,52.50f ,52.75f ,53.00f ,53.25f ,53.50f ,53.75f ,54.00f ,54.25f ,54.50f ,54.75f ,55.00f ,55.25f ,55.50f ,55.75f ,56.00f ,56.25f ,56.50f ,56.75f ,57.00f ,57.25f ,57.50f ,57.75f ,58.00f ,58.25f ,58.50f ,58.75f ,59.00f ,59.25f ,59.50f ,59.75f ,60.00f ,60.25f ,60.50f ,60.75f ,61.00f ,61.25f ,61.50f ,61.75f ,62.00f ,62.25f ,62.50f ,62.75f ,63.00f ,63.25f ,63.50f ,63.75f ,64.00f ,64.25f ,64.50f ,64.75f ,65.00f ,65.25f ,65.50f ,65.75f ,66.00f ,66.25f ,66.50f ,66.75f ,67.00f ,67.25f ,67.50f ,67.75f ,68.00f ,68.25f ,68.50f ,68.75f ,69.00f ,69.25f ,69.50f ,69.75f ,70.00f ,70.25f ,70.50f ,70.75f ,71.00f ,71.25f ,71.50f ,71.75f ,72.00f ,72.25f ,72.50f ,72.75f ,73.00f ,73.25f ,73.50f ,73.75f ,74.00f ,74.25f ,74.50f ,74.75f ,75.00f ,75.25f ,75.50f ,75.75f ,76.00f ,76.25f ,76.50f ,76.75f ,77.00f ,77.25f ,77.50f ,77.75f ,78.00f ,78.25f ,78.50f ,78.75f ,79.00f ,79.25f ,79.50f ,79.75f ,80.00f ,80.25f ,80.50f ,80.75f ,81.00f ,81.25f ,81.50f ,81.75f ,82.00f ,82.25f ,82.50f ,82.75f ,83.00f ,83.25f ,83.50f ,83.75f ,84.00f ,84.25f ,84.50f ,84.75f ,85.00f ,85.25f ,85.50f ,85.75f ,86.00f ,86.25f ,86.50f ,86.75f ,87.00f ,87.25f ,87.50f ,87.75f ,88.00f ,88.25f ,88.50f ,88.75f ,89.00f ,89.25f ,89.50f ,89.75f ,90.00f ,90.25f ,90.50f ,90.75f ,91.00f ,91.25f ,91.50f ,91.75f ,92.00f ,92.25f ,92.50f ,92.75f ,93.00f ,93.25f ,93.50f ,93.75f ,94.00f ,94.25f ,94.50f ,94.75f ,95.00f ,95.25f ,95.50f ,95.75f ,96.00f ,96.25f ,96.50f ,96.75f ,97.00f ,97.25f ,97.50f ,97.75f ,98.00f ,98.25f ,98.50f ,98.75f ,99.00f ,99.25f ,99.50f ,99.75f ,100.00f ,100.25f ,100.50f ,100.75f ,101.00f ,101.25f ,101.50f ,101.75f ,102.00f ,102.25f ,102.50f ,102.75f ,103.00f ,103.25f ,103.50f ,103.75f ,104.00f ,104.25f ,104.50f ,104.75f ,105.00f ,105.25f ,105.50f ,105.75f ,106.00f ,106.25f ,106.50f ,106.75f ,107.00f ,107.25f ,107.50f ,107.75f ,108.00f ,108.25f ,108.50f ,108.75f ,109.00f ,109.25f ,109.50f ,109.75f ,110.00f ,110.25f ,110.50f ,110.75f ,111.00f ,111.25f ,111.50f ,111.75f ,112.00f ,112.25f ,112.50f ,112.75f ,113.00f ,113.25f ,113.50f ,113.75f ,114.00f ,114.25f ,114.50f ,114.75f ,115.00f ,115.25f ,115.50f ,115.75f ,116.00f ,116.25f ,116.50f ,116.75f ,117.00f ,117.25f ,117.50f ,117.75f ,118.00f ,118.25f ,118.50f ,118.75f ,119.00f ,119.25f ,119.50f ,119.75f ,120.00f ,120.25f ,120.50f ,120.75f ,121.00f ,121.25f ,121.50f ,121.75f ,122.00f ,122.25f ,122.50f ,122.75f ,123.00f ,123.25f ,123.50f ,123.75f ,124.00f ,124.25f ,124.50f ,124.75f ,125.00f ,125.25f ,125.50f ,125.75f ,126.00f ,126.25f ,126.50f ,126.75f ,127.00f ,127.25f ,127.50f ,127.75f ,128.00f ,128.25f ,128.50f ,128.75f ,129.00f ,129.25f ,129.50f ,129.75f ,130.00f ,130.25f ,130.50f ,130.75f ,131.00f ,131.25f ,131.50f ,131.75f ,132.00f ,132.25f ,132.50f ,132.75f ,133.00f ,133.25f ,133.50f ,133.75f ,134.00f ,134.25f ,134.50f ,134.75f ,135.00f ,135.25f ,135.50f ,135.75f ,136.00f ,136.25f ,136.50f ,136.75f ,137.00f ,137.25f ,137.50f ,137.75f ,138.00f ,138.25f ,138.50f ,138.75f ,139.00f ,139.25f ,139.50f ,139.75f ,140.00f ,140.25f ,140.50f ,140.75f ,141.00f ,141.25f ,141.50f ,141.75f ,142.00f ,142.25f ,142.50f ,142.75f ,143.00f ,143.25f ,143.50f ,143.75f ,144.00f ,144.25f ,144.50f ,144.75f ,145.00f ,145.25f ,145.50f ,145.75f ,146.00f ,146.25f ,146.50f ,146.75f ,147.00f ,147.25f ,147.50f ,147.75f ,148.00f ,148.25f ,148.50f ,148.75f ,149.00f ,149.25f ,149.50f ,149.75f ,150.00f ,150.25f ,150.50f ,150.75f ,151.00f ,151.25f ,151.50f ,151.75f ,152.00f ,152.25f ,152.50f ,152.75f ,153.00f ,153.25f ,153.50f ,153.75f ,154.00f ,154.25f ,154.50f ,154.75f ,155.00f ,155.25f ,155.50f ,155.75f ,156.00f ,156.25f ,156.50f ,156.75f ,157.00f ,157.25f ,157.50f ,157.75f ,158.00f ,158.25f ,158.50f ,158.75f ,159.00f ,159.25f ,159.50f ,159.75f ,160.00f ,160.25f ,160.50f ,160.75f ,161.00f ,161.25f ,161.50f ,161.75f ,162.00f ,162.25f ,162.50f ,162.75f ,163.00f ,163.25f ,163.50f ,163.75f ,164.00f ,164.25f ,164.50f ,164.75f ,165.00f ,165.25f ,165.50f ,165.75f ,166.00f ,166.25f ,166.50f ,166.75f ,167.00f ,167.25f ,167.50f ,167.75f ,168.00f ,168.25f ,168.50f ,168.75f ,169.00f ,169.25f ,169.50f ,169.75f ,170.00f ,170.25f ,170.50f ,170.75f ,171.00f ,171.25f ,171.50f ,171.75f ,172.00f ,172.25f ,172.50f ,172.75f ,173.00f ,173.25f ,173.50f ,173.75f ,174.00f ,174.25f ,174.50f ,174.75f ,175.00f ,175.25f ,175.50f ,175.75f ,176.00f ,176.25f ,176.50f ,176.75f ,177.00f ,177.25f ,177.50f ,177.75f ,178.00f ,178.25f ,178.50f ,178.75f ,179.00f ,179.25f ,179.50f ,179.75f ,180.00f ,180.25f ,180.50f ,180.75f ,181.00f ,181.25f ,181.50f ,181.75f ,182.00f ,182.25f ,182.50f ,182.75f ,183.00f ,183.25f ,183.50f ,183.75f ,184.00f ,184.25f ,184.50f ,184.75f ,185.00f ,185.25f ,185.50f ,185.75f ,186.00f ,186.25f ,186.50f ,186.75f ,187.00f ,187.25f ,187.50f ,187.75f ,188.00f ,188.25f ,188.50f ,188.75f ,189.00f ,189.25f ,189.50f ,189.75f ,190.00f ,190.25f ,190.50f ,190.75f ,191.00f ,191.25f ,191.50f ,191.75f ,192.00f ,192.25f ,192.50f ,192.75f ,193.00f ,193.25f ,193.50f ,193.75f ,194.00f ,194.25f ,194.50f ,194.75f ,195.00f ,195.25f ,195.50f ,195.75f ,196.00f ,196.25f ,196.50f ,196.75f ,197.00f ,197.25f ,197.50f ,197.75f ,198.00f ,198.25f ,198.50f ,198.75f ,199.00f ,199.25f ,199.50f ,199.75f ,200.00f ,200.25f ,200.50f ,200.75f ,201.00f ,201.25f ,201.50f ,201.75f ,202.00f ,202.25f ,202.50f ,202.75f ,203.00f ,203.25f ,203.50f ,203.75f ,204.00f ,204.25f ,204.50f ,204.75f ,205.00f ,205.25f ,205.50f ,205.75f ,206.00f ,206.25f ,206.50f ,206.75f ,207.00f ,207.25f ,207.50f ,207.75f ,208.00f ,208.25f ,208.50f ,208.75f ,209.00f ,209.25f ,209.50f ,209.75f ,210.00f ,210.25f ,210.50f ,210.75f ,211.00f ,211.25f ,211.50f ,211.75f ,212.00f ,212.25f ,212.50f ,212.75f ,213.00f ,213.25f ,213.50f ,213.75f ,214.00f ,214.25f ,214.50f ,214.75f ,215.00f ,215.25f ,215.50f ,215.75f ,216.00f ,216.25f ,216.50f ,216.75f ,217.00f ,217.25f ,217.50f ,217.75f ,218.00f ,218.25f ,218.50f ,218.75f ,219.00f ,219.25f ,219.50f ,219.75f ,220.00f ,220.25f ,220.50f ,220.75f ,221.00f ,221.25f ,221.50f ,221.75f ,222.00f ,222.25f ,222.50f ,222.75f ,223.00f ,223.25f ,223.50f ,223.75f ,224.00f ,224.25f ,224.50f ,224.75f ,225.00f ,225.25f ,225.50f ,225.75f ,226.00f ,226.25f ,226.50f ,226.75f ,227.00f ,227.25f ,227.50f ,227.75f ,228.00f ,228.25f ,228.50f ,228.75f ,229.00f ,229.25f ,229.50f ,229.75f ,230.00f ,230.25f ,230.50f ,230.75f ,231.00f ,231.25f ,231.50f ,231.75f ,232.00f ,232.25f ,232.50f ,232.75f ,233.00f ,233.25f ,233.50f ,233.75f ,234.00f ,234.25f ,234.50f ,234.75f ,235.00f ,235.25f ,235.50f ,235.75f ,236.00f ,236.25f ,236.50f ,236.75f ,237.00f ,237.25f ,237.50f ,237.75f ,238.00f ,238.25f ,238.50f ,238.75f ,239.00f ,239.25f ,239.50f ,239.75f ,240.00f };
        float p[] = { 0f , 0f , 0f , 0f , 0f , 1f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f , 0f };
        ArrayList<Entry> dataVals = new ArrayList<Entry>();
        for (int i=0;i<960;i++){
            dataVals.add(new Entry(o[i],p[i]));
        }

        return dataVals;
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
                    Toast.makeText(this, "Device Not Found in page 1d !" , Toast.LENGTH_SHORT).show();
                }
            }
        }
    }



//    public void AcceptThread() {
//        // Use a temporary object that is later assigned to mmServerSocket
//        // because mmServerSocket is final.
//        BluetoothServerSocket tmp = null;
//
//
//        try {
//            // MY_UUID is the app's UUID string, also used by the client code.
//            tmp = bluetoothAdapter.listenUsingRfcommWithServiceRecord(BT_NAME, MY_UUID);
//        } catch (IOException e) {
//            Log.e("TAG", "Socket's listen() method failed", e);
//        }
//        mmServerSocket = tmp;
//    }
//
//    public void run() throws IOException {
//        BluetoothSocket socket = null;
//        // Keep listening until exception occurs or a socket is returned.
//        while (true) {
//            try {
//                socket = mmServerSocket.accept();
//            } catch (IOException e) {
//                Log.e("TAG", "Socket's accept() method failed", e);
//                break;
//            }
//
//            if (socket != null) {
//                // A connection was accepted. Perform work associated with
//                // the connection in a separate thread.
//                //manageMyConnectedSocket(socket);
//                mmServerSocket.close();
//                break;
//            }
//        }
//    }
//
//    // Closes the connect socket and causes the thread to finish.
//    public void cancel() {
//        try {
//            mmServerSocket.close();
//        } catch (IOException e) {
//            Log.e("TAG", "Could not close the connect socket", e);
//        }
//    }
}
