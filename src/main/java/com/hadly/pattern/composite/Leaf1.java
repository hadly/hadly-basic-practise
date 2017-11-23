package com.hadly.pattern.composite;

import com.sun.org.apache.xpath.internal.operations.String;

/**
 * 组合中的叶子节点
 * Created by lizhinian on 2017/11/22.
 */
public class Leaf1 extends Component {
    public Leaf1(java.lang.String name) {
        super(name);
    }

    @Override
    public void add(Component component) {
        System.out.println("不能增加");
    }

    @Override
    public void remove(Component component) {
        System.out.println("不能删除");
    }

    @Override
    public void display(int depth) {
        System.out.println(getDepthString(depth) + name);
    }

    @Override
    public void duty() {
        System.out.println(name + ",员工招聘工作的叶子");
    }
}
