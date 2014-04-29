package com.example.contactlist.homefinder.app;

import android.app.Activity;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class Mapa extends Activity {

    //Atributos
    private LatLng HAMBURG;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapa);
        HAMBURG = new LatLng(Double.parseDouble(getIntent().getStringExtra("latitud")), Double.parseDouble(getIntent().getStringExtra("longitud")));
        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        Marker hamburg = map.addMarker(new MarkerOptions().position(HAMBURG)
                .title("Destino"));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(HAMBURG, 15));
        map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
    }
}