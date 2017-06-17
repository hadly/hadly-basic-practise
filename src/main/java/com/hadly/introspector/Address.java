package com.hadly.introspector;

/**
 * Created by hadly on 2017/6/15.
 */
public class Address {
    private String add;

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    @Override
    public String toString() {
        return "Address{" +
                "add='" + add + '\'' +
                '}';
    }
}
