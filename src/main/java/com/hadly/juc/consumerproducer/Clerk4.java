package com.hadly.juc.consumerproducer;

/**
 * 1.用notify的话，可能出现所有线程都被卡住的情况，即，没有线程能得到执行
 * 2.原因：notify只能唤醒一个线程，如果本方唤醒了本方，没有意义。而且while判断标记+notify会导致”死锁”。
 * --即，2等待消费，3也等待消费，0生产完后，通知2，，2消费完后，继续等待消费，此时2 3都卡住
 * --0继续生产
 * --...
 *
 * --TODO 用notify应该是有问题，但没太想透彻；将两个方法中的notify换成notifyAll就不会有问题
 * --TODO notifyAll会通知到所有的线程，所以不会有问题
 *
 * Thread-1生产者------------32
 * Thread-0生产者invoked 32
 * Thread-0生产者stucked 32
 * Thread-3消费者**** 32
 * Thread-2消费者invoked 32
 * Thread-2消费者stucked 32
 * Thread-3消费者stucked 32
 * Thread-1生产者------------33
 * Thread-0生产者invoked 33
 * Thread-0生产者stucked 33
 * Thread-1生产者stucked 33
 */
public class Clerk4 implements IClerk {
    //产品数
    private int number = 0;
    /*资源标记*/
    private boolean flag = false;

    public static void main(String[] args) {
        IClerk clerk = new Clerk4();
        new Thread(new Producer(clerk)).start();
        new Thread(new Producer(clerk)).start();

        new Thread(new Consumer(clerk)).start();
        new Thread(new Consumer(clerk)).start();
    }

    /**
     * 生产者生产出来的产品交给店员
     */
    public synchronized void addProduct() {
        while (flag) {//先判断标记是否已经生产了，如果已经生产，等待消费；
            try {
                System.out.println(Thread.currentThread().getName() + "生产者stucked " + number);
                wait();//让生产线程等待
                System.out.println(Thread.currentThread().getName() + "生产者invoked " + number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        number++;//生产一个
        System.out.println(Thread.currentThread().getName() + "生产者------------" + number);
        flag = true;//将资源标记为已经生产
//        notify();//唤醒在等待操作资源的线程（队列）
        notifyAll();//TODO 用notifyAll不会有问题
    }

    /**
     * 消费者从店员取产品
     */
    public synchronized void getProduct() {
        while (!flag) {//如果还没有生产，就等待
            try {
                System.out.println(Thread.currentThread().getName() + "消费者stucked " + number);
                wait();
                System.out.println(Thread.currentThread().getName() + "消费者invoked " + number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(Thread.currentThread().getName() + "消费者**** " + number);
        flag = false;
//        notify();
        notifyAll();//TODO 用notifyAll不会有问题
    }
}
