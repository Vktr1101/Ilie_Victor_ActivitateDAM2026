package com.example.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ListaVentilatoareActivity extends AppCompatActivity {

    private ArrayList<Ventilator> listaPrimita;
    private VentilatorAdapter adapter;
//    private ArrayAdapter<Ventilator> adapter;
    private int pozitieEditata = -1;

    private final ActivityResultLauncher<Intent> launcherEditare = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Bundle bundle = result.getData().getExtras();
                    if (bundle != null) {
                        Ventilator ventilatorEditat = bundle.getParcelable("obiect_ventilator");
                        if (ventilatorEditat != null && pozitieEditata != -1) {
                            listaPrimita.set(pozitieEditata, ventilatorEditat);
                            adapter.notifyDataSetChanged();
                            Toast.makeText(this, "Obiect modificat cu succes!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
    );

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
        adapter = new VentilatorAdapter(this, listaPrimita);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            pozitieEditata = position;
            Intent intent = new Intent(ListaVentilatoareActivity.this, AdaugaVentilator.class);
            intent.putExtra("obiect_de_editat", listaPrimita.get(position));
            launcherEditare.launch(intent);
        });

        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            listaPrimita.remove(position);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Obiect sters!", Toast.LENGTH_SHORT).show();
            return true;
        });
    }
}