package com.automatedtellermachine.android.atmproject;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by matthewkim on 12/13/17.
 */
public class PinActivity extends MainActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pin_layout);
    }

    public void pinNumber(View View) {
        String submitPin = ((Button) View).getText().toString();
            Intent nextScreen = new Intent(this, HomeActivity.class);
            startActivity(nextScreen);
    }
}