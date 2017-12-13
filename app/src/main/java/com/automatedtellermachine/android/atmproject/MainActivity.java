package com.automatedtellermachine.android.atmproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Start button to the ATM machine
    public void startButton(View view) {

            TextView touchStart = (TextView) findViewById(R.id.touchStart);
            touchStart.setEnabled(true);
            Toast.makeText(MainActivity.this, "Please Enter your Pin Number",
                    Toast.LENGTH_LONG).show();
    }


}
