package com.example.android.sunshine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private static final String FORECAST_SHARE_HASHTAG = " #SunshineApp";
    TextView displayWeather;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        displayWeather = findViewById(R.id.weatherDetails);

        Intent intent = getIntent();
        if(intent.hasExtra("data")){
            String weatherData = intent.getStringExtra("data");
            displayWeather.setText(weatherData);
        }
    }
}