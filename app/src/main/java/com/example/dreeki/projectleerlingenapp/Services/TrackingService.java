package com.example.dreeki.projectleerlingenapp.Services;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.NonNull;
import android.support.v4.BuildConfig;
import android.util.Log;

import com.example.dreeki.projectleerlingenapp.App;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.example.dreeki.projectleerlingenapp.R;


public class TrackingService extends Service implements LocationListener {

    private static final String TAG = TrackingService.class.getSimpleName();
    private static final int MY_PERMISSIONS_REQUEST_COARSE_LOCATION = 0;

    private static final int CONFIG_CACHE_EXPIRY = 600;  // 10 minutes.

    private FirebaseAuth auth;
    private FirebaseUser firebaseUser;
    private GoogleApiClient mGoogleApiClient;
    private DatabaseReference mFirebaseTransportRef;
    private Location previousLocation;
    private FirebaseRemoteConfig mFirebaseRemoteConfig;
    private PowerManager.WakeLock mWakelock;
    private App app;

    public TrackingService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        app = (App)getApplication();

        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(BuildConfig.DEBUG)
                .build();
        mFirebaseRemoteConfig.setConfigSettings(configSettings);

        authenticate();
    }

    @Override
    public void onDestroy() {
        // Stop receiving location updates.
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, TrackingService.this);
        }
        // Release the wakelock
        if (mWakelock != null) {
            mWakelock.release();
        }
        super.onDestroy();
    }

    private void authenticate() {
        auth = FirebaseAuth.getInstance();
        auth.signInAnonymously()
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in firebaseUser's information
                            Log.d("Firebase", "signInAnonymously:success");
                            firebaseUser = auth.getCurrentUser();
                            String path = getString(R.string.firebase_location) + firebaseUser.getUid();
                            mFirebaseTransportRef = FirebaseDatabase.getInstance().getReference(path);
                            app.setUserFirebaseUid(firebaseUser.getUid());
                            startLocationTracking();
                        } else {
                            // If sign in fails, display a message to the firebaseUser.
                            Log.w("Firebase", "signInAnonymously:failure", task.getException());
                        }
                    }
                });
    }

    private void fetchRemoteConfig() {
        long cacheExpiration = CONFIG_CACHE_EXPIRY;
        if (mFirebaseRemoteConfig.getInfo().getConfigSettings().isDeveloperModeEnabled()) {
            cacheExpiration = 0;
        }
        mFirebaseRemoteConfig.fetch(cacheExpiration)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.i(TAG, "Remote config fetched");
                        mFirebaseRemoteConfig.activateFetched();
                    }
                });
    }

    private GoogleApiClient.ConnectionCallbacks mLocationRequestCallback = new GoogleApiClient
            .ConnectionCallbacks() {

        @SuppressLint("MissingPermission") // permissions are checked in HomeActivity
        @Override
        public void onConnected(Bundle bundle) {
            final LocationRequest request = new LocationRequest();
            request.setInterval(mFirebaseRemoteConfig.getLong("LOCATION_REQUEST_INTERVAL"));
            request.setFastestInterval(mFirebaseRemoteConfig.getLong
                    ("LOCATION_REQUEST_INTERVAL_FASTEST"));
            request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

            FusedLocationProviderClient locationProviderClient = LocationServices.getFusedLocationProviderClient(getApplicationContext());

            locationProviderClient.requestLocationUpdates(request, new LocationCallback(){

                @Override
                public void onLocationAvailability(LocationAvailability locationAvailability){
                    Log.d(TAG, "Locatie qvqilqble");
                }

                @Override
                public void onLocationResult(LocationResult result){
                    onLocationChanged(result.getLastLocation());
                    Log.d(TAG, "Locatie changed");
                }
            }, null);

            // Hold a partial wake lock to keep CPU awake when the we're tracking location.
            PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
            mWakelock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "MyWakelockTag");
            mWakelock.acquire();
        }

        @Override
        public void onConnectionSuspended(int reason) {
            // TODO: Handle gracefully
        }
    };

    /**
     * Starts location tracking by creating a Google API client, and
     * requesting location updates.
     */
    private void startLocationTracking() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(mLocationRequestCallback)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    /**
     * Determines if the current location is approximately the same as the location
     * for a particular status. Used to check if we'll add a new status, or
     * update the most recent status of we're stationary.
     */
    private boolean locationIsAtStatus(Location location) {
        if (previousLocation == null) {
            return false;
        }

        Location locationForStatus = new Location("");
        locationForStatus.setLatitude(previousLocation.getLatitude());
        locationForStatus.setLongitude(previousLocation.getLongitude());
        float distance = location.distanceTo(locationForStatus);
        Log.d(TAG, String.format("Distance from previous location is %sm", distance));
        return distance < mFirebaseRemoteConfig.getLong("LOCATION_MIN_DISTANCE_CHANGED");
    }

    private float getBatteryLevel() {
        Intent batteryStatus = registerReceiver(null,
                new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int batteryLevel = -1;
        int batteryScale = 1;
        if (batteryStatus != null) {
            batteryLevel = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, batteryLevel);
            batteryScale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, batteryScale);
        }
        return batteryLevel / (float) batteryScale * 100;
    }

    public void onLocationChanged(Location location) {
        fetchRemoteConfig();

        Map<String, Object> currentLocation = new HashMap<>();
        currentLocation.put("lat", location.getLatitude());
        currentLocation.put("lng", location.getLongitude());
        currentLocation.put("time", new Date().getTime());
        currentLocation.put("power", getBatteryLevel());

        if (!locationIsAtStatus(location)) {
            // als we verder dan de minimum afstand van de vorige locatie zijn,
            // de nieuwe locatie pushen naar firebase
            mFirebaseTransportRef.child("current_location").setValue(currentLocation);
        }

        previousLocation = location;
    }
}
