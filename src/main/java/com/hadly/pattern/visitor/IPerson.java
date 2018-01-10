package com.hadly.pattern.visitor;

/**
 *
 * Created by lizhinian on 2017/11/27.
 */
public interface IPerson {
    void accept(IAction visitor);
}
