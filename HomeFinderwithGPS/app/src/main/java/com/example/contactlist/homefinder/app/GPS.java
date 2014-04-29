package com.example.contactlist.homefinder.app;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;


public class GPS implements LocationListener {

    private String latitud = "";
    private String longitud = "";
    private Context ctx;
    private LocationManager locationManager;
    private String provider;

    public GPS(Context context) {
        ctx = context;
        getLocation();
    }

    public void getLocation() {
        locationManager=(LocationManager)ctx.getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        provider=locationManager.getBestProvider(criteria,false);
        Location location = locationManager.getLastKnownLocation(provider);
        if(location!=null) {
            onLocationChanged(location);
        }
        locationManager.requestLocationUpdates(provider, 400, 1, this);
    }

    public String getLatitud() {
        return latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    @Override
    public void onLocationChanged(Location location) {
        latitud = String.valueOf(location.getLatitude());
        longitud = String.valueOf(location.getLongitude());
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }
}