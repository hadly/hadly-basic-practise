package com.hadly.pattern.composite;

/**
 * Created by lizhinian on 2017/11/22.
 */
public class Leaf2 extends Component {
    public Leaf2(String name) {
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
        System.out.println(name + ",财务工作的叶子");
    }
}
