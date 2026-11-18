package com.example.pregatire_test1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Carte> carti = new ArrayList<>();

    private final ActivityResultLauncher<Intent> launcherAdaugare = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Bundle bundle = result.getData().getExtras();
                    if (bundle != null) {
                        Carte carte = bundle.getParcelable("obiect_carte");
                        if (carte != null) {
                            carti.add(carte);
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

        Button btnAdauga = findViewById(R.id.ilie_victor_btn_adauga);
        Button btnLista = findViewById(R.id.ilie_victor_btn_lista);

        btnAdauga.setOnClickListener(v -> {
            Intent it = new Intent(MainActivity.this, AdaugaCarte.class);
            launcherAdaugare.launch(it);
        });

        btnLista.setOnClickListener(v -> {
            Intent it = new Intent(MainActivity.this, VizioneazaLista.class);
            it.putParcelableArrayListExtra("lista_carti", carti);
            startActivity(it);
        });
    }
}