package com.example.lab8;

import android.graphics.Bitmap;

public class MasinaOnline {
    private String descriere;
    private Bitmap imagine;
    private String urlDetalii;

    public MasinaOnline(String descriere, Bitmap imagine, String urlDetalii) {
        this.descriere = descriere;
        this.imagine = imagine;
        this.urlDetalii = urlDetalii;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public Bitmap getImagine() {
        return imagine;
    }

    public void setImagine(Bitmap imagine) {
        this.imagine = imagine;
    }

    public String getUrlDetalii() {
        return urlDetalii;
    }

    public void setUrlDetalii(String urlDetalii) {
        this.urlDetalii = urlDetalii;
    }
}