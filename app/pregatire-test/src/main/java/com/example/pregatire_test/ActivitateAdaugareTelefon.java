package com.example.pregatire_test;

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

public class ActivitateAdaugareTelefon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_activitate_adaugare_telefon);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText etId = findViewById(R.id.etId);
        EditText etModel = findViewById(R.id.etModel);
        Spinner spSpatiuMemorie = findViewById(R.id.spSpatiuMemorie);
        CheckBox cbEstePornit = findViewById(R.id.cbEstePornit);
        Button btnSalveaza = findViewById(R.id.btnSalveaza);

        btnSalveaza.setOnClickListener(v -> {
            String id_string = etId.getText().toString().trim();
            Integer id = Integer.parseInt(id_string);

            String model = etModel.getText().toString().trim();

            Integer spatiuMemorie = Integer.parseInt(spSpatiuMemorie.getSelectedItem().toString());

            Boolean estePornit = cbEstePornit.isChecked();

            Telefon tableta = new Telefon(id, model, spatiuMemorie, estePornit);

            Intent it = new Intent();
            Bundle bundle = new Bundle();
            bundle.putParcelable("obiect_telefon", tableta);
            it.putExtras(bundle);

            setResult(RESULT_OK, it);
            finish();
        });
    }
}