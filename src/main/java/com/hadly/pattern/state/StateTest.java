package com.hadly.pattern.state;

/**
 * Created by lizhinian on 2017/11/13.
 */
public class StateTest {
    public static void main(String[] args) {
        Work work = new Work();

        work.setHour(9);
        work.coding();

        work.setHour(10);
        work.coding();

        work.setHour(12);
        work.coding();

        work.setHour(13);
        work.coding();

        work.setHour(17);
        work.coding();

        work.setHour(18);
        work.coding();
    }
}
