package com.example.lab4;

import android.os.Parcel;
import android.os.Parcelable;

public class Ventilator implements Parcelable {
    private String marca;
    private int viteza;
    private double putereConsumata;
    private boolean areOscilatie;
    private boolean estePornit;

    public Ventilator(String marca, int treaptaViteza, double putereConsumata, boolean areOscilatie, boolean estePornit) {
        this.marca = marca;
        this.viteza = treaptaViteza;
        this.putereConsumata = putereConsumata;
        this.areOscilatie = areOscilatie;
        this.estePornit = estePornit;
    }

    protected Ventilator(Parcel in) {
        marca = in.readString();
        viteza = in.readInt();
        putereConsumata = in.readDouble();
        areOscilatie = in.readByte() != 0;
        estePornit = in.readByte() != 0;
    }

    public static final Creator<Ventilator> CREATOR = new Creator<Ventilator>() {
        @Override
        public Ventilator createFromParcel(Parcel in) {
            return new Ventilator(in);
        }

        @Override
        public Ventilator[] newArray(int size) {
            return new Ventilator[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(marca);
        dest.writeInt(viteza);
        dest.writeDouble(putereConsumata);
        dest.writeByte((byte) (areOscilatie ? 1 : 0));
        dest.writeByte((byte) (estePornit ? 1 : 0));
    }

    @Override
    public String toString() {
        return "Ventilator {" +
                "Marca='" + marca + '\'' +
                ", Viteza=" + viteza +
                ", Putere=" + putereConsumata + "W" +
                ", Oscilație=" + (areOscilatie ? "Da" : "Nu") +
                ", Pornit=" + (estePornit ? "Da" : "Nu") +
                '}';
    }
}


