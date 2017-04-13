package com.vophamtuananh.myopengl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    BackgroundView backgroundView;
    ForegeoundView foregeoundView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        backgroundView = (BackgroundView) findViewById(R.id.background_View);
        foregeoundView = (ForegeoundView) findViewById(R.id.foreground_View);
    }

    @Override
    protected void onResume() {
        super.onResume();
        backgroundView.onResume();
        foregeoundView.onResume();
    }

    @Override
    protected void onPause() {
        backgroundView.onPause();
        foregeoundView.onPause();
        super.onPause();
    }

}
