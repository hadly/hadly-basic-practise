/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hadly.pattern.singleton;

/**
 * @author lizhinian
 */
public class Test {
    public Test() {
        System.out.println("I'm constructor");
    }

    public static Test getInstance() {
        return SingletonHolder.singleton;
    }

    public static void test() {
        System.out.println("call test");
    }

    public static void main(String[] args) {
        Test.test();
        System.out.println("--");
        Test.getInstance();
    }

    private static class SingletonHolder {
        public static final Test singleton = new Test();
    }
}
