package com.hadly.pattern.adaptor;

/**
 * 《适配器模式》
 * --将一个类的接口转换成客户希望的另外一个接口
 * --系统的数据和行为正确但接口不符时
 * --想复用一些现有的类，但接口和现有环境的要求不一致时，使用适配器模式
 * --比如调用第三方组件，接口规范和系统的接口不一样，这时候就可以考虑使用适配器模式
 * Created by lizhinian on 2017/11/17.
 */
public class Adaptor extends Target {
    /**
     * 需要适配的对象
     */
    private Adaptee adaptee = new Adaptee();

    public void request() {
        adaptee.specificRequest();
    }
}
