package com.hadly.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Created by hadly on 2017/2/15.
 */
public class ReflectTest {
    public static void main(String[] args) throws Exception{
//        getName();
//        test();
//        initializeClass();
//        callMethod();
        callPrivateMethod();
    }

    private static void callPrivateMethod() throws Exception{
        Class clazz = Demo.class;
        //不能用getMethod()，只能获取public的方法
        Method method = clazz.getDeclaredMethod("privateMethod", int.class, String.class);
        //这个如果不设置，会提示 can not access a member of class com.hadly.reflect.Demo with modifiers "private"
        //如果JVM设置了安全限制，会抛异常
        method.setAccessible(true);
        method.invoke(clazz.newInstance(), 10, "privateStr");
    }

    //只知道类名com.hadly.reflect.Demo，想调用类里面的某个方法：
    private static void callMethod() {
        try {
            Class clazz = Class.forName("com.hadly.reflect.Demo");//等同于Demo.calss
            Method method = null;
//            method = clazz.getMethod("publicMethod");
//            method.invoke(clazz.newInstance());

            //调用类里面的公有方法；这里不能调用私有方法privateMethod，因为没有权限
            //即，调用类Demo的publicMethod1方法
            method = clazz.getDeclaredMethod("publicMethod1", int.class, String.class);
            method.invoke(clazz.newInstance(), 123, "zifuchuan");

            //下面这样调用是不行的，参数个数不匹配
//            method = clazz.getMethod("publicMethod1", int.class, String.class);
//            method.invoke(clazz.newInstance());
            //java.lang.IllegalArgumentException: wrong number of arguments
//            at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
//            at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)
//            at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
//            at java.lang.reflect.Method.invoke(Method.java:601)

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void initializeClass() {
        try {
            Class<?> clazz = Class.forName("com.hadly.reflect.Demo");

            for (Constructor constructor : clazz.getDeclaredConstructors()) {
                print("constructor=" + constructor);
                Demo demo = (Demo) constructor.newInstance("pubField", "priField");
                demo.publicField = "demo自己构造公有参数";
                print(demo);
            }


//            Demo demo = (Demo) clazz.newInstance();
//            demo.publicField = "demo公有参数";
//            print("demo=" + demo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void test() {
//        //1...
//        print(Demo.class.toString());
//        //下面这样也是可以的
//        print(int.class.toString());
//        print(void.class.toString());
//
//        //2...
//        try {
//            Demo demo = Demo.class.newInstance();
//
//            //1.
////            boolean isInstance = Demo.class.isInstance(demo);//返回true
////            boolean isInstance = Demo.class.isInstance("12345");//返回false
////            print(isInstance);
//
//            //2.
//            boolean isAssign = Demo.class.isAssignableFrom("12345".getClass());//false
////            boolean isAssign = Demo.class.isAssignableFrom(demo.getClass());//false
//            print(isAssign);
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }

        //3...
//        print(Demo.class.getName());
//        print(int.class.getName());
//
//        int[] intArray = new int[3];
//        print(intArray.getClass().getName()); // [I
//
//        String[] stringArray = new String[4];
//        print(stringArray.getClass().getName()); // [Ljava.lang.String;
//
//        print(new String[1][2][3][4].getClass().getName()); // [[[[Ljava.lang.String;

        //4...
//        {
//            print(Demo.class.getClassLoader());
//            print(int.class.getClassLoader());
//        }

        //5...
//        try {
//            Demo demo = (Demo)Class.forName("com.hadly.reflect.Demo").newInstance();
//            print(demo);
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

        //6...
//        try {
//            Field field = Demo.class.getDeclaredField("privateField");
//            print(field);
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        }
//
//        for(Constructor constructor : Demo.class.getDeclaredConstructors()){
//            print(constructor);
//        }

//        for (Class clazz : Demo.class.getClasses()){
//            print(clazz);
//        }
//        for (Class clazz : Demo.class.getDeclaredClasses()){
//            print(clazz);
//        }
//        //---
////        for (Field field : Demo.class.getFields()){
////            print(field);
////        }//只返回公有字段
//        for (Field field : Demo.class.getDeclaredFields()){
//            print(field);
//        }//返回公有 和 私有字段

        //7...
        Demo.class.getResourceAsStream("");


    }

    private static void getName() {
//        Demo demo = new Demo();
//        print(demo.getClass().getName());
    }

    private static void print(Object str) {
        if (null == str) {
            System.out.println("str is null");
            return;
        }
        System.out.println(str.toString());
    }
}
