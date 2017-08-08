package com.hadly.juc.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * Created by hadly on 2017/8/8.
 * 1.测试thread.stop()方法，该方法会导致对象状态不一致，已废弃
 */
public class TestStop {
    private static final Thread thread = new Thread() {
        public void run() {
            try {
                System.out.println(TimeConsumingSort.sort());
            } catch (Error err) {
                err.printStackTrace();
            }
            System.out.println("in thread thread");
        }
    };

    public static void main(String[] args) throws Exception {
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("go to stop thread thread");
        thread.stop();
        System.out.println("finish main");
    }
}
