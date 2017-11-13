package com.hadly.pattern.state;

/**
 * Created by lizhinian on 2017/11/13.
 */
public class AfterNoonState implements State {
    @Override
    public void coding(Work work) {
        if (work.getHour() < 17) {
            System.out.println(work.getHour() + "点了，精神还行");
        } else {
            work.setState(new WorkOffState());
            work.coding();
        }
    }
}
