package com.example.et.entnty;

public class Record {

    /**
     * time : 2019-10-31 11:47:58
     * number : -50.0000000000
     * type : 转出
     * types : 已到账
     * hash : null
     * tophone : 15503591218
     */

    private String time;
    private String number;
    private String type;
    private String types;
    private Object hash;
    private String tophone;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public Object getHash() {
        return hash;
    }

    public void setHash(Object hash) {
        this.hash = hash;
    }

    public String getTophone() {
        return tophone;
    }

    public void setTophone(String tophone) {
        this.tophone = tophone;
    }
}
