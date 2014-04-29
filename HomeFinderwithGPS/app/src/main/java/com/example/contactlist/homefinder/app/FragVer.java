package com.example.contactlist.homefinder.app;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import logicaDeNegocios.Propiedad;


public class FragVer extends Fragment implements AdapterView.OnItemClickListener {
    ListView listView;
    Communicator communicator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_frag__ver,container,false);
        listView= (ListView) view.findViewById(R.id.lvVer);
        listView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        communicator.respond(i);
    }

    public void setAdapter(ItemAdapter adapter){
        listView.setAdapter(adapter);
    }

    public void setCommunicator(Communicator communicator){
        this.communicator=communicator;
    }


    public interface Communicator{
        public void respond(int index);
    }
}
