package com.example.contactlist.homefinder.app;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

public class CaractPrincipales extends Fragment implements View.OnClickListener{

    //Atributos
    ImageButton button;
    EditText precio,general,estructura,otro,amueblado;
    CheckBox electricidad,agua,internet,cable,alimentacion,chotro;
    CaractGenerales.Comunicador comunicador;

    //Mñetodos
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_caract__principales,container,false);
        precio= (EditText) view.findViewById(R.id.etPrecio);
        general= (EditText) view.findViewById(R.id.etGeneral);
        estructura= (EditText) view.findViewById(R.id.etEstructura);
        otro= (EditText) view.findViewById(R.id.etOtro);
        amueblado= (EditText) view.findViewById(R.id.etAmueblado);
        electricidad= (CheckBox) view.findViewById(R.id.chluz);
        agua= (CheckBox) view.findViewById(R.id.chagua);
        internet= (CheckBox) view.findViewById(R.id.chinternet);
        cable= (CheckBox) view.findViewById(R.id.chcable);
        alimentacion= (CheckBox) view.findViewById(R.id.chalimentacion);
        chotro= (CheckBox) view.findViewById(R.id.chotro);
        ArrayAdapter<CharSequence> adapter;
        adapter= ArrayAdapter.createFromResource(getActivity(),R.array.Contrato,android.R.layout.simple_spinner_item);
        button= (ImageButton) view.findViewById(R.id.btSiguiente2);
        button.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View view) {
        String servicios="";
        if(electricidad.isChecked())
            servicios+="Electricidad, ";
        if (agua.isChecked())
            servicios+="Agua, ";
        if(internet.isChecked())
            servicios+="Internet, ";
        if (cable.isChecked())
            servicios+="Cable, ";
        if (alimentacion.isChecked())
            servicios+="Alimentacion";
        if (chotro.isChecked())
            servicios+=", "+otro.getText().toString();
        comunicador.respondSiguiente2(precio.getText().toString(),general.getText().toString(),estructura.getText().toString(),
                amueblado.getText().toString(),servicios,"Alquiler");
    }

    public void setComunicador(CaractGenerales.Comunicador comunicador){
        this.comunicador=comunicador;
    }
}
