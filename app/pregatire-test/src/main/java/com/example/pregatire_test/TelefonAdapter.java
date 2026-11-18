package com.example.pregatire_test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TelefonAdapter extends BaseAdapter {
    private Context ctx;
    private ArrayList<Telefon> telefoane;

    public TelefonAdapter(Context ctx, ArrayList<Telefon> telefoane) {
        this.ctx = ctx;
        this.telefoane = telefoane;
    }

    @Override
    public int getCount() {
        return telefoane.size();
    }

    @Override
    public Object getItem(int position) {
        return telefoane.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View v = inflater.inflate(R.layout.item_layout, parent, false);

        TextView tvId = v.findViewById(R.id.tvId);
        TextView tvModel = v.findViewById(R.id.tvModel);
        TextView tvSpatiuMemorie = v.findViewById(R.id.tvSpatiuMemorie);
        TextView tvEstePornit = v.findViewById(R.id.tvEstePornit);

        Telefon telefon = (Telefon) getItem(position);
        tvId.setText("Id: " + telefon.getIdTableta());
        tvModel.setText("Model: " + telefon.getModel());
        tvSpatiuMemorie.setText("Spatiu memorie: " + telefon.getSpatiuMemorie());
        tvEstePornit.setText(telefon.isEstePornit() ? "Este pornita" : "Nu este pornita");

        return v;
    }
}
