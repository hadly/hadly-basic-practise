package com.hadly.introspector;

/**
 * Created by hadly on 2017/6/15.
 * NOTE：和Student的区别是，age的名字是age111，为了测试即便名字不一样，只要getter方法一致就能拷贝成功
 */
public class Student1 {
    private String name;
    private int age111;
    private Address address;

    @Override
    public String toString() {
        return "Student [name=" + name + ", age=" + age111 + ", address="
                + address + "]";
    }

    public Student1() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age111;
    }

    public void setAge(int age111) {
        this.age111 = age111;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Student1(String name, int age111, Address address) {
        this.name = name;
        this.age111 = age111;
        this.address = address;
    }
}

