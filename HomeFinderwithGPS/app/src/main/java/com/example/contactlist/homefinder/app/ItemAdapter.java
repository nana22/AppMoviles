package com.example.contactlist.homefinder.app;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

import logicaDeNegocios.Propiedad;


public class ItemAdapter extends BaseAdapter {
    protected Activity activity;
    protected List<Propiedad> items;


    public ItemAdapter(Activity activity, List<Propiedad> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }


    @Override
    public Object getItem(int position) {
        return items.get(position);
    }


    @Override
    public long getItemId(int position) {
        long a = 0;
        return a;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vi = inflater.inflate(R.layout.list_item, null);
        }

        Propiedad item = items.get(position);

        TextView nombre = (TextView) vi.findViewById(R.id.nombre);
        nombre.setText(item.getTipo());

        TextView tipo = (TextView) vi.findViewById(R.id.tipo);
        tipo.setText("Precio: " + item.getPrecio());

        return vi;
    }
}