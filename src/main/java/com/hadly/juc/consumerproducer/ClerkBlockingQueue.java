package com.hadly.juc.consumerproducer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by hadly on 2017/8/7.
 * 1.BlockingQueue是JDK内部实现的同步队列，实现方式采用的是await() / signal()方法
 * 2.可以在生成对象时指定容量大小。它用于阻塞操作的是put()和take()方法
 * 3.
 */
public class ClerkBlockingQueue implements IClerk {
    // 仓库最大存储量
    private final int MAX_SIZE = 10;
    private BlockingQueue<Object> list = new LinkedBlockingQueue<Object>(10);

    public static void main(String[] args) {
        IClerk clerk = new ClerkBlockingQueue();
        new Thread(new Producer(clerk)).start();
        new Thread(new Producer(clerk)).start();

        new Thread(new Consumer(clerk)).start();
        new Thread(new Consumer(clerk)).start();
    }

    public void addProduct() {
        int num = 1;
        if (list.size() > MAX_SIZE) {
            System.out.println("【要生产的产品数量】:" + num + "    【库存量】:" + list.size()
                    + "    暂时不能执行生产任务!");
        }

        System.out.println("条件满足，开始生产, num=" + num);
        // 生产条件满足情况下，生产num个产品
        for (int i = 1; i <= num; ++i) {
            try {
                list.put(new Object());
                System.out.println("【已经生产产品数】:" + num + "    【现仓储量为】:" + list.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }//for
    }

    public void getProduct() {
        int num = 2;
        // 如果仓库存储量不足
        if (list.size() == 0) {
            System.out.println("【库存量】:0    暂时不能执行消费任务!");
        }

        // 消费条件满足情况下，消费num个产品
        for (int i = 1; i <= num; ++i) {
            try {
                // 消费产品，自动阻塞
                list.take();
                System.out.println("【已经消费产品数】:" + i + "    【现仓储量为】:" + list.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }//for

        System.out.println("\n\n");
    }//getProduct
}
