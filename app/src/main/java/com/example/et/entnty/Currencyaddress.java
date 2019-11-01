package com.example.et.entnty;

public class Currencyaddress {
    private String string;
    private String stringname;
    private int id;

    public Currencyaddress(String string, String stringname, int id) {
        this.string = string;
        this.stringname = stringname;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStringname() {
        return stringname;
    }

    public void setStringname(String stringname) {
        this.stringname = stringname;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
