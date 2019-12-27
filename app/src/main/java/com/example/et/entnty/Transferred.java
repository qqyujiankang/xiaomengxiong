package com.example.et.entnty;

public class Transferred {
    private String Transferred;
    private String name;


    public Transferred(String transferred, String name) {
        Transferred = transferred;
        this.name = name;
    }

    public String getTransferred() {
        return Transferred;
    }

    public void setTransferred(String transferred) {
        Transferred = transferred;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
