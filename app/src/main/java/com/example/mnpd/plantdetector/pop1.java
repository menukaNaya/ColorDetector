package com.example.mnpd.plantdetector;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

public class pop1 extends AppCompatActivity {

    private TextView redlight, redavg, reddark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop1);

        DisplayMetrics dm= new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width=dm.widthPixels;
        int height=dm.heightPixels;

        getWindow().setLayout((int)(width*0.8),(int)(height*0.5));

        redlight = (TextView) findViewById(R.id.redlight);
        redlight.setText(String.format("%.2f", MainActivity.lightRed)+"%");

        redavg = (TextView) findViewById(R.id.redavg);
        redavg.setText(String.format("%.2f", MainActivity.avgRed)+"%");

        reddark = (TextView) findViewById(R.id.reddark);
        reddark.setText(String.format("%.2f", MainActivity.drkRed)+"%");
    }
}
