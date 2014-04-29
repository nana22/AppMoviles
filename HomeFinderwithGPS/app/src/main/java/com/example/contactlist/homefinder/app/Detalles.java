package com.example.contactlist.homefinder.app;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Detalles extends Fragment implements View.OnClickListener {

    private TextView textView;
    private Button verMapa;
    private String latitud = "";
    private String longitud = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_detalles,container,false);
        textView= (TextView) view.findViewById(R.id.tvDato);
        verMapa = (Button) view.findViewById(R.id.btVerMapa);
        verMapa.setOnClickListener(this);
        return view;
    }

    public void changeData(String propiedad, String dueño, String latitud, String longitud){
        this.latitud = latitud;
        this.longitud = longitud;
        String texto = propiedad+"\n\n"+dueño;
        textView.setText(texto);
    }

    @Override
    public void onClick(View view) {
        if (latitud != "" && longitud != "" && longitud != null && latitud != null && latitud != "0.0" && longitud != "0.0") {
            System.out.println(latitud);
            System.out.println(longitud);
            Intent nuevaV = new Intent(getActivity(), Mapa.class);
            nuevaV.putExtra("latitud", latitud);
            nuevaV.putExtra("longitud", longitud);
            startActivity(nuevaV);
        }
        else {
            Toast.makeText(getActivity().getApplicationContext(), "No hay vista en mapa disponible.", Toast.LENGTH_SHORT).show();
        }
    }
}
