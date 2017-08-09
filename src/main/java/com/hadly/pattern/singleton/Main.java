/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hadly.pattern.singleton;

/**
 * @author lizhinian
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("begin singleton test");

        SingletonHungry.testSingleton();
        System.out.println("----");

        SingletonInnerClass.testSingleton();
        System.out.println("----");
        SingletonInnerClass.getInstance();
    }
}
