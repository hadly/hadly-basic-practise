package com.hadly.jmx;

/**
 * Created by hadly on 2017/6/15.
 */
/*
   * 该类名称必须与实现的接口的前缀保持一致（即MBean前面的名称
   */
public class Hello implements HelloMBean {
    private String name;

    private String age;

    public void getTelephone() {
        System.out.println("get Telephone");
    }

    public void helloWorld() {
        System.out.println("hello world");
    }

    public void helloWorld(String str) {
        System.out.println("helloWorld:" + str);
    }

    public String getName() {
        System.out.println("get name " + name);
        return name;
    }

    public void setName(String name) {
        System.out.println("set name " + name);
        this.name = name;
    }

    public String getAge() {
        System.out.println("get age " + age);
        return age;
    }

    public void setAge(String age) {
        System.out.println("set age " + age);
        this.age = age;
    }

    //NOTE：有返回值的方法，貌似在jconsole上显示不出来
    public void getAge1(){
        System.out.println("get age1 " + age);
    }

    public void printHello() {
        System.out.println("hello world, " + name);
    }

    public void printHello(String myname) {
        System.out.println("hello " + myname);
    }
}
