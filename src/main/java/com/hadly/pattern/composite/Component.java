package com.hadly.pattern.composite;

/**
 * 组合中的接口声明，表示这个组合对象或者叶子节点有哪些功能
 * --该类也可定为接口，以面向接口编程
 * Created by lizhinian on 2017/11/22.
 */
public abstract class Component {
    protected String name;

    public Component(String name) {
        this.name = name;
    }

    abstract void add(Component component);
    abstract void remove(Component component);
    abstract void display(int depth);

    /**
     * 职责是什么
     */
    abstract void duty();

    public String getDepthString(int depth){
        StringBuilder sb = new StringBuilder();
        while (depth-- >= 0){
            sb.append("-");
        }
        return sb.toString();
    }
}
