package com.example.myapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myapplication.models.LugarTuristico;
import com.example.myapplication.R;

public class AdaptadorLugar extends ArrayAdapter<LugarTuristico> {

    public AdaptadorLugar(Context context, LugarTuristico[] datos) {
        super(context, R.layout.lyitemlugar, datos);
    }


    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.lyitemlugar, null);

        TextView lblTitulo = (TextView)item.findViewById(R.id.lblNombreLugar);
        lblTitulo.setText(getItem(position).getNombre());

        TextView lblSubtitulo = (TextView)item.findViewById(R.id.lblInfoLugar);
        lblSubtitulo.setText(getItem(position).getInformacion());

        return(item);
    }

}
