package com.hadly.juc.consumerproducer;

/**
 * 生产者生产一个，消费者就消费一个，运行没问题
 */
public class Clerk2 implements IClerk {
    //产品数
    private int number = 0;
    /*资源标记*/
    private boolean flag = false;

    public static void main(String[] args){
        IClerk clerk = new Clerk3();
        new Thread(new Producer(clerk)).start();
        new Thread(new Consumer(clerk)).start();
    }

    /**
     * 生产者生产出来的产品交给店员
     */
    public synchronized void addProduct() {
        if (flag) {//先判断标记是否已经生产了，如果已经生产，等待消费；
            try {
                wait();//让生产线程等待
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        number++;//生产一个
        System.out.println(Thread.currentThread().getName() + "生产者------------" + number);
        flag = true;//将资源标记为已经生产
        notify();//唤醒在等待操作资源的线程（队列）
    }

    /**
     * 消费者从店员取产品
     */
    public synchronized void getProduct() {
        if (!flag) {//如果还没有生产，就等待
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(Thread.currentThread().getName() + "消费者**** " + number);
        flag = false;
        notify();
    }
}
