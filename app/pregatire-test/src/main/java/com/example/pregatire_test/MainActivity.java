package com.example.pregatire_test;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Telefon> telefoane = new ArrayList<>();

    private final ActivityResultLauncher<Intent> launcherAdaugare = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Bundle bundle = result.getData().getExtras();
                    if (bundle != null) {
                        Telefon telefon = bundle.getParcelable("obiect_telefon");
                        if (telefon != null) {
                            telefoane.add(telefon);
                        }
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnAdauga = findViewById(R.id.btnAdauga);
        Button btnLista = findViewById(R.id.btnLista);

        btnAdauga.setOnClickListener(v -> {
            Intent it = new Intent(MainActivity.this, ActivitateAdaugareTelefon.class);
            launcherAdaugare.launch(it);
        });

        btnLista.setOnClickListener(v -> {
            Intent it = new Intent(MainActivity.this, ActivitateListaTablete.class);
            it.putParcelableArrayListExtra("lista_telefoane", (ArrayList<Telefon>) telefoane);
            startActivity(it);
        });
    }
}