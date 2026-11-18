package com.example.lab8;

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

public class VizioneazaMasini extends AppCompatActivity {

    private ArrayList<MasinaOnline> listaMasini = new ArrayList<>();
    private List<Bitmap> imagini = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vizioneaza_masini);

        ListView lvMasini = findViewById(R.id.lvMasini);

        List<String> linkuriImagini = new ArrayList<>();
        linkuriImagini.add("https://commons.wikimedia.org/wiki/Special:FilePath/Dacia_Logan_III.jpg?width=640");
        linkuriImagini.add("https://commons.wikimedia.org/wiki/Special:FilePath/BMW_M3_Competition_(G80)_IMG_4041.jpg?width=640");
        linkuriImagini.add("https://commons.wikimedia.org/wiki/Special:FilePath/Toyota_Corolla_001.jpg?width=640");
        linkuriImagini.add("https://commons.wikimedia.org/wiki/Special:FilePath/2018_Audi_A4_Avant_S_Line_TDi_S-A_2.0_Front.jpg?width=640");
        linkuriImagini.add("https://commons.wikimedia.org/wiki/Special:FilePath/Tesla_Model_3_Front_View.jpg?width=640");

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
                listaMasini = new ArrayList<>();
                listaMasini.add(new MasinaOnline("Dacia Logan III - Sedan economic", imagini.get(0), "https://ro.wikipedia.org/wiki/Dacia_Logan"));
                listaMasini.add(new MasinaOnline("BMW M3 CS - Performanta sport", imagini.get(1), "https://en.wikipedia.org/wiki/BMW_M3"));
                listaMasini.add(new MasinaOnline("Toyota Corolla - Hibrid fiabil", imagini.get(2), "https://en.wikipedia.org/wiki/Toyota_Corolla"));
                listaMasini.add(new MasinaOnline("Audi A4 S-Line - Confort si tehnologie", imagini.get(3), "https://en.wikipedia.org/wiki/Audi_A4"));
                listaMasini.add(new MasinaOnline("Tesla Model 3 - Automobil electric", imagini.get(4), "https://en.wikipedia.org/wiki/Tesla_Model_3"));

                MasinaAdapter adapter = new MasinaAdapter(getApplicationContext(), listaMasini);
                lvMasini.setAdapter(adapter);
            });
        });

        lvMasini.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent it = new Intent(getApplicationContext(), WebViewActivity.class);
            it.putExtra("link", listaMasini.get(i).getUrlDetalii());
            startActivity(it);
        });
    }
}