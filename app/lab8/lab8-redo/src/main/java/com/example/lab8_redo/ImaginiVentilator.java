package com.example.lab8_redo;

import android.graphics.Bitmap;

public class ImaginiVentilator {
    private String descriere;
    private Bitmap imagine;
    private String linkDetalii;

    public ImaginiVentilator(String descriere, Bitmap imagine, String linkDetalii) {
        this.descriere = descriere;
        this.imagine = imagine;
        this.linkDetalii = linkDetalii;
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

    public String getLinkDetalii() {
        return linkDetalii;
    }

    public void setLinkDetalii(String linkDetalii) {
        this.linkDetalii = linkDetalii;
    }
}
