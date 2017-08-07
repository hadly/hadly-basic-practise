package com.hadly.juc.deadlock;

/**
 * Created by hadly on 2017/8/5.
 * 两个方法，按照不同的加锁顺序，加锁lock1和lock2，会导致死锁
 */
public class Consumer {

    public void consumeInOrder() {
        System.out.println("consumeInOrder");
        synchronized (SharedInstance.lock1) {
            try {
                System.out.println("consumeInOrder acquire lock1");
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("consumeInOrder try to acquire lock2");
            synchronized (SharedInstance.lock2) {
                System.out.println("1");
            }
        }
    }

    public void consumeInReversedOrder() {
        synchronized (SharedInstance.lock2) {
            try {
                System.out.println("consumeInReversedOrder acquire lock2");
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("consumeInReversedOrder try to acquire lock1");
            synchronized (SharedInstance.lock1) {
                System.out.println("2");
            }
        }
    }//end of function
}
