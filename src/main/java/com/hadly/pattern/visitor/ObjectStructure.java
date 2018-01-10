package com.hadly.pattern.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lizhinian on 2017/11/27.
 */
public class ObjectStructure {
    private List<IPerson> elements = new ArrayList<>();

    public void add(IPerson person){
        elements.add(person);
    }

    public void remove(IPerson person){
        elements.remove(person);
    }

    public void display(IAction visitor){
        for (IPerson person : elements){
            person.accept(visitor);
        }
    }
}
