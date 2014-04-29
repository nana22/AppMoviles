package com.example.contactlist.homefinder.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class ACDetalles extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acdetalles);

        Intent intent=getIntent();
        String dueño=intent.getStringExtra("dueño");
        String propiedad=intent.getStringExtra("propiedad");
        Detalles fragment2= (Detalles) getFragmentManager().findFragmentById(R.id.fragmentDetalles);
        if(fragment2!=null) {
            fragment2.changeData(propiedad,dueño);
        }
    }
}
