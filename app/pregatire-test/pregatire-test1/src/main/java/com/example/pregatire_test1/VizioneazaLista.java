package com.example.pregatire_test1;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class VizioneazaLista extends AppCompatActivity {
    private ArrayList<Carte> cartiPrimite;
    private CarteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vizioneaza_lista);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView lvCarti = findViewById(R.id.ilie_victor_lv_carti);

        if (getIntent().hasExtra("lista_carti")) {
            cartiPrimite = getIntent().getParcelableArrayListExtra("lista_carti");
        } else {
            cartiPrimite = new ArrayList<>();
        }

        adapter = new CarteAdapter(this, cartiPrimite);
        lvCarti.setAdapter(adapter);

        lvCarti.setOnItemClickListener((parent, view, position, id) -> {
            Carte carteSelectata = cartiPrimite.get(position);
            Toast.makeText(this, "Ai selectat cartea: " + carteSelectata.getTitlu(), Toast.LENGTH_SHORT).show();
        });
    }
}