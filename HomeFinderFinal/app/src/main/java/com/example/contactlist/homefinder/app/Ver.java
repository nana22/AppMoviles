package com.example.contactlist.homefinder.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import java.util.ArrayList;
import logicaDeNegocios.Publicacion;


public class Ver extends Activity implements FragVer.Communicator{

    //Atributos
    private ArrayList<String> bodyPropiedades;
    private ArrayList<String> bodyDueños;
    private Publicacion publicacion;
    private FragVer fragVer;
    private Detalles detalles;
    private ProgressDialog ringProgressDialog;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);
        if( Build.VERSION.SDK_INT >= 9){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        ringProgressDialog = ProgressDialog.show(Ver.this, "Por favor, espere ...", "Cargando datos...", true);
        publicacion = new Publicacion();
        ringProgressDialog.setCancelable(true);
        Thread thread = new Thread(new cargarPropiedadesThread());
        thread.start();
    }


    private void setList() {
        cargarLista();
        manager = getFragmentManager();
        ItemAdapter adapter = new ItemAdapter(this, publicacion.getPropiedades());
        fragVer = (FragVer) manager.findFragmentById(R.id.fragment);
        fragVer.setAdapter(adapter);
        fragVer.setCommunicator(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ver, menu);
        return true;
    }

    //Botones del menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.contrato) {
            publicacion.getPropiedadesByContrato();
            setList();
            return true;
        }
        if (i == R.id.tipo) {
            publicacion.getPropiedadesByTipo();
            setList();
            return true;
        }
        if (i == R.id.precio) {
            publicacion.getPropiedadesByPrecio();
            setList();
            return true;
        }
        if (i == R.id.provincia) {
            publicacion.getPropiedadesByProvincia();
            setList();
            return true;
        }
        if (i == R.id.action_settings) {
            volverMain();
            return true;
        }
        return super.onOptionsItemSelected(item);
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
        this.finish();
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
        if(detalles !=null && detalles.isVisible()) {
            detalles.changeData(bodyPropiedades.get(index), bodyDueños.get(index));
        }
        else{
            Intent intent= new Intent(this,ACDetalles.class);
            intent.putExtra("propiedad", bodyPropiedades.get(index));
            intent.putExtra("dueño",bodyDueños.get(index));
            startActivity(intent);
        }

    }

    private class cargarPropiedadesThread implements Runnable {
        @Override
        public void run() {
            try {
                publicacion.consultarPropiedades();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setList();
                    }
                });
            } catch (Exception e) {
                mostrarErrorDialog();
            }
            ringProgressDialog.dismiss();
        }
    }
}