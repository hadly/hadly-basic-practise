package com.hadly.pattern.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 有枝叶行为，用来存储子部件；可实现增加，删除等
 * Created by lizhinian on 2017/11/22.
 */
public class Composite extends Component {
    private List<Component> list = new ArrayList<>();

    public Composite(String name) {
        super(name);
    }

    @Override
    public void add(Component component) {
        list.add(component);
    }

    @Override
    public void remove(Component component) {
        list.remove(component);
    }

    @Override
    public void display(int depth) {
        System.out.println(getDepthString(depth) + name);
        for (Component component : list){
            component.display(depth + 2);
        }
    }

    @Override
    public void duty() {
        for (Component component : list){
            component.duty();
        }
    }
}
