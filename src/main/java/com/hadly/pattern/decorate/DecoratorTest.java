package com.hadly.pattern.decorate;

/**
 * 《装饰模式》动态的给一个对象添加一些额外的职责，比生成子类更灵活
 * Created by lizhinian on 2017/11/14.
 */
public class DecoratorTest {
    public static void main(String[] args) {
        // 被装饰对象
        IComponent component1 = new ConcreteComponent("被装饰对象1");
        // 装饰器
        Decorator decorator = new ConcreteDecoratorA();
        // 装饰器2
        Decorator decorator2 = new ConcreteDecoratorA();
        // 用该装饰器装饰具体对象1
        decorator.decorate(component1);
        decorator2.decorate(decorator);// 相当于是装饰了两次

        // 调用被装饰方法
        decorator2.show();
    }
}
