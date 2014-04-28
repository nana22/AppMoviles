package com.example.contactlist.homefinder.app;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Detalles extends Fragment {
    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_detalles,container,false);
        textView= (TextView) view.findViewById(R.id.tvDato);
        return view;
    }

    public void changeData(String propiedad, String dueño){
        String texto=propiedad+"\n\n"+dueño;
        textView.setText(texto);
    }
}
