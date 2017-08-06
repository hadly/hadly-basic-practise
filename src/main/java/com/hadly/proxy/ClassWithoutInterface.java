package com.hadly.proxy;

/**
 * Created by mi-123 on 2017/5/21.
 */
public class ClassWithoutInterface {
    private String name;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "ClassWithoutInterface{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
