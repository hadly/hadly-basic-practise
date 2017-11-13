package com.hadly.pattern.state;

/**
 * Created by lizhinian on 2017/11/13.
 */
public class NoonState implements State {
    @Override
    public void coding(Work work) {
        if (work.getHour() < 13) {
            System.out.println(work.getHour() + "点了，吃午饭");
        } else {
            work.setState(new AfterNoonState());
            work.coding();
        }
    }
}
