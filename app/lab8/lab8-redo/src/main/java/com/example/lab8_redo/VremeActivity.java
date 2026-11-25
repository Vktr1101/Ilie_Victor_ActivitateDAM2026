package com.example.lab8_redo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VremeActivity extends AppCompatActivity {

    private static final String API_KEY = "secret";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vreme);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText etOras = findViewById(R.id.etOras);
        Button btnCauta = findViewById(R.id.btnCauta);
        TextView tvRezultat = findViewById(R.id.tvRezultat);
        Spinner spnZile = findViewById(R.id.spnZile);

        String[] optiuniZile = {"1 zi", "5 zile"};
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                optiuniZile
        );
        spnZile.setAdapter(adapterSpinner);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        btnCauta.setOnClickListener(v -> {
            String oras = etOras.getText().toString().trim();
            if (oras.isEmpty()) {
                Toast.makeText(this, "Introdu un oras!", Toast.LENGTH_SHORT).show();
                return;
            }

            int indexSelectat = spnZile.getSelectedItemPosition();
            String metodaForecast = (indexSelectat == 0) ? "1day" : "5day";

            executor.execute(() -> {
                HttpURLConnection conn = null;
                HttpURLConnection connVreme = null;
                try {
                    String orasCodificat = URLEncoder.encode(oras, "UTF-8");
                    String link = "https://dataservice.accuweather.com/locations/v1/cities/search?apikey=" + API_KEY + "&q=" + orasCodificat;

                    URL url = new URL(link);
                    conn = (HttpURLConnection) url.openConnection();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder builder = new StringBuilder();
                    String linie;
                    while ((linie = reader.readLine()) != null) {
                        builder.append(linie);
                    }
                    reader.close();

                    JSONArray vector = new JSONArray(builder.toString());
                    JSONObject obiect = vector.getJSONObject(0);
                    String cheieOras = obiect.getString("Key");

//                    handler.post(() -> tvRezultat.setText("Cod oras (" + oras + "): " + cheieOras));

//                    String linkVreme = "https://dataservice.accuweather.com/forecasts/v1/daily/1day/" + cheieOras + "?apikey=" + API_KEY + "&metric=true";
                    String linkVreme = "https://dataservice.accuweather.com/forecasts/v1/daily/" + metodaForecast + "/" + cheieOras + "?apikey=" + API_KEY + "&metric=true";
                    URL urlVreme = new URL(linkVreme);
                    connVreme = (HttpURLConnection) urlVreme.openConnection();
                    BufferedReader readerVreme = new BufferedReader(new InputStreamReader(connVreme.getInputStream()));
                    StringBuilder builderVreme = new StringBuilder();
                    String linieVreme;
                    while ((linieVreme = readerVreme.readLine()) != null) {
                        builderVreme.append(linieVreme);
                    }
                    readerVreme.close();

                    JSONObject radacina = new JSONObject(builderVreme.toString());
                    JSONArray dailyForecasts = radacina.getJSONArray("DailyForecasts");
//                    JSONObject zi = dailyForecasts.getJSONObject(0);
//                    JSONObject temperature = zi.getJSONObject("Temperature");
//
//                    double tempMin = temperature.getJSONObject("Minimum").getDouble("Value");
//                    double tempMax = temperature.getJSONObject("Maximum").getDouble("Value");
//                    String unitate = temperature.getJSONObject("Maximum").getString("Unit");
//
//                    handler.post(() -> tvRezultat.setText(
//                            "Oras: " + oras + " (Key: " + cheieOras + ")\n" +
//                                    "Temperatura minima: " + tempMin + " " + unitate + "\n" +
//                                    "Temperatura maxima: " + tempMax + " " + unitate
//                    ));

                    StringBuilder rezultatFinal = new StringBuilder();
                    rezultatFinal.append("Oras: ").append(oras).append(" (Key: ").append(cheieOras).append(")\n\n");

                    for (int i = 0; i < dailyForecasts.length(); i++) {
                        JSONObject zi = dailyForecasts.getJSONObject(i);
                        String data = zi.getString("Date").substring(0, 10);
                        JSONObject temperature = zi.getJSONObject("Temperature");

                        double tempMin = temperature.getJSONObject("Minimum").getDouble("Value");
                        double tempMax = temperature.getJSONObject("Maximum").getDouble("Value");
                        String unitate = temperature.getJSONObject("Maximum").getString("Unit");

                        rezultatFinal.append("Ziua ").append(i + 1).append(" (").append(data).append("):\n")
                                .append("  Minima: ").append(tempMin).append(" ").append(unitate).append("\n")
                                .append("  Maxima: ").append(tempMax).append(" ").append(unitate).append("\n\n");
                    }

                    String textFinal = rezultatFinal.toString();
                    handler.post(() -> tvRezultat.setText(textFinal));

                } catch (Exception e) {
                    e.printStackTrace();
                    handler.post(() -> tvRezultat.setText(e.getClass().getSimpleName() + ": " + e.getMessage()));                } finally {
                    if (conn != null) {
                        conn.disconnect();
                    }
                    if (connVreme != null) {
                        connVreme.disconnect();
                    }
                }
            });
        });
    }
}