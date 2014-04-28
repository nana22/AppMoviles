package com.example.contactlist.homefinder.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import java.util.ArrayList;
import java.util.List;

import logicaDeNegocios.Propiedad;
import logicaDeNegocios.Publicacion;


public class Ver extends Activity implements FragVer.Communicator{

    //Atributos
    private ArrayList<String> bodyPropiedades;
    private ArrayList<String> bodyDueños;
    private Publicacion publicacion;
    FragVer fragVer;
    Detalles detalles;
    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);
        if( Build.VERSION.SDK_INT >= 9){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        publicacion = new Publicacion();
        try {
            publicacion.consultarPropiedades();
            publicacion.getPropiedadesByPrecio();
            cargarLista();
            manager = getFragmentManager();
            ItemAdapter adapter = new ItemAdapter(this, publicacion.getPropiedades());
            fragVer = (FragVer) manager.findFragmentById(R.id.fragment);
            fragVer.setAdapter(adapter);
            fragVer.setCommunicator(this);
        } catch (Exception e){
            mostrarErrorDialog();
        }
    }

    //Carga la lista que muestra las propiedades
    private void cargarLista() {
        bodyPropiedades = new ArrayList<String>();
        bodyDueños = new ArrayList<String>();
        for (int i = 0; i < publicacion.getPropiedades().size(); i++) {
            bodyPropiedades.add(publicacion.getPropiedades().get(i).toString());
            bodyDueños.add(publicacion.getPropiedades().get(i).getDueño().toString());
        }
    }

    public void volverMain() {
        startActivity(new Intent(this, MainActivity.class));
    }

    //Muestra un mensaje en caso de haber error de conexión
    private void mostrarErrorDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Error de conexión")
                .setMessage("Verifique que esté conectado a una red.")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        volverMain();
                        dialog.cancel();
                        finish();
                    }
                })
                .setIcon(R.drawable.error)
                .show();
    }

    @Override
    public void respond(int index) {
        detalles = (Detalles) manager.findFragmentById(R.id.fragment2);
        if(detalles !=null && detalles.isVisible())
        {
            detalles.changeData(bodyPropiedades.get(index), bodyDueños.get(index));
        }
        else{
            Intent intent= new Intent(this,ACDetalles.class);
            intent.putExtra("propiedad",bodyPropiedades.get(index));
            intent.putExtra("dueño",bodyDueños.get(index));
            startActivity(intent);
        }

    }
}