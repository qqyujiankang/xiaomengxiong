package com.example.et.entnty;

public class NodeReturns {
    private String name;
    private String total;
    private String convertinto;

    public NodeReturns(String name, String total, String convertinto) {
        this.name = name;
        this.total = total;
        this.convertinto = convertinto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getConvertinto() {
        return convertinto;
    }

    public void setConvertinto(String convertinto) {
        this.convertinto = convertinto;
    }
}
