/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hadly.pattern.singleton;

/**
 * @author lizhinian
 */
public class SingletonHungry {

    public static final String NAME = "SingletonHungry";
    private static final SingletonHungry instance = new SingletonHungry();

    private SingletonHungry() {
        System.out.println("i'm constructor of SingletonHungry");
    }

    public static SingletonHungry getInstance() {
        return instance;
    }

    //在调用类的别的方法的时候就会加载类，而加载的时候就会初始化instance
    public static void testSingleton() {
        System.out.println("call SingletonHungry.testSingleton(xxx)");
    }
}
