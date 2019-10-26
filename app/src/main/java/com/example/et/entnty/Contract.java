package com.example.et.entnty;

/**
 * 选择币种
 */
public class Contract {

    /**
     * id : 3
     * number : 300
     * money : 18.21305633298324
     */

    private int id;
    private int number;
    private double money;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "id=" + id +
                ", number=" + number +
                ", money=" + money +
                '}';
    }
}
