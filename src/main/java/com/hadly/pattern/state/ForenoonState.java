package com.hadly.pattern.state;

/**
 * Created by lizhinian on 2017/11/13.
 */
public class ForenoonState implements State {
    @Override
    public void coding(Work work) {
        if (work.getHour() < 12) {
            System.out.println(work.getHour() + "点了，精神好");
        } else {
            work.setState(new NoonState());
            work.coding();
        }
    }
}
