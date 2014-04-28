package com.example.contactlist.homefinder.app;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.TextView;


public class GPS extends Service implements LocationListener {
    private Context ctx;
    private double latitud;
    private double longitud;
    private Location location;
    private boolean gpsActivo;
    private TextView texto;
    private LocationManager locationManager;

    public GPS(Context c){
        super();
        this.ctx = c;
        getLocation();
    }

    public void getLocation() {
        try{
            locationManager = (LocationManager)this.ctx.getSystemService(LOCATION_SERVICE);
            gpsActivo = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        }
        catch (Exception e){ }
        if (gpsActivo){
            latitud = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER).getLatitude();
            longitud = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER).getLongitude();
        }
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double pLatitud) {
        latitud = pLatitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double pLongitud) {
        longitud = pLongitud;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {
        return;
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {}

    @Override
    public void onProviderEnabled(String s) {
        return;
    }

    @Override
    public void onProviderDisabled(String s) {
        return;
    }
}
