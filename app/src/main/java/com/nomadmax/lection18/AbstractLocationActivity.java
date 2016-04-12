package com.nomadmax.lection18;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Max on 08.04.16.
 */
public class AbstractLocationActivity extends FragmentActivity {

    protected TextView tvLat, tvLng, tvAlt, tvAcc, tvSpeed;

    protected View status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);
        tvLat = (TextView) findViewById(R.id.tv_lat);
        tvLng= (TextView) findViewById(R.id.tv_lng);
        tvAlt = (TextView) findViewById(R.id.tv_alt);
        tvAcc= (TextView) findViewById(R.id.tv_acc);
        tvSpeed= (TextView) findViewById(R.id.tv_speed);
        status = findViewById(R.id.gps_status);
    }


    protected void updateViews(Location location) {
        tvLat.setText(String.format("%03.10f",location.getLatitude()));
        tvLng.setText(String.format("%03.10f",location.getLongitude()));
        if (location.hasAltitude()) {
            tvAlt.setText(String.format("%03.10f",location.getAltitude()));
        }
        if (location.hasSpeed()) {
            tvSpeed.setText(String.format("%03.10f",location.getSpeed()));
        }
        if (location.hasAccuracy()) {
            tvAcc.setText(String.format("%03.10f",location.getAccuracy()));
        }
    }
}
