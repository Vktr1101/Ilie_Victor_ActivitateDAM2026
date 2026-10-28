package com.example.lab4;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ListaVentilatoareActivity extends AppCompatActivity {

    private ArrayList<Ventilator> listaPrimita;
    private ArrayAdapter<Ventilator> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_ventilatoare);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView listView = findViewById(R.id.listViewVentilatoare);

        if (getIntent().hasExtra("lista_mea")) {
            listaPrimita = getIntent().getParcelableArrayListExtra("lista_mea");
        } else {
            listaPrimita = new ArrayList<>();
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaPrimita);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Ventilator ventilatorSelectat = listaPrimita.get(position);
            Toast.makeText(this, ventilatorSelectat.toString(), Toast.LENGTH_SHORT).show();
        });

        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            listaPrimita.remove(position);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Obiect sters!", Toast.LENGTH_SHORT).show();
            return true;
        });
    }
}