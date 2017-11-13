package com.hadly.pattern.state;

/**
 * Created by lizhinian on 2017/11/13.
 */
public class WorkOffState implements State {
    @Override
    public void coding(Work work) {
        System.out.println(work.getHour() + "点了，下班了");
    }
}
