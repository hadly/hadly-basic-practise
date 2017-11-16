package com.hadly.pattern.decorate;

/**
 * 具体的被装饰对象
 * Created by hadly on 2017/11/1.
 */
public class ConcreteComponent implements IComponent{
    private String name;

    public ConcreteComponent(String name) {
        this.name = name;
    }

    @Override
    public void show() {
        System.out.println("具体操作的对象 " + name);
    }
}
