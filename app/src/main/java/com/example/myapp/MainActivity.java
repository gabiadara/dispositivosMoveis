package com.example.myapp;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager sm;
    Sensor sensorLux;
    TextView tvLux;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        tvLux = findViewById(R.id.textView);
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorLux = sm.getDefaultSensor(Sensor.TYPE_LIGHT);
        sm.registerListener(this, sensorLux, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float lux = event.values[0];
        tvLux.setText("lux " + lux);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}