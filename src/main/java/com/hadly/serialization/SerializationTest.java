package com.hadly.serialization;

import java.io.*;

/**
 * Created by hadly on 2017/2/20.
 */
public class SerializationTest {
    public static void main(String[] args) {
        Student student = new Student("hadly", 'M', 30, 3.6);
        File file = new File("d:\\test--\\student.txt");
        try {
            file.createNewFile();

            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(student);
            oos.flush();
            oos.close();
            fos.close();

            //反序列化
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Student student1 = (Student) ois.readObject();
            print(student1);
//            print(student1.getName());
//            print(student1.getSex());
//            print(student1.getYear());
//            print(student1.getGpa());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void print(Object o) {
        System.out.println(o);
    }
}
