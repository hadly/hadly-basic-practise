package com.hadly.pattern.chain;

/**
 * Created by lizhinian on 2017/11/2.
 */
public abstract class AbstractHandler implements IHandler{
    protected IHandler successor;

    public void setSuccessor(IHandler successor) {
        this.successor = successor;
    }
}
