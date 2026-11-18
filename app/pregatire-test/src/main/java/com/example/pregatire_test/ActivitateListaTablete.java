package com.example.pregatire_test;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ActivitateListaTablete extends AppCompatActivity {

    private ArrayList<Telefon> telefoanePrimite;
    private TelefonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_activitate_lista_telefoane);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView listViewTablete = findViewById(R.id.lvTelefoane);

        if (getIntent().hasExtra("lista_tablete")) {
            telefoanePrimite = getIntent().getParcelableArrayListExtra("lista_telefoane");
        } else {
            telefoanePrimite = new ArrayList<>();
        }

        adapter = new TelefonAdapter(this, telefoanePrimite);
        listViewTablete.setAdapter(adapter);


    }
}