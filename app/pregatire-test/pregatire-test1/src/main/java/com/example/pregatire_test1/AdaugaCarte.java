package com.example.pregatire_test1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AdaugaCarte extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adauga_carte);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText etTitlu = findViewById(R.id.ilie_victor_et_titlu);
        EditText etAn = findViewById(R.id.ilie_victor_et_an);
        Spinner spEditura = findViewById(R.id.ilie_victor_sp_editura);
        CheckBox cbValabila = findViewById(R.id.ilie_victor_cb_valabila);
        Button btnSalveaza = findViewById(R.id.ilie_victor_btn_salveaza);

        btnSalveaza.setOnClickListener(v -> {
            String titlu = etTitlu.getText().toString().trim();
            Integer an = Integer.parseInt(etAn.getText().toString().trim());
            String editura = spEditura.getSelectedItem().toString();
            Boolean valabila = cbValabila.isChecked();
            Carte carte = new Carte(titlu, an, editura, valabila);

            Intent it = new Intent();
            Bundle bundle = new Bundle();
            bundle.putParcelable("obiect_carte", carte);
            it.putExtras(bundle);

            setResult(RESULT_OK, it);
            finish();
        });
    }
}