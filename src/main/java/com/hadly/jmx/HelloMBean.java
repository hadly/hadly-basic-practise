package com.hadly.jmx;

/**
 * Created by hadly on 2017/6/15.
 */
public interface HelloMBean {
    public String getName();

    public void setName(String name);

    public String getAge();

    public void setAge(String age);

    public void helloWorld();

    public void helloWorld(String str);

    public void getTelephone();

    public void getAge1();

    void printHello();

    void printHello(String name);
}