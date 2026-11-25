package com.example.lab8_redo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class VentilatorAdapter extends BaseAdapter {
    private Context ctx;
    private ArrayList<ImaginiVentilator> ventilatorList;

    public VentilatorAdapter(Context ctx, ArrayList<ImaginiVentilator> ventilatorList) {
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
        View v = inflater.inflate(R.layout.item_ventilator, parent,false);

        ImageView ivVentilator = v.findViewById(R.id.ivVentilator);
        TextView tvDescriere = v.findViewById(R.id.tvDescriereVentilator);

        ImaginiVentilator ventilator = ventilatorList.get(position);
        ivVentilator.setImageBitmap(ventilator.getImagine());
        tvDescriere.setText(ventilator.getDescriere());

        return v;
    }
}
