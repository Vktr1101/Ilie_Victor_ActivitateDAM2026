package com.example.lab8;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MasinaAdapter extends BaseAdapter {
    private Context ctx;
    private ArrayList<MasinaOnline> listaMasini;

    public MasinaAdapter(Context ctx, ArrayList<MasinaOnline> listaMasini) {
        this.ctx = ctx;
        this.listaMasini = listaMasini;
    }

    @Override
    public int getCount() {
        return listaMasini.size();
    }

    @Override
    public Object getItem(int position) {
        return listaMasini.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            v = LayoutInflater.from(ctx).inflate(R.layout.item_masina, parent, false);
        }

        ImageView ivMasina = v.findViewById(R.id.ivMasina);
        TextView tvDescriere = v.findViewById(R.id.tvDescriereMasina);

        MasinaOnline masina = listaMasini.get(position);
        ivMasina.setImageBitmap(masina.getImagine());
        tvDescriere.setText(masina.getDescriere());

        return v;
    }
}