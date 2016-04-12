package com.nomadmax.lection18;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

/**
 * Created by Max on 08.04.16.
 */
public class LocationFusedActivity extends AbstractLocationActivity  {

    private static final int MILLISECONDS_PER_SECOND = 1000;

    public static final int UPDATE_INTERVAL_IN_SECONDS = 5;
    private static final long UPDATE_INTERVAL = MILLISECONDS_PER_SECOND * UPDATE_INTERVAL_IN_SECONDS;

    private static final int FASTEST_INTERVAL_IN_SECONDS = 1;
    private static final long FASTEST_INTERVAL = MILLISECONDS_PER_SECOND * FASTEST_INTERVAL_IN_SECONDS;

    private GoogleApiClient mPlayServices;

    private LocationRequest mLocationRequest;

    private GoogleApiClient.OnConnectionFailedListener mConnectionFailedListener = new GoogleApiClient.OnConnectionFailedListener() {
        @Override
        public void onConnectionFailed(@NonNull ConnectionResult _connectionResult) {
            Toast.makeText(LocationFusedActivity.this, _connectionResult.getErrorMessage(), Toast.LENGTH_SHORT).show();
            LocationFusedActivity.this.finish();
        }
    };
    private GoogleApiClient.ConnectionCallbacks mConnectionCallbacks = new GoogleApiClient.ConnectionCallbacks() {
        @Override
        public void onConnected(@Nullable Bundle _bundle) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mPlayServices, mLocationRequest, new LocationListener() {
                @Override
                public void onLocationChanged(Location _location) {
                    updateViews(_location);
                }
            });

        }

        @Override
        public void onConnectionSuspended(int _i) {

        }
    };

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mPlayServices = new GoogleApiClient.Builder(this)
                    .enableAutoManage(this, mConnectionFailedListener)
                    .addApi(LocationServices.API)
                    .addConnectionCallbacks(mConnectionCallbacks)
//                    .useDefaultAccount()
//                    .addApi(Drive.API)
//                    .addScope(Drive.SCOPE_FILE)
//                    .addApiIfAvailable(Wearable.API)
                    .build();

        //            Location location = LocationServices.FusedLocationApi.getLastLocation(mPlayServices);

        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(UPDATE_INTERVAL)
                .setFastestInterval(FASTEST_INTERVAL);
        }
}
