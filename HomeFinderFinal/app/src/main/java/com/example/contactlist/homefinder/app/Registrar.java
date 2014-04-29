package com.example.contactlist.homefinder.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import logicaDeNegocios.Persona;
import logicaDeNegocios.Propiedad;
import logicaDeNegocios.Publicacion;


public class Registrar extends Activity implements CaractGenerales.Comunicador {

    //Atributos
    private String nombre, apellido, facebook, telefono,celular,tipo, contrato, precio, general, estructura, servicios, amueblado, provincia, ciudad, señal1, señal2, latitud, longitud;
    private Publicacion publicacion;
    private FragmentManager manager;
    private CaractGenerales frag_caractGenerales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        if( Build.VERSION.SDK_INT >= 9){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        try {
            publicacion = new Publicacion();
            publicacion.consultarDueños();
            manager=getFragmentManager();
            frag_caractGenerales= (CaractGenerales) manager.findFragmentById(R.id.fragmentGeneral);
            frag_caractGenerales.setComunicador(this);
        } catch (Exception e){
            mostrarErrorDialog();
        }
    }

    public void Registrar() {
        try {
            List<String> caracteristicas = new ArrayList<String>();
            caracteristicas.add(general);
            caracteristicas.add(estructura);
            caracteristicas.add(amueblado);
            caracteristicas.add(servicios);
            Propiedad propiedad = new Propiedad(tipo, contrato, precio, provincia, ciudad, latitud, longitud, señal1, señal2, caracteristicas, facebook);
            Persona persona = new Persona(nombre, apellido, "", telefono, celular, facebook);
            System.out.println(persona.toString());
            publicacion.submitPersona(persona);
            publicacion.submitPropiedad(propiedad);
            Toast.makeText(getBaseContext(), "Propiedad registrada", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), "Propiedad registrada", Toast.LENGTH_SHORT).show();
            Intent nuevaV = new Intent(this, MainActivity.class);
            startActivity(nuevaV);
            this.finish();
        }
    }

    @Override
    public void respondSiguiente(String nombre, String apellido, String face, String tel, String cel, String type) {
        this.nombre=nombre;this.apellido=apellido;this.facebook=face;
        this.telefono=tel;
        this.celular=cel;
        this.tipo=type;
        CaractPrincipales caractPrincipales= new CaractPrincipales();
        FragmentTransaction transaction= manager.beginTransaction();
        caractPrincipales.setComunicador(this);
        transaction.replace(R.id.registrargroup,caractPrincipales,"Principales");
        transaction.addToBackStack("Siguiente");
        transaction.commit();
    }

    @Override
    public void respondSiguiente2(String precio, String general, String estructura, String amueblado, String servicios, String contrato) {
        this.precio=precio; this.general=general; this.estructura=estructura;
        this.amueblado=amueblado;
        this.servicios=servicios;
        this.contrato=contrato;
        CaractSecundarias caractSecundarias=new CaractSecundarias();
        FragmentTransaction transaction= manager.beginTransaction();
        caractSecundarias.setComunicador(this);
        transaction.replace(R.id.registrargroup,caractSecundarias,"Secundarias");
        transaction.addToBackStack("Siguiente2");
        transaction.commit();
    }

    @Override
    public void respondRegistrar(String provincia, String ciudad, String direccionexacta, String señas, String latitud, String longitud) {
        this.provincia=provincia;
        this.ciudad=ciudad;
        this.señal1=direccionexacta;
        this.señal2=señas;
        this.latitud = latitud;
        this.longitud = longitud;
        Registrar();
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
}