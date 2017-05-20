package com.hadly.serialization;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by hadly on 2017/2/21.
 */
public class Student implements Serializable {
    //这种情况不会被序列化
//    private transient String name;
    private String name;
    private char sex;
    private transient int year;
    private double gpa;

    public Student(String name, char sex, int year, double gpa) {
        this.name = name;
        this.sex = sex;
        this.year = year;
        this.gpa = gpa;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        //通过这种方式，再次将transient标注的字段进行序列化
        out.writeInt(year);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        year = in.readInt();

    }

    public String getName() {
        return name;
    }

    public char getSex() {
        return sex;
    }

    public int getYear() {
        return year;
    }

    public double getGpa() {
        return gpa;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", sex=" + sex +
                ", year=" + year +
                ", gpa=" + gpa +
                '}';
    }
}
