package com.example.contactlist.homefinder.app;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

public class CaractGenerales extends Fragment implements View.OnClickListener {

    //Atributos
    EditText nombre,apellido,facebook,telefono1,celular;
    Spinner spinner;
    ImageButton button;
    Comunicador comunicador;

    //Metodos
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_caract__generales,container,false);
        nombre= (EditText) view.findViewById(R.id.etNombre);
        apellido= (EditText) view.findViewById(R.id.etApellido);
        facebook= (EditText) view.findViewById(R.id.etFacebook);
        telefono1= (EditText) view.findViewById(R.id.etTelefono1);
        celular= (EditText) view.findViewById(R.id.etTelefono2);
        button = (ImageButton) view.findViewById(R.id.btSiguiente1);
        button.setOnClickListener(this);
        spinner= (Spinner) view.findViewById(R.id.spinnerTipo);
        ArrayAdapter<CharSequence> adapter;
        adapter= ArrayAdapter.createFromResource(getActivity(),R.array.Tipo,android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        return view;
    }

    public void setComunicador(Comunicador comunicador){
        this.comunicador=comunicador;
    }
    @Override
    public void onClick(View view) {
        comunicador.respondSiguiente(nombre.getText().toString(),apellido.getText().toString(),facebook.getText().toString(),telefono1.getText().toString(),
                celular.getText().toString(),spinner.getSelectedItem().toString());

    }

    public interface Comunicador{
        public void respondSiguiente(String nombre,String apellido,String face,String tel,String cel,String type);
        public void respondSiguiente2(String precio,String general,String estructura,String amueblado,String servicios,String contrato);
        public void respondRegistrar(String provincia, String ciudad, String direccionexacta, String señas, String s, String toString);
    }

}
