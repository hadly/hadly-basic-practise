package com.hadly.pattern.visitor;

/**
 * Created by lizhinian on 2017/11/27.
 */
public class Man implements IPerson{
    @Override
    public void accept(IAction visitor) {
        visitor.getManConclusion(this);
    }
}
