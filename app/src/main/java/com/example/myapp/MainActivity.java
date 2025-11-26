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
    Sensor sensorLux, sensorPressure;
    TextView tvLux, tvPressure;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        tvLux = findViewById(R.id.textView);
        tvPressure = findViewById(R.id.textView2);
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorLux = sm.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorPressure = sm.getDefaultSensor(Sensor.TYPE_PRESSURE);
        sm.registerListener(this, sensorLux, SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(this, sensorPressure, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        int sensorType = event.sensor.getType();

        if(sensorType == 6) {
            float pressure = event.values[0];
            tvPressure.setText("pressure " + pressure);
        } else {
            float lux = event.values[0];
            tvLux.setText("lux " + lux);

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}