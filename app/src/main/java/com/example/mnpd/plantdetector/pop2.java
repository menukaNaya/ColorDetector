package com.example.mnpd.plantdetector;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

public class pop2 extends AppCompatActivity {

    private TextView greenlight, greenavg, greendark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop2);

        DisplayMetrics dm= new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width=dm.widthPixels;
        int height=dm.heightPixels;

        getWindow().setLayout((int)(width*0.8),(int)(height*0.5));

        greenlight = (TextView) findViewById(R.id.greenlight);
        greenlight.setText(String.format("%.2f", MainActivity.lightGrn)+"%");

        greenavg = (TextView) findViewById(R.id.greenavg);
        greenavg.setText(String.format("%.2f", MainActivity.avgGrn)+"%");

        greendark = (TextView) findViewById(R.id.greendark);
        greendark.setText(String.format("%.2f", MainActivity.drkGrn)+"%");
    }
}
