package com.automatedtellermachine.android.atmproject;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by matthewkim on 12/13/17.
 */

public class HomeActivity extends PinActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);

    }
}
