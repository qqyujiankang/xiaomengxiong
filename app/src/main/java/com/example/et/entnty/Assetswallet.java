package com.example.et.entnty;

import android.os.Parcel;
import android.os.Parcelable;

public class Assetswallet implements Parcelable {

    /**
     * name : USDT
     * img : https://etadmin.etac.io/glodimg/usdt.png
     * number : 0.0000000000
     */
    private String type;
    private String name;
    private String img;
    private String number;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.img);
        dest.writeString(this.number);
    }

    public Assetswallet() {
    }

    protected Assetswallet(Parcel in) {
        this.name = in.readString();
        this.img = in.readString();
        this.number = in.readString();
    }

    public static final Parcelable.Creator<Assetswallet> CREATOR = new Parcelable.Creator<Assetswallet>() {
        @Override
        public Assetswallet createFromParcel(Parcel source) {
            return new Assetswallet(source);
        }

        @Override
        public Assetswallet[] newArray(int size) {
            return new Assetswallet[size];
        }
    };
}
