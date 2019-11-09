package com.example.et.entnty;

import android.os.Parcel;
import android.os.Parcelable;

public class Ass implements Parcelable {


    /**
     * newnumber : 0
     * id : 8
     * number : 993890.0000000000
     * name : USDT
     * cny : 993890
     */

    private int newnumber;
    private int id;
    private String number;
    private String name;
    private double cny;

    public int getNewnumber() {
        return newnumber;
    }

    public void setNewnumber(int newnumber) {
        this.newnumber = newnumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCny() {
        return cny;
    }

    public void setCny(double cny) {
        this.cny = cny;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.newnumber);
        dest.writeInt(this.id);
        dest.writeString(this.number);
        dest.writeString(this.name);
        dest.writeDouble(this.cny);
    }

    public Ass() {
    }

    protected Ass(Parcel in) {
        this.newnumber = in.readInt();
        this.id = in.readInt();
        this.number = in.readString();
        this.name = in.readString();
        this.cny = in.readDouble();
    }

    public static final Parcelable.Creator<Ass> CREATOR = new Parcelable.Creator<Ass>() {
        @Override
        public Ass createFromParcel(Parcel source) {
            return new Ass(source);
        }

        @Override
        public Ass[] newArray(int size) {
            return new Ass[size];
        }
    };
}
