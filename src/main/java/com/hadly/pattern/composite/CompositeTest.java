package com.hadly.pattern.composite;

/**
 * 《组合模式》
 * --部分和整体，可以被一致性地对待。可以一致的使用组合结构和单个对象
 * --基本对象可以组合成更复杂的组合对象
 * --用户不用关心是叶子节点还是一个组合组件
 * Created by lizhinian on 2017/11/21.
 */
public class CompositeTest {
    public static void main(String[] args) {
        Composite root = new Composite("北京总公司");
        root.add(new Leaf1("总公司人力"));
        root.add(new Leaf2("总公司财务"));

        Composite comp = new Composite("上海华东分公司");
        comp.add(new Leaf1("华东分公司人力"));
        comp.add(new Leaf2("华东分公司财务"));
        root.add(comp);

        Composite comp1 = new Composite("南京办事处");
        comp1.add(new Leaf1("南京办事处人力"));
        comp1.add(new Leaf2("南京办事处财务"));
        comp.add(comp1);

        Composite comp2 = new Composite("杭州办事处");
        comp2.add(new Leaf1("杭州办事处人力"));
        comp2.add(new Leaf2("杭州办事处财务"));
        comp.add(comp2);

        System.out.println("树状结构图如下：");
        root.display(1);

        System.out.println("职责图如下:");
        root.duty();
    }
}
