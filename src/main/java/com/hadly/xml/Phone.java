package com.hadly.xml;

/**
 * Created by hadly on 2017/5/23.
 */
public class Phone {
    private String brand;
    private int num;

    public Phone() {
    }

    public Phone(String brand, int num) {
        this.brand = brand;
        this.num = num;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
