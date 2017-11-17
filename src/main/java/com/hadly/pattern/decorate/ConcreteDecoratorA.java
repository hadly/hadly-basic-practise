package com.hadly.pattern.decorate;

/**
 * 具体装饰类，具体的装饰其设置进去的类
 * --新加的类装饰了原有类的核心职责或主要行为；将装饰的行为放在新类中
 * --核心功能和装饰功能分开；可以去除相关类中重复的装饰逻辑
 * --input stream就是用了装饰模式
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
