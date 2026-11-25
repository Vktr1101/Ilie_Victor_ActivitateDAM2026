package com.example.lab8_redo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VizioneazaVentilatoare extends AppCompatActivity {

    private ArrayList<ImaginiVentilator> listaVentilatoare = new ArrayList<>();
    private List<Bitmap> imagini = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vizioneaza_ventilatoare);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView lvVentilatoare = findViewById(R.id.lvVentilatoare);

        List<String> linkuriImagini = new ArrayList<>();
        linkuriImagini.add("https://commons.wikimedia.org/wiki/Special:FilePath/Ceiling_fan.jpg?width=640");
        linkuriImagini.add("https://commons.wikimedia.org/wiki/Special:FilePath/Dyson_Air_Multiplier.jpg?width=640");
        linkuriImagini.add("https://commons.wikimedia.org/wiki/Special:FilePath/80mm_fan.jpg?width=640");
        linkuriImagini.add("https://commons.wikimedia.org/wiki/Special:FilePath/Box_fan.jpg?width=640");
        linkuriImagini.add("https://commons.wikimedia.org/wiki/Special:FilePath/Exhaust_fan.jpg?width=640");

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            for (String link : linkuriImagini) {
                HttpURLConnection con = null;
                try {
                    URL url = new URL(link);
                    con = (HttpURLConnection) url.openConnection();
                    con.setRequestProperty("User-Agent", "Lab8AndroidApp/1.0 (student@ase.ro)");
                    InputStream is = con.getInputStream();
                    Bitmap bmp = BitmapFactory.decodeStream(is);
                    imagini.add(bmp);
                } catch (Exception e) {
                    e.printStackTrace();
                    imagini.add(null);
                } finally {
                    if (con != null) {
                        con.disconnect();
                    }
                }
            }

            handler.post(() -> {
                listaVentilatoare = new ArrayList<>();
                listaVentilatoare.add(new ImaginiVentilator("Ventilator de tavan - Racire uniforma", imagini.get(0), "https://en.wikipedia.org/wiki/Ceiling_fan"));
                listaVentilatoare.add(new ImaginiVentilator("Ventilator fara palete Dyson - Flux continuu", imagini.get(1), "https://en.wikipedia.org/wiki/Bladeless_fan"));
                listaVentilatoare.add(new ImaginiVentilator("Ventilator PC 80mm - Racire componente", imagini.get(2), "https://en.wikipedia.org/wiki/Computer_fan"));
                listaVentilatoare.add(new ImaginiVentilator("Ventilator de podea tip Box - Debit mare", imagini.get(3), "https://en.wikipedia.org/wiki/Box_fan"));
                listaVentilatoare.add(new ImaginiVentilator("Ventilator de evacuare - Ventilatie industriala", imagini.get(4), "https://en.wikipedia.org/wiki/Mechanical_fan"));

                VentilatorAdapter adapter = new VentilatorAdapter(getApplicationContext(), listaVentilatoare);
                lvVentilatoare.setAdapter(adapter);
            });
        });

        lvVentilatoare.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent it = new Intent(getApplicationContext(), WebViewActivity.class);
            it.putExtra("link", listaVentilatoare.get(i).getLinkDetalii());
            startActivity(it);
        });
    }
}