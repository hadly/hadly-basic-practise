package com.hadly.pattern.proxy;

/**
 * 《代理模式》-为其他对象提供一种代理，以控制对这个对象的访问
 * --Subject类，定义 被代理类 和 代理类 共同的接口。这样能用Subject类的地方都能用代理类
 * Created by lizhinian on 2017/11/17.
 */
public interface ISubject {
    void request();
}
