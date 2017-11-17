package com.hadly.pattern.proxy;

/**
 * 《代理对象》
 * --隐藏被代理对象
 * --控制真实对象访问时的权限
 * Created by lizhinian on 2017/11/17.
 */
public class Proxy implements ISubject {
    private ISubject subject;

    /**
     * 代理类在执行时，会调用真实的被代理类进行执行
     */
    @Override
    public void request() {
        System.out.println("--代理之前可以做点事情");
        if (null == subject) {
            subject = new RealSubject();
        }
        subject.request();
        System.out.println("--代理之后可以做点事情");
    }
}
