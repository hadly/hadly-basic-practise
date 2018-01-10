package com.hadly.pattern.visitor;

/**
 * Created by lizhinian on 2017/11/27.
 */
public class Woman implements IPerson{
    @Override
    public void accept(IAction visitor) {
        visitor.getWomanConclusion(this);
    }
}
