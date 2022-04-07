package com.example.logintime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeDuration extends AppCompatActivity {

    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    String Date;
    TextView getDateAndTime;
    private Chronometer chronometer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_duration);

        getDateAndTime = findViewById(R.id.waktulogin);
        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        Date = simpleDateFormat.format(calendar.getTime());
        getDateAndTime.setText(Date);

        chronometer = findViewById(R.id.chronometer);
        chronometer.setFormat("%s");
        chronometer.setBase(SystemClock.elapsedRealtime());

        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if ((SystemClock.elapsedRealtime() - chronometer.getBase()) >= 50000) {
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    Toast.makeText(TimeDuration.this, "Bing!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();

        MaterialButton logoutbtn = (MaterialButton) findViewById(R.id.logoutbtn);

        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();
                String waktulogout = simpleDateFormat.format(calendar.getTime());
                Intent i = new Intent(TimeDuration.this, MainActivity.class);
                i.putExtra("key",elapsedMillis);
                i.putExtra("waktulogout",waktulogout);
                startActivity(i);
            }
        });
    }
}