package com.example.logintime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView username =(TextView) findViewById(R.id.username);
        TextView password =(TextView) findViewById(R.id.password);
        TextView durasilogin =(TextView) findViewById(R.id.durasilogin);
        TextView telahlogin =(TextView) findViewById(R.id.telahloggin);
        TextView logouttime =(TextView) findViewById(R.id.waktulogout);

        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginbtn);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Long value = extras.getLong("key");
            String waktulogout = extras.getString("waktulogout");
            value = value/1000;
            telahlogin.setText("Anda sebelumnya telah login dengan durasi:");
            durasilogin.setText(Long.toString(value) + " detik");
            logouttime.setText("Waktu Logout: " + waktulogout);
        }

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
                    //correct
                    Toast.makeText(MainActivity.this,"LOGIN SUCCESSFUL",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, TimeDuration.class);
                    startActivity(intent);
                }else
                    //incorrect
                    Toast.makeText(MainActivity.this,"LOGIN FAILED !!!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}