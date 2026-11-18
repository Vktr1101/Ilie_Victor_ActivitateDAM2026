package com.example.lab8;

import android.os.Parcel;
import android.os.Parcelable;

public class Masina implements Parcelable {
    private String marca;
    private int an;
    private double pret;
    private int putere;
    private boolean estePornita;

    public Masina(String marca, int an, double pret, int putere, boolean estePornita) {
        this.marca = marca;
        this.an = an;
        this.pret = pret;
        this.putere = putere;
        this.estePornita = estePornita;
    }

    protected Masina(Parcel in) {
        marca = in.readString();
        an = in.readInt();
        pret = in.readDouble();
        putere = in.readInt();
        estePornita = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(marca);
        dest.writeInt(an);
        dest.writeDouble(pret);
        dest.writeInt(putere);
        dest.writeByte((byte) (estePornita ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Masina> CREATOR = new Creator<Masina>() {
        @Override
        public Masina createFromParcel(Parcel in) {
            return new Masina(in);
        }

        @Override
        public Masina[] newArray(int size) {
            return new Masina[size];
        }
    };

    public boolean isEstePornita() {
        return estePornita;
    }

    public void setEstePornita(boolean estePornita) {
        this.estePornita = estePornita;
    }

    public int getPutere() {
        return putere;
    }

    public void setPutere(int putere) {
        this.putere = putere;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public int getAn() {
        return an;
    }

    public void setAn(int an) {
        this.an = an;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
}
