package com.example.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AdaugaVentilator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adauga_ventilator);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void culegeDate(View view) {
        EditText etMarca = findViewById(R.id.marca_ET);
        EditText etViteza = findViewById(R.id.viteza_ET);
        EditText etPutereConsumata = findViewById(R.id.putereConsumata_ET);
        CheckBox cbOscilatie = findViewById(R.id.areOscilatie_CB);
        CheckBox cbPornit = findViewById(R.id.estePornit_CB);

        String marca = etMarca.getText().toString();

        String vitezaString = etViteza.getText().toString();
        Integer viteza = Integer.parseInt(vitezaString);

        String putereConsumataString = etPutereConsumata.getText().toString();
        Double putereConsumata = Double.parseDouble(putereConsumataString);

        Boolean oscilatie = cbOscilatie.isChecked();
        Boolean estePornit = cbPornit.isChecked();

        Ventilator ventilator = new Ventilator(marca, viteza, putereConsumata, oscilatie, estePornit);

        Intent intentResult = new Intent();
        Bundle bundle = new Bundle();
        bundle.putParcelable("obiect_ventilator", ventilator);
        intentResult.putExtras(bundle);

        setResult(RESULT_OK, intentResult);
        finish();
    }
}