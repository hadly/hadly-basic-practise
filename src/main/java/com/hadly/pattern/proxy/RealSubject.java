package com.hadly.pattern.proxy;

/**
 * Created by lizhinian on 2017/11/17.
 */
public class RealSubject implements ISubject {
    @Override
    public void request() {
        System.out.println("真实被代理类");
    }
}
