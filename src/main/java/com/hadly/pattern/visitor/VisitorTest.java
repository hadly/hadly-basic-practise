package com.hadly.pattern.visitor;

/**
 * 《访问者模式》
 * --讲的是表示一个作用于某对象结构中的各元素的操作，使得可以在不改变各元素的类的前提下，定义
 *   作用于这些元素的新操作
 * --
 * Created by lizhinian on 2017/11/21.
 */
public class VisitorTest {
    public static void main(String[] args) {
        ObjectStructure objectStructure = new ObjectStructure();
        objectStructure.add(new Man());
        objectStructure.add(new Woman());

        Success v1 = new Success();
        objectStructure.display(v1);
    }
}
