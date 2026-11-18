package com.example.pregatire_test1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CarteAdapter extends BaseAdapter {
    private Context ctx;
    private ArrayList<Carte> carti;

    public CarteAdapter(Context ctx, ArrayList<Carte> carti) {
        this.ctx = ctx;
        this.carti = carti;
    }


    @Override
    public int getCount() {
        return carti.size();
    }

    @Override
    public Object getItem(int position) {
        return carti.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View v = inflater.inflate(R.layout.carte_layout, parent, false);

        TextView tvTitlu = v.findViewById(R.id.tvTitlu);
        TextView tvAn = v.findViewById(R.id.tvAn);
        TextView tvEditura = v.findViewById(R.id.tvEditura);
        TextView tvValabila = v.findViewById(R.id.tvValabila);

        Carte carte = (Carte) getItem(position);
        tvTitlu.setText("Titlu: " + carte.getTitlu());
        tvAn.setText("An: " + carte.getAn());
        tvEditura.setText("Editura: " + carte.getEditura());
        tvValabila.setText(carte.isValabila() ? "Este valabila" : "Nu este valabila");

        return v;
    }
}
