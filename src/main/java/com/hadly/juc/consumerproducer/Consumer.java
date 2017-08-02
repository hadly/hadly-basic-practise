package com.hadly.juc.consumerproducer;

/**
 * Created by hadly on 2017/7/24.
 */
public class Consumer implements Runnable {
    private IClerk clerk;

    public Consumer(IClerk clerk) {
        this.clerk = clerk;
    }

    public void run() {
        System.out.println("消费者开始取走产品.");
        while (true) {
            try {
//                Thread.sleep((int) (Math.random() * 10) * 100);
                Thread.sleep(10 * 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.getProduct();  //取产品
        }
    }
}
