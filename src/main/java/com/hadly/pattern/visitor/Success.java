package com.hadly.pattern.visitor;

/**
 * Created by lizhinian on 2017/11/27.
 */
public class Success implements IAction{
    @Override
    public void getManConclusion(Man elementA) {
        System.out.println(elementA.getClass().getName() + " " + this.getClass().getName()
        + "时，背后一个伟大的女人");
    }

    @Override
    public void getWomanConclusion(Woman elementB) {
        System.out.println(elementB.getClass().getName() + " " + this.getClass().getName()
                + "时，背后一个不成功的男人");
    }
}
