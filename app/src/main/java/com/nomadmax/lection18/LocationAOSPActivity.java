package com.nomadmax.lection18;

import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

/**
 * Created by Max on 08.04.16.
 */
public class LocationAOSPActivity extends AbstractLocationActivity{

    private LocationManager mLocationManager;



    private GpsStatus.Listener mGpsStatusListener = new GpsStatus.Listener() {
        @Override
        public void onGpsStatusChanged(int event) {
            switch (event){
                case GpsStatus.GPS_EVENT_FIRST_FIX:
                    status.setBackgroundColor(ContextCompat.getColor(LocationAOSPActivity.this, android.R.color.holo_green_dark));
                    break;
                case GpsStatus.GPS_EVENT_STARTED:
                    status.setBackgroundColor(ContextCompat.getColor(LocationAOSPActivity.this, android.R.color.holo_green_light));
                    break;
                case GpsStatus.GPS_EVENT_STOPPED:
                    status.setBackgroundColor(ContextCompat.getColor(LocationAOSPActivity.this, android.R.color.holo_red_light));
                    break;
            }
        }
    };

    private LocationListener mLocationListener =  new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            updateViews(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLocationManager.addGpsStatusListener(mGpsStatusListener);
//        List<String> allProviders = mLocationManager.getAllProviders();
//        Criteria criteria = new Criteria();
//        criteria.setAccuracy(Criteria.ACCURACY_HIGH);
//        String provider = mLocationManager.getBestProvider(criteria, true);
        String provider = LocationManager.GPS_PROVIDER;
        mLocationManager.requestLocationUpdates(provider, 1000, 0, mLocationListener);
//        mLocationManager.requestLocationUpdates();
    }


    @Override
    protected void onPause() {
        super.onPause();
        mLocationManager.removeGpsStatusListener(mGpsStatusListener);
    }
}
