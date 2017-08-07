package com.hadly.juc.consumerproducer;

/**
 * 1.可能会出现一个产品被消费多次的情况
 * Thread-1生产者------------1
 * Thread-2消费者****1
 * Thread-3消费者****1
 * Thread-0生产者------------2
 * Thread-2消费者****2
 * --线程0 1生产，2 3消费
 * ----线程2 3都在wait消费，线程1生产后notify，线程2被激活了，运行完后有可能通知到线程3，后者并没有判断flag而直接消费
 * <p>
 * 2. 原因：
 * --当两个线程同时操作生产者生产或者消费者消费时，如果有生产者或者的两个线程都wait()时，
 * 再次notify(),由于其中一个线程已经改变了标记而另外一个线程再次往下直接执行的时候没有判断标记而导致的。
 * --if判断标记，只有一次，会导致不该运行的线程运行了。出现了数据错误的情况。
 * <p>
 * 3. 解决方案
 * while判断标记，解决了线程获取执行权后，是否要运行！也就是每次wait()后再notify()
 * 时先再次判断标记
 * 4.可能会出现一个产品无消费者的情况
 * Thread-0生产者------------24
 * Thread-2消费者**** 24
 * Thread-1生产者------------25
 * Thread-0生产者------------26
 * Thread-3消费者**** 26
 * --线程0 1生产，2 3消费
 * --线程0 1等待生产，线程2消费完毕，通知1生产，1生产完后notify，可能通知到0进行生产，随后通知3进行消费
 * --导致1生产的25没线程消费
 */
public class Clerk3 implements IClerk {
    //产品数
    private int number = 0;
    /*资源标记*/
    private boolean flag = false;

    public static void main(String[] args) {
        IClerk clerk = new Clerk3();
        new Thread(new Producer(clerk)).start();
        new Thread(new Producer(clerk)).start();

        new Thread(new Consumer(clerk)).start();
        new Thread(new Consumer(clerk)).start();
    }

    /**
     * 生产者生产出来的产品交给店员
     */
    public synchronized void addProduct() {
        if (flag) {//先判断标记是否已经生产了，如果已经生产，等待消费；
            try {
                System.out.println(Thread.currentThread().getName() + "生产者stucked");
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
                System.out.println(Thread.currentThread().getName() + "消费者stucked");
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
