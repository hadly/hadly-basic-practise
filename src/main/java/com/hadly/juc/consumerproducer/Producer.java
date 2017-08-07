package com.hadly.juc.consumerproducer;

/**
 * Created by hadly on 2017/7/24.
 */
public class Producer implements Runnable {
    private IClerk clerk;

    public Producer(IClerk clerk) {
        this.clerk = clerk;
    }

    public void run() {
        System.out.println("生产者开始生产产品. " + Thread.currentThread().getName());
        while (true) {
            try {
//                Thread.sleep((int) (Math.random() * 10) * 100);
                Thread.sleep(10 * 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.addProduct(); //生产产品
        }
    }
}
