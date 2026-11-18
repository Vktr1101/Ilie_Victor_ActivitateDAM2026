package com.example.pregatire_test;

import android.os.Parcel;
import android.os.Parcelable;

public class Telefon implements Parcelable {
    private int idTelefon;
    private String model;
    private int spatiuMemorie;
    private boolean estePornit;

    public Telefon(int idTelefon, String model, int spatiuMemorie, boolean estePornit) {
        this.idTelefon = idTelefon;
        this.model = model;
        this.spatiuMemorie = spatiuMemorie;
        this.estePornit = estePornit;
    }


    protected Telefon(Parcel in) {
        idTelefon = in.readInt();
        model = in.readString();
        spatiuMemorie = in.readInt();
        estePornit = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idTelefon);
        dest.writeString(model);
        dest.writeInt(spatiuMemorie);
        dest.writeByte((byte) (estePornit ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Telefon> CREATOR = new Creator<Telefon>() {
        @Override
        public Telefon createFromParcel(Parcel in) {
            return new Telefon(in);
        }

        @Override
        public Telefon[] newArray(int size) {
            return new Telefon[size];
        }
    };

    public int getIdTableta() {
        return idTelefon;
    }

    public void setIdTableta(int idTelefon) {
        this.idTelefon = idTelefon;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSpatiuMemorie() {
        return spatiuMemorie;
    }

    public void setSpatiuMemorie(int spatiuMemorie) {
        this.spatiuMemorie = spatiuMemorie;
    }

    public boolean isEstePornit() {
        return estePornit;
    }

    public void setEstePornit(boolean estePornit) {
        this.estePornit = estePornit;
    }
}
