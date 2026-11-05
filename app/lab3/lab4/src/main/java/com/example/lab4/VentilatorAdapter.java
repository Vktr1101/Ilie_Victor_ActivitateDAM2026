package com.example.lab4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class VentilatorAdapter extends BaseAdapter {
    private Context ctx;
    private ArrayList<Ventilator> ventilatorList;

    public VentilatorAdapter(Context ctx, ArrayList<Ventilator> ventilatorList) {
        this.ctx = ctx;
        this.ventilatorList = ventilatorList;
    }


    @Override
    public int getCount() {
        return ventilatorList.size();
    }

    @Override
    public Object getItem(int position) {
        return ventilatorList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View v = inflater.inflate(R.layout.item_layout, parent,false);

        TextView tvMarca = v.findViewById(R.id.idMarca);
        TextView tvViteza = v.findViewById(R.id.idViteza);
        TextView tvPutere = v.findViewById(R.id.idPutere);
        TextView tvOscilatie = v.findViewById(R.id.idOscilatie);
        TextView tvPornit = v.findViewById(R.id.idPornit);

        Ventilator ventilator = (Ventilator) getItem(position);
        tvMarca.setText("Marca: " + ventilator.getMarca());
        tvViteza.setText("Viteza: " + ventilator.getViteza());
        tvPutere.setText("Putere consumata: " + ventilator.getPutereConsumata());
        tvOscilatie.setText(ventilator.getAreOscilatie() ? "Are oscilatie" : "Nu are oscilatie");
        tvPornit.setText(ventilator.getEstePornit() ? "Este pornit" : "Nu este pornit");

        return v;
    }
}
