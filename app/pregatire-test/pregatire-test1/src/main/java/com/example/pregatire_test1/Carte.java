package com.example.pregatire_test1;

import android.os.Parcel;
import android.os.Parcelable;

public class Carte implements Parcelable {
    private String titlu;
    private int an;
    private String editura;
    private boolean valabila;

    public Carte(String titlu, int an, String editura, boolean valabila) {
        this.titlu = titlu;
        this.an = an;
        this.editura = editura;
        this.valabila = valabila;
    }

    protected Carte(Parcel in) {
        titlu = in.readString();
        an = in.readInt();
        editura = in.readString();
        valabila = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(titlu);
        dest.writeInt(an);
        dest.writeString(editura);
        dest.writeByte((byte) (valabila ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Carte> CREATOR = new Creator<Carte>() {
        @Override
        public Carte createFromParcel(Parcel in) {
            return new Carte(in);
        }

        @Override
        public Carte[] newArray(int size) {
            return new Carte[size];
        }
    };

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public int getAn() {
        return an;
    }

    public void setAn(int an) {
        this.an = an;
    }

    public String getEditura() {
        return editura;
    }

    public void setEditura(String editura) {
        this.editura = editura;
    }

    public boolean isValabila() {
        return valabila;
    }

    public void setValabila(boolean valabila) {
        this.valabila = valabila;
    }
}
