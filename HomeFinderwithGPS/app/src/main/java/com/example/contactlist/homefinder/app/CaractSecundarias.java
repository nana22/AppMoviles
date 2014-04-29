package com.example.contactlist.homefinder.app;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


public class CaractSecundarias extends Fragment implements View.OnClickListener{
    Button button1, button2;
    EditText ciudad,direccionexacta,señas;
    TextView latitud, longitud;
    CaractGenerales.Comunicador comunicador;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_caract__secundarias,container,false);
        ciudad= (EditText) view.findViewById(R.id.etCiudad);
        direccionexacta= (EditText) view.findViewById(R.id.etDir);
        señas= (EditText) view.findViewById(R.id.etDir2);
        latitud = (TextView) view.findViewById(R.id.latitud);
        longitud = (TextView) view.findViewById(R.id.longitud);
        button1 = (Button) view.findViewById(R.id.btRegistro);
        button1.setOnClickListener(this);
        button2 = (Button) view.findViewById(R.id.btUbicacion);
        button2.setOnClickListener(this);
        return view;
    }

    public void setComunicador(CaractGenerales.Comunicador comunicador){
        this.comunicador=comunicador;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btRegistro)
            comunicador.respondRegistrar("Cartago",ciudad.getText().toString(),direccionexacta.getText().toString(),señas.getText().toString(), latitud.getText().toString(),longitud.getText().toString());
        if (view.getId() == R.id.btUbicacion) {
            try {
                GPS servicio = new GPS(getActivity().getApplicationContext());
                latitud.setText(servicio.getLatitud());
                longitud.setText(servicio.getLongitud());
            } catch (Exception e) {
                Toast.makeText(getActivity().getApplicationContext(), "Error al obtener coordenadas.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
