package com.example.mnpd.plantdetector;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

public class pop3 extends AppCompatActivity {

    private TextView yellowlight, yellowavg, yellowdark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop3);

        DisplayMetrics dm= new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width=dm.widthPixels;
        int height=dm.heightPixels;

        getWindow().setLayout((int)(width*0.8),(int)(height*0.5));


        yellowlight = (TextView) findViewById(R.id.yellowlight);
        yellowlight.setText(String.format("%.2f", MainActivity.lightYlw)+"%");

        yellowavg = (TextView) findViewById(R.id.yellowavg);
        yellowavg.setText(String.format("%.2f", MainActivity.avgYlw)+"%");

        yellowdark = (TextView) findViewById(R.id.yellowdark);
        yellowdark.setText(String.format("%.2f", MainActivity.drkGrn)+"%");
    }
}
