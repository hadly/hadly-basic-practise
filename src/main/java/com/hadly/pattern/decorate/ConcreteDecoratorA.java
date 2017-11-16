package com.hadly.pattern.decorate;

/**
 * 具体装饰类，具体的装饰其设置进去的类
 * Created by lizhinian on 2017/11/16.
 */
public class ConcreteDecoratorA extends Decorator {
    @Override
    public void show() {
        decorateMtd1();
        super.show();
        decorateMtd2();
    }//

    private void decorateMtd1(){
        System.out.println("这是装饰方法1");
    }

    private void decorateMtd2(){
        System.out.println("这是装饰方法2");
    }
}
