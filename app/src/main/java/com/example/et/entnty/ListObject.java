package com.example.et.entnty;

import android.graphics.Bitmap;

/**
 * Created by ywx on 2018/4/17.
 */

public class ListObject {
    String name;
    Bitmap image_name;
    Object object;
    public ListObject() {

    }
    public ListObject(String name, Bitmap image_name, Object object) {
        this.name = name;
        this.image_name = image_name;
        this.object = object;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getImage_name() {
        return image_name;
    }

    public void setImage_name(Bitmap image_name) {
        this.image_name = image_name;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
