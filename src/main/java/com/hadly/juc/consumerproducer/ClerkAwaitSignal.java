package com.hadly.juc.consumerproducer;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by hadly on 2017/8/7.
 * 1.通过在Lock对象上调用newCondition()方法，将条件变量和一个锁对象进行绑定，进而控制并发程序访问竞争资源的安全
 * 2.用两个condition的原因：使用了两个条件变量 consume的await放置在notEmpty 之上，唤醒在put的时候，produce的await放置在notfull之上，
 * 唤醒在take()的时候，唤醒是signal而不是signalAll，这样做就不会因为大量唤醒导致竞争从而减低效率，
 * 通过锁对象的分析，减低竞争
 */
public class ClerkAwaitSignal implements IClerk {
    // 仓库最大存储量
    private final int MAX_SIZE = 10;
    private final Lock lock = new ReentrantLock();
    // 仓库满的条件
    private final Condition full = lock.newCondition();
    // 仓库空的条件
    private final Condition empty = lock.newCondition();
    // 仓库存储的载体
    private List<Object> list = new LinkedList<Object>();

    public static void main(String[] args) {
        IClerk clerk = new ClerkAwaitSignal();
        new Thread(new Producer(clerk)).start();
        new Thread(new Producer(clerk)).start();

        new Thread(new Consumer(clerk)).start();
        new Thread(new Consumer(clerk)).start();
    }

    public void addProduct() {
        int num = 1;
        lock.lock();
        while (list.size() > MAX_SIZE) {
            System.out.println("【要生产的产品数量】:" + num + "    【库存量】:" + list.size()
                    + "    暂时不能执行生产任务!");
            // 由于条件不满足，生产阻塞
            try {
                full.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }//while

        System.out.println("条件满足，开始生产, num=" + num);
        // 生产条件满足情况下，生产num个产品
        for (int i = 1; i <= num; ++i) {
            list.add(new Object());
        }
        System.out.println("【已经生产产品数】:" + num + "    【现仓储量为】:" + list.size());

        // 唤醒其他所有线程
//        full.signalAll();
//        empty.signalAll();
        //TODO 更优的做法，只定向通知empty，避免所竞争
        empty.signalAll();//或者也可以用signal()只通知一个empty线程

        // 释放锁
        lock.unlock();
    }

    public void getProduct() {
        int num = 2;
        lock.lock();

        // 如果仓库存储量不足
        while (list.size() < num) {
            System.out.println("【要消费的产品数量】:" + num + "    【库存量】:" + list.size()
                    + "    暂时不能执行消费任务!");
            try {
                // 由于条件不满足，消费阻塞
                empty.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("条件满足，开始消费，num=" + num + ", list.size=" + list.size());
        // 消费条件满足情况下，消费num个产品
        for (int i = 1; i <= num; ++i) {
            list.remove(list.size() - 1);
        }

        System.out.println("【已经消费产品数】:" + num + "    【现仓储量为】:" + list.size());

        // 唤醒其他所有线程
//        full.signalAll();
//        empty.signalAll();
        //TODO 更优的做法，只定向通知full，避免所竞争
        full.signalAll();

        // 释放锁
        lock.unlock();
        System.out.println("\n\n");
    }
}
