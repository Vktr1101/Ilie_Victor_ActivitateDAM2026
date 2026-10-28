package com.example.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView tvRezultat;
    private ArrayList<Ventilator> ventilatorList = new ArrayList<>();

    private final ActivityResultLauncher<Intent> launcherAdaugare = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Bundle bundle = result.getData().getExtras();
                    if (bundle != null) {
                        Ventilator ventilatorPrimit = bundle.getParcelable("obiect_ventilator");
                        if (ventilatorPrimit != null) {
                            ventilatorList.add(ventilatorPrimit);
                            tvRezultat.setText(ventilatorPrimit.toString());
                            Toast.makeText(this, "Obiect primit: " + ventilatorPrimit.toString(), Toast.LENGTH_LONG).show();
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

        tvRezultat = findViewById(R.id.textView);
        Button btnDeschide = findViewById(R.id.button);
        Button btnVeziLista = findViewById(R.id.btnLista);

        btnDeschide.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AdaugaVentilator.class);
            launcherAdaugare.launch(intent);
        });

        btnVeziLista.setOnClickListener(v -> {
            Intent intentLista = new Intent(MainActivity.this, ListaVentilatoareActivity.class);
            intentLista.putParcelableArrayListExtra("lista_mea", ventilatorList);
            startActivity(intentLista);
        });
    }
}