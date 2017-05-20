package com.hadly.classloader;

/**
 * Created by hadly on 2017/2/15.
 */
public class Person {
    private String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public String getName(String test) {
        System.out.println("person name = " + test);
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';

    }
}
